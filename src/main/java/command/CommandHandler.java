package command;

import exceptions.InputException;
import task.*;
import userInterface.Message;

public class CommandHandler {
    private static final String LIST = "list";
    private static final String TASK_STATUS = "^(mark [0-9]|unmark [0-9])";
    private static final String DELETE = "delete [0-9]";
    private static final String TODOS = "todo(.*)";
    private static final String DEADLINES = "deadline(.*)";
    private static final String EVENTS = "event(.*)";
    private static final String EXIT = "bye";

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
        } else {
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
            if ((command.matches("deadline"))) {
                throw new InputException("Don't have an empty description for deadline");
            }
            Tasks record = new Deadlines(deadlines[0].substring(9), deadlines[1]);
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
            if ((command.matches("events"))) {
                throw new InputException("Don't have an empty description for event");
            }
            String[] times = events[1].split(" /to ", 2);
            if (times.length == 2) {
                Tasks record = new Events(events[0].substring(6), times[0], times[1]);
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
        DataManage.saveText(listCommands);
        System.out.println(Message.FAREWELL);
    }

}
