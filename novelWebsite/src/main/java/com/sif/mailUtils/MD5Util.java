package com.sif.mailUtils;

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
}