package com.spring.learn.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class DateService {

    public static String getStartDate() {
        return "19700101";
    }

    public String currentDate(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("YYYYMMdd"));
    }
}
