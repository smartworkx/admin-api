package nl.smartworkx.admin;

import java.time.LocalDate;

import nl.smartworkx.admin.datetime.ClockHolder;

public class DateUtils {
    public static LocalDate getNow() {
        return LocalDate.now(ClockHolder.getClock());
    }
}