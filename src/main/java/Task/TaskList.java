package task;

import ui.Ui;

import java.util.List;
import java.util.ArrayList;

import exception.DukeException;

public class TaskList{

    private final List<Task> tasks;
    private final Ui ui;

    public TaskList() {
        tasks = new ArrayList<>();
        ui = new Ui("aoliba");
    }

    public void addTask(Task task) {
        tasks.add(task);
        ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        ui.showSize(tasks.size());
    }

/*
    public void addTask(String description) throws DukeException {
        Scanner scanner = new Scanner(description);
        scanner.useDelimiter(" ");
        String taskType = scanner.next().toLowerCase();

        String taskDescription;
        if (scanner.hasNext()) {
            taskDescription = scanner.nextLine().trim();
        } else {
            taskDescription = "";
        }
        scanner.close();

        Task task;
        switch (taskType) {
            case "todo":
                if (taskDescription.isEmpty()) {
                    throw new DukeException("NO!!! The description of a todo task cannot be empty!");
                }
                task = new Todo(taskDescription);
                break;
            case "deadline":
                String[] deadlineParts = taskDescription.split(" /by ", 2);
                String deadlineDescription = deadlineParts[0];
                String by = deadlineParts.length > 1 ? deadlineParts[1] : "";
                task = new Deadline(deadlineDescription, by);
                break;
            case "event":
                String[] eventParts = taskDescription.split(" /from ", 3);
                String eventDescription = eventParts[0];
                if (eventParts.length < 2) {
                    System.out.println("Invalid event task format. Please include the start and end times.");
                    return;
                }
                String[] timeParts = eventParts[1].split(" /to ", 2);
                String start = timeParts[0];
                String end = timeParts.length > 1 ? timeParts[1] : "";
                task = new Event(eventDescription, start, end);
                break;
            default:
                System.out.println("I dont understand what do you mean, please try again.");
                return;
        }
        tasks.add(task);
        System.out.println("Added: " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        
    }
 */
    public void deleteTask(int taskNumber) {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            ui.invalidIndex();
            return;
        }
        System.out.println("Noted. I've removed this task: " + tasks.get(taskNumber-1).getDescription());
        tasks.remove(taskNumber - 1);
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks added yet.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    public void markTaskAsDone(int taskNumber, boolean markDone) {
        // boolean found = false;
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            ui.invalidIndex();
            return;
        }
        Task task = tasks.get(taskNumber - 1);
        // task is UNmarked, and we want to mark it
        if (!task.isDone() && markDone) {
            task.markAsDone();
        } else if (task.isDone() && !markDone) { // task is marked, and we want to UNmark it
            task.markAsNotDone();
        }
        else {
            System.out.println("Task already marked as " + (markDone ? "done": "not done yet"));
            return;
        }
        System.out.println("Nice! I've marked this task as " + (markDone ? "done": "not done yet"));
        
        /*
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                // task is UNmarked, and we want to mark it
                if (!task.isDone() && markDone) {
                    task.markAsDone();
                } else if (task.isDone() && !markDone) { // task is marked, and we want to UNmark it
                    task.markAsNotDone();
                }
                else {
                    System.out.println("Task already marked as " + (markDone ? "done": "not done yet"));
                    break;
                }
                System.out.println("Nice! I've marked this task as " + (markDone ? "done": "not done yet"));
                // System.out.println(task);
                found = true;
                break;
            }
        }
        */
    }

    public void unmarkTaskAsDone(String description) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                if (task.isDone()) {
                    task.markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(task);
                } else {
                    System.out.println("Task.Task is already marked as not done.");
                }
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Task.Task not found.");
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

}
