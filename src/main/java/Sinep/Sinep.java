package Sinep;

import ExceptionHandling.InvalidCommandException;
import ExceptionHandling.InvalidCommandMessageException;
import ExceptionHandling.InvalidTaskIndexException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.io.IOException;


public class Sinep {
    protected static final String FILE_PATH = "src/main/java/Sinep/Sinep.txt";
    static String line = "_____________________________________________________________________";
    static String nl = System.lineSeparator();
    static ArrayList<Task> taskList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        loadTaskFile(taskList);
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
                case "delete":
                    try {
                        deleteTask(input);
                    } catch (InvalidTaskIndexException | InvalidCommandMessageException e) {
                        System.out.println("The delete command is invalid" + nl + line);
                    }
                    break;
                default:
                    try {
                        throw new InvalidCommandException();
                    } catch (InvalidCommandException e) {
                        System.out.println("Please enter a valid command: todo, deadline or event");
                    }
            }
            saveTasks(taskList);
        }
    }

    protected static void loadTaskFile(ArrayList<Task> taskList) throws IOException {
        File taskFile = new File(FILE_PATH);
        if (!taskFile.exists()) {
            try {
                File parentDir = taskFile.getParentFile();
                if (!parentDir.exists() && parentDir.mkdirs()) {
                     // Create the directory if it doesn't exist
                    throw new IOException("Failed to create directory " + parentDir.getAbsolutePath());
                }
                File dataFile = new File(FILE_PATH);
                if (!dataFile.getParentFile().exists()) {
                    Files.createDirectories(dataFile.getParentFile().toPath());
                }
            } catch (IOException e) {
                System.out.println("An error occurred while trying to create the file: " + e.getMessage());
                return; // Exit the method if file creation fails
            }
        }
        try {
            Scanner scanner = new Scanner(taskFile);
            int counter = 0;
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                if (nextLine.startsWith("[T]")) {
                    String description = nextLine.substring(6).trim();
                    taskList.add(new Todo(description));
                    if (nextLine.charAt(4) == 'X') {
                        Task markingTask = taskList.get(counter);
                        markingTask.markAsDone();
                    }
                } else if (nextLine.startsWith("[D]")) {
                    String command = getDeadlineCommand(nextLine);
                    System.out.println(command);
                    taskList.add(new Deadline(command));
                    if (nextLine.charAt(4) == 'X') {
                        Task markingTask = taskList.get(counter);
                        markingTask.markAsDone();
                    }
                } else {
                    String command = getEventCommand(nextLine);
                    taskList.add(new Event(command));
                    if (nextLine.charAt(4) == 'X') {
                        Task markingTask = taskList.get(counter);
                        markingTask.markAsDone();
                    }
                }
                counter++;
            }
        } catch (FileNotFoundException e) {
            File parentDir = taskFile.getParentFile();
            if (!parentDir.exists() && parentDir.mkdirs()) {
                throw new IOException("File not found.");
            }
        }
    }

    private static String getEventCommand(String nextLine) {
        int startDescription = nextLine.indexOf("[E]") + 6;
        int endDescription = nextLine.indexOf(" (from:");
        String description = nextLine.substring(startDescription, endDescription).trim();
        String fromKeyword = "(from: ";
        String toKeyword = " to:";
        int start = nextLine.indexOf(fromKeyword) + fromKeyword.length();
        int end = nextLine.indexOf(toKeyword);
        String fromTime = nextLine.substring(start, end).trim();
        String startKeyword = "to: ";
        int start1 = nextLine.indexOf(startKeyword) + startKeyword.length();
        int end1 = nextLine.lastIndexOf(')');
        String toTime = nextLine.substring(start1, end1).trim();
        String command;
        command = "event " + description + " /from " + fromTime + " /to " + toTime;
        return command;
    }

    private static String getDeadlineCommand(String nextLine) {
        int startDescription = nextLine.indexOf("[D]") + 6;
        int endDescription = nextLine.indexOf(" (by:");
        String description = nextLine.substring(startDescription, endDescription).trim();
        String keyword = "(by: ";
        int start = nextLine.indexOf(keyword) + keyword.length();
        int end = nextLine.lastIndexOf(')');

        String dateTime = nextLine.substring(start, end).trim();
        String command;
        command = "deadline " + description + " /by " + dateTime;
        return command;
    }

    protected static void writeToFile(String textToAdd, boolean isAppend) throws IOException {
        File taskFile = new File(FILE_PATH);
        if (!taskFile.getParentFile().exists() && taskFile.getParentFile().mkdirs()) {
            throw new IOException("File not found."); // This will create the directory if it doesn't exist
        }
        FileWriter fw = new FileWriter(FILE_PATH, isAppend);
        fw.write(textToAdd + System.lineSeparator());
        fw.close();
    }
    protected static void saveTasks(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            try {
                writeToFile("", false);
            } catch (IOException e) {
                System.out.println("Error saving your file. IO issues.");
            }
        } else {
            try {
                writeToFile(taskList.get(0).toString(), false);
            } catch (IOException e) {
                System.out.println("Error saving your file. IO issues.");
            }
            int i = 1;
            while (i < taskList.size()) {
                try {
                    writeToFile(taskList.get(i).toString(), true);
                    i += 1;
                } catch (IOException e) {
                    System.out.println("Error saving your file. IO issues.");
                }
            }
        }
    }

    public static void printGreeting() {
        final String greeting = "Hello! I'm Sinep, your personal chatbot!\n"
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

    public static void deleteTask(String input) throws InvalidTaskIndexException, InvalidCommandMessageException {
        if (Objects.equals(input, "delete")) {
            throw new InvalidCommandMessageException();
        }
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        if (taskIndex >= taskList.size()) {
            throw new InvalidTaskIndexException();
        }
        System.out.println(line);
        System.out.println("Noted. I have removed this task:");
        Task removedTask = taskList.remove(taskIndex);
        System.out.println(removedTask);
        System.out.println("Now you have " + taskList.size() + " tasks left.");
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
