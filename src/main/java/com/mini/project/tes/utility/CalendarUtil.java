package com.mini.project.tes.utility;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;

public class CalendarUtil {
    public static Calendar GET_GMT7(){
        return Calendar.getInstance(TimeZone.getTimeZone("GMT+7"));
    }
    public static Timestamp GET_GMT7_TIMESTAMP(){
        return new Timestamp(GET_GMT7().getTimeInMillis());
    }
}
