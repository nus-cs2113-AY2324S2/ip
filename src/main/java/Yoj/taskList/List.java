package Yoj.taskList;

import Yoj.exception.InvalidCommandException;
import Yoj.exception.YojException;
import Yoj.storage.Storage;
import Yoj.tasks.Deadline;
import Yoj.tasks.Event;
import Yoj.tasks.ToDo;
import Yoj.tasks.Task;
import Yoj.ui.*;

import java.io.IOException;
import java.util.ArrayList;

public class List {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static void addTask(String userInput) throws YojException, IOException {
        if (userInput.startsWith("todo")) {
            manageToDo(userInput);
        } else if (userInput.startsWith("deadline")) {
            manageDeadline(userInput);
        } else if (userInput.startsWith("event")) {
            manageEvent(userInput);
        }
        Ui.printShortLine();
        Ui.addTaskMessage();
        Ui.printLine();
    }

    public static void deleteTask(String userInput) {
        String taskNum = userInput.substring("delete ".length()).trim();
        int num = Integer.parseInt(taskNum);
        Task deletedTask = tasks.get(num - 1);
        tasks.remove(num - 1);
        Ui.taskDeletedMessage(deletedTask);

    }
    public static void manageToDo(String userInput) throws YojException, IOException {
        String description = userInput.substring("todo".length()).trim();
        if (description.isEmpty()) {
            throw new YojException("The description of todo cannot be empty...");
        } else {
            tasks.add(new ToDo(userInput.substring(5)));
        }
    }
    public static void manageDeadline(String userInput) throws YojException, IOException {
        String description = userInput.substring("deadline".length()).trim();
        if (description.isEmpty()) {
            throw new YojException("The description of deadline cannot be empty...");
        } else {
            String[] parts = userInput.substring(9).split(" /by ");
            tasks.add(new Deadline(parts[0].trim(), parts[1].trim()));
        }
    }
    public static void manageEvent(String userInput) throws YojException, IOException {
        String description = userInput.substring("event".length()).trim();
        if (description.isEmpty()) {
            throw new YojException("The description of event cannot be empty...");
        } else {
            String[] parts = userInput.substring(6).split(" /from ");
            String[] times = parts[1].split(" /to ");
            tasks.add(new Event(parts[0].trim(), times[0].trim(), times[1].trim()));
        }
    }

    public static void findTask(String userInput) {
        ArrayList<Task> tasksFound = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(userInput)) {
                tasksFound.add(task);
            }
        }
        if (tasksFound.isEmpty()){
            Ui.printNoTaskFound();
        } else {
            Ui.printTaskFound(tasksFound);
        }
    }

}
