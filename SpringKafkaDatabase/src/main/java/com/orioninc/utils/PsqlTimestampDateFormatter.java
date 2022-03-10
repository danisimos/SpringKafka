package com.orioninc.utils;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class PsqlTimestampDateFormatter {
    public String formatFrom(String timestamp) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date parse = sdf1.parse(timestamp.replace("T", " "));

        return sdf2.format(parse) + ".000000";
    }

    public String formatTo(String timestamp) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date parse = sdf1.parse(timestamp.replace("T", " "));

        return sdf2.format(parse) + ".999999";
    }
}
