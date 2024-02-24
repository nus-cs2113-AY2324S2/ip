import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import javax.swing.table.TableRowSorter;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DataHandler {

    public static final String FILE_PATH = "src/main/java/data/data.txt";

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    public static void tasksToFile(Task[] taskList) throws IOException {
        File f = new File(FILE_PATH);
        FileWriter fw = new FileWriter(FILE_PATH);
        for(Task task : taskList){
            if (task == null) break;
            String stringToWrite = task.getBadge() + "," + (task.getStatusIcon().equals("X") ? 1 : 0)
                    + "," + task.description + "\n";
            writeToFile(FILE_PATH, stringToWrite);
        }
    }

    public static Task[] readFileContents(String filePath) throws IOException {
        File f = new File(filePath);
        if (!f.exists()){
            f.createNewFile();
        }
        Scanner s = new Scanner(f);
        Task[] taskList = new Task[Spike.MAX_TASK];
        for (int i = 0; s.hasNext(); i++) {
            taskList[i] = processData(s.nextLine());
        }
        return taskList;
    }

    private static Task processData(String s) {
        String[] taskInfo = s.split(",");
        String Badge = taskInfo[0];
        switch (Badge) {
        case "T":
            Todo todoObj = new Todo(taskInfo[2]);
            if (taskInfo[1].equals("1")) todoObj.setDone(true);
            return todoObj;
        case "D":
            Deadline deadlineObj = new Deadline(taskInfo[2]);
            if (taskInfo[1].equals("1")) deadlineObj.setDone(true);
            return deadlineObj;
        case "E":
            Event eventObj = new Event(taskInfo[2]);
            if (taskInfo[1].equals("1")) eventObj.setDone(true);
            return eventObj;
        }
        return null;
    }
}
