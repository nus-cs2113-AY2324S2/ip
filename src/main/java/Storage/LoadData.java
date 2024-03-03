package Storage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.EmptyTaskException;
import exceptions.MissingDeadlineException;
import exceptions.MissingStartException;
import tasks.*;

public class LoadData extends Storage {
    public static ArrayList<Task> savedTasks = new ArrayList<>(100);

    public LoadData(String filepath) {
        super(filepath);
    }

    public static ArrayList<Task> loadData(String filepath) throws FileNotFoundException {
        File data = new File(filepath);
        Scanner input = new Scanner(data);
        int count = 0;
        do {
            String task = input.nextLine();
            count++;
            char type = task.charAt(0);
            String description = task.substring(8);
            boolean isCompleted = task.charAt(4) != '0';
            String[] params = description.split("\\|");
            if (type == 'T') {
                savedTasks.add(new ToDo(description));
            } else if (type == 'D') {
                    String taskDescription = params[0].trim();
                    String deadline = params[1].trim();
                    savedTasks.add(new Deadline(taskDescription,deadline));
            } else if (type == 'E') {
                    String taskDescription = params[0].trim();
                    String from = params[1].trim();
                    String by = params[2].trim();
                    savedTasks.add(new Event(taskDescription,from,by));
            } else {
                break;
            }
            int index = savedTasks.size() - 1;
            savedTasks.get(index).setDone(isCompleted);
        } while (input.hasNext());
    return savedTasks;
    }
}