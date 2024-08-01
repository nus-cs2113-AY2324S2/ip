package kapwa;

import java.io.IOException;
import java.util.ArrayList;

import data.FileAccess;
import exception.KapwaException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();  

    public void addTask(String inputLine) throws KapwaException {
        String[] parts = inputLine.split(" ", 2);
        if (parts.length < 2) {
            throw new KapwaException("Invalid task format.");
        }
        String taskType = parts[0];
        Task newTask = null;

        try {
            switch (taskType) {
            case "todo":
                newTask = new Todo(parts[1]);
                if (parts[1].trim().isEmpty()) {
                    throw new KapwaException("The description of a todo cannot be empty.");
                }
                break;
            case "deadline":
                String[] deadlineParts = parts[1].split("/by", 2);
                newTask = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
                if (deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
                    throw new KapwaException("The description and by of a deadline cannot be empty.");
                }
                break;
            case "event":
                String[] eventDetails = parts[1].split("/from", 2);
                String[] fromTo = eventDetails[1].split("/to", 2);
                newTask = new Event(eventDetails[0].trim(), fromTo[0].trim(), fromTo[1].trim());
                if (eventDetails[0].trim().isEmpty() || fromTo[0].trim().isEmpty() || fromTo[1].trim().isEmpty()) {
                    throw new KapwaException("The description, from and to of an event cannot be empty.");
                }
                break;
            default:
                System.out.println("Invalid task type.");
                return;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid task format.");
            return;
        }

        tasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        try {
            FileAccess.appendToFile("kapwa.txt", newTask.toStoreString() + "\n");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
        }
    }

    public void displayTaskList() throws KapwaException{
        if (tasks.isEmpty()) {
            throw new KapwaException("There are no tasks in the list.");
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void markTask(int taskNumber, boolean isDone) {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            System.out.println("Task number is invalid.");
            return;
        }
        Task task = tasks.get(taskNumber - 1);
        if (isDone) {
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            task.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("  " + task);
    }

    public void deleteTask(int taskNumber) {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            System.out.println("Task number is invalid.");
            return;
        }
        Task task = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void find(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        if (foundTasks.isEmpty()) {
            System.out.println("There are no matching tasks in your list.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println((i + 1) + ". " + foundTasks.get(i));
            }
        }
    }
}
