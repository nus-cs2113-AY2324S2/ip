import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class Krot {
    static final String NAME = "Krot"; // Static variable for the bot's name
    static final String FILE_PATH = "../../../data/taskList.txt";
    static ArrayList<Task> tasks = new ArrayList<>(); // Static variable list of all the tasks
    static boolean hasEnded = false; // Static variable to end the chatbot

    public static void printName() {
        // Prints the name of bot when replying
        System.out.println(NAME + ":");
    }

    public static void greeting() {
        // Prints out the standard greeting
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
        printSeparator();
    }

    public static void printSeparator() {
        // Print line separators
        System.out.println("-");
    }

    public static void endChat() {
        // Prints closing message
        printSeparator();
        printName();
        System.out.println("Bye. Hope to see you again soon!");
        hasEnded = true;
    }

    public static void saveList() {
        FileWrite fw = new FileWrite(FILE_PATH, tasks);
        try {
            fw.writeToFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createTask(String key, String line) {
        // Prints and creates the task added
        printSeparator();
        printName();
        Task task;
        if (key.equalsIgnoreCase("todo")) {
            task = createTodo(line);
        } else if (key.equalsIgnoreCase("deadline")) {
            try {
                task = createDeadline(line);
            } catch (Exception e) { // Catches if wrong initializer
                System.out.println(e.getMessage());
                printSeparator();
                return;
            }
        } else {
            try {
                task = createEvent(line);
            } catch (Exception e) { // Catches if wrong initializer is used
                System.out.println(e.getMessage());
                printSeparator();
                return;
            }
        }
        System.out.println("More tasks to do! I've added:");
        printTask(task);
        System.out.println("Get to working, you now have " + tasks.size() + " tasks in the list!");
        printSeparator();
    }

    public static void deleteTask(String[] phrases) throws ArrayIndexOutOfBoundsException, InvalidInputException {
        printSeparator();
        printName();
        Pattern pattern = Pattern.compile("^[0-9]+$");
        String phrase;
        int taskIndex;
        Task task;
        try {
            phrase = phrases[1];
            if (!pattern.matcher(phrase).matches()) {
                throw new InvalidInputException("That's not  number, enter a proper task number");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Which task you trying to delete?");
        }
        try {
            taskIndex = parseInt(phrase);
            task = tasks.get(taskIndex - 1);
            tasks.remove(taskIndex - 1);
        }
        catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("I can't delete a task that isn't there...");
        }
        System.out.println("Deleted task " + taskIndex + " from the list.");
        printTask(task);
        System.out.println("You now have " + tasks.size() + " tasks to complete.");
    }

    private static void printTask(Task task) {
        // Prints specified task
        System.out.println("["
                + task.getTaskType() + "]"
                + "["
                + (task.isDone ? "X" : " ")
                + "] "
                + task.task
                + (task.getTaskType().equalsIgnoreCase("E") ? " (from: " + task.getStart() + " " : "")
                + (task.getTaskType().equalsIgnoreCase("E") ? "to: " + task.getEnd() + ")" :
                task.getTaskType().equalsIgnoreCase("D") ? " (by: " + task.getEnd() + ")" : ""));
    }

    private static Task createEvent(String line) throws StringIndexOutOfBoundsException, EmptyInputException {
        // Creates new event
        Task task;
        String title;
        String start;
        String end;
        title = line.split("/")[0].split(" ", 2)[1].strip();
        int fromIndex = line.indexOf("/from");
        int toIndex = line.indexOf("/to");
        if (title.isBlank()) {
            throw new EmptyInputException("Why is the task description blank!"); // Throws exception if task is blank
        }
        if (fromIndex == -1 || toIndex == -1) {
            throw new StringIndexOutOfBoundsException("Enter the duration with the initializer /from <start> /to <end> or don't try at all"); // Throws exception if initializers not found
        }
        start = line.substring(fromIndex + 5, toIndex).strip();
        end = line.substring(line.indexOf("/to") + 3).strip();
        if (start.isBlank() || end.isBlank()) {
            throw new EmptyInputException("When is this event happening?"); // Throws exception if durations are blank
        }
        task = new Event(title, start, end, "E");
        tasks.add(task);
        return task;
    }

    private static Task createDeadline(String line) throws ArrayIndexOutOfBoundsException, EmptyInputException {
        // Creates new deadline task
        Task task;
        String title;
        String end;
        title = line.split("/")[0].split(" ", 2)[1].strip();
        if (title.isBlank()) {
            throw new EmptyInputException("Why is the task description blank!"); // Throws exception if task is blank
        }
        int byIndex = line.indexOf("/by");
        if (byIndex == -1) {
            throw new ArrayIndexOutOfBoundsException("Enter a due date with the initializer /by"); // Throws exception if initializer not found
        }
        end = line.substring(byIndex + 3).strip();
        if (end.isBlank()) {
            throw new EmptyInputException("Enter a due date for this task"); // Throws exception if due date is blank
        }
        task = new Deadline(title, end, "D");
        tasks.add(task);
        return task;
    }

    private static Task createTodo(String line) {
        // Creates new task to do
        String title;
        Task task;
        title = line.split(" ", 2)[1];
        task = new Todo(title, "T");
        tasks.add(task);
        return task;
    }

    public static void listTasks() {
        // Prints the list of tasks
        printSeparator();
        printName();
        int i = 1;
        if (tasks.isEmpty()) {
            System.out.println("There's no tasks to do! :)");
        }
        for (Task task : tasks) {
            System.out.print(i + ".");
            printTask(task);
            i += 1;
        }
        printSeparator();
    }

    public static void markTask(String[] line, String key) throws ArrayIndexOutOfBoundsException, IndexOutOfBoundsException {
        // Marks or unmarks tasks
        printSeparator();
        printName();
        int index;
        Task t;
        try {
            index = parseInt(line[1]) - 1;
            t = tasks.get(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Which task are u trying to " + key.toLowerCase());
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("That task doesn't exist...");
        }
        if (t.isDone) {
            if (key.equalsIgnoreCase("mark")) { // Catch if the task is already marked
                System.out.println("Task " + (index + 1) + " is already marked done");
                printSeparator();
                return;
            }
            System.out.println("More to do? I've marked this task as not done yet:");
        } else {
            if (key.equalsIgnoreCase("unmark")) { // Catch if the task is already unmarked
                System.out.println("Task " + (index + 1) + " is already marked done");
                printSeparator();
                return;
            }
            System.out.println("Wow good job at clearing a task! I,ve marked this task as done:");
        }
        t.markDone();
        printTask(t);
        printSeparator();
    }

    public static void checkKey(String line) {
        // Checks the keywords and runs the corresponding responses
        String[] phrases = line.split(" ", 2);
        String key = phrases[0].toLowerCase();

        switch (key) {
        case "bye":
            endChat();
            break;
        case "list":
            listTasks();
            break;
        case "mark":
        case "unmark":
            try {
                markTask(phrases, key);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                printSeparator();
            }
            saveList();
            break;
        case "todo":
        case "deadline":
        case "event":
            try {
                if (phrases[1].isBlank()) {
                    String triggerError = phrases[2];
                }
                createTask(key, line);
                saveList();
            } catch (ArrayIndexOutOfBoundsException e) { // Catch if only key is typed
                printSeparator();
                printName();
                System.out.println("What task are you trying to add??? Please enter a task");
                printSeparator();
                return;
            }
            break;
        case "delete":
            try {
                deleteTask(phrases);
                saveList();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                printSeparator();
                return;
            }
            break;
        default:
            printSeparator();
            printName();
            System.out.println("I'm sorry I didnt quite catch that");
            printSeparator();
        }
    }

    public static void readDb() {
        FileReader fileReader = new FileReader(FILE_PATH);
        try {
            tasks = fileReader.getTasks();
        } catch (FileNotFoundException e) {
            return;
        }
    }

    public static void main(String[] args) {
        String line;
        printSeparator();
        greeting();
        Scanner in = new Scanner(System.in);
        readDb();
        while (!hasEnded) {
            System.out.println("You:");
            line = in.nextLine();
            checkKey(line);
        }
        in.close();
    }
}

