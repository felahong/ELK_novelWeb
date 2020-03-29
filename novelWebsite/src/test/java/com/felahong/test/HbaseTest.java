package com.felahong.test;

import com.sif.mailUtils.HBaseUtils;
import com.sif.mailUtils.MD5Util;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;

public class HbaseTest {

    public static void main(String[] args) throws IOException {
        Connection connection = HBaseUtils.client();
        Table table = connection.getTable(TableName.valueOf("novel_detail_test"));

        String rowKey = MD5Util.md5("制霸五洲"+"正文 第一章 淫贼可别乱叫");
        System.out.println(rowKey);
        Get get = new Get(rowKey.getBytes());

        Result result = table.get(get);
        for (KeyValue keyValue:result.raw()) {
            String key = new String(keyValue.getQualifier());
            if("chapterUrl".equals(key)){
                System.out.println("value: "+new String(keyValue.getValue()));
            }
        }
    }
}
