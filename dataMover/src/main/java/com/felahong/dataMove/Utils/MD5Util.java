package com.felahong.dataMove.Utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Program: NovelWebOnBigDataStructure
 * @Package: com.felahong.mysql2Hbase.Utils
 * @Description: 提供MD5 加密的工具类
 * Created by felahong on 2020/2/24 17:20
 */

public class MD5Util {

    //MD5fangfa
    public static String md5(String key){
        // 加密后的字符串
        String rowkey = DigestUtils.md5Hex(key);
        return rowkey;
    }

    public static void main(String[] args) {

        String str = "努力学习，为了更好的生活";
        System.out.println(DigestUtils.md5Hex(str));
    }
}