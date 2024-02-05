import java.util.Scanner;
public class Dul {
    public static final int maxtasks = 100;
    public static String[] tasks = new String[maxtasks];
    public static int taskCount = 0;

    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| |\n"
                + "| | | | | | | |\n"
                + "| |_| | |_| | |\n"
                + "|____/ \\__,_|_|\n";
        System.out.println("Hello I'm\n" + logo);
        System.out.println("What can I do for you?");
        String guyInput = "";
        Scanner in = new Scanner(System.in);
        while (!guyInput.equals("bye")) {
            guyInput = in.nextLine();
            processInput(guyInput);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void processInput(String input) {
        if (input.equals("list")) {
            listTasks();
        } else if (!input.equals("bye")) {
            addTask(input);
        }
    }

    public static void listTasks() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }

    public static void addTask(String task) {
        tasks[taskCount] = task;
        taskCount++;
        System.out.println("added: " + task);
    }
}


