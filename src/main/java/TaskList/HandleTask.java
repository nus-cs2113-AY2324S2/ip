package TaskList;

import ExceptionHandling.InvalidCommandException;
import ExceptionHandling.InvalidCommandMessageException;
import ExceptionHandling.InvalidTaskIndexException;
import Parser.CommandParse;
import UI.BackboneUI;
import UI.CommandUI;
import UI.ExceptionUI;
import java.util.Objects;
import java.util.Scanner;
import static Sinep.Sinep.taskList;

public class HandleTask {

    /**
     * Processes the task-related commands and updates the task list accordingly.
     *
     * @param command The task command to execute.
     * @param scanner The scanner object for user input.
     * @param input   The user input string.
     * @return True if the "bye" command is issued, otherwise false.
     */
    public static boolean taskCommands(String command, Scanner scanner, String input) {
        switch (command) {
            case "bye":
                return byeCommand(scanner);
            case "list":
                CommandUI.printList();
                break;
            case "mark":
                markCommand(input);
                break;
            case "unmark":
                unmarkCommand(input);
                break;
            case "todo":
                todoCommand(input);
                break;
            case "deadline":
                deadlineCommand(input);
                break;
            case "event":
                eventCommand(input);
                break;
            case "delete":
                deleteCommand(input);
                break;
            case "find":
                findTask(input.split(" ", 2)[1]);
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

    private static void deleteCommand(String input) {
        try {
            deleteTask(input);
        } catch (InvalidTaskIndexException | InvalidCommandMessageException e) {
            ExceptionUI.printInvalidDeleteIndex();
        }
    }

    private static void eventCommand(String input) {
        try {
            addEvent(input);
        } catch (InvalidCommandMessageException e) {
            ExceptionUI.printInvalidEventMessage();
        }
    }

    private static void deadlineCommand(String input) {
        try {
            addDeadline(input);
        } catch (InvalidCommandMessageException e) {
            ExceptionUI.printInvalidDeadlineMessage();
        }
    }

    private static void todoCommand(String input) {
        try {
            addTodo(input);
        } catch (InvalidCommandMessageException e) {
            ExceptionUI.printInvalidTodoMessage();
        }
    }

    private static void unmarkCommand(String input) {
        try {
            unmarkList(input);
        } catch (InvalidTaskIndexException e){
            ExceptionUI.printInvalidTaskIndex();
        }
    }

    private static void markCommand(String input) {
        try {
            markList(input);
        } catch (InvalidTaskIndexException e){
            ExceptionUI.printInvalidTaskIndex();
        }
    }

    private static boolean byeCommand(Scanner scanner) {
        CommandUI.printBye();
        scanner.close();
        return true;
    }

    /**
     * Searches for tasks that contain the given keyword and prints them.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public static void findTask(String keyword) {
        System.out.println(BackboneUI.line);
        System.out.println("Here are the matching tasks in your list:");

        int taskNumber = 1;
        for (Task task : taskList) {
            if (task.description.contains(keyword)) {
                System.out.println(taskNumber + "." + task);
                taskNumber++;
            }
        }
        if (Objects.equals(taskNumber, 1)) {
            System.out.println("Sorry, the task does not exist!");
        }

        System.out.println(BackboneUI.line);
    }

    /**
     * Deletes a task from the task list based on the given input index.
     *
     * @param input The input string containing the index of the task to delete.
     * @throws InvalidTaskIndexException        If the task index is invalid.
     * @throws InvalidCommandMessageException If the input command message is invalid.
     */
    public static void deleteTask(String input) throws InvalidTaskIndexException, InvalidCommandMessageException {
        if (Objects.equals(input, "delete")) {
            throw new InvalidCommandMessageException();
        }
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        if (taskIndex >= taskList.size()) {
            throw new InvalidTaskIndexException();
        }
        CommandUI.printDelete(taskIndex);
    }

    /**
     * Marks a task as done based on the given input index.
     *
     * @param input The input string containing the index of the task to mark as done.
     * @throws InvalidTaskIndexException If the task index is invalid.
     */
    public static void markList(String input) throws InvalidTaskIndexException {
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        if (taskIndex >= taskList.size()) {
            throw new InvalidTaskIndexException();
        }
        Task markingTask = taskList.get(taskIndex);
        markingTask.markAsDone();
        CommandUI.printMark(taskIndex, markingTask);
    }

    /**
     * Unmarks a task as done based on the given input index.
     *
     * @param input The input string containing the index of the task to unmark as done.
     * @throws InvalidTaskIndexException If the task index is invalid.
     */
    public static void unmarkList(String input) throws InvalidTaskIndexException {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        if (taskIndex >= taskList.size()) {
            throw new InvalidTaskIndexException();
        }
        Task markingTask = taskList.get(taskIndex);
        markingTask.unmarkAsDone();
        CommandUI.printUnmark(taskIndex, markingTask);
    }

    /**
     * Adds a new Todo task to the task list based on the given description.
     *
     * @param input The input string containing the description of the Todo task.
     * @throws InvalidCommandMessageException If the input command message is invalid.
     */
    public static void addTodo(String input) throws InvalidCommandMessageException {
        if (Objects.equals(input, "todo")) {
            throw new InvalidCommandMessageException();
        }
        String actualDescription = CommandParse.getTodoCommand(input);
        Todo newTodo = new Todo(actualDescription);
        taskList.add(newTodo);
        CommandUI.todoAddMessage(newTodo);
    }

    /**
     * Adds a new Deadline task to the task list based on the given description.
     *
     * @param input The input string containing the description of the Deadline task.
     * @throws InvalidCommandMessageException If the input command message is invalid.
     */
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

        CommandUI.commandDeadlineMessage(newDeadline);

    }

    /**
     * Adds a new Event task to the task list based on the given description.
     *
     * @param input The input string containing the description of the Event task.
     * @throws InvalidCommandMessageException If the input command message is invalid.
     */
    public static void addEvent(String input) throws InvalidCommandMessageException{
        boolean containStart = input.contains("/from");
        Event newEvent = getEvent(input, containStart);
        taskList.add(newEvent);
        CommandUI.commandEventMessage(newEvent);
    }

    /**
     * Helper method to create an Event task from the given input.
     *
     * @param input        The input string containing the description of the Event task.
     * @param containStart A flag indicating whether the input contains a start date/time.
     * @return The created Event task.
     * @throws InvalidCommandMessageException If the input command message is invalid.
     */
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
