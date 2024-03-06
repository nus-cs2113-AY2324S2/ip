package com.arriky.task;

import com.arriky.exception.ArrikyRuntimeException;
import com.arriky.exception.ErrorMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class ArrikyDateTime {
    private boolean isTimeStamp;
    private final LocalDateTime dateTime;
    private final DateTimeFormatter inputFormatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd").optionalStart().appendPattern(" HH:mm").optionalEnd().parseDefaulting(ChronoField.HOUR_OF_DAY, 0).parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0).toFormatter();
    private final DateTimeFormatter displayFormatterDateTime = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
    private final DateTimeFormatter displayFormatterDate = DateTimeFormatter.ofPattern("MMM d yyyy");
    private final DateTimeFormatter serializeFormatterDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final DateTimeFormatter serializeFormatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public ArrikyDateTime(String dateTimeString) throws ArrikyRuntimeException {
        try {
            String[] parsedInputString = dateTimeString.split(" ");
            if (parsedInputString.length == 1) {
                isTimeStamp = false;
            } else if (parsedInputString.length == 2) {
                isTimeStamp = true;
            }
            dateTime = LocalDateTime.parse(dateTimeString, inputFormatter);

        } catch (DateTimeParseException e) {
            throw new ArrikyRuntimeException(ErrorMessage.INVALID_DATETIME_FORMAT);
        }
    }

    public String getDisplayDateTime() {
        String output;
        if (isTimeStamp) {
            output = dateTime.format(displayFormatterDateTime);
        } else {
            output = dateTime.format(displayFormatterDate);
        }
        return output;
    }

    public String getSerializeDateTime() {
        String output;
        if (isTimeStamp) {
            output = dateTime.format(serializeFormatterDateTime);
        } else {
            output = dateTime.format(serializeFormatterDate);
        }
        return output;
    }
}
