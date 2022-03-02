package com.orioninc.utils;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class PsqlTimestampDateFormatter {
    public String format(String timestamp) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date parse = sdf1.parse("2019-02-03T15:02".replace("T", " "));

        return sdf2.format(parse);
    }
}
