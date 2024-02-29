package Byte.ui;

import Byte.task.TaskList;


public class Ui {
    public void printWelcomeMessage() {
        printLineSeparator();
        System.out.println("Hello! I'm Byte, your friendly chat assistant!");
        System.out.println("What can I do for you?");
        printLineSeparator();
    }

    public void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        printLineSeparator();
    }

    public void printTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
    }

    public void printLineSeparator() {
        System.out.println("____________________________________________________________");
    }

    public void printResponse(String response) {
        printLineSeparator();
        System.out.println(response);
    }

    public void printError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    public void printLoadingError() {
        System.out.println("File not found. Starting with an empty task list.");
    }

}
