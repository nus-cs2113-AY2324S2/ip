package soot.manager;

import soot.task.Deadline;
import soot.task.Event;
import soot.task.Task;
import soot.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SavedFileManager {
    public static ArrayList<String> readSavedFile(String path) throws FileNotFoundException {
        ArrayList<String> readFile = new ArrayList<>();
        File savedFile = new File(path);
        Scanner s = new Scanner(savedFile);
        while (s.hasNext()) {
            readFile.add(s.nextLine());
        }
        s.close();
        return readFile;
    }

    public static void loadSavedTasks(String line) {
        System.out.println("line:" + line);
        String[] taskInfo = line.split(" ; ", 0);
        boolean isTaskDone = !taskInfo[1].trim().equals("0");

        switch (taskInfo[0].trim()) {
        case ("T"):
            CommandManager.taskList.add(new Todo(taskInfo[2], isTaskDone));
            CommandManager.listCounter++;
            break;
        case ("D"):
            CommandManager.taskList.add(new Deadline(taskInfo[2], isTaskDone, taskInfo[3]));
            CommandManager.listCounter++;
            break;
        case ("E"):
            CommandManager.taskList.add(new Event(taskInfo[2], isTaskDone, taskInfo[3], taskInfo[4]));
            CommandManager.listCounter++;
            break;
        default:
            System.out.println("something went wrong, perhaps an invalid task type?");
        }
        System.out.println(Arrays.toString(taskInfo));
    }


    public static void saveFinalFile() throws IOException {
        FileWriter fw = new FileWriter("saved-data/saved.txt");
        for (int i = 0; i < CommandManager.taskList.size(); i++) {
            String stringToSave = formatTaskLine(CommandManager.taskList.get(i));
            fw.write(stringToSave + "\n");
        }
        fw.close();
    }

    public static String formatTaskLine(Task task) {
        String formattedLine;
        int taskDone = task.isDone ? 1 : 0;
        switch (task.taskType) {
        case TODO:
            formattedLine = "T ; " + taskDone + " ; " + task.taskName;
            break;
        case DEADLINE:
            Deadline deadline = (Deadline) task;
            formattedLine = "D ; " + taskDone + " ; " + deadline.taskName + " ; " + deadline.dueDate;
            break;
        case EVENT:
            Event event = (Event) task;
            formattedLine = "E ; " + taskDone + " ; " + event.taskName + " ; " + event.startDate + " ; " + event.endDate;
            break;
        default:
            formattedLine = "i could not read this task";
            System.out.println("task of unknown type detected");
        }
        return formattedLine;
    }
}
