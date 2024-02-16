package Sinep;

import ExceptionHandling.InvalidCommandException;
import ExceptionHandling.InvalidCommandMessageException;
import ExceptionHandling.InvalidTaskIndexException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class Sinep {
    static String line = "_____________________________________________________________________";
    static String nl = System.lineSeparator();
    static List<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printGreeting();
        while (true) {
            String input = scanner.nextLine();
            String command = input.split(" ", 2)[0]; // Get the first word of the input as the command

            switch (command) {
                case "bye":
                    printBye();
                    scanner.close();
                    return;
                case "list":
                    printList();
                    break;
                case "mark":
                    try {
                        markList(input);
                    } catch (InvalidTaskIndexException e){
                        System.out.println("The task you are trying to edit does not exist!" + nl + line);
                    }
                    break;
                case "unmark":
                    try {
                        unmarkList(input);
                    } catch (InvalidTaskIndexException e){
                        System.out.println("The task you are trying to edit does not exist!" + nl + line);
                    }
                    break;
                case "todo":
                    try {
                        addTodo(input);
                    } catch (InvalidCommandMessageException e) {
                        System.out.println("The todo command message is invalid" + nl + line);
                    }
                    break;
                case "deadline":
                    try {
                        addDeadline(input);
                    } catch (InvalidCommandMessageException e) {
                        System.out.println("The deadline command is invalid" + nl +line);
                    }
                    break;
                case "event":
                    try {
                        addEvent(input);
                    } catch (InvalidCommandMessageException e) {
                        System.out.println("The event command is invalid" + nl + line);
                    }
                    break;
                default:
                    try {
                        throw new InvalidCommandException();
                    } catch (InvalidCommandException e) {
                        System.out.println("Please enter a valid command: todo, deadline or event");
                    }
            }
        }
    }
    public static void printGreeting() {
        final String greeting = "Hello! I'm Sinep.Sinep, your personal chatbot!\n"
                + "What can I do for you today?";
        System.out.println(line + nl + greeting + nl + line);
    }
    public static void printBye() {
        final String bye = "Bye. Hope to see you again soon!";
        System.out.println(line + nl + bye + nl + line);

    }

    public static void printList() {
        System.out.println(line);
        System.out.println("Here are the current tasks in your list:");
        if (taskList.isEmpty()) {
            System.out.println("Great job! You have no tasks!");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + "." + taskList.get(i));
            }
        }
        System.out.println(line);
    }

    public static void markList(String input) throws InvalidTaskIndexException {
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        if (taskIndex >= taskList.size()) {
            throw new InvalidTaskIndexException();
        }
        Task markingTask = taskList.get(taskIndex);
        markingTask.markAsDone();
        System.out.println(line + nl + "Got it! Sinep.Task " + (taskIndex + 1) + " marked as done:");
        System.out.println(markingTask.getStatusIcon() + " " + markingTask.description + nl + line);
    }

    public static void unmarkList(String input) throws InvalidTaskIndexException {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        if (taskIndex >= taskList.size()) {
            throw new InvalidTaskIndexException();
        }
        Task markingTask = taskList.get(taskIndex);
        markingTask.unmarkAsDone();
        System.out.println(line + nl + "Got it! Sinep.Task " + (taskIndex + 1) + " marked as undone:");
        System.out.println(markingTask.getStatusIcon() + " " + markingTask.description + nl + line);
    }


    public static void addTodo(String input) throws InvalidCommandMessageException {
        if (Objects.equals(input, "todo")) {
            throw new InvalidCommandMessageException();
        }
        String actualDescription = input.replace("todo ", "");
        Todo newTodo = new Todo(actualDescription);
        taskList.add(newTodo);
        System.out.println(line + nl + "Added to task list:" + nl + newTodo);
        System.out.println("Now you have " + taskList.size() + " tasks in the list." + nl + line);
    }

    public static void addDeadline(String input) throws InvalidCommandMessageException{
        boolean containsDeadline = input.contains("/by");
        if (Objects.equals(input, "deadline") || !containsDeadline) {
            throw new InvalidCommandMessageException();
        }
        String actualDescription = input.replace("deadline ", "");
        String[] inputParts = actualDescription.split("/by", 2);
        if (Objects.equals(inputParts[0], "") || Objects.equals(inputParts[1], "")) {
            throw new InvalidCommandMessageException();
        }
        Deadline newDeadline = new Deadline(actualDescription);
        taskList.add(newDeadline);
        String descriptionToPrint = newDeadline.toString();
        System.out.println(line + nl + "Added to task list:" + nl + descriptionToPrint);
        System.out.println("Now you have " + taskList.size() + " tasks in the list." + nl + line);
    }

    public static void addEvent(String input) throws InvalidCommandMessageException{
        boolean containStart = input.contains("/from");
        boolean containEnd = input.contains("/to");
        if (Objects.equals(input, "event") || !containStart || !containEnd) {
            throw new InvalidCommandMessageException();
        }
        String actualDescription = input.replace("event ", "");
        String[] inputParts = actualDescription.split("/from|/to", 3);
        if (Objects.equals(inputParts[0], "") || Objects.equals(inputParts[1], "") || Objects.equals(inputParts[2], "")) {
            throw new InvalidCommandMessageException();
        }
        Event newEvent = new Event(actualDescription);
        taskList.add(newEvent);
        String descriptionToPrint = newEvent.toString();
        System.out.println(line + nl + "Added to task list:" + nl + descriptionToPrint);
        System.out.println("Now you have " + taskList.size() + " tasks in the list." + nl + line);
    }

}
