package geepee;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import geepee.system.*;
import geepee.task.*;
import geepee.task.list.*;
import geepee.exceptions.*;

public class GeePee {

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
            int fromIndex = line.indexOf("/from");
            int toIndex = line.indexOf("/to", fromIndex + 1);
            String eventDescription = InputParser.getEventDescription(line, fromIndex);
            String eventFrom = InputParser.getEventFrom(line, fromIndex, toIndex);
            String eventTo = InputParser.getEventTo(line, toIndex);
            list.addEvent(eventDescription, eventFrom, eventTo);
        } catch (EmptyDescriptionException e) {
            SystemMessage.printEmptyEventDescriptionMessage();
        } catch (MissingFromException e) {
            SystemMessage.printMissingFromMessage();
        } catch (MissingToException e) {
            SystemMessage.printMissingToMessage();
        }
    }

    private static void handleTaskStatusChange(List list, String line) {
        String[] words = line.split(" ");
        int number = Integer.parseInt(words[1]);
        if (number >= 0 && number <= list.getSize()) {
            list.changeTaskStatus(number - 1, (words[0].equals("mark") ? true : false));
        }
    }

    private static void handleUserInput(List list, String line)
            throws InvalidCommandException {
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

    private static void updateFile(Task[] tasks, List list, FileHandler fileHandler) {
        try {
            String newData = "";
            for (int i = 0; i < list.getSize(); i++) {
                newData += tasks[i].toFileFriendlyString() + System.lineSeparator();
            }
            fileHandler.writeToFile(newData);
        } catch (IOException e) {
            System.out.println("Unable to append!");
        }
    }

    private static void initialiseLoop() {
        Scanner in = new Scanner(System.in);
        new File("data/").mkdir();
        FileHandler fileHandler = new FileHandler("data/data.txt");
        List list = new List("data/data.txt");
        String line = "";
        while (!line.equals("bye")) {
            line = in.nextLine().trim();
            try {
                handleUserInput(list, line);
                Task[] tasks = list.getTasks();
                updateFile(tasks, list, fileHandler);
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
