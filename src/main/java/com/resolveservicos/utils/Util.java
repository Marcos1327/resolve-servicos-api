package com.resolveservicos.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class Util {

    public boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }


    public LocalDate convertStringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }

    public boolean isDateBeforeNow(LocalDate date) {
        return date.isBefore(LocalDate.now());
    }

    // Crie um metodo que verifica se o horario
    public boolean isTimeBeforeNow(LocalTime time) {
        return time.isBefore(LocalTime.now());
    }

    public LocalTime convertStringToLocalTime(String time) {
        return LocalTime.parse(time);
    }
}
