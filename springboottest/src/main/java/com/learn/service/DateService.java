package com.learn.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class DateService {

    public String currentDate(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("YYYYMMdd"));
    }
}
