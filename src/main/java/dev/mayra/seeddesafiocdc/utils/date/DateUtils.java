package dev.mayra.seeddesafiocdc.utils.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static LocalDate fromStringFormattedToLocalDate(String date) {
        return LocalDate.parse(date,
            DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
