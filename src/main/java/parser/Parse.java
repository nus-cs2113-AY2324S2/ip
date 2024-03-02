package parser;
import tasks.Task;

import java.util.Scanner;

public class Parse {
    public static int getTaskNumber(String input) {
        return Integer.parseInt(input.split(" ")[1]);
    }

    public static String formatToFile(Task item) {
        String postFormat;
        String preFormat = item.getTask();
        String taskType = preFormat.split("]")[0];
        String isDone = item.isDone ? "1" : "0";

        if (taskType.equals("[T")) {
            postFormat = "T | " + isDone + " | " + item.description;
        } else if (taskType.equals("[D")) {
            postFormat = "D | " + isDone + " | " + item.description.split("\\(")[0] + " | " + item.getTime();
        } else {
            postFormat = "E | " + isDone + " | " + item.description.split("\\(")[0] + " | " + item.getTime();
        }
        return postFormat;
    }

    public static void formatFromFile(Scanner s) {
        String line = s.nextLine();
        String taskType = line.split(" \\| ")[0];
        int taskNumber =  Integer.parseInt(line.split(" \\| ")[1]);
        boolean taskDone = taskNumber == 1;
        String taskContent = line.split(" \\| ")[2];

        //type of task
        if (taskType.equals("T")) {
            Task.createTask("todo", taskDone,taskContent, true);
        } else if (taskType.equals("D")) {
            String taskTiming = line.split(" \\| ")[3];
            Task.createTask("deadline", taskDone,taskContent + "(by: " + taskTiming + ")", true);
        } else {
            String taskTiming = line.split(" \\| ")[3];
            String taskFrom = taskTiming.split("to")[0];
            String taskTo = taskTiming.split("to")[1];
            Task.createTask("event", taskDone,taskContent + "(from: " + taskFrom + "to" + taskTo + ")", true);
        }
    }


}
