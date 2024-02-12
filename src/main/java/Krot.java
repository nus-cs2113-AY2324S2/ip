import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Krot {
    static final String NAME = "Krot"; // Static variable for the bot's name
    static List<Task> tasks = new ArrayList<>(); // Static variable list of all the tasks
    static boolean hasEnded = false; // Static variable to end the chatbot

    public static String readInput() {
        // Reads the input and returns the input as a string
        System.out.println("You:");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

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
            } catch (ArrayIndexOutOfBoundsException e) { // Catches if wrong initializer
                System.out.println("When is the task due??");
                System.out.println("Enter a due date with the initializer /by");
                printSeparator();
                return;
            } catch (EmptyDateException e) { // Catches if empty due date
                System.out.println("Enter a due date for this task");
                printSeparator();
                return;
            } catch (BlankTaskException e) { // Catches if the task description is blank
                System.out.println("Why is the task description blank!");
                printSeparator();
                return;
            }
        } else {
            try {
                task = createEvent(line);
            } catch (StringIndexOutOfBoundsException e) { // Catches if wrong initializer is used
                System.out.println(
                        "Enter the duration with the initializer /from <start> /to <end> or don't try at all"
                );
                printSeparator();
                return;
            } catch (EmptyDurationException e) { // Catches if duration is empty
                System.out.println("When is this event happening?");
                printSeparator();
                return;
            } catch (BlankTaskException e) { // Catches if the task description is blank
                System.out.println("Why is the task description blank!");
                printSeparator();
                return;
            }
        }
        System.out.println("More tasks to do! I've added:");
        printTask(task);
        System.out.println("Get to working, you now have " + tasks.size() + " tasks in the list!");
        printSeparator();
    }

    private static void printTask(Task task) {
        // Prints specified task
        System.out.println("["
                + task.getTaskType() + "]"
                + "["
                + (task.isDone ? "x" : " ")
                + "] "
                + task.task
                + task.getStart()
                + " "
                + task.getEnd());
    }

    private static Task createEvent(String line) throws StringIndexOutOfBoundsException, EmptyDurationException, BlankTaskException {
        // Creates new event
        Task task;
        String title;
        String start = "";
        String end = "";
        title = line.split("/")[0].split(" ", 2)[1].strip();
        int fromIndex = line.indexOf("/from");
        int toIndex = line.indexOf("/to");
        if (title.isBlank()) {
            throw new BlankTaskException(); // Throws exception if task is blank
        }
        if (fromIndex == -1 || toIndex == -1) {
            throw new StringIndexOutOfBoundsException(); // Throws exception if initializers not found
        }
        start = line.substring(fromIndex + 5, toIndex).strip();
        end = line.substring(line.indexOf("/to") + 3).strip();
        if (start.isBlank() || end.isBlank()) {
            throw new EmptyDurationException(); // Throws exception if durations are blank
        }
        task = new Event(title, "E", " (from: " + start, "to: " + end + ")");
        tasks.add(task);
        return task;
    }

    private static Task createDeadline(String line) throws ArrayIndexOutOfBoundsException, EmptyDateException, BlankTaskException {
        // Creates new deadline task
        Task task;
        String title;
        String end = "";
        title = line.split("/")[0].split(" ", 2)[1].strip();
        if (title.isBlank()) {
            throw new BlankTaskException(); // Throws exception if task is blank
        }
        int byIndex = line.indexOf("/by");
        if (byIndex == -1) {
            throw new ArrayIndexOutOfBoundsException(); // Throws exception if initializer not found
        }
        end = line.substring(byIndex + 3).strip();
        if (end.isBlank()) {
            throw new EmptyDateException(); // Throws exception if due date is blank
        }
        task = new Deadline(title, "(by: " + end + ")", "D");
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

    public static void listTask(Task task) {
        // Lists 1 the task in the list
        String taskType = task.getTaskType();
        String cross = " ";
        if (task.isDone) {
            cross = "X";
        }
        System.out.println("[" + taskType + "]" + "[" + cross + "] " + task.task + task.getStart() + task.getEnd());
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
            listTask(task);
            i += 1;
        }
        printSeparator();
    }

    public static void markTask(String[] line, String key) {
        // Marks or unmarks tasks
        int index = Integer.parseInt(line[1]) - 1;
        Task t = tasks.get(index);
        printSeparator();
        printName();
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
        listTask(t);
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
            markTask(phrases, key);
            break;
        case "todo":
        case "deadline":
        case "event":
            try {
                if (phrases[1].isBlank()) {
                    String triggerError = phrases[2];
                }
                createTask(key, line);
            } catch (ArrayIndexOutOfBoundsException e) { // Catch if only key is typed
                printSeparator();
                printName();
                System.out.println("What task are you trying to add??? Please enter a task");
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

    public static void main(String[] args) {
        String line;
        printSeparator();
        greeting();
        while (!hasEnded) {
            line = readInput();
            checkKey(line);
        }
    }
}
