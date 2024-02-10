package geepee;

import java.util.Scanner;
import geepee.system.*;
import geepee.task.list.*;
import geepee.exceptions.*;

public class GeePee {

    // constant to determine required padding to extract the name of a Todo object
    public static final int TODO_PADDING = 5;

    // constants to determine required padding to extract the name of a Deadline
    // object, and when it needs to be completed by
    public static final int DEADLINE_PADDING = 9;
    public static final int BY_PADDING = 4;

    // constants to determine required padding to extract the name of a Event
    // object, and when it starts and ends
    public static final int EVENT_PADDING = 6;
    public static final int FROM_PADDING = 6;
    public static final int TO_PADDING = 4;

    public static void handleTodo(List list, String line) {
        try {
            InputHandler.checkTodoInputValidity(line);
        } catch (EmptyDescriptionException e) {
            SystemMessage.printEmptyDescriptionMessage();
            return;
        }
        String todoName = line.substring(TODO_PADDING).trim();
        list.addTodo(todoName);
    }

    public static void handleDeadline(List list, String line) {
        try {
            InputHandler.checkDeadlineInputValidity(line);
        } catch (EmptyDescriptionException e) {
            SystemMessage.printEmptyDescriptionMessage();
            return;
        }
        int byIndex = line.indexOf("/");
        String by = line.substring(byIndex + BY_PADDING).trim();
        String deadlineName = line.substring(DEADLINE_PADDING, byIndex).trim();
        list.addDeadline(deadlineName, by);
    }

    public static void handleEvent(List list, String line) {
        try {
            InputHandler.checkEventInputValidity(line);
        } catch (EmptyDescriptionException e) {
            SystemMessage.printEmptyDescriptionMessage();
            return;
        }
        int fromIndex = line.indexOf("/");
        int toIndex = line.indexOf("/", fromIndex + 1);
        String from = line.substring(fromIndex + FROM_PADDING, toIndex).trim();
        String to = line.substring(toIndex + TO_PADDING).trim();
        String eventName = line.substring(EVENT_PADDING, fromIndex).trim();
        list.addEvent(eventName, from, to);
    }

    public static void handleTaskStatusChange(List list, String line) {
        String[] words = line.split(" ");
        int number = Integer.parseInt(words[1]);
        if (number >= 0 && number <= list.getSize()) {
            list.changeTaskStatus(number - 1, (words[0].equals("mark") ? true : false));
        }
    }

    public static void handleUserInput(List list, String line) throws InvalidCommandException {
        String command = line.split(" ")[0];
        if (command.equals("") || command.equals("bye")) {
            return;
        } else if (command.equals("list")) {
            ListMessage.printAllTasks(list);
        } else if (command.equals("mark") || command.equals("unmark")) {
            handleTaskStatusChange(list, line);
        } else if (command.equals("todo")) {
            handleTodo(list, line);
        } else if (command.equals("deadline")) {
            handleDeadline(list, line);
        } else if (command.equals("event")) {
            handleEvent(list, line);
        } else {
            throw new InvalidCommandException();
        }
    }

    public static void initialiseLoop() {
        Scanner in = new Scanner(System.in);
        List list = new List();
        String line = "";
        while (!line.equals("bye")) {
            line = in.nextLine().trim();
            try {
                handleUserInput(list, line);
            } catch (InvalidCommandException e) {
                SystemMessage.printInvalidCommandMessage();
            }
        }
    }

    public static void main(String[] args) {
        SystemMessage.printWelcomeMessage();
        initialiseLoop();
        SystemMessage.printExitMessage();
    }
}
