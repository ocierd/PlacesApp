package com.example.demo.services.utils;

public class StringUtils {

    
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }


    public static boolean areEqual(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return true;
        }
        if (str1 == null || str2 == null) {
            return false;
        }
        return str1.equals(str2);
    }

}

