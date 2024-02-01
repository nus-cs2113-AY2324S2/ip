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

    public static void echo(String line) {
        // Prints echo
        printSeparator();
        printName();
        tasks.add(new Task(line));
        System.out.println("added '" + line + "' to list");
        printSeparator();
    }

    public static void printTask(int index) {
        Task task = tasks.get(index);
        String cross = " ";
        if (task.isDone) {
            cross = "X";
        }
        System.out.println("[" + cross + "] " + task.task);
    }

    public static void listTasks() {
        // Prints the list of tasks
        printSeparator();
        printName();
        for (int i = 1; i < tasks.size() + 1; i += 1) {
            System.out.print(i + ".");
            printTask(i - 1);
        }
        printSeparator();
    }

    public static void markTask(String input) {
        String[] strArr = input.split(" ");
        int index = Integer.parseInt(strArr[1]) - 1;
        Task t = tasks.get(index);
        t.markDone();
        printSeparator();
        printName();
        if (t.isDone) {
            System.out.println("Wow good job at clearing a task! I,ve marked this task as done:");
        } else {
            System.out.println("More to do? I've marked this task as not done yet:");
        }
        printTask(index);
        printSeparator();
    }

    public static void checkKey(String key) {
        // Checks the keywords and runs the corresponding responses
        switch (key.split(" ")[0].toLowerCase()) {
        case "bye":
            endChat();
            break;
        case "list":
            listTasks();
            break;
        case "mark":
        case "unmark":
            markTask(key);
            break;
        default:
            echo(key);
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
