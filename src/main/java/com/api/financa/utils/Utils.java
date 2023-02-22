package com.api.financa.utils;

import java.time.LocalDateTime;

public class Utils {


    public static class DateUtils {

        public static String getCurrentDateQueryString(){
            LocalDateTime now = LocalDateTime.now();
            int year = now.getYear();
            int month = now.getMonthValue();

            if (month < 10){
                return year + "-0" + month;
            }
            else{
                return year + "-" + month;
            }
        }


    }


}
