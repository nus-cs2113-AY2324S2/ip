import daisy.error.IllegalEntryException;
import daisy.error.IllegalEventFormatException;
import daisy.error.IllegalDeadlineFormatException;
import daisy.task.Deadline;
import daisy.task.Event;
import daisy.task.Task;
import daisy.task.Todo;

import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class Daisy {

    protected static Task[] tasks = new Task[100];
    protected static int task_no = 0;

    protected static String defaultFileLocation = System.getProperty("user.dir") + "\\src\\main\\java\\daisy\\data\\Daisy.txt";

    protected static String INTRO_PROMPT = "Good day! This is Daisy.\nAny task for today?";
    protected final static String EXIT_PROMPT = "Ending prompt received. Remember to keep to the deadlines!";
    protected final static String LINE_BREAK = "____________________________________";

    public static void main(String[] args) {

        loadData();

        System.out.println(LINE_BREAK);
        System.out.println(INTRO_PROMPT);
        System.out.println(LINE_BREAK);

        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        while (!command.equals("bye")) {
            System.out.println(LINE_BREAK);
            String[] separate_commands = command.split(" ",2);
            switch (separate_commands[0]) {
                case "list":
                    for (int i = 0; i < task_no; i++) {
                        System.out.println((i + 1) + "." + tasks[i]);
                    }
                    break;
                case "mark":
                    tasks[Integer.parseInt(separate_commands[1])-1].setDone();
                    System.out.println("Congrats on completing the task!");
                    System.out.println(tasks[Integer.parseInt(separate_commands[1])-1]);
                    break;
                case "unmark":
                    tasks[Integer.parseInt(separate_commands[1])-1].setUndone();
                    System.out.println("More time needed for the following task? Sure!");
                    System.out.println(tasks[Integer.parseInt(separate_commands[1])-1]);
                    break;
                case "todo":
                    try {
                        createTodo(separate_commands[1], true, false);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Error! No event detected for todo. Try again!");
                    }
                    break;
                case "deadline":
                    try {
                        String[] separate_deadlines = separate_commands[1].split(" /by ");
                        if (separate_deadlines.length < 2) {
                            throw new IllegalDeadlineFormatException();
                        }
                        createDeadline(separate_deadlines[0],separate_deadlines[1], true, false);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Error! No event detected for deadline. Try again!");
                    } catch (IllegalDeadlineFormatException e) {
                        System.out.println("Error! daisy.task.Deadline entry is not following format. Try again!");
                    }
                    break;
                case "event":
                    try {
                        String eventLine = separate_commands[1];
                        int from = eventLine.indexOf(" /from ");
                        int to = eventLine.indexOf(" /to ");
                        if (from == -1 || to == -1 ) {
                            throw new IllegalEventFormatException();
                        }
                        createEvent(eventLine.substring(0, from),eventLine.substring(from + " /from ".length(), to), eventLine.substring(to+" /to ".length()), true, false);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Error! No event detected for event. Try again!");
                    } catch (IllegalEventFormatException e){
                        System.out.println("Error! daisy.task.Event entry is not following format. Try again!");
                    }
                    break;
                default:
                    try {
                        throw new IllegalEntryException();
                    } catch (IllegalEntryException e){
                        System.out.println("Your input does not match any of my programs! Try again!");
                    }
                    break;
            }
            System.out.println(LINE_BREAK);
            command = in.nextLine();
        }

        System.out.println(EXIT_PROMPT);
        System.out.println(LINE_BREAK);

        saveData();

    }

    public static void addItem(Task item, boolean printOut) {
        tasks[task_no] = item;
        task_no++;

        if (printOut) {
            System.out.println("daisy.task.Task received! The following has been added to your list of todos:\n" + item);
            System.out.println(String.format("Now you have %d tasks in your todo list.", task_no));
        }
    }

    public static void createTodo(String taskName, boolean printOut, boolean setDone) {
        Todo newTodo = new Todo(taskName);
        if (setDone) {
            newTodo.setDone();
        }
        addItem(newTodo, printOut);
    }

    public static void createDeadline(String taskName, String taskDeadline, boolean printOut, boolean setDone) {
        Deadline newDeadline = new Deadline(taskName,taskDeadline);
        if (setDone) {
            newDeadline.setDone();
        }
        addItem(newDeadline, printOut);
    }

    public static void createEvent(String taskName, String fromDate, String toDate, boolean printOut, boolean setDone) {
        Event newEvent = new Event(taskName, fromDate, toDate);
        if (setDone) {
            newEvent.setDone();
        }
        addItem(newEvent, printOut);
    }

    public static void loadData() {
        File taskFile = new File(defaultFileLocation);
        System.out.println("Please wait while Daisy loads your previous data!");
        try {
            Scanner entryReader = new Scanner(taskFile);
            while (entryReader.hasNextLine()) {
                String[] entryData = entryReader.nextLine().split(",");
                boolean setDone = "1".equals(entryData[1]);
                switch(entryData[0]) {
                    case "T":
                        createTodo(entryData[2], false, setDone);
                        break;
                    case "D":
                        createDeadline(entryData[2], entryData[3], false, setDone);
                        break;
                    case "E":
                        createEvent(entryData[2], entryData[3], entryData[4], false, setDone);
                        break;
                }
            }
            entryReader.close();
            System.out.println("Data is successfully loaded! Program will now begin.");
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found! Daisy will create a new one for this entry!");
        }
    }

    public static void saveData() {
        System.out.println("Daisy will begin saving the data for this entry!");
        try {
            FileWriter entryWriter = new FileWriter(defaultFileLocation);
            for (Task task : tasks) {
                if (task != null) {
                    String entryInput = task.save() + "\n";
                    entryWriter.write(entryInput);
                }
                else {
                    break;
                }
            }
            entryWriter.close();
            System.out.println("Successfully saved file. Program will now exit.");
        } catch (IOException e) {
            System.out.println("Error has occurred when saving. This entry will not be saved!");
        }

    }
}
