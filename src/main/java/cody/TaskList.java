package cody;

import cody.parser.Parser;
import cody.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public String executeCommand(String input) throws CodyException {
        return Parser.parseCommand(input, this);
    }

    public String printList() {
        String listString = " Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            listString += " " + (i + 1) + ". [" + task.getTaskType() + "] "
                    + "[" + task.getStatusIcon() + "] "
                    + task.getDescription() + "\n";
        }
        return listString;
    }

    public String handleMarking(String input) {
        try {
            String[] parts = input.split(" ");
            int index = Integer.parseInt(parts[1]) - 1;
            boolean isDone = input.startsWith("mark");
            tasks.get(index).markTask(isDone);
            return " Good job! I've marked this task as done:\n"
                    + " [" + tasks.get(index).getStatusIcon() + "] " + tasks.get(index).getDescription();
        } catch (NumberFormatException e) {
            return " Task number is invalid. Please enter a valid number";
        } catch (IndexOutOfBoundsException e) {
            return " Task number is out of range. You have " + tasks.size() + " tasks";
        }
    }

    public String addTask(String input) {
        try {
            Task task = Parser.createTaskFromInput(input);
            tasks.add(task);
            return " Got it. I've added this task:\n"
                    + " [" + task.getTaskType() + "] [" + task.getStatusIcon() + "] " + task.getDescription() + "\n"
                    + " Now you have " + tasks.size() + " tasks in the list.";
        } catch (CodyException e) {
            return e.getMessage();
        }
    }

    public String deleteTask(String input) {
        try {
            String[] parts = input.split(" ");
            int index = Integer.parseInt(parts[1]) - 1;
            Task task = tasks.get(index);
            tasks.remove(index);
            return " Noted. I've removed this task:\n"
                    + " [" + task.getTaskType() + "] [" + task.getStatusIcon() + "] " + task.getDescription() + "\n"
                    + " Now you have " + tasks.size() + " tasks in the list.";
        } catch (NumberFormatException e) {
            return " Task number is invalid. Please enter a valid number";
        } catch (IndexOutOfBoundsException e) {
            return " Task number is out of range. You have " + tasks.size() + " tasks";
        }
    }

    public String findTask(String input) {
        String keyword = input.substring(5).trim();
        String listString = "Here are the matching tasks in your list:\n";
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(keyword)) {
                listString += " " + (i + 1) + ". [" + task.getTaskType() + "] "
                        + "[" + task.getStatusIcon() + "] "
                        + task.getDescription() + "\n";
                count++;
            }
        }
        if (count == 0) {
            listString = "There are no matching tasks in your list";
        }
        return listString;
    }
}
