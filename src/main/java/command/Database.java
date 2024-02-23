package command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.List;

public class Database {

    public final static String FILE_PATH = "John.txt";
    public final static String TEMP_FILE_PATH = "John2.txt";
    public final static String DATA_SEPERATOR = ";";
    private final static String DEADLINE_IDENTIFIER = "d";
    private final static String EVENT_IDENTIFIER = "e";
    private final static String TODO_IDENTIFIER = "t";

    public static void readData(List<Task> taskList) throws FileNotFoundException {

        File f = new File(FILE_PATH);
        Scanner data = new Scanner(f);

        while (data.hasNext()) {
            String next = data.nextLine();
            loadDataLine(taskList, next);
        }
        data.close();
    }

    public static void storeData(List<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);

        for (Task task : taskList) {
            storeTask(fw, task);
        }

        fw.close();
    }

    private static void storeTask(FileWriter fw, Task task) throws IOException {
        fw.write(task.getDataStorageString());
        fw.write(System.lineSeparator());
    }

    private static void loadDataLine(List<Task> taskList, String dataLine) {
        String[] separated = dataLine.split(DATA_SEPERATOR);

        switch (separated[0]) {

        case DEADLINE_IDENTIFIER:
            readDeadlineData(taskList, separated);
            break;

        case EVENT_IDENTIFIER:
            readEventData(taskList, separated);
            break;

        case TODO_IDENTIFIER:
            readTodoData(taskList, separated);
            break;

        default:
            break;

        }
    }

    private static void readTodoData(List<Task> taskList, String[] separated) {
        try {
            taskList.add(new ToDo(separated[1]));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Corrupted Todo data found.");
        }
    }

    private static void readDeadlineData(List<Task> taskList, String[] separated) {
        try {
            taskList.add(new Deadline(separated[1], separated[2]));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Corrupted Deadline data found.");
        }
    }

    private static void readEventData(List<Task> taskList, String[] separated) {
        try {
            taskList.add(new Event(separated[1], separated[2], separated[3]));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Corrupted Event data found.");
        }
    }

}
