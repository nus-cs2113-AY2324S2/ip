package GermaBot;

import Tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    static final String LINE= "____________________________________________";
    static final String WELCOME_MESSAGE = "Hei...! GermaBot here! \n"
            + "Let me load your saved To Do List first...";
    static final String GOODBYE_MESSAGE = "Thanks for using me! Hope to see you again soon~!";
    static final String LOAD_COMPLETE = "Loading complete!";

    private static final Scanner scanner = new Scanner(System.in);;

    public UI() {
    }

    public String getInput() {
        return scanner.nextLine();
    }

    public void printWelcomeMessage() {
        System.out.println(LINE);
        System.out.println(WELCOME_MESSAGE);
    }

    public void printLoadComplete() {

        System.out.println(LOAD_COMPLETE);
    }

    public void printFileNotFoundException() {
        System.out.println("Uh oh, there is no saved file yet! Please create a GermaBotsData.txt " +
                "file under './src/data/GermaBotData'!!\n"
                + "Because there is no saved file found, I will start with an empty list!");
    }

    public void printFileReadException() {
        System.out.println("Uh oh, there seems to be an issue with the save file...\n" +
                "Because there is an error with the save file, I will start with an empty list!");
    }

    public static void printLoadFileException() {
        System.out.println("Oh no, There was an error loading your save file! Please check" +
                " to make sure it follows the format...");
    }

    public static void printPostLoadingMessage() {
        System.out.println("What may I do for you this fine day?");
        System.out.println(LINE);
    }

    public void printByeMessage() {
        System.out.println(LINE);
        System.out.println(GOODBYE_MESSAGE);
        System.out.println(LINE);
    }

    public static void printListEmptyMessage() {
        System.out.println("Umm... You haven't added any Tasks yet... Let's try adding " +
                "some now!");
    }

    public static void printMarkUndone(int idx, ArrayList<Task> toDoList) {
        System.out.println("Aww, not done? Okay, I'll mark this task as undone: "
                + "[" + toDoList.get(idx).getStatusIcon() + "] " + toDoList.get(idx).getDescription());
    }

    public static void printMarkDone(int idx, ArrayList<Task> toDoList) {
        System.out.println("Good job! I'll mark this task as done: "
                + "[" + toDoList.get(idx).getStatusIcon() + "] " + toDoList.get(idx).getDescription());
    }

    public void printUnknownInputException(String input) {
        System.out.println("Uhh.. I'm sorry but I'm not quite sure what in the world '" + input + "' means..." +
                " Remember to include the Task' Type in front of the input!!");
    }

    public static void printTaskList(ArrayList<Task> toDoList) {
        System.out.println("Gotcha! Here are your tasks:");
        for (int i = 0; i < toDoList.size(); i++) {
            System.out.println(i + 1 + ". " + toDoList.get(i));
        }
    }

    public static void printIOException() {
        System.out.println("Uh oh, I could not save that to the file...");
    }

    public static void printEmptyTaskException() {
        System.out.println("Uh oh, you did not specify a task to add! Let's try again!");
    }

    public static void printMissingStartDateException() {
        System.out.println("Uh oh, you did not specify a start time! Let's try again!");
    }

    public static void printMissingDeadlineException() {
        System.out.println("Uh oh, you did not specify the deadline! Let's try again!");
    }

    public static void printTaskNotFoundException(int idx) {
        System.out.println("Sorry but... There is no task number " + (idx + 1) + "... " +
                "Let's try again!");
    }

    public static void deleteTask(ArrayList<Task> toDoList, int idxToDelete) {
        System.out.println("Okay! I've removed this task from your To Do List:");
        System.out.println(toDoList.get(idxToDelete));
        toDoList.remove(idxToDelete);
        System.out.println("Now you have, let me count... " + toDoList.size() + " items left in your " +
                "To Do List!");
    }

    public static void printCreateEventMessage(String toDoTask, String startDate, String endDate) {
        System.out.println("Gotcha! Added '" + toDoTask + "' to your To Do List!" +
                " This will happen from " + startDate + " to " + endDate + ", so please remember to mark it" +
                " on your calender! (Or ask me!)");
    }

    public static void printCreateDeadlineMessage(String toDoTask, String date) {
        System.out.println("Gotcha! Added '" + toDoTask + "' to your To Do List!" +
                " You have to finish this by "  + date + ", so be reminded!");
    }
}


