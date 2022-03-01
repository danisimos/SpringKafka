package com.orioninc.utils;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PsqlTimestampDateFormatter {
    public String format(String timestamp) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date parse = sdf1.parse("2019-02-03T15:02".replace("T", " "));

        return sdf2.format(parse);
    }
}
