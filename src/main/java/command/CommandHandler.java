package command;

import Storage.DataManager;
import exceptions.InputException;
import task.*;
import userInterface.Message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CommandHandler {
    private static final String LIST = "list";
    private static final String TASK_STATUS = "^(mark [0-9]|unmark [0-9])";
    private static final String DELETE = "delete [0-9]";
    private static final String TODOS = "todo(.*)";
    private static final String DEADLINES = "deadline(.*)";
    private static final String EVENTS = "event(.*)";
    private static final String EXIT = "bye";
    private static final String INPUT_DATE_TIME = "dd/MM/yyyy HHmm";
    private static final String OUTPUT_DATE_TIME = "MMM dd yyyy HH:mm";

    private static final String FIND = "find(.*)";

    /**
     * Handles the command inputted by the user.
     *
     * @param command User input detail about a command.
     * @param listCommands List of tasks stored inside the ChatBot.
     * @return Indication on whether the program should continue running.
     * @throws InputException If the command is not understood by the ChatBot.
     */
    public static boolean handle(String command,
                              TaskLists listCommands) throws InputException {
        if (command.equals(EXIT)) {
            decodeExit(listCommands);
            return false;
        } else if (command.equals(LIST)) {
            decodeList(listCommands);
            return true;
        } else if (command.matches(TASK_STATUS)) {
            decodeMark(command, listCommands);
            return true;
        } else if (command.matches(TODOS)) {
            decodeTodo(command, listCommands);
            return true;
        } else if (command.matches(DEADLINES)) {
            decodeDeadline(command, listCommands);
            return true;
        } else if (command.matches(EVENTS)) {
            decodeEvent(command, listCommands);
            return true;
        } else if (command.matches(DELETE)) {
            decodeDelete(command, listCommands);
            return true;
        } else if (command.matches(FIND)) {
            decodeFind(command, listCommands);
            return true;
        }else {
            throw new InputException("I cannot understand the command");
        }
    }

    /**
     * Decodes the todo command and adds the task to the list of commands.
     *
     * @param command User input detail about a task to do.
     * @param listCommands List of tasks stored inside the ChatBot.
     * @throws InputException If the command is not understood by the ChatBot.
     */
    private static void decodeTodo(String command,
                                       TaskLists listCommands) throws InputException {
        if ((command.matches("todo"))) {
            throw new InputException("Don't have an empty description for todo");
        }
        Tasks record = new ToDos(command.substring(5));
        listCommands.addTask(record);
        System.out.println(Message.ADD_TASK_OUTPUT_FRONT);
        System.out.println("  " + record);
        listCommands.taskNumPrint();
        System.out.print(Message.DASH);
    }

    /**
     * Decodes the delete command and removes the task from the list of commands.
     *
     * @param command User input about deletion of a task.
     * @param listCommands List of tasks stored inside the ChatBot.
     * @throws InputException If the command is not understood by the ChatBot.
     */
    private static void decodeDelete(String command,
                                     TaskLists listCommands) throws InputException {
        String[] split = command.split(" ");
        String index = split[1];
        int i = Integer.parseInt(index);
        if (i > listCommands.tasksSize()) {
            throw new InputException("index out of bound");
        }
        listCommands.delete(i);

    }

    /**
     * Decodes the mark command and marks the task as done.
     *
     * @param command User input detail about marking or unmarking a task.
     * @param listCommands List of tasks stored inside the ChatBot.
     * @throws InputException If the command is not understood by the ChatBot.
     */
    private static void decodeMark(String command,
                                   TaskLists listCommands) throws InputException {
        String[] split = command.split(" ");
        String option = split[0];
        String index = split[1];
        int i = Integer.parseInt(index);
        if (i > listCommands.tasksSize()) {
            throw new InputException("index out of bound");
        }
        if (option.startsWith("un")) {
            listCommands.unBox(i);
        } else {
            listCommands.box(i);
        }
    }

    /**
     * Decodes the deadline command and adds the task to the list of commands.
     *
     * @param command User input detail about the deadline.
     * @param listCommands List of tasks stored inside the ChatBot.
     * @throws InputException If the command is not understood by the ChatBot.
     */
    private static void decodeDeadline(String command,
                                       TaskLists listCommands) throws InputException {
        String[] deadlines = command.split(" /by ", 2);
        if (deadlines.length == 2) {
            if ((deadlines[0].equals("deadline"))) {
                throw new InputException("Don't have an empty description for deadline");
            }
            String deadline = decodeDateTime(deadlines[1]);
            Tasks record = new Deadlines(deadlines[0].substring(9), deadline);
            listCommands.addTask(record);
            System.out.println(Message.ADD_TASK_OUTPUT_FRONT);
            System.out.println("  " + record);
            listCommands.taskNumPrint();
            System.out.print(Message.DASH);
        } else {
            throw new InputException("Wrong input syntax, have a time for deadline");
        }
    }

    /**
     * Decodes the event command and adds the task to the list of commands.
     *
     * @param command User input detail about the event.
     * @param listCommands List of tasks stored inside the ChatBot.
     * @throws InputException If the command is not understood by the ChatBot.
     */
    private static void decodeEvent(String command,
                                    TaskLists listCommands) throws InputException {
        String[] events = command.split(" /from ", 2);
        if (events.length == 2) {
            if ((events[0].equals("event"))) {
                throw new InputException("Don't have an empty description for event");
            }
            String[] times = events[1].split(" /to ", 2);
            if (times.length == 2) {
                String from = decodeDateTime(times[0]);
                String to = decodeDateTime(times[1]);
                Tasks record = new Events(events[0].substring(6), from, to);
                listCommands.addTask(record);
                System.out.println(Message.ADD_TASK_OUTPUT_FRONT);
                System.out.println("  " + record);
                listCommands.taskNumPrint();
                System.out.print(Message.DASH);
            } else {
                throw new InputException("invalid syntax, have a start and end time");
            }
        } else {
            throw new InputException("wrong input syntax, have a description command");
        }
    }

    /**
     * Decodes the list command and prints the list of tasks.
     *
     * @param listCommands List of tasks stored inside the ChatBot.
     */
    public static void decodeList(TaskLists listCommands) {
        System.out.println(Message.LIST_OUTPUT_FRONT);
        listCommands.printTasks();
        System.out.print(Message.DASH);
    }

    /**
     * Decodes the exit command and saves the list of tasks to a text file.
     *
     * @param listCommands List of tasks stored inside the ChatBot.
     */
    public static void decodeExit(TaskLists listCommands) {
        DataManager.saveText(listCommands);
        System.out.println(Message.FAREWELL);
    }

    /**
     * Decodes the find command and find the tasks that contains the keywords.
     *
     * @param command Keywords of the search.
     * @param listCommands List of tasks stored inside the ChatBot.
     * @throws InputException If the command is not understood by the ChatBot.
     */
    public static void decodeFind(String command,
                                   TaskLists listCommands) throws InputException {
        String[] split = command.split(" ");
        if (split.length != 1) {
            String searchKey = split[1];
            listCommands.search(searchKey);
        } else {
            throw new InputException("Please do not input empty string inside find");
        }
    }

    /**
     * Converts date and time input into the right format.
     *
     * @param date Input dates using input format.
     * @return Input date using output format.
     * @throws InputException If the date format is not understood by the chatBot.
     */
    public static String decodeDateTime(String date) throws InputException {
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(INPUT_DATE_TIME));
        } catch (DateTimeParseException error) {
            throw new InputException("Please follow the date-time format: dd/MM/yyyy HHmm\n");
        }
        return dateTime.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_TIME));
    }

}
