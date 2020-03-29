package com.sif.conversion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @program: BrotherNovel
 * @description: 时间的转化
 * @author: xifujiang
 * @create: 2019-01-08 17:50
 **/
public class DateChange {
    //Date类型转字符串
    public static String dateToString(Date date){
        if(date == null)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    //字符串类型转Date
    public static Date stringToDate(String str) throws ParseException {
        System.out.println("22222:   "+str);

        if(str.trim() == "" || str == null) {
            System.out.println("time1111:   "+str);
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(str);
    }

}
