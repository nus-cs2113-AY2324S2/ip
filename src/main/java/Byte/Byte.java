package Byte;

import Byte.exception.ByteException;
import Byte.task.Deadline;
import Byte.task.Event;
import Byte.task.Task;
import Byte.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Byte {
    private static final int MAX_TASKS = 100;
    private static final Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;
    private static boolean tasksChanged = false;
    private static final String FILE_PATH = "./src/main/java/Byte/byte.txt";


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        runByte(scanner);
        if (tasksChanged){
            try{
                    saveTasksToFile();
            }catch (IOException e){
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        scanner.close();
    }

    public static void runByte(Scanner scanner){
        try{
            loadTasksFromFile(FILE_PATH);
        }catch (FileNotFoundException e){
            System.out.println("File not found. Starting with an empty task list.");
        }
        printWelcomeMessage();
        while(true){
            try{
                String userInput = scanner.nextLine();
                printLineSeparator();
                if (processUserInput(userInput)) {
                    break;
                }
            } catch (ByteException e){
                System.out.println(e.getMessage());
                printLineSeparator();
            }
        }
    }

    public static boolean processUserInput(String userInput) throws ByteException {
        if (userInput.trim().isEmpty()) {
            throw new ByteException("Hmm, it seems like you didn't enter any command. Please try again.");
        } else if (userInput.equals("bye")) {
            printGoodbyeMessage();
            return true;
        } else if (userInput.startsWith("mark ")) {
            markTask(userInput.substring("mark ".length()), true);
        } else if (userInput.startsWith("unmark ")) {
            markTask(userInput.substring("unmark ".length()), false);
        } else if (userInput.equals("list")) {
            listTasks();
        } else if (userInput.startsWith("todo ")) {
            handleToDoCommand(userInput);
        } else if (userInput.startsWith("deadline ")) {
            handleDeadlineCommand(userInput);
        } else if (userInput.startsWith("event ")) {
            handleEventCommand(userInput);
        } else {
            throw new ByteException("I'm sorry, but I don't know what that means.");
        }
        return false;
    }

    private static void handleToDoCommand(String userInput) throws ByteException {
        String description = userInput.substring("todo ".length()).trim();
        if (description.isEmpty()){
            throw new ByteException("The description of a todo cannot be empty.");
        }
        addTask(new ToDo(userInput.substring("todo ".length())));
    }

    private static void handleDeadlineCommand(String userInput) throws ByteException{
        String trimmedInput = userInput.trim();
        if (!trimmedInput.contains(" /by ")) {
            throw new ByteException("Oops! The deadline command is missing a '/by' to specify the deadline.");
        }
        String[] deadlineDetails = trimmedInput.split(" /by ", 2);
        String description = deadlineDetails[0].substring("deadline ".length()).trim();
        String deadline = deadlineDetails.length > 1 ? deadlineDetails[1].trim() : "";
        if (description.isEmpty()) {
            throw new ByteException("The description of a deadline cannot be empty.");
        }
        if (deadline.isEmpty()) {
            throw new ByteException("The deadline date cannot be empty.");
        }
        addTask(new Deadline(description, deadline));
    }

    private static void handleEventCommand(String userInput) throws ByteException {
        String trimmedInput = userInput.trim();
        if (!trimmedInput.contains(" /from ") || !trimmedInput.contains(" /to ")) {
            throw new ByteException("Oops! The event command is missing a '/from' and '/to' to specify the event timing.");
        }
        String descriptionPart = trimmedInput.split(" /from ", 2)[0];
        String timingPart = trimmedInput.split(" /from ", 2)[1];
        String description = descriptionPart.substring("event ".length()).trim();
        String[] timingDetails = timingPart.split(" /to ", 2);
        String startTime = timingDetails.length > 0 ? timingDetails[0].trim() : "";
        String endTime = timingDetails.length > 1 ? timingDetails[1].trim() : "";
        if (description.isEmpty()) {
            throw new ByteException("The description of an event cannot be empty.");
        }
        if (startTime.isEmpty() || endTime.isEmpty()) {
            throw new ByteException("Both the start time and end time for the event must be specified.");
        }
        addTask(new Event(description, startTime, endTime));
    }

    private static void printWelcomeMessage() {
        printLineSeparator();
        System.out.println("Hello! I'm Byte, your friendly chat assistant!");
        System.out.println("What can I do for you?");
        printLineSeparator();
    }

    private static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        printLineSeparator();
    }

    private static void addTask(Task task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount++] = task;
            System.out.println("Got it. I've added this task:\n " + task);
            System.out.println("Now you have " + taskCount + " tasks in the list.");
            tasksChanged = true;
        } else {
            System.out.println("Sorry, I can only handle up to " + MAX_TASKS + " tasks!");
        }
        printLineSeparator();
    }

    private static void listTasks() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        printLineSeparator();
    }

    private static void markTask(String taskNumberStr, boolean isDone) {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(taskNumberStr)-1;
            Task task = tasks[taskNumber];
            if (isDone) {
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:\n  " + tasks[taskNumber]);
            } else {
                task.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:\n  " + tasks[taskNumber]);
            }
            tasksChanged = true;
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        printLineSeparator();
    }

    private static void printLineSeparator() {
        System.out.println("____________________________________________________________");
    }

    private static void loadTasksFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            processTaskFromLine(line);
        }
    }

    private static void processTaskFromLine(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        String description = parts[2];
        switch (type) {
            case "T":
                addTask(new ToDo(description));
                break;
            case "D":
                String by = parts[3];
                addTask(new Deadline(description, by));
                break;
            case "E":
                String startTime = parts[3];
                String endTime = parts[4];
                addTask(new Event(description, startTime, endTime));
                break;
            default:
                break;
        }
    }

    private static void saveTasksToFile() throws IOException {
        FileWriter writeToFile = new FileWriter(FILE_PATH);
        for (int i=0; i<taskCount; i++){
            Task task = tasks[i];
            writeToFile.write(task.toFileString() + "\n");
        }
        writeToFile.close();
        tasksChanged = false;
    }
}


