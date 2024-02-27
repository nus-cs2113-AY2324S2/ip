package command;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    public static List<Task> taskList = new ArrayList<>();

    public static void storeData() {
        try {
            Database.storeData(taskList);
        } catch (IOException e) {
            System.out.println("Error storing data.");
        }
    }

    public static void readData() {
        try {
            Database.readData(taskList);
            System.out.println("Read in previous " + taskList.size() + " entries.");

        } catch (FileNotFoundException e) {
            System.out.println("No previous data found");
        }
    }

    public static void printLists() {

        for (int i = 1; i <= taskList.size(); i += 1) {
            System.out.print(i + ".");
            System.out.println(taskList.get(i - 1));
        }
    }

    public static void markTask(int taskID) {
        try {

            taskList.get(taskID - 1).markCompleted();
            System.out.println("Marking as done:");
            System.out.println(taskList.get(taskID - 1));

        } catch (IndexOutOfBoundsException e) {

            System.out.println("Invalid Index");

        }
    }

    public static void unmarkTask(int taskID) {
        try {

            taskList.get(taskID - 1).markUncompleted();
            System.out.println("Marking as undone:");
            System.out.println(taskList.get(taskID - 1));

        } catch (IndexOutOfBoundsException e) {

            System.out.println("Invalid index");

        }
    }

    public static void addTodo(String input) {

        taskList.add(new ToDo(input.trim()));
        System.out.println("Added ToDo: " + input);

    }

    public static void addDeadline(String input) {
        try {

            taskList.add(new Deadline(input));
            System.out.println("Added Deadline: " + taskList.get(taskList.size() - 1).getName());

        } catch (JohnException e) {

            System.out.println("Invald input");

        }
    }

    public static void addEvent(String input) {
        try {

            taskList.add(new Event(input));
            System.out.println("Added Event: " + taskList.get(taskList.size() - 1).getName());

        } catch (JohnException e) {

            System.out.println("Invalid input");

        }
    }

    public static void deleteTask(int taskId) {

        try {
            System.out.println("Removing Task: ");
            System.out.println(taskList.get(taskId - 1));
            taskList.remove(taskId - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid range");
        }
    }

    public static void findTasks(String query) throws JohnException {

        if (query.isBlank()) {
            throw new JohnException();
        }

        int count = 0;

        for (int i = 1; i <= taskList.size(); i += 1) {
            if (taskList.get(i - 1).getName().contains(query)) {
                System.out.println(i + "." + taskList.get(i - 1));
                count += 1;
            }
        }

        if (count == 0) {
            System.out.println("No Tasks Found");
        } else {
            System.out.println("Search concluded.");
        }
    }

}
