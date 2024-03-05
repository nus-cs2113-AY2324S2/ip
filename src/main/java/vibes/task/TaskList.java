package vibes.task;

import vibes.exception.CommandNotFoundException;
import vibes.exception.InvalidArgumentException;
import vibes.storage.Storage;
import vibes.task.type.Deadline;
import vibes.task.type.Event;
import vibes.task.type.Task;
import vibes.task.type.Todo;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> tasks = new ArrayList<>();

    public void executeCommand(String commandToExecute, String userInput) throws CommandNotFoundException, InvalidArgumentException {
        int taskNumber;
        String description;

        switch (commandToExecute) {
        case "list":
            listTasks();
            break;
        case "mark":
            taskNumber = Integer.parseInt(userInput.substring(5)) - 1;
            setAsDone(taskNumber);
            break;
        case "unmark":
            taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
            setAsNotDone(taskNumber);
            break;
        case "todo":
            if (5 > userInput.length()) {
                throw new InvalidArgumentException();
            }
            description = userInput.substring(userInput.indexOf(" ") + 1);
            addTodo(description);

            showTaskAddedMessage();
            break;
        case "event":
            description = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf("/from") - 1);
            String from = userInput.substring(userInput.indexOf("/from") + 6, userInput.indexOf("/to") - 1);
            String to = userInput.substring(userInput.indexOf("/to") + 4);
            addEvent(description, from, to);

            showTaskAddedMessage();
            break;
        case "deadline":
            description = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf("/by") - 1);
            String by = userInput.substring(userInput.indexOf("/by") + 4);
            addDeadline(description, by);

            showTaskAddedMessage();
            break;
        case "delete":
            taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
            deleteTask(taskNumber);
            break;
        default:
            throw new CommandNotFoundException();
        }

        try {
            Storage.writeToFile(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showTaskAddedMessage() {
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t   " + tasks.get(tasks.size() - 1));
        System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
    }

    public void addEvent(String description, String from, String to) {
        tasks.add(new Event(description, from, to));
    }

    public void addDeadline(String description, String by) {
        tasks.add(new Deadline(description, by));
    }

    public void addTodo(String description) {
        tasks.add(new Todo(description));
    }

    public void listTasks() {
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t " + (i + 1) + "." + tasks.get(i));
        }
    }

    public void setAsDone(int taskNumber) {
        tasks.get(taskNumber).setDone(true);
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t   " + tasks.get(taskNumber));
    }

    public void setAsNotDone(int taskNumber) {
        tasks.get(taskNumber).setDone(false);
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.println("\t   " + tasks.get(taskNumber));
    }

    public void deleteTask(int taskNumber) {
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t   " + tasks.get(taskNumber));
        tasks.remove(taskNumber);
        System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
    }

}
