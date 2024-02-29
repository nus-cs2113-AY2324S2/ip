package TaskList;

import ExceptionHandling.InvalidCommandException;
import ExceptionHandling.InvalidCommandMessageException;
import ExceptionHandling.InvalidTaskIndexException;
import Parser.CommandParse;
import Sinep.*;
import UI.CommandUI;
import UI.ExceptionUI;

import java.util.Objects;
import java.util.Scanner;

public class HandleTask {


    public static boolean taskCommands(String command, Scanner scanner, String input) {
        switch (command) {
            case "bye":
                CommandUI.printBye();
                scanner.close();
                return true;
            case "list":
                CommandUI.printList();
                break;
            case "mark":
                try {
                    markList(input);
                } catch (InvalidTaskIndexException e){
                    ExceptionUI.printInvalidTaskIndex();
                }
                break;
            case "unmark":
                try {
                    unmarkList(input);
                } catch (InvalidTaskIndexException e){
                    ExceptionUI.printInvalidTaskIndex();
                }
                break;
            case "todo":
                try {
                    addTodo(input);
                } catch (InvalidCommandMessageException e) {
                    ExceptionUI.printInvalidTodoMessage();
                }
                break;
            case "deadline":
                try {
                    addDeadline(input);
                } catch (InvalidCommandMessageException e) {
                    ExceptionUI.printInvalidDeadlineMessage();
                }
                break;
            case "event":
                try {
                    addEvent(input);
                } catch (InvalidCommandMessageException e) {
                    ExceptionUI.printInvalidEventMessage();
                }
                break;
            case "delete":
                try {
                    deleteTask(input);
                } catch (InvalidTaskIndexException | InvalidCommandMessageException e) {
                    ExceptionUI.printInvalidDeleteIndex();
                }
                break;
            default:
                try {
                    throw new InvalidCommandException();
                } catch (InvalidCommandException e) {
                    ExceptionUI.printInvalidCommand();
                }
        }
        return false;
    }

    public static void deleteTask(String input) throws InvalidTaskIndexException, InvalidCommandMessageException {
        if (Objects.equals(input, "delete")) {
            throw new InvalidCommandMessageException();
        }
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        if (taskIndex >= Sinep.taskList.size()) {
            throw new InvalidTaskIndexException();
        }
        CommandUI.printDelete(taskIndex);
    }

    public static void markList(String input) throws InvalidTaskIndexException {
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        if (taskIndex >= Sinep.taskList.size()) {
            throw new InvalidTaskIndexException();
        }
        Task markingTask = Sinep.taskList.get(taskIndex);
        markingTask.markAsDone();
        CommandUI.printMark(taskIndex, markingTask);
    }

    public static void unmarkList(String input) throws InvalidTaskIndexException {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        if (taskIndex >= Sinep.taskList.size()) {
            throw new InvalidTaskIndexException();
        }
        Task markingTask = Sinep.taskList.get(taskIndex);
        markingTask.unmarkAsDone();
        CommandUI.printUnmark(taskIndex, markingTask);
    }

    public static void addTodo(String input) throws InvalidCommandMessageException {
        if (Objects.equals(input, "todo")) {
            throw new InvalidCommandMessageException();
        }
        String actualDescription = CommandParse.getTodoCommand(input);
        Todo newTodo = new Todo(actualDescription);
        Sinep.taskList.add(newTodo);
        CommandUI.todoAddMessage(newTodo);
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
        Sinep.taskList.add(newDeadline);
        CommandUI.commandAddMessage(newDeadline.toString());
    }
    public static void addEvent(String input) throws InvalidCommandMessageException{
        boolean containStart = input.contains("/from");
        Event newEvent = getEvent(input, containStart);
        Sinep.taskList.add(newEvent);
        CommandUI.commandAddMessage(newEvent.toString());
    }

    private static Event getEvent(String input, boolean containStart) throws InvalidCommandMessageException {
        boolean containEnd = input.contains("/to");
        if (Objects.equals(input, "event") || !containStart || !containEnd) {
            throw new InvalidCommandMessageException();
        }
        String actualDescription = input.replace("event ", "");
        String[] inputParts = actualDescription.split("/from|/to", 3);
        if (Objects.equals(inputParts[0], "") || Objects.equals(inputParts[1], "") || Objects.equals(inputParts[2], "")) {
            throw new InvalidCommandMessageException();
        }
        return new Event(actualDescription);
    }
}
