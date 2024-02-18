package main;

import exceptions.AragornException;
import commands.inputParser;
import tasks.*;
import java.util.Scanner;

public class Aragorn {

    private static final String LINE =  "    __________________________________________________________\n";
    private static final String GREET = "    Hello! I am main.Aragorn son of Arathorn, and am called Elessar, the Elfstone, Dúnadan,\n" +
            "    the heir of Isildur Elendil's son of Gondor.\n" +
            "    What can I do for you?\n";
    private static final String EXIT = "    Bye. Hope to see you again soon!\n";
    private static final String TAB = "    ";

    private static final String commandList = "    Here is a list of commands:\n" +
            "\n" +
            "    \"list\": Displays list of tasks.\n" +
            "\n" +
            "    \"todo <description>\": Adds a Todo task to the list.\n" +
            "\n" +
            "    \"deadline <description> /by <deadline>\": Adds a task and its deadline to the list.\n" +
            "\n" +
            "    \"event <description> /from <start> /to <end>\": Adds an event and its start and end conditions to the list.\n" +
            "\n" +
            "    \"mark <task number>\": Marks the corresponding task in the list as completed.\n" +
            "\n" +
            "    \"unmark <task number>\": Marks the corresponding task in the list as incomplete.\n" +
            "\n" +
            "    \"/help\": Displays this list of commands.\n" +
            "\n" +
            "    \"bye\": Closes the program.\n";

    public static void main(String[] args) throws AragornException {
        Task[] list = new Task[100];
        int listLength = 0;
        int remainingTasks = 0;
        System.out.println(LINE + GREET + LINE);
        int index;
        String icon;
        Scanner in = new Scanner(System.in);
        while(true) {
            String userInput = in.nextLine();
            String commandType = inputParser.commandIdentifier(userInput);
            try {
                inputParser input = new inputParser(userInput.trim(), commandType);


                switch (commandType) {
                    case "LIST":
                        if (listLength == 0) {
                            System.out.println(LINE + "    List is empty. Add tasks to view them here.\n" + LINE);
                            break;
                        }
                        System.out.println(LINE);
                        System.out.println("    Here are the tasks in your list: ");
                        for (int i = 0; i < listLength; i += 1) {
                            System.out.println(TAB + (i + 1) + ". " + list[i].taskString());
                        }
                        System.out.println("\n");
                        printRemainingTasks(remainingTasks);
                        break;

                    case "UNMARK":
                        try {
                            index = Integer.parseInt(input.getSplitInput()[0]);
                            icon = list[index].getStatusIcon();
                            if (icon.equals(" ")) {
                                System.out.println(LINE + "    This task has already been unmarked.\n" + LINE);
                                break;
                            }
                            list[index].markAsUndone();
                            remainingTasks += 1;
                            System.out.println(LINE + TAB + "OK, I've marked this task as incomplete:\n" + TAB +
                                    "   " + list[index].taskString() + "\n");
                            printRemainingTasks(remainingTasks);
                        } catch (NullPointerException e) {
                            System.out.println(LINE + "    Task index is not in the list\n" + LINE);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println(LINE + "    Invalid tasks.Task\n" + LINE);
                        }
                        break;

                    case "MARK":
                        try {
                            index = Integer.parseInt(input.getSplitInput()[0]);
                            icon = list[index].getStatusIcon();
                            if (icon.equals("X")) {
                                System.out.println(LINE + "    This task has already been marked.\n" + LINE);
                                break;
                            }
                            list[index].markAsDone();
                            remainingTasks -= 1;
                            System.out.println(LINE + TAB + "Nice! I've marked this task as done:\n" + TAB +
                                    "   " + list[index].taskString() + "\n");
                            printRemainingTasks(remainingTasks);
                        } catch (NullPointerException e) {
                            System.out.println(LINE + "    Task index is not in the list\n" + LINE);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println(LINE + "    Invalid tasks.Task\n" + LINE);
                        }
                        break;

                    case "TODO":
                        if (input.getSplitInput()[0].trim().isEmpty()) {
                            break;
                        }
                        list[listLength] = new ToDo(input.getSplitInput()[0]);
                        printAddTask(list[listLength]);
                        listLength += 1;
                        remainingTasks += 1;
                        printRemainingTasks(remainingTasks);
                        break;

                    case "DEADLINE":
                        try {
                            if (input.getSplitInput()[0].isEmpty() || input.getSplitInput()[1].isEmpty()) {
                                break;
                            }
                            list[listLength] = new Deadline(input.getSplitInput()[0], input.getSplitInput()[1]);
                            printAddTask(list[listLength]);
                            listLength += 1;
                            remainingTasks += 1;
                            printRemainingTasks(remainingTasks);
                        } catch (NullPointerException e) {
                            break;
                        }
                        break;

                    case "EVENT":
                        try {
                            if (input.getSplitInput()[0].isEmpty() || input.getSplitInput()[1].isEmpty() || input.getSplitInput()[2].isEmpty()) {
                                break;
                            }
                            list[listLength] = new Event(input.getSplitInput()[0], input.getSplitInput()[1], input.getSplitInput()[2]);
                            printAddTask(list[listLength]);
                            listLength += 1;
                            remainingTasks += 1;
                            printRemainingTasks(remainingTasks);
                        } catch (NullPointerException e) {
                            break;
                        }
                        break;

                    case "HELP":
                        System.out.println(LINE + commandList + LINE);
                        break;

                    case "INVALID":
                        if (userInput.trim().isEmpty()) {
                            System.out.println(LINE + "    Input is empty. Use \"/help\" command to view the list of commands\n" + LINE);
                        } else {
                            System.out.println(LINE + "    Your input is invalid. Use the \"/help\" command to view the list of commands.\n" + LINE);
                        }
                        break;

                    case "BYE":
                        System.out.println(LINE + TAB + EXIT + LINE);
                        return;
                }
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                System.out.println(LINE + "    Invalid task index format\n" + LINE);
            }
        }
    }

    private static void printAddTask(Task list) {
        System.out.println(LINE + "    Got it. I've added this task:");
        System.out.println(TAB + list.taskString() + "\n");
    }

    private static void printRemainingTasks(int remainingTasks) {
        System.out.println("    You have " + remainingTasks + " remaining tasks in the list.\n" + LINE);
    }

}
