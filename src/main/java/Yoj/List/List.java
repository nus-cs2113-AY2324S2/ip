package Yoj.List;

import Yoj.exception.InvalidCommandException;
import Yoj.exception.YojException;
import Yoj.storage.Storage;
import Yoj.tasks.Deadline;
import Yoj.tasks.Event;
import Yoj.tasks.Task;
import Yoj.tasks.ToDo;
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
        if (tasks.size() == 0) {
            System.out.println("okie task removed!!");
            System.out.println(deletedTask);
            System.out.println("there's no more tasks in the list.. please add new tasks below :)");
        } else {
            System.out.println("okie task removed!!");
            System.out.println(deletedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }

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
    public static void manageUserInput(String userInput) throws YojException, InvalidCommandException, IOException {
        if (userInput.equals("list")) {
            Ui.printList();
        } else if(userInput.matches("mark \\d+")) {
            String[] taskIndex = userInput.split(" ");
            int index = Integer.parseInt(taskIndex[1] );
            tasks.get(index - 1).markDone();
            Ui.markDoneMessage();
            System.out.println(tasks.get(index-1).taskType() + "[X] " + tasks.get(index - 1).getDescription());
        } else if(userInput.matches("unmark \\d+")) {
            String[] taskIndex = userInput.split(" ");
            int index = Integer.parseInt(taskIndex[1]);
            tasks.get(index - 1).markUndone();
            Ui.markUndoneMessage();
            System.out.println(tasks.get(index-1).taskType() + "[ ] " + tasks.get(index - 1).getDescription());
        } else if(userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event")) {
            addTask(userInput);
        } else if(userInput.startsWith("delete")) {
            deleteTask(userInput);
        } else if(userInput.equals("bye")){
            Ui.byeMessage();
            Storage.save(tasks);
        } else throw new InvalidCommandException("command not recognised :<");
    }

}
