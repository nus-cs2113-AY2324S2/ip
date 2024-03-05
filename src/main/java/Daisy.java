import daisy.error.IllegalEntryException;
import daisy.error.IllegalEventFormatException;
import daisy.error.IllegalDeadlineFormatException;
import daisy.task.Deadline;
import daisy.task.Event;
import daisy.task.Task;
import daisy.task.Todo;
import daisy.ui.Ui;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class Daisy {

    protected static ArrayList<Task> tasks = new ArrayList<>();

    protected static String defaultFileLocation = System.getProperty("user.dir") + "\\src\\main\\java\\daisy\\data\\Daisy.txt";

    public static void main(String[] args) {

        loadData();

        Ui ui = new Ui();

        ui.sendIntro();

        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        while (!command.equals("bye")) {
            ui.setLineBreak();
            String[] separate_commands = command.split(" ",2);
            switch (separate_commands[0]) {
                case "list":
                    ui.listTasks(tasks);
                    break;
                case "mark":
                    tasks.get(Integer.parseInt(separate_commands[1])-1).setDone();
                    ui.printMarked(tasks.get(Integer.parseInt(separate_commands[1])-1));
                    break;
                case "unmark":
                    tasks.get(Integer.parseInt(separate_commands[1])-1).setUndone();
                    ui.printUnmarked(tasks.get(Integer.parseInt(separate_commands[1])-1));
                    break;
                case "delete":
                    deleteItem(Integer.parseInt(separate_commands[1])-1);
                    break;
                case "todo":
                    try {
                        createTodo(separate_commands[1], true, false);
                    } catch (IndexOutOfBoundsException e) {
                        ui.printTodoMissingError();
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
                        ui.printDeadlineMissingError();
                    } catch (IllegalDeadlineFormatException e) {
                        ui.printDeadlineInputError();
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
                        ui.printEventMissingError();
                    } catch (IllegalEventFormatException e){
                        ui.printEventInputError();
                    }
                    break;
                default:
                    try {
                        throw new IllegalEntryException();
                    } catch (IllegalEntryException e){
                        ui.printInvalidCommandError();
                    }
                    break;
            }
            ui.setLineBreak();
            command = in.nextLine();
        }

        ui.sendExit();

        saveData();

    }

    public static void addItem(Task item) {
        tasks.add(item);
        System.out.println("Task received! The following has been added to your list of todos:\n" + item);
        outputSize();
    }

    public static void deleteItem(int index) {
        System.out.println("I see. The following task will be removed from your list:\n" + tasks.get(index));
        tasks.remove(index);
        outputSize();
    }

    public static void outputSize() {
        System.out.println(String.format("Now you have %d tasks in your todo list.", tasks.size()));
    }
    public static void addItem(Task item, boolean printOut) {
        tasks.add(item);

        if (printOut) {
            System.out.println("Task received! The following has been added to your list of todos:\n" + item);
            outputSize();
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
