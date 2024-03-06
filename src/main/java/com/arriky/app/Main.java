package com.arriky.app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        //create dates from strings
        LocalDate d1 = LocalDate.parse("02/1/2019");


        System.out.println(d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"))); // -> Oct 15 2019
    }
}
