package Xavier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

import Exceptions.InvalidInputException;
import Exceptions.NoInputException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.util.ArrayList;

public class TaskManager {
    public static final int MAX_ENTRIES = 100;
    ArrayList<Task> itemList = new ArrayList<>();

    public static final String FILEPATH = "/Users/jasonlienardi/Documents/CS2113/ip/src/main/java/Xavier/toDoList.txt";

    public void handleCommand(String command) {
        if (command.equals("list")) {
            printList();
        } else if (command.startsWith("mark") || command.startsWith(("unmark"))) {
            handleMarkAsDone(command);
        } else if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
            printOutput(command);
        } else if (command.startsWith("delete")) {
            deleteTask(command);
        } else {
            printErrorMessage();
        }
    }

    private void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < itemList.size(); i++) {
            System.out.println(i + 1 + "." + itemList.get(i).toString());
        }
    }

    public void handleMarkAsDone(String command) {
        String[] stringArray = command.split(" ");
        int index = Integer.parseInt(stringArray[1]) - 1;
        if (command.startsWith("mark")) {
            itemList.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
        } else if (command.startsWith("unmark")) {
            itemList.get(index).markAsNotDone();
            System.out.println("OK, I've marked this task as not yet done:");
        }
        System.out.println(itemList.get(index).toString());
        saveFile(FILEPATH);
    }

    public void handleAddTask(String command) throws InvalidInputException, NoInputException {
        String[] stringArray = command.split(" ");
        if (stringArray.length == 1) {
            throw new NoInputException();
        }

        if (command.startsWith("todo")){
            int toDoStartIndex = 4;
            String toDoString = command.substring(toDoStartIndex);
            Todo toDo = new Todo(toDoString);
            itemList.add(toDo);
        }
        else if (command.startsWith("deadline")){
            if (!command.contains("/by")) {
                throw new InvalidInputException();
            }
            int deadlineIndex = command.indexOf("/by");
            String description = command.substring(8, deadlineIndex);
            String by = command.substring(deadlineIndex + 4);
            Deadline deadline = new Deadline(description, by);
            itemList.add(deadline);
        }
        else if (command.startsWith("event")){
            if (!command.contains("/from") || !command.contains("/to")) {
                throw new InvalidInputException();
            }
            int startIndex = command.indexOf("/from");
            int endIndex = command.indexOf("/to");
            String description = command.substring(5, startIndex);
            String start = command.substring(startIndex + 6, endIndex);
            String end = command.substring(endIndex + 4);
            Event event = new Event(description, start, end);
            itemList.add(event);
        }
        saveFile(FILEPATH);
    }

    public void deleteTask(String command) {
        String[] stringArray = command.split(" ");
        int index = Integer.parseInt(stringArray[1]) - 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println(itemList.get(index).toString());
        itemList.remove(index);
        System.out.println("Now you have " + itemList.size() + " tasks in the list.");
        saveFile(FILEPATH);
    }

    private void printOutput(String command) {
        String[] stringArray = command.split(" ");
        try {
            handleAddTask(command);
            System.out.println("Got it. I've added this task:");
            System.out.println(itemList.get(itemList.size()-1).toString());
            System.out.println("Now you have " + itemList.size() + " tasks in the list.");
        } catch (NoInputException e) {
            System.out.println("OOPS!!! The description of a " + stringArray[0] + " cannot be empty.");
        } catch (InvalidInputException e) {
            if (stringArray[0].equals("deadline")) {
                System.out.println("OOPS!!! " + stringArray[0] + " must contains /by");
            } else if (stringArray[0].equals("event")) {
                System.out.println("OOPS!!! " + stringArray[0] + " must contains /from and /to");
            }
        }
    }

    private void printErrorMessage() {
        System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
    public static void createOrCheckFile(String fileName) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                boolean created = file.createNewFile();
                if (created) {
                    System.out.println("File created: " + fileName);
                }
            }
        } catch (IOException e) {
            System.err.println("Error creating or checking file: " + e.getMessage());
        }
    }

    //writing files
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private void saveFile(String file) {
        createOrCheckFile(FILEPATH);
        try {
            for (int i = 0; i < itemList.size(); i++) {
                writeToFile(file, itemList.get(i).toString() );
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    //reading files
    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    private static void readFile(String file) {
        try {
            printFileContents(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
