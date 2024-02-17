package Magus.task.fileio;

import Magus.task.TaskType;

import java.util.List;

public class Parser {
    private TaskType taskType;
    private boolean isDone;
    private String taskInfo;
    private static final String DELIMITER = "\t|\t";

    public Parser(String storedString) {
        String[] storedStringSplit = storedString.split(DELIMITER, maxSplitCount);
        int maxSplitCount = 3; // Obtain the task badge and task completion status

        if (storedStringSplit.length != maxSplitCount) {
            return; // Invalid stored string
        }

        char badge = storedStringSplit[0].charAt(0);
        taskType = TaskType.getEnum(badge);

        isDone = Boolean.parseBoolean(storedStringSplit[1]);
        taskInfo = storedStringSplit[2];
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public String getTaskInfo() {
        return taskInfo;
    }

    public boolean isDone() {
        return isDone;
    }

    public static String[] split(String taskInfo) {
        return taskInfo.split(DELIMITER);
    }
}
