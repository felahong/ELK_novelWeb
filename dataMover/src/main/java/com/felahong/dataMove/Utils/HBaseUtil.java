package com.felahong.dataMove.Utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.coprocessor.AggregationClient;
import org.apache.hadoop.hbase.client.coprocessor.LongColumnInterpreter;

/**
 * @Program: NovelWebOnBigDataStructure
 * @Package: com.felahong.mysql2Hbase.Utils
 * @Description: Created by felahong on 2020/2/24 19:18
 */

public class HBaseUtil {

    public static Configuration getConfig(){

        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","hadoop001");
        configuration.set("hbase.zookeeper.property.clientPort","2181");
        configuration.set("hbase.master","hadoop001:60000");
        return configuration;
    }

    public static void rowCountByCoprocessor(String tableName) throws Throwable {
        Configuration conf = HBaseUtil.getConfig();
        Connection connection = ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();
        TableName name = TableName.valueOf(tableName);
        //先disable表，添加协处理器后再enable表
        admin.disableTable(name);
        HTableDescriptor descriptor = admin.getTableDescriptor(name);
        String coprocessorClass = "org.apache.hadoop.hbase.coprocessor.AggregateImplementation";
        if (! descriptor.hasCoprocessor(coprocessorClass)) {
            descriptor.addCoprocessor(coprocessorClass);
        }
        admin.modifyTable(name, descriptor);
        admin.enableTable(name);

//        // 计时
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();

        Scan scan = new Scan();
        AggregationClient aggregationClient = new AggregationClient(conf);

        System.out.println("RowCount: " + aggregationClient.rowCount(name, new LongColumnInterpreter(), scan));
//        stopWatch.stop();
//        System.out.println("统计耗时：" +stopWatch.getTotalTimeMillis());
    }

    public static void main(String[] args) {

        try {
            rowCountByCoprocessor("novel_detail_test");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}