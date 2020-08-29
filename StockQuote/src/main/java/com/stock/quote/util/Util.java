/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock.quote.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Util {

    // Pattern example: "yyyy-MM-dd"
    public static String dateToString(long createdDate, String pattern) {
        //DateTimeZone.setDefault(DateTimeZone.forID(timeZone));
        DateTime dt = new DateTime(createdDate);
        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        String dtStr = fmt.print(dt);
//         System.out.println("Date String " + dtStr);
        return dtStr;
    }

    public static long stringDateToEpoch(String dateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
        DateTime dt = formatter.parseDateTime(dateTime);
        return dt.getMillis();
    }

    public static void convertToTimezone(String timeZone) {
        DateTimeZone.setDefault(DateTimeZone.forID(timeZone));
    }
}
