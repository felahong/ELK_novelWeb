package com.felahong.dataMove.MapReduce;

import com.felahong.dataMove.Bean.NovelDetail;
import com.felahong.dataMove.Utils.HBaseUtil;
import com.felahong.dataMove.Utils.MD5Util;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

/**
 * @Program: NovelWebOnBigDataStructure
 * @Package: com.felahong.mysql2Hbase.MapReduce
 * @Description: 数据从Mysql 迁移到Hbase 的MR 程序
 * Created by felahong on 2020/2/24 17:14
 */

public class Mysql2HbaseMapReduce {

    /**
     * @Description 内部Mapper 类
     * @Author felahong 2020/2/24 17:16
     **/
    public static class Mysql2HbaseMapper extends Mapper<LongWritable, NovelDetail, NovelDetail, NullWritable> {

        /**
         * @Description 数据内容已经封装在Bean 里，包含在NovelDetail 中，所以value 只需要为null 即可
         * @Author felahong 2020/2/24 17:24
         **/
        protected void map(LongWritable key, NovelDetail value, Context context) throws IOException, InterruptedException {
            context.write(value, NullWritable.get());
        }
    }

    /**
     * @Description
     * @Author felahong 2020/2/24 17:27
     **/
    public static class Mysql2HbaseReduce extends TableReducer<NovelDetail, NullWritable, ImmutableBytesWritable> {

        /**
         * @Description 此时values 是空的，数据封装在key 中
         * @Author felahong 2020/2/24 17:31
         **/
        protected void reduce(NovelDetail key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            int detailId = key.getId();
            String authorName = key.getAuthorName();
            String novelName = key.getNovelName();
            String chapterName = key.getChapterName();
            String chapterUrl = key.getChapterUrl();
            int novelId = key.getNovelId();

            // rowkey 设计: 小说名字加上章节名字组成
            String rowkey = MD5Util.md5(novelName + chapterName);

            // HBase Put 操作，创建put 对象，写入rowkey
            final Put put = new Put(Bytes.toBytes(rowkey));

            // 迭代赋值
            for (NullWritable v : values) {
                put.addColumn(Bytes.toBytes("t"), Bytes.toBytes("id"), Bytes.toBytes(detailId));
                put.addColumn(Bytes.toBytes("t"), Bytes.toBytes("authorName"), Bytes.toBytes(authorName));
                put.addColumn(Bytes.toBytes("t"), Bytes.toBytes("novelName"), Bytes.toBytes(novelName));
                put.addColumn(Bytes.toBytes("t"), Bytes.toBytes("chapterName"), Bytes.toBytes(chapterName));
                put.addColumn(Bytes.toBytes("t"), Bytes.toBytes("chapterUrl"), Bytes.toBytes(chapterUrl));
                put.addColumn(Bytes.toBytes("t"), Bytes.toBytes("novelId"), Bytes.toBytes(novelId));
            }

            // key, value
            context.write(new ImmutableBytesWritable(Bytes.toBytes(rowkey)), put);

        }
    }

    /**
     * @Description
     * @Author felahong 2020/2/24 17:34
     **/
    public static class Mysql2HbaseDriver extends Configured implements Tool{

        public static void main(String[] args) throws Exception {
            Configuration config = HBaseUtil.getConfig();
            int exitCode = ToolRunner.run(config, new Mysql2HbaseDriver(), args);
            System.exit(exitCode);
        }

        @Override
        public int run(String[] strings) throws Exception {

            Configuration conf = this.getConf();

            DBConfiguration.configureDB(conf,"com.mysql.jdbc.Driver",
                    "jdbc:mysql://hadoop001:3306/spider?useSSL=false", "root", "root");

            // job 创建实例
            final Job job = Job.getInstance(conf);

            job.setJarByClass(Mysql2HbaseDriver.class);
            job.setMapperClass(Mysql2HbaseMapper.class);
//            job.setReducerClass(Mysql2HbaseReduce.class);

            job.setMapOutputKeyClass(NovelDetail.class);
            job.setMapOutputValueClass(NullWritable.class);

            // 设置reducer相关，reducer 往hbase 输出
            // novel_detail: output table
            TableMapReduceUtil.initTableReducerJob("novel_detail", Mysql2HbaseReduce.class, job);

            // 设置输入格式是从database中读取
            job.setInputFormatClass(DBInputFormat.class);
            DBInputFormat.setInput(job, NovelDetail.class, "novel_detail", null, "id",
                    "id", "author_name", "novel_name", "chapter_name", "chapter_url", "novel_id");

            job.waitForCompletion(true);

            return 0;
        }
    }
}