package com.arriky.exception;

public class ErrorMessage {
    public String INVALID_ID = "Task ID should be an integer. Please try again.";
    public String INVALID_COMMAND = "Command does not exist. Please try again.";
    public String ID_NOT_EXIST = "Task ID does not exist. Please try again";
    public String INVALID_EVENT_FORMAT = "Invalid event format. Please follow 'event <NAME> /from <START_TIME> /to <END_TIME>' format";
    public String INVALID_DEADLINE_FORMAT = "Invalid deadline format. Please follow 'deadline <NAME> /by <DUE_TIME>' format";
    public String INCORRECT_ARGUMENT_AMOUNT_0 = "There should be no argument for this command. Please try again.";
    public String INCORRECT_ARGUMENT_AMOUNT_1 = "There should be exactly 1 argument for this command. Please try again.";
    public String LOCAL_RECORD_CORRUPTED = "Local record file is corrupted, import is incomplete.";
    public String LOCAL_RECORD_NOT_EXIST = "There is no local record file found, a new file will be created";
}
