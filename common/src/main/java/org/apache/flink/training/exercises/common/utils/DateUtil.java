package org.apache.flink.training.exercises.common.utils;

import java.text.ParseException;
import org.apache.commons.lang3.time.DateUtils;

public class DateUtil {
    private static String[] parsePatterns = { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" };

    public static boolean parseDate(String str) {
        if (str == null) {
            return false;
        }
        try {
            DateUtils.parseDate(str, parsePatterns);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(parseDate("2021-06-20T16:42:06.575Z"));
    }
}