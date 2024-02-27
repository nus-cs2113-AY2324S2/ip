package cody;

import cody.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    ArrayList<Task> tasks;

    public void printList() {
        String listString = " Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            listString += " " + (i + 1) + ". [" + task.getTaskType() + "] "
                    + "[" + task.getStatusIcon() + "] "
                    + task.getDescription() + "\n";
        }
        Ui.printMessage(listString);
    }

    private void markTask(int index) {
        Ui.printMessage(" Good job! I've marked this task as done:\n"
                + " [" + tasks.get(index).getStatusIcon() + "] " + tasks.get(index).getDescription() + "\n");
    }

    public void handleMarking(String input) {
        try {
            String[] parts = input.split(" ");
            int index = Integer.parseInt(parts[1]) - 1;
            boolean isDone = input.startsWith("mark");
            tasks.get(index).markTask(isDone);
            markTask(index);
        } catch (NumberFormatException e) {
            System.err.println(" Task number is invalid. Please enter a valid number");
        } catch (IndexOutOfBoundsException e) {
            System.err.println(" Task number is out of range. You have " + tasks.size() + " tasks in the list");
        }
    }

    public void addTask(String input) {
        try {
            Task task = Parser.createTaskFromInput(input);
            tasks.add(task);
            printTask(task);
        } catch (CodyException e) {
            System.err.println(e.getMessage());
        }
    }

    private void printTask(Task task) {
        Ui.printMessage(" Got it. I've added this task:\n"
                + " [" + task.getTaskType() + "] [" + task.getStatusIcon() + "] " + task.getDescription() + "\n"
                + " Now you have " + tasks.size() + " tasks in the list.\n");
    }

    public void deleteTask(String input) {
        String[] parts = input.split(" ");
        int index = Integer.parseInt(parts[1]) - 1;
        try {
            Task task = tasks.get(index);
            tasks.remove(index);
            printDeleteTask(task);
        } catch (NumberFormatException e) {
            System.err.println(" Task number is invalid. Please enter a valid number");
        } catch (IndexOutOfBoundsException e) {
            System.err.println(" Task number is out of range. You have " + tasks.size() + " tasks in the list");
        }
    }

    private void printDeleteTask(Task task) {
        Ui.printMessage(" Noted. I've removed this task:\n"
                + " [" + task.getTaskType() + "] [" + task.getStatusIcon() + "] " + task.getDescription() + "\n"
                + " Now you have " + tasks.size() + " tasks in the list.\n");
    }

    public TaskList() {
        tasks = new ArrayList<>();
        Storage.loadTasksFromFile(tasks);
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (!input.equals("bye")) {
            Parser.handleCommand(input, this);
            input = in.nextLine();
        }
        Storage.saveTasks(tasks);
    }
}

    