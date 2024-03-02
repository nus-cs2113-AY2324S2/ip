package parser;
import exceptions.EmptyTaskException;
import tasks.Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;

public class Parse {
    public static int getTaskNumber(String input) {
        return Integer.parseInt(input.split(" ")[1]);
    }

    /**
     * Saves the list of items to a txt file
     *
     * @param item to be iterated and parsed before written into the file
     */
    public static String formatToFile(Task item) {
        String postFormat;
        String preFormat = item.getTask();
        String taskType = preFormat.split("]")[0];
        String isDone = item.isDone ? "1" : "0";

        if (taskType.equals("[T")) {
            postFormat = "T | " + isDone + " | " + item.description;
        } else if (taskType.equals("[D")) {
            postFormat = "D | " + isDone + " | " + item.description.split("\\(")[0] + " | " + getTime(item);
        } else {
            postFormat = "E | " + isDone + " | " + item.description.split("\\(")[0] + " | " + getTime(item);
        }
        return postFormat;
    }

    /**
     * Loads the list of items from a txt file
     *
     * @param s to be iterated and parsed before written into list inside Task
     */
    public static void formatFromFile(Scanner s) {
        String line = s.nextLine();
        String taskType = line.split(" \\| ")[0];
        int taskNumber = Integer.parseInt(line.split(" \\| ")[1]);
        boolean taskDone = taskNumber == 1;
        String taskContent = line.split(" \\| ")[2];

        //type of task
        if (taskType.equals("T")) {
            Task.createTask("todo", taskDone, taskContent, true);
        } else if (taskType.equals("D")) {
            String taskTiming = line.split(" \\| ")[3];
            Task.createTask("deadline", taskDone, taskContent + "(by:" + taskTiming + ")", true);
        } else {
            String taskTiming = line.split(" \\| ")[3];
            String taskFrom = taskTiming.split("to")[0];
            String taskTo = taskTiming.split("to")[1];
            Task.createTask("event", taskDone, taskContent + "(from:" + taskFrom + "to" + taskTo + ")", true);
        }
    }

    /**
     * Formats events into the desired format, with time being an optional parameter
     * Warns the user if the input format does not allow time to be formatted
     *
     * @param taskContent content to be processed
     * @throws EmptyTaskException if the task is empty
     */
    public static String formatEvent(String taskContent) throws EmptyTaskException{
        String eventContent = taskContent.split("/from ")[0];
        if (eventContent.trim().isEmpty()) {
            throw new EmptyTaskException();
        } else {
            String eventTiming = taskContent.split("/from ")[1];
            String eventFrom = eventTiming.split("/to ")[0].trim();
            String eventTo = eventTiming.split("/to ")[1].trim();

            try {
                LocalDate dateFrom = LocalDate.parse(eventFrom);
                LocalDate dateTo = LocalDate.parse(eventTo);
                eventFrom = dateFrom.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                eventTo = dateTo.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (Exception e) {
                System.out.println("Warning: Task added is not in in YYYY-MM-DD");
            }

            return taskContent.split("/")[0] + " (from: " + eventFrom + " to: " + eventTo + ")";
        }
    }

    /**
     * Formats deadlines into the desired format, with time being an optional parameter
     * Warns the user if the input format does not allow time to be formatted
     *
     * @param taskContent content to be processed
     * @throws EmptyTaskException if the task is empty
     */
    public static String formatDeadline(String taskContent) throws EmptyTaskException {
        String deadlineContent = taskContent.split("/by ")[0];
        if (deadlineContent.trim().isEmpty()) {
            throw new EmptyTaskException();
        } else {
            String deadlineTiming = taskContent.split("/by ")[1];
            try {
                LocalDate date = LocalDate.parse(deadlineTiming);
                deadlineTiming = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (Exception e) {
                System.out.println("Warning: Task added is not in in YYYY-MM-DD");
            }
            return taskContent.split("/")[0] + " (by: " + deadlineTiming + ")";
        }
    }

    /**
     * Gets the deadlines and events' time parameter from their description
     *
     * @param item content to be processed
     * @throws Exception for any formatting issues
     */
    public static String getTime(Task item) {
        String output = "";
        try {
            if (item.taskType.equals("[D]")) {
                String initial = item.getTask().split("by:")[1];
                output = initial.split("\\)")[0];
            } else if (item.taskType.equals("[E]")) {
                String initialFrom = (item.getTask().split("from:")[1]).split("to:")[0];
                String initialTo = (item.getTask().split("to:")[1]).split("\\)")[0];
                output = initialFrom.trim() + " to: " + initialTo.trim();
            }
        } catch (Exception e) {
            System.out.println("Error loading data:" + e.getMessage());
        }
        return output;
    }

}
