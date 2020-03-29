package com.felahong.dataMove

import com.felahong.dataMove.Utils.MD5Util
import org.apache.hadoop.hbase.{HBaseConfiguration, HConstants}
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat
import org.apache.spark._
import org.apache.hadoop.mapreduce.Job
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.Row


/**
  * Program: NovelWebOnBigDataStructure
  * Package: com.felahong.mysql2Hbase 
  * Description: 
  *
  * Created by felahong on 2020/2/24 19:39
  */

object Mysql2HBase {

  def main(args: Array[String]): Unit = {
    doMove()
  }

  def doMove(): Unit = {

    // 参数配置
    val config = Map(
      "spark.cores" -> "local[2]",
      "mysqlHost" -> "hadoop001",
      "mysqlDatabase" -> "spider",
      "mysqlTableName" -> "novel_test",
      "mysqlUserName" -> "root",
      "mysqlPassWord" -> "root",
      "hbaseTableName" -> "test",
      "hbaseFamily" -> "f",
      "hbase.master" -> "hadoop001:60000",
      "zookeeper.quorum" -> "hadoop001",
      "zookeeper.clientPort" -> "2181"
    )

    // 创建sparkConf, sparkSession 和sparkContext
//    val conf = new SparkConf().setAppName("MySQL2HBase").setMaster(config("spark.cores"))
    val conf = new SparkConf().setAppName("mysql2Hbase").setMaster("local")
    val ss = SparkSession.builder().config(conf).getOrCreate()
    val sc = ss.sparkContext

    // jdbc 连接mysql 读取数据
    val jdbcDF = ss.read.format("jdbc").option("url", "jdbc:mysql://" + config("mysqlHost") + ":3306/" + config("mysqlDatabase") + "?useSSL=false")
      .option("driver","com.mysql.jdbc.Driver")
      .option("dbtable", config("mysqlTableName"))
      .option("user", config("mysqlUserName"))
      .option("password", config("mysqlPassWord"))
      .load()

    // 获取sql查询结果的字段名称及其数据类型
    val datas = jdbcDF.dtypes   // Returns all column names and their data types as an array.

    // 构造RDD[(new ImmutableBytesWritable, put)辅助ffdjk]
    val putRdd = jdbcDF.rdd.map{ row =>
      // 设计插入hbase 的rowkey，对书名和章节名取md5
      println(row.get(1).toString)
      println(row.get(3).toString)
      val rowkey = MD5Util.md5(row.get(1).toString + row.get(3).toString)

      //  准备写入HBase的数据结构ImmutableBytesWritable
      val put = new Put(Bytes.toBytes(rowkey))
      for(i <- 0 until row.size){   /** Number of elements in the Row. */
        // 列簇、字段名称、字段值
        put.addImmutable(Bytes.toBytes(config("hbaseFamily")), Bytes.toBytes(datas.apply(i)._1),
          Bytes.toBytes(getString(datas.apply(i)._2, row, i)))
      }
      (new ImmutableBytesWritable, put)
    }

    // hbase 相关配置
    val hbaseConf =HBaseConfiguration.create()
    hbaseConf.set(TableOutputFormat.OUTPUT_TABLE, config("hbaseTableName"))
    hbaseConf.set(HConstants.MASTER_PORT, config("hbase.master"))
    hbaseConf.set(HConstants.ZOOKEEPER_QUORUM, config("zookeeper.quorum"))
    hbaseConf.set(HConstants.ZOOKEEPER_CLIENT_PORT, config("zookeeper.clientPort"))

    // job 相关配置
    val job = Job.getInstance(hbaseConf)
    job.setOutputFormatClass(classOf[TableOutputFormat[ImmutableBytesWritable]])
    job.setOutputKeyClass(classOf[ImmutableBytesWritable])
    job.setOutputValueClass(classOf[Put])

    putRdd.saveAsNewAPIHadoopDataset(job.getConfiguration)  // 封装了SparkHadoopWriter.write

    // 关闭
    sc.stop()
    ss.stop()

  }

  /**
    * 根据每个字段的字段类型调用不同的函数，将字段值转换为HBase 可读取的字节形式，这样也解决了数字导入到HBase中变成乱码的问题
    * @param value_type
    * @param row
    * @param i
    * @return
    */
  def getString(value_type: String, row: Row, i: Int): String = {
    var str = ""
    if ("IntegerType" == value_type) {
      str = row.getInt(i).toString
    }
    else if ("StringType" == value_type) {
      str = row.getString(i)
    }
    else if ("TimestampType" == value_type) {
      str = row.getTimestamp(i).toString
    }
    str
  }

}
