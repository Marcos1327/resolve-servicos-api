package com.resolveservicos.utils;

import com.resolveservicos.entities.model.Role;
import com.resolveservicos.enums.RoleName;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class Util {

    public boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
    public boolean isNullOrEmptyToEnumValue(RoleName value) {
        return value == null;
    }



    public LocalDate convertStringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }

    public boolean isDateBeforeNow(LocalDate date) {
        return date.isBefore(LocalDate.now());
    }

    public boolean isTimeBeforeNow(String date, String time) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalDate appointmentDate = LocalDate.parse(date, dateFormatter);
        LocalTime appointmentTime = LocalTime.parse(time, timeFormatter);

        LocalDateTime appointmentDateTime = LocalDateTime.of(appointmentDate, appointmentTime);
        LocalDateTime now = LocalDateTime.now();

        if (appointmentDate.isEqual(now.toLocalDate())) {
            return !appointmentTime.isBefore(now.toLocalTime());
        }

        return true;
    }


    public LocalTime convertStringToLocalTime(String time) {
        return LocalTime.parse(time);
    }

}
