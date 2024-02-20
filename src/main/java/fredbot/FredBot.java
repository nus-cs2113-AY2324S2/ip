package fredbot;

import fredbot.exception.EmptyDescriptionException;
import fredbot.exception.UnknownCommandException;
import fredbot.task.Deadline;
import fredbot.task.Event;
import fredbot.task.Task;
import fredbot.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FredBot {
    private static ArrayList<Task> allTasks;
    private static final int TASK_CAPACITY = 100;
    private static int count;

    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_UNMARK = "unmark";

    private static final String MESSAGE_ADD = "Are you sure you'll ever get to it? Fine, I've added this task:";
    private static final String MESSAGE_DELETE = "Finally lifting my load? I've removed this task:";
    private static final String MESSAGE_EMPTY_LIST = "Go and touch some grass... your list is empty.";
    private static final String MESSAGE_EMPTY_DESCRIPTION = "I can't do that if you don't give me the description...";
    private static final String MESSAGE_ERROR = "What did you do... Something went wrong.";
    private static final String MESSAGE_UNKNOWN_COMMAND = "I have no idea what you just said.";
    private static final String MESSAGE_GOODBYE = "Finally... goodbye.";
    private static final String MESSAGE_LIST_HEADER = "Mr Busy over here has these tasks in his list:";
    private static final String MESSAGE_MARK = "Ok and do you want a medal for that? I've marked this as done:";
    private static final String MESSAGE_UNMARK = "Why am I not surprised... I've marked this task as not done yet:";
    private static final String MESSAGE_WELCOME = "Yo I'm FredBot. What do you want with me?";

    private static final String PREFIX_BY = "/by";
    private static final String PREFIX_FROM = "/from";
    private static final String PREFIX_TO = "/to";

    private static final String FILE_PATH = "data/fredbot.txt";

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        loadFredBot();
        showWelcomeMessage();
        while (true) {
            String userInput = readUserInput();
            executeCommand(userInput);
            saveFredBot();
        }
    }

    private static void loadFredBot() {
        initTaskList();
        File f = new File(FILE_PATH);
        try {
            loadTaskList(f);
        } catch (FileNotFoundException e) {
            initSaveFile(f);
        }
    }

    private static void loadTaskList(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String task = s.nextLine();
            try {
                processLine(task);
            } catch (EmptyDescriptionException e) {
                System.out.println(MESSAGE_EMPTY_DESCRIPTION);
            }
        }
    }

    private static void processLine(String task) throws EmptyDescriptionException {
        String taskType = task.substring(0, 1);
        String[] taskArgs = splitArgs(task);
        switch (taskType) {
        case "T":
            allTasks.add(new Todo(taskArgs[2].trim()));
            markDone(allTasks.get(count), taskArgs[1]);
            count++;
            break;
        case "D":
            allTasks.add(new Deadline(taskArgs[2].trim(), taskArgs[3].trim()));
            markDone(allTasks.get(count), taskArgs[1]);
            count++;
            break;
        case "E":
            allTasks.add(new Event(taskArgs[2].trim(), taskArgs[3].trim(), taskArgs[4].trim()));
            markDone(allTasks.get(count), taskArgs[1]);
            count++;
            break;
        }
    }

    private static void markDone(Task t, String status) {
        if (status.trim().equals("1")) {
            t.markAsDone();
        }
    }

    private static String[] splitArgs(String task) {
        return task.split("\\|", 5);
    }

    private static void initSaveFile(File f) {
        try {
            new File("data").mkdir();
            f.createNewFile();
        } catch (IOException e) {
            System.out.println(MESSAGE_ERROR);
        }
    }

    private static void initTaskList() {
        allTasks = new ArrayList<>();
        count = 0;
    }

    private static void showWelcomeMessage() {
        System.out.println(MESSAGE_WELCOME);
    }

    private static String readUserInput() {
        return SCANNER.nextLine();
    }

    private static void saveFredBot() {
        try {
            writeToFile();
        } catch (IOException e) {
            System.out.println(MESSAGE_ERROR);
        }
    }

    private static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i = 0; i < count; i++) {
            fw.write(allTasks.get(i).saveString() + System.lineSeparator());
        }
        fw.close();
    }

    private static void executeCommand(String input) {
        try {
            extractCommand(input);
        } catch (UnknownCommandException e) {
            System.out.println(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private static void extractCommand(String input) throws UnknownCommandException {
        final String[] commandWordAndArgs = splitCommandWordAndArgs(input);
        final String commandWord = commandWordAndArgs[0];
        final String commandArgs = commandWordAndArgs[1];

        switch (commandWord) {
        case COMMAND_TODO:
            executeAddTodo(commandArgs);
            break;
        case COMMAND_DEADLINE:
            executeAddDeadline(commandArgs);
            break;
        case COMMAND_EVENT:
            executeAddEvent(commandArgs);
            break;
        case COMMAND_LIST:
            executeList();
            break;
        case COMMAND_MARK:
            executeMark(commandArgs);
            break;
        case COMMAND_UNMARK:
            executeUnmark(commandArgs);
            break;
        case COMMAND_DELETE:
            executeDelete(commandArgs);
            break;
        case COMMAND_EXIT:
            executeExit();
            break;
        default:
            throw new UnknownCommandException();
        }
    }

    private static String[] splitCommandWordAndArgs(String input) {
        final String[] split = input.split(" ", 2);
        return split.length == 2 ? split : new String[]{split[0], ""};
    }

    private static void executeAddTodo(String input) {
        try {
            allTasks.add(new Todo(input));
            echoTask();
        } catch (EmptyDescriptionException e) {
            System.out.println(MESSAGE_EMPTY_DESCRIPTION);
        }
    }

    private static void executeAddDeadline(String input) {
        String[] split = splitDeadlineAndDate(input);
        try {
            allTasks.add(new Deadline(split[0], split[1]));
            echoTask();
        } catch (EmptyDescriptionException e) {
            System.out.println(MESSAGE_EMPTY_DESCRIPTION);
        }
    }

    private static String[] splitDeadlineAndDate(String input) {
        final int indexOfByPrefix = input.indexOf(PREFIX_BY);
        String[] deadlineAndDate = new String[2];

        deadlineAndDate[0] = input.substring(0, indexOfByPrefix).trim();
        deadlineAndDate[1] = removePrefix(input.substring(indexOfByPrefix), PREFIX_BY).trim();
        return deadlineAndDate;
    }

    private static void executeAddEvent(String input) {
        String[] split = splitEventAndDates(input);
        try {
            allTasks.add(new Event(split[0], split[1], split[2]));
            echoTask();
        } catch (EmptyDescriptionException e) {
            System.out.println(MESSAGE_EMPTY_DESCRIPTION);
        }
    }

    private static String[] splitEventAndDates(String input) {
        final int indexOfFromPrefix = input.indexOf(PREFIX_FROM);
        final int indexOfToPrefix = input.indexOf(PREFIX_TO);
        String[] eventAndDates = new String[3];

        eventAndDates[0] = input.substring(0, indexOfFromPrefix).trim();
        eventAndDates[1] = removePrefix(input.substring(indexOfFromPrefix, indexOfToPrefix), PREFIX_FROM).trim();
        eventAndDates[2] = removePrefix(input.substring(indexOfToPrefix), PREFIX_TO).trim();
        return eventAndDates;
    }

    private static String removePrefix(String s, String prefix) {
        return s.replace(prefix, "");
    }

    private static void echoTask() {
        System.out.println(MESSAGE_ADD);
        System.out.println(allTasks.get(count).toString());
        count++;
        String task = (count == 1) ? " task " : " tasks ";
        System.out.println("You now have " + count + task + "in the list.");
    }

    private static void executeList() {
        if (count == 0) {
            System.out.println(MESSAGE_EMPTY_LIST);
            return;
        }

        System.out.println(MESSAGE_LIST_HEADER);
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + allTasks.get(i).toString());
        }
    }

    private static void executeMark(String taskNumber) {
        int index = Integer.parseInt(taskNumber) - 1;
        allTasks.get(index).markAsDone();
        System.out.println(MESSAGE_MARK);
        System.out.println(allTasks.get(index).toString());
    }

    private static void executeUnmark(String taskNumber) {
        int index = Integer.parseInt(taskNumber) - 1;
        allTasks.get(index).unmarkAsDone();
        System.out.println(MESSAGE_UNMARK);
        System.out.println(allTasks.get(index).toString());
    }

    private static void executeDelete(String taskNumber) {
        int index = Integer.parseInt(taskNumber) - 1;
        System.out.println(MESSAGE_DELETE);
        System.out.println(allTasks.remove(index).toString());
        count--;
        String task = (count == 1) ? " task " : " tasks ";
        System.out.println("You now have " + count + task + "in the list.");
    }

    private static void executeExit() {
        System.out.println(MESSAGE_GOODBYE);
        System.exit(0);
    }
}