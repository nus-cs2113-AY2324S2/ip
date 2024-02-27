package main;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import exceptions.AragornException;
import utilities.CommandIdentifier;
import ui.Constants;
import utilities.FileHandler;
import utilities.InputParser;
import tasks.Task;
import tasks.ToDo;
import tasks.Deadline;
import tasks.Event;
import java.util.Scanner;

public class Aragorn {

    private static final ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) throws AragornException {
        System.out.println(Constants.HELLOMESSAGE);
        try {
            readFile();
        } catch (IOException e) {
            System.out.println(Constants.FILEREADERROR);
        }
        int index;
        String icon;
        Scanner in = new Scanner(System.in);
        while(true) {
            String userInput;
            userInput = in.nextLine();
            String commandType = CommandIdentifier.commandIdentifier(userInput);
            try {
                InputParser input = new InputParser(userInput.trim(), commandType);
                int remainingTasks = 0;
                for (Task i : list) {
                    if (i.getStatusIcon().equals(Constants.INCOMPLETE)) {
                        remainingTasks += 1;
                    }
                }

                switch (commandType) {
                    case Constants.LIST:
                        listCommand(remainingTasks);
                        break;

                    case Constants.UNMARK:
                        try {
                            index = Integer.parseInt(input.getSplitInput()[0]);
                            icon = list.get(index).getStatusIcon();
                            if (icon.equals(Constants.INCOMPLETE)) {
                                System.out.println(Constants.ALREADYUNMARKED);
                                break;
                            }
                            list.get(index).markAsUndone();
                            remainingTasks += 1;
                            System.out.println(Constants.UNMARKTASK + list.get(index).taskString() + Constants.NEWLINE);
                            printRemainingTasks(remainingTasks);
                        } catch (NullPointerException e) {
                            System.out.println(Constants.INVALIDINDEX);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println(Constants.INVALIDTASK);
                        }
                        break;

                    case Constants.MARK:
                        try {
                            index = Integer.parseInt(input.getSplitInput()[0]);
                            icon = list.get(index).getStatusIcon();
                            if (icon.equals(Constants.COMPLETE)) {
                                System.out.println(Constants.ALREADYMARKED);
                                break;
                            }
                            list.get(index).markAsDone();
                            remainingTasks -= 1;
                            System.out.println(Constants.MARKTASK + list.get(index).taskString() + Constants.NEWLINE);
                            printRemainingTasks(remainingTasks);
                        } catch (NullPointerException e) {
                            System.out.println(Constants.INVALIDINDEX);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println(Constants.INVALIDTASK);
                        }
                        break;

                    case Constants.DELETE:
                        try {
                            index = Integer.parseInt(input.getSplitInput()[0]);
                            icon = list.get(index).getStatusIcon();
                            if (icon.equals(Constants.INCOMPLETE)) {
                                remainingTasks -= 1;
                            }
                            System.out.println(Constants.DELETETASK + list.get(index).taskString() + Constants.NEWLINE);
                            list.remove(index);
                            printRemainingTasks(remainingTasks);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println(Constants.INVALIDTASK);
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(Constants.INVALIDINDEX);
                        }
                        break;

                    case Constants.TODO:
                        todoCommand(input, remainingTasks);
                        break;

                    case Constants.DEADLINE:
                        deadlineCommand(input, remainingTasks);
                        break;

                    case Constants.EVENT:
                        eventCommand(input, remainingTasks);
                        break;

                    case Constants.HELP:
                        System.out.println(Constants.HELPMESSAGE);
                        break;

                    case Constants.INVALID:
                        if (userInput.trim().isEmpty()) {
                            System.out.println(Constants.EMPTYINPUT);
                        } else {
                            System.out.println(Constants.INVALIDINPUT);
                        }
                        break;

                    case Constants.BYE:
                        System.out.println(Constants.BYEMESSAGE);
                        writeFile();
                        return;
                }
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                System.out.println(Constants.INVALIDINDEXFORMAT);
            }
            writeFile();
        }
    }

    private static void eventCommand(InputParser input, int remainingTasks) {
        try {
            if (input.getSplitInput()[0].isEmpty() || input.getSplitInput()[1].isEmpty() || input.getSplitInput()[2].isEmpty()) {
                return;
            }
            Event newEvent = new Event(input.getSplitInput()[0].trim(), false, input.getSplitInput()[1].trim(), input.getSplitInput()[2].trim());
            list.add(newEvent);
            printAddTask(newEvent);
            remainingTasks += 1;
            printRemainingTasks(remainingTasks);
        } catch (NullPointerException e) {
            return;
        }
    }

    private static void deadlineCommand(InputParser input, int remainingTasks) {
        try {
            if (input.getSplitInput()[0].isEmpty() || input.getSplitInput()[1].isEmpty()) {
                return;
            }
            Deadline newDeadline = new Deadline(input.getSplitInput()[0].trim(), false, input.getSplitInput()[1].trim());
            list.add(newDeadline);
            printAddTask(newDeadline);
            remainingTasks += 1;
            printRemainingTasks(remainingTasks);
        } catch (NullPointerException e) {
            return;
        }
    }

    private static void todoCommand(InputParser input, int remainingTasks) {
        if (input.getSplitInput()[0].trim().isEmpty()) {
            return;
        }
        ToDo newTask = new ToDo(input.getSplitInput()[0].trim(), false);
        list.add(newTask);
        printAddTask(newTask);
        remainingTasks += 1;
        printRemainingTasks(remainingTasks);
    }

    private static void listCommand(int remainingTasks) {
        if (list.isEmpty()) {
            System.out.println(Constants.EMPTYLIST);
            return;
        }
        System.out.println(Constants.LINE);
        System.out.println(Constants.CURRENTLIST);
        for (int i = 0; i < list.size(); i += 1) {
            System.out.println(Constants.TAB + (i + 1) + Constants.DOT + list.get(i).taskString());
        }
        System.out.println(Constants.NEWLINE);
        printRemainingTasks(remainingTasks);
    }

    private static void readFile() throws IOException {
        try {
            List<String> entries = FileHandler.readFile(Constants.FILEPATH);
            list.clear();

            for (String entry : entries) {
                Task task = formatEntry(entry);
                list.add(task);
            }

            if (list.isEmpty()) {
                return;
            }

            System.out.println(Constants.FILEREADLIST);
            for (int i = 0; i < list.size(); i += 1) {
                System.out.println(Constants.TAB + (i + 1) + Constants.DOT + list.get(i).taskString());
            }
         } catch (IOException e) {
            System.out.println(Constants.NOFILE);
        }
    }

    private static Task formatEntry(String entry) {
        Task newTask;
        try {
            String[] formattedEntry = entry.split(Constants.BAR);
            String taskType = formattedEntry[0].toUpperCase();
            boolean done = formattedEntry[1].equals(Constants.ONE);
            switch (taskType) {
                case Constants.TODO:
                    newTask = new ToDo(formattedEntry[2], done);

                    break;

                case Constants.DEADLINE:
                    newTask = new Deadline(formattedEntry[2], done, formattedEntry[3]);
                    break;

                case Constants.EVENT:
                    newTask = new Event(formattedEntry[2], done, formattedEntry[3], formattedEntry[4]);
                    break;

                default:
                    return taskTypeError(taskType);
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(Constants.FILEERROR);
            return null;
        }
        return newTask;
    }

    private static Task taskTypeError(String taskType) {
        System.out.println(Constants.TASKTYPEERROR + taskType);
        return null;
    }

    private static void writeFile() {
        try {
            List<String> formattedEntries = new ArrayList<>();
            for (Task newTask : list) {
                formattedEntries.add(newTask.toFileString());
            }
            FileHandler.writeFile(Constants.FILEPATH, formattedEntries);
        } catch (IOException e) {
            System.out.println(Constants.FILEWRITEERROR + e.getMessage() + Constants.NEWLINE + Constants.LINE);
        }
    }

    private static void printAddTask(Task list) {
        System.out.println(Constants.ADDEDTASK);
        System.out.println(Constants.TAB + list.taskString() + Constants.NEWLINE);
    }

    private static void printRemainingTasks(int remainingTasks) {
        int size = list.size();
        Constants.printRemainingTasks(remainingTasks, size);
    }

}

