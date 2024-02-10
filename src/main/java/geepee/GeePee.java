package geepee;

import java.util.Scanner;
import geepee.system.*;
import geepee.task.list.*;
import geepee.exceptions.*;

public class GeePee {

    // constants to determine required padding to extract the name of a Event
    // object, and when it starts and ends
    private static final int EVENT_PADDING = 6;
    private static final int FROM_PADDING = 6;
    private static final int TO_PADDING = 4;

    private static void handleTodo(List list, String line) {
        try {
            String todoDescription = InputParser.getTodoDescription(line);
            list.addTodo(todoDescription);
        } catch (EmptyDescriptionException e) {
            SystemMessage.printEmptyTodoDescriptionMessage();
        }
    }

    private static void handleDeadline(List list, String line) {
        try {
            int byIndex = line.indexOf("/by");
            String deadlineDescription = InputParser.getDeadlineDescription(line, byIndex);
            String deadlineBy = InputParser.getDeadlineBy(line, byIndex);
            list.addDeadline(deadlineDescription, deadlineBy);
        } catch (EmptyDescriptionException e) {
            SystemMessage.printEmptyDeadlineDescriptionMessage();
        } catch (MissingDeadlineException e) {
            SystemMessage.printMissingDeadlineMessage();
        }
    }

    private static void handleEvent(List list, String line) {
        try {
            InputParser.checkValidEventDescription(line);
        } catch (EmptyDescriptionException e) {
            SystemMessage.printEmptyEventDescriptionMessage();
            return;
        }
        int fromIndex = line.indexOf("/");
        int toIndex = line.indexOf("/", fromIndex + 1);
        String from = line.substring(fromIndex + FROM_PADDING, toIndex).trim();
        String to = line.substring(toIndex + TO_PADDING).trim();
        String eventName = line.substring(EVENT_PADDING, fromIndex).trim();
        list.addEvent(eventName, from, to);
    }

    private static void handleTaskStatusChange(List list, String line) {
        String[] words = line.split(" ");
        int number = Integer.parseInt(words[1]);
        if (number >= 0 && number <= list.getSize()) {
            list.changeTaskStatus(number - 1, (words[0].equals("mark") ? true : false));
        }
    }

    private static void handleUserInput(List list, String line) throws InvalidCommandException {
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

    private static void initialiseLoop() {
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
