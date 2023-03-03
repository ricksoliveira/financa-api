package com.api.financa.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class Utils {


    public static class DateUtils {

        public static String getCurrentDateQueryString(){

            log.info("Gerando String parametro baseado no mes atual");

            LocalDateTime now = LocalDateTime.now();
            int year = now.getYear();
            int month = now.getMonthValue();

            log.debug("Mes atual: [{}]. Ano atual: [{}]", month, year);

            if (month < 10){
                return year + "-0" + month;
            }
            else{
                return year + "-" + month;
            }
        }


    }


}
