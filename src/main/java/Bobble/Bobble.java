package Bobble;

import Bobble.task.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Bobble {

    private static ArrayList<Task> taskList = new ArrayList<>();
    public static final String LINE_WRAP = "____________________________________________________________\n";

    public static void main(String[] args) {
        start();
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

        while (!userInput.equals("bye")) {
            String[] UserInputs = getCommandAndDesc(userInput);
            String command = UserInputs[0];
            try {
                switch (command) {
                case "list":
                    listResponse();
                    break;
                case "todo":
                    ToDo newToDo = new ToDo(UserInputs[1]);
                    taskList.add(newToDo);
                    addTaskResponse(newToDo);
                    break;
                case "deadline":
                    String[] parts = UserInputs[1].split("/by");
                    Deadline newDeadline = new Deadline(parts[0], parts[1]);
                    taskList.add(newDeadline);
                    addTaskResponse(newDeadline);
                    break;
                case "event":
                    parts = UserInputs[1].split("/from");
                    String[] duration = parts[1].split("/to");
                    Event newEvent = new Event(parts[0], duration[0], duration[1]);
                    taskList.add(newEvent);
                    addTaskResponse(newEvent);
                    break;
                case "mark":
                    int taskNumber = Integer.parseInt(userInput.substring(5)) - 1;
                    markResponse(taskList.get(taskNumber), taskNumber);
                    break;
                case "unmark":
                    taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
                    unmarkResponse(taskList.get(taskNumber), taskNumber);
                    break;
                case "delete":
                    taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
                    deleteResponse(taskList.get(taskNumber), taskNumber);
                    break;
                default:
                    throw new BobbleExceptionCommand();
                }
            } catch (BobbleExceptionCommand e) {
                System.out.println(LINE_WRAP +
                        "OOPS!! I'm sorry, but I don't know what that means :-(\n" +
                        LINE_WRAP);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(LINE_WRAP +
                        "OOPS!! One or more fields of a(n) " + command + " cannot be empty.\n" +
                        LINE_WRAP);
            }
            userInput = input.nextLine();
        }
        goodbye();
    }

    private static void deleteResponse(Task task, int taskNumber) {
        System.out.println(LINE_WRAP + "Noted. I've removed this task:\n" +
                task.toString() + "\nNow you have " + (taskList.size() - 1) +
                " task(s) in the list.\n" + LINE_WRAP);
        taskList.remove(taskNumber);
    }

    private static void listResponse() {
        System.out.println(LINE_WRAP + "Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i).toString());
        }
        System.out.println(LINE_WRAP);
    }

    private static void markResponse(Task task, int taskNumber) {
        taskList.get(taskNumber).setDone(true);
        System.out.println("Nice! I've marked this task as done:\n" +
                task.toString() + "\n" + LINE_WRAP);
    }

    private static void unmarkResponse(Task task, int taskNumber) {
        taskList.get(taskNumber).setDone(false);
        System.out.println("OK, I've marked this task as not done yet:\n" +
                task.toString() + "\n" + LINE_WRAP);
    }

    public static String[] getCommandAndDesc(String input) {
        return input.split(" ", 2);
    }

    public static void addTaskResponse(Task task) {
        System.out.println(LINE_WRAP +
                "Got it. I've added this task: \n  " +
                task.toString() +
                "\nNow you have " + taskList.size() + " task(s) in the list.\n" +
                LINE_WRAP);
    }

    //Greets user
    public static void start() {
        System.out.println(LINE_WRAP +
                "Hello! I'm Bobble\n" +
                "What can I do for you?\n" +
                LINE_WRAP);
    }

    //Exits
    public static void goodbye() {
        System.out.println(LINE_WRAP +
                "Bye. Hope to see you again soon!\n" + LINE_WRAP);
    }

}
