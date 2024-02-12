import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Krot {
    static String botName = "Krot"; // Static variable for the bot's name
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
        System.out.println(botName + ":");
    }

    public static void greeting() {
        // Prints out the standard greeting
        System.out.println("Hello! I'm " + botName);
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
        // Prints echo
        printSeparator();
        printName();
        String title;
        Task task;
        System.out.println("More tasks to do! I've added:");
        if (key.equalsIgnoreCase("todo")) {
            title = line.split(" ", 2)[1];
            task = new Todo(title, "T");
            tasks.add(task);
        } else if (key.equalsIgnoreCase("deadline")) {
            title = line.split("/")[0].split(" ", 2)[1].strip();
            String end = " (by:" + line.split("/by", 2)[1] + ")";
            task = new Deadline(title, end, "D");
            tasks.add(task);
        } else {
            title = line.split("/")[0].split(" ", 2)[1].strip();
            String[] datesArr = line.split("/");
            String start = "from: " + datesArr[1].split(" ", 2)[1];
            String end = "to: " + datesArr[2].split(" ", 2)[1];
            task = new Event(title, "E", " (" + start, end + ")");
            tasks.add(task);
        }
        System.out.println("[" + task.getTaskType() + "]" + "[" + (task.isDone ? "x" : " ") + "] " + task.task + task.getStart() + task.getEnd());
        System.out.println("Get to working, you now have " + tasks.size() + " tasks in the list!");
        printSeparator();
    }

    public static void printTask(Task task) {
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
        for (Task task : tasks) {
            System.out.print(i + ".");
            printTask(task);
            i += 1;
        }
        printSeparator();
    }

    public static void markTask(String[] line) {
        int index = Integer.parseInt(line[1]) - 1;
        Task t = tasks.get(index);
        t.markDone();
        printSeparator();
        printName();
        if (t.isDone) {
            System.out.println("Wow good job at clearing a task! I,ve marked this task as done:");
        } else {
            System.out.println("More to do? I've marked this task as not done yet:");
        }
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
            markTask(phrases);
            break;
        case "todo":
        case "deadline":
        case "event":
            createTask(key, line);
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
