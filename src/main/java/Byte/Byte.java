package Byte;

import Byte.exception.ByteException;
import Byte.task.Deadline;
import Byte.task.Event;
import Byte.task.Task;
import Byte.task.ToDo;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class Byte {
    private static final int MAX_TASKS = 100;
    private static final List<Task> tasksList = new ArrayList<>();
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        runByte(scanner);
        scanner.close();
    }

    public static void runByte(Scanner scanner){
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
            tasksList.add(task);
            taskCount++;
            System.out.println("Got it. I've added this task:\n " + task);
            System.out.println("Now you have " + taskCount + " tasks in the list.");
        } else {
            System.out.println("Sorry, I can only handle up to " + MAX_TASKS + " tasks!");
        }
        printLineSeparator();
    }

    private static void listTasks() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasksList.get(i));
        }
        printLineSeparator();
    }

    private static void markTask(String taskNumberStr, boolean isDone) {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(taskNumberStr)-1;
            Task task = tasksList.get(taskNumber);
            if (isDone) {
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:\n  " + task);
            } else {
                task.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:\n  " + task);
            }
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
}


