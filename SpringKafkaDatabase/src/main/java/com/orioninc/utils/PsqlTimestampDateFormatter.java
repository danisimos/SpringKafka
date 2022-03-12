package com.orioninc.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class PsqlTimestampDateFormatter {
    public String formatFrom(String timestamp) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date parse;
        try {
            parse = sdf1.parse(timestamp.replace("T", " "));
        } catch(ParseException e) {
            throw new IllegalArgumentException("Wrong date format");
        }

        return sdf2.format(parse) + ".000000";
    }

    public String formatTo(String timestamp) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date parse;
        try {
            parse = sdf1.parse(timestamp.replace("T", " "));
        } catch(ParseException e) {
            throw new IllegalArgumentException("Wrong date format");
        }

        return sdf2.format(parse) + ".999999";
    }
}
