package com.example.todo2.model;

import androidx.room.TypeConverter;

import java.util.Date;

//Converts Date class to type supported by database
public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
