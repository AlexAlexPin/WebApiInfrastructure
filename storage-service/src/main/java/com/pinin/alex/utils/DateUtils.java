package com.pinin.alex.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;

public final class DateUtils {

    public static Long getDateAsLong(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
