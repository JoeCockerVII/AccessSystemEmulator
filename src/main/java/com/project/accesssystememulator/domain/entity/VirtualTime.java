package com.project.accesssystememulator.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ilyin
 * @since 05.08.2022
 */
public class VirtualTime {

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    static LocalDateTime startDate = LocalDateTime.parse("01-01-2022 08:00", formatter);
    static LocalDateTime endDate   = LocalDateTime.parse("31-12-2022 18:00",formatter);
    static int daysNumbers = getGetDaysBetweenTwoDate(startDate,endDate);

    public static LocalDateTime getRandomDataFromInterval(LocalDateTime startDateTime, LocalDateTime endDateTime){
            LocalDate startDate = startDateTime.toLocalDate();
            LocalDate endDate = endDateTime.toLocalDate();
            LocalTime startTime = startDateTime.toLocalTime();
            LocalTime endTime = VirtualTime.endDate.toLocalTime();

            long randomDay = ThreadLocalRandom.current().nextLong(startDate.toEpochDay(), endDate.toEpochDay());
            int randomTime = ThreadLocalRandom.current().nextInt(startTime.toSecondOfDay(), endTime.toSecondOfDay());

            LocalDate randomLocalDate = LocalDate.ofEpochDay(randomDay);
            LocalTime randomLocalTime = LocalTime.ofSecondOfDay(randomTime);
            return LocalDateTime.of(randomLocalDate,randomLocalTime);
    }

    public static int getRandomNumber(int a, int b){
        return a + (int) (Math.random() * b);
    }

    public static int getGetDaysBetweenTwoDate(LocalDateTime a, LocalDateTime b){
        return (int) ChronoUnit.DAYS.between(a,b);
    }


    public static int getDaysNumbers() {
        return daysNumbers;
    }

    public static DateTimeFormatter getFormatter() {
        return formatter;
    }

    public static LocalDateTime getStartDate() {
        return startDate;
    }

    public static LocalDateTime getEndDate() {
        return endDate;
    }
}
