package kyrene.ui;

import kyrene.taskList.TaskList;

import java.util.Scanner;

public class Ui {
    private final static String LOGO = "    _   _  _    _  ____   ____  _   _  ____\n"
            + "    | | / /\\ \\  / /|  _ \\ | ___|| \\ | || ___|\n"
            + "    | |/ /  \\ \\/ / | |_| || ===||  \\| || ===|\n"
            + "    | |\\ \\   |  |  | |\\ / | |__ | |\\  || |__\n"
            + "    |_| \\_\\  |__|  |_| \\_\\|____||_| \\_||____|   by Zhou Junmin\n";
    private final static String DIVIDER = "    |==========================================================|\n";
    private final static String HELLO = "    Hi, I am Kyrene, your private reminder assistant.\n"
            + "    What can I do for you?\n";
    private final static String BYE = "    Bye! Wish to see you again soon!\n";
    private final static String ERROR_WRITE_TO_FILE_FAILED = "    Error! Write to file failed.\n";
    private final static String ERROR_FOLDER_CREATION_FAILED = "    Error! Folder creation failed.\n";
    private final static String ERROR_FILE_CREATION_FAILED = "    Error! File creation failed.\n";
    private final static String ERROR_FILE_CORRUPTED = "    Error! File loaded is corrupted.\n";
    private final static String ERROR_TASK_NOT_EXIST = "    Error! This task does not exist.\n";
    private final static String ERROR_MISSING_TASK = "    Error! Your task description is missing.\n    For creating tasks, please type \"[task type (todo/deadline/event)] [task description]\"([] is to be omitted).\n    For example:\n        todo have a nice day\n    or\n        deadline get a cup of coffee /by 2024-02-29 0900\n    or\n        event celebrate birthday /from 2024-10-27 1700 /to 2359\n";
    private final static String ERROR_MISSING_TIME = "    Error! Your deadline/event task created is incomplete in terms of time.\n    For creating deadline task, please type \"deadline [task description] /by [time]\"([] is to be omitted).\n    For example:\n        deadline get a cup of coffee /by 2024-02-29 0900\n    For creating event task, please type \"event [task description] /from [starting time] /to [ending time]\"([] is to be omitted).\n    For example:\n        event celebrate birthday /from 2023-10-27 1700 /to 2359\n";
    private final static String ERROR_INVALID_COMMAND = "    Error! This command is invalid. Some available commands are ([] is to be omitted):\n        list\n        mark [task index]\n        unmark [task index]\n        delete [task index]\n        [task type (todo/deadline/event)] [task description]\n        due [due time] (to check which deadlines before the given time)\n        at [date] (to check which events at the given date)\n        find [keyword]\n        bye\n";
    private final static String ERROR_INVALID_DDL_TIME_FORMAT = "    Error! The format of input deadline date and time is invalid.\n    Please re-enter the time of deadline as [Year-Month-Date HourMinute]([] is to be omitted).\n    For example:\n        2024-02-29 0900\n        2024-02-29\n        0900\n";
    private final static String ERROR_INVALID_EVENT_START_TIME_FORMAT = "    Error! The format of input event start date and time is invalid.\n    Please re-enter the start time of event as [Year-Month-Date HourMinute]([] is to be omitted).\n    For example:\n        2024-02-29 0900\n        2024-02-29\n        0900\n";
    private final static String ERROR_INVALID_EVENT_END_TIME_FORMAT = "    Error! The format of input event end date and time is invalid.\n    Please re-enter the end time of event as [Year-Month-Date HourMinute]([] is to be omitted).\n    For example:\n        2024-02-29 0900\n        2024-02-29\n        0900\n";
    private final static String ERROR_INVALID_DATE_FORMAT = "    Error! The format of input date is invalid.\n    Please re-enter the date as [Year-Month-Date]([] is to be omitted).\n    For example:\n        2024-02-29\n";
    private final static String ERROR_INVALID_TIME_FORMAT = "    Error! The format of input time is invalid.\n    Please re-enter the time as [Year-Month-Date HourMinute]([] is to be omitted).\n    For example:\n        2024-02-29 0900\n        2024-02-29\n        0900\n";
    public Ui() {}

    public static void showDivider() {
        System.out.printf("%s\n", DIVIDER);
    }

    public static void showHello() {
        showDivider();
        System.out.printf("%s\n", LOGO);
        showDivider();
        System.out.printf("%s\n", HELLO);
        showDivider();
    }

    public static void showBye() {
        System.out.printf("%s\n", BYE);
        showDivider();
    }

    public static void showTaskCount(int taskCount) {
        if(taskCount < 2){
            System.out.printf("    Now you have %d task(including finished ones) in your list.\n\n", taskCount);
        }
        else{
            System.out.printf("    Now you have %d tasks(including finished ones) in your list.\n\n", taskCount);
        }
    }

    public static void showMatchCount(int matchCount) {
        if(matchCount < 2){
            System.out.printf("    You have %d matched task in your list.\n\n", matchCount);
        }
        else{
            System.out.printf("    You have %d matched tasks in your list.\n\n", matchCount);
        }
    }

    public static void showErrorMissingTime() {
        System.out.printf("%s\n", ERROR_MISSING_TIME);
        showDivider();
    }

    public static void showErrorWriteToFileFailed() {
        System.out.printf("%s\n", ERROR_WRITE_TO_FILE_FAILED);
        showDivider();
    }

    public static void showErrorTaskNotExist(int taskCount) {
        System.out.printf("%s", ERROR_TASK_NOT_EXIST);
        showTaskCount(taskCount);
        showDivider();
    }

    public static void showErrorMissingTask() {
        System.out.printf("%s\n", ERROR_MISSING_TASK);
        showDivider();
    }

    public static void showErrorInvalidCommand() {
        System.out.printf("%s\n", ERROR_INVALID_COMMAND);
        showDivider();
    }

    public static void showErrorFileCorrupted(int lineNumber) {
        System.out.printf("%s    Error occurs at line %d.\n", ERROR_FILE_CORRUPTED, lineNumber);
        showDivider();
    }

    public static void showErrorFileNotFound(String filePath) {
        System.out.printf("    File %s is not found. Trying to create file...\n", filePath);
    }

    public static void showErrorFolderNotFound(String folderPath) {
        System.out.printf("    Folder %s is not found. Trying to create folder...\n", folderPath);
    }

    public static void showErrorCreatingFileFailed() {
        System.out.printf("%s\n", ERROR_FILE_CREATION_FAILED);
        showDivider();
    }

    public static void showErrorCreatingFolderFailed() {
        System.out.printf("%s\n", ERROR_FOLDER_CREATION_FAILED);
        showDivider();
    }

    public static void showErrorInvalidDateFormat() {
        System.out.printf("%s\n", ERROR_INVALID_DATE_FORMAT);
        showDivider();
    }

    public static void showErrorInvalidTimeFormat() {
        System.out.printf("%s\n", ERROR_INVALID_TIME_FORMAT);
        showDivider();
    }

    public static void showErrorInvalidDdlTimeFormat() {
        System.out.printf("%s\n", ERROR_INVALID_DDL_TIME_FORMAT);
        showDivider();
    }

    public static void showErrorInvalidEventStartTimeFormat() {
        System.out.printf("%s\n", ERROR_INVALID_EVENT_START_TIME_FORMAT);
        showDivider();
    }

    public static void showErrorInvalidEventEndTimeFormat() {
        System.out.printf("%s\n", ERROR_INVALID_EVENT_END_TIME_FORMAT);
        showDivider();
    }

    public static void showSuccessAddingTask(String taskAdded, int taskCount) {
        System.out.printf("    Task has been successfully added: %s\n", taskAdded);
        showTaskCount(taskCount);
        showDivider();
    }

    public static void showSuccessDeletingTask(String taskDeleted, int taskCount) {
        System.out.printf("    Sure! I have successfully deleted this task from your list:\n        %s\n", taskDeleted);
        showTaskCount(taskCount);
        showDivider();
    }

    public static void showSuccessMarkingTask(int taskNumber, boolean isDone) {
        if (isDone) {
            System.out.printf("    Congrats! Task %d is done!\n\n", taskNumber);
        } else {
            System.out.printf("    Task %d is marked as not done.\n\n", taskNumber);
        }
        showDivider();
    }

    public static void showSuccessLoadingFile(String filePath) {
        System.out.printf("    File %s has been loaded.\n", filePath);
        showDivider();
    }

    public static void showSuccessCreatingFolder(String folderPath) {
        System.out.printf("    Folder %s is successfully created.\n", folderPath);
    }

    public static void showSuccessCreatingFile(String filePath) {
        System.out.printf("    File %s is successfully created.\n", filePath);
        showDivider();
    }

    public static void showTasks(TaskList tasks) {
        System.out.println("    Here is your to-do list of required tasks:");
        for(int i = 0; i < tasks.size(); i++){
            System.out.printf("        %d.%s\n", i + 1, tasks.get(i).toString());
        }
        int taskCount = tasks.size();
        showTaskCount(taskCount);
        showDivider();
    }

    public static void showEvents(TaskList events) {
        System.out.println("    Here is your event list that matches the given date:");
        for(int i = 0; i < events.size(); i++){
            System.out.printf("        %d.%s\n", i + 1, events.get(i).toString());
        }
        int matchCount = events.size();
        showMatchCount(matchCount);
        showDivider();
    }

    public static void showDeadlines(TaskList deadlines) {
        System.out.println("    Here is your deadline list before the given time:");
        for(int i = 0; i < deadlines.size(); i++){
            System.out.printf("        %d.%s\n", i + 1, deadlines.get(i).toString());
        }
        int matchCount = deadlines.size();
        showMatchCount(matchCount);
        showDivider();
    }

    public static void showMatches(TaskList matches) {
        System.out.println("    Here is your to-do list that matches the given keyword:");
        for(int i = 0; i < matches.size(); i++){
            System.out.printf("        %d.%s\n", i + 1, matches.get(i).toString());
        }
        int matchCount = matches.size();
        showMatchCount(matchCount);
        showDivider();
    }

    public static void showLoadingFile(String filePath) {
        System.out.printf("    Loading file %s ...\n", filePath);
    }

    public static String readCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

}
