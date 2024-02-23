package savedData;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.EmptyTaskException;
import exceptions.MissingDeadlineException;
import exceptions.MissingStartException;
import tasks.*;

public class LoadData {
    public static ArrayList<Task> savedTasks = new ArrayList<>(100);
    public static void loadTodo(String description) throws EmptyTaskException {
        if (description.isBlank()) {
            throw new EmptyTaskException();
        }
        savedTasks.add(new ToDo(description));
    }

    public static void loadDeadline(String description) throws EmptyTaskException, MissingDeadlineException {
        String[] params = description.split("\\|");
        String task = params[0].trim();
        String deadline = params[1].trim();
        if (task.isBlank()) {
            throw new EmptyTaskException();
        }
        if (deadline.isBlank()) {
            throw new MissingDeadlineException();
        }
        savedTasks.add(new Deadline(task,deadline));
    }

    public static void loadEvent(String description) throws  EmptyTaskException, MissingDeadlineException,
            MissingStartException {
        String[] params = description.split("\\|");
        String task = params[0].trim();
        String from = params[1].trim();
        String by = params[2].trim();
        if (task.isBlank()) {
            throw new EmptyTaskException();
        }
        if (from.isBlank()) {
            throw new MissingStartException();
        }
        if (by.isBlank()) {
            throw new MissingDeadlineException();
        }
        savedTasks.add(new Event(task,from,by));
    }

    public static ArrayList<Task> loadData() throws FileNotFoundException {
        File data = new File("src/data/peekay.txt");
        Scanner input = new Scanner(data);
        int count = 0;
        do {
            String task = input.nextLine();
            count++;
            char type = task.charAt(0);
            String description = task.substring(8);
            boolean isCompleted = task.charAt(4) != '0';
            if (type == 'T') {
                try {
                    loadTodo(description);
                } catch (EmptyTaskException e){
                    System.out.println("Error Loading Task " + count + "! Todo should not be empty.");
                }
            } else if (type == 'D') {
                try {
                    loadDeadline(description);
                } catch (MissingDeadlineException e) {
                    System.out.println("Task " + count + " is missing its deadline!");
                } catch (EmptyTaskException e){
                    System.out.println("Error Loading Task " + count + "! Task should not be empty.");
                }
            } else if (type == 'E') {
                try {
                    loadEvent(description);
                } catch (MissingDeadlineException e) {
                    System.out.println("Task " + count + " is missing its deadline!");
                } catch (MissingStartException e) {
                    System.out.println("Task " + count + " is missing its start time!");
                } catch (EmptyTaskException e) {
                    System.out.println("Error Loading Task " + count + "! Task should not be empty.");
                }
            } else {
                break;
            }
            int index = savedTasks.size() - 1;
            savedTasks.get(index).setDone(isCompleted);
        } while (input.hasNext());
    return savedTasks;
    }
}