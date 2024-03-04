package cody;

import cody.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public String executeCommand(String input) throws CodyException {
        Parser.handleCommand(input, this);
    }

    public boolean isExit() {
        return true;
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

    private void markTask(int index) {
        Ui.printMessage(" Good job! I've marked this task as done:\n"
                + " [" + tasks.get(index).getStatusIcon() + "] " + tasks.get(index).getDescription());
    }

    public void handleMarking(String input) {
        try {
            String[] parts = input.split(" ");
            int index = Integer.parseInt(parts[1]) - 1;
            boolean isDone = input.startsWith("mark");
            tasks.get(index).markTask(isDone);
            markTask(index);
        } catch (NumberFormatException e) {
            Ui.printException(new CodyException(" Task number is invalid. Please enter a valid number"));
        } catch (IndexOutOfBoundsException e) {
            Ui.printException(new CodyException(" Task number is out of range. You have " + tasks.size() + " tasks"));
        }
    }

    public void addTask(String input) {
        try {
            Task task = Parser.createTaskFromInput(input);
            tasks.add(task);
            printTask(task);
        } catch (CodyException e) {
            Ui.printException(e);
        }
    }

    private void printTask(Task task) {
        Ui.printMessage(" Got it. I've added this task:\n"
                + " [" + task.getTaskType() + "] [" + task.getStatusIcon() + "] " + task.getDescription() + "\n"
                + " Now you have " + tasks.size() + " tasks in the list.");
    }

    public void deleteTask(String input) {
        try {
            String[] parts = input.split(" ");
            int index = Integer.parseInt(parts[1]) - 1;
            Task task = tasks.get(index);
            tasks.remove(index);
            printDeleteTask(task);
        } catch (NumberFormatException e) {
            Ui.printException(new CodyException(" Task number is invalid. Please enter a valid number"));
        } catch (IndexOutOfBoundsException e) {
            Ui.printException(new CodyException(" Task number is out of range. You have " + tasks.size() + " tasks"));
        }
    }

    private void printDeleteTask(Task task) {
        Ui.printMessage(" Noted. I've removed this task:\n"
                + " [" + task.getTaskType() + "] [" + task.getStatusIcon() + "] " + task.getDescription() + "\n"
                + " Now you have " + tasks.size() + " tasks in the list.");
    }

    public void findTask(String input) {
        String keyword = input.substring(5).trim();
        String listString = " Here are the matching tasks in your list:\n";
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
            listString = " There are no matching tasks in your list";
        }
        Ui.printMessage(listString);
    }

    public TaskList() {
        tasks = new ArrayList<>();
        Storage.load(tasks);
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (!input.equals("bye")) {
            Parser.handleCommand(input, this);
            input = in.nextLine();
        }
        Storage.save(tasks);
    }
}

    