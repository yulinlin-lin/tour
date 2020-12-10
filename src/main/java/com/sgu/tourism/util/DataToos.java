package com.sgu.tourism.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataToos {

    private static SimpleDateFormat SDF_YYMMDD = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static SimpleDateFormat SDF_YYMMDD_2 = new SimpleDateFormat("yyyy-MM-dd");

    public static  String getNowDateYYYY_MM_DD(Date date){
        String format = SDF_YYMMDD.format(date);
        return format;
    }

    public static  String getNowDateYYYY_MM_DD_2(Date date){
        String format = SDF_YYMMDD_2.format(date);
        return format;
    }




}
