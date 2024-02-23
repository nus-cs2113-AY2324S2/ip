package allez;

import allez.task.Deadline;
import allez.task.Event;
import allez.task.Task;
import allez.task.ToDo;

import java.util.ArrayList;
import java.util.Scanner;


public class Allez {
    protected static ArrayList<Task> tasks = new ArrayList<>();
    protected static  int taskCount = 0;
    protected static Scanner in = new Scanner(System.in);

    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    public static void printList(ArrayList<Task> tasks){
        int count = 0;

        if(tasks.isEmpty()) {
            System.out.println("List is currently empty");
            return;
        }

        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println("\t" + (count+1) + ". " + task.toString());
            count+=1;
        }

    }

    public static void main(String[] args) {
        boolean hasEnded = false;

        printGreeting();
        SaveManager saveManager = new SaveManager("./data/data.txt");
        tasks = saveManager.loadSave();
        taskCount = Task.getNumberOfTasks();

        while(!hasEnded) {
            hasEnded = executeCommands();
        }

        saveManager.writeSave(tasks);
        printExit();
    }

    private static boolean executeCommands() {
        String line = in.nextLine();
        String firstWord = line.split(" ",2)[0];
        printHyphens();

        switch(firstWord){
        case "bye":
            return true;
        case "mark":
            markTask(line);
            break;
        case "todo":
            createTask(line, TaskType.TODO);
            break;
        case "deadline":
            createTask(line, TaskType.DEADLINE);
            break;
        case "event":
            createTask(line, TaskType.EVENT);
            break;
        case "list":
            printList(tasks);
            break;
        case "delete":
            deleteTask(line);
            break;
        default:
            System.out.println("Invalid command. Please try again.");
            break;
        }

        return false;
    }

    private static void createTask(String line, TaskType type) {
        boolean taskCreated;

        switch (type){
        case TODO:
            taskCreated = createToDoTask(line);
            break;
        case DEADLINE:
            taskCreated = createDeadlineTask(line);
            break;
        case EVENT:
            taskCreated = createEventTask(line);
            break;
        default:
            System.out.println("Invalid TaskType occurred.");
            return;
        }

        if(!taskCreated){
            return;
        }

        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + tasks.get(taskCount).toString());
        taskCount +=1;
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    private static boolean createEventTask(String line) {
        String description;
        String to;
        String[] lineSegment;
        String from;
        try {
            lineSegment = verifyEventCommand(line);
            description = lineSegment[0].trim();
            from = lineSegment[1].trim();
            to = lineSegment[2].trim();
            tasks.add(new Event(description, from, to));
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please input event for the task in a format as shown below");
            System.out.println("event <description> /from <start> /to <end>");
        } catch (MissingDetailsException e) {
            System.out.println("Please input something for description, from and to");
        } catch (Exception e) {
            System.out.println("Error encountered");
        }
        return false;
    }

    private static String[] verifyEventCommand(String line) throws MissingDetailsException {
        String[] checkFrom = line.split(" /from ", 2);
        String[] checkTo = checkFrom[1].split(" /to ", 2);
        String[] lineSegment = new String[3];
        lineSegment[0] = checkFrom[0].substring(5);
        lineSegment[1] = checkTo[0];
        lineSegment[2] = checkTo[1];
        if(lineSegment[0].isBlank() || lineSegment[1].isBlank() || lineSegment[2].isBlank()) {
            throw new MissingDetailsException();
        }
        return lineSegment;
    }

    private static boolean createDeadlineTask(String line) {
        String description;
        String[] lineSegment;
        String by;
        try {
            lineSegment = verifyDeadlineCommand(line);
            description = lineSegment[0].trim();
            by = lineSegment[1].trim();
            tasks.add(new Deadline(description,by));
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please input deadline for the task in a format as shown below");
            System.out.println("deadline <description> /by <deadline>");
        } catch (MissingDetailsException e) {
            System.out.println("Please input something for description and deadline");
        } catch (Exception e) {
            System.out.println("Error encountered");
        }
        return false;
    }

    private static String[] verifyDeadlineCommand(String line) throws MissingDetailsException {
        String[] lineSegment = line.substring(9).split(" /by ", 2);
        if (lineSegment[0].isBlank() || lineSegment[1].isBlank()) {
            throw new MissingDetailsException();
        }
        return lineSegment;
    }

    private static boolean createToDoTask(String line) {
        String description;
        description = line.substring(4).trim();
        if (description.isEmpty()) {
            System.out.println("Please add a description of your task");
            return false;
        }
        tasks.add(new ToDo(description));
        return true;
    }

    private static void markTask(String line) {
        int toMark;
        try {
            toMark = Integer.parseInt(line.substring(4).trim()) -1;
            tasks.get(toMark).markDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("\t" + tasks.get(toMark).toString());
        } catch (NumberFormatException e) {
            System.out.println("Please input a number only");
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Please input a number within current number of tasks");
        }
    }

    private static void deleteTask(String line) {
        int toDelete;
        Task temp;
        try {
            toDelete = Integer.parseInt(line.substring(6).trim()) -1;
            temp = tasks.get(toDelete);
            tasks.remove(toDelete);
            System.out.println("Aight. I've removed this task:");
            System.out.println("\t" + temp.toString());
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (NumberFormatException e) {
            System.out.println("Please input a number only");
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Please input a number within current number of tasks");
        }
    }


    private static void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printGreeting() {
        printHyphens();
        System.out.println("Hello! I'm Allez");
        System.out.println("What can I do for you?");
    }

    private static void printHyphens() {
        System.out.println("__________________________________________________");
    }

    private static void loadSavedFile() {

    }
}
