import java.util.Scanner;
public class Oley {
    public static Task[] tasks = new Task[100];
    public static int taskNumber = 0;

    public static void addTask(String sentence){
        if (sentence.startsWith("deadline")) {
            tasks[taskNumber] = new Deadline(sentence.substring(9));
        } else if (sentence.startsWith("todo")) {
            tasks[taskNumber] = new Todo(sentence.substring(5));
        } else if (sentence.startsWith("event")) {
            tasks[taskNumber] = new Event(sentence.substring(6));
        }
        System.out.println("    " + "added: " + tasks[taskNumber].getTaskName());
        taskNumber++;
        if (taskNumber <= 1) {
            System.out.println("    Now you have " + taskNumber + " task in the list.");
        } else {
            System.out.println("    Now you have " + taskNumber + " tasks in the list.");
        }
    }

    public static void initialise(){
        String logo = "  _____  __       \n"
                + " /  _  \\|  | ____ ___  ___ \n"
                + "|  | |  |  |/ ___ \\  \\/  /\n"
                + "|  |_|  |  |  ____/\\    /\n"
                + " \\_____/|__|\\_____|/   /\n"
                + "                  /___/";
        System.out.println("Greetings from\n" + logo);
        lineBreaker();
        System.out.println("    Hello, I'm your cute and lovely friend Oley.");
        System.out.println("    What can I do for you?");
        lineBreaker();
    }

    public static void exit() {
        System.out.println("    Bye~ Feel free to talk to me anytime. I will always be here waiting for you. ฅʕ•̫͡•");
    }

    public static void lineBreaker() {
        System.out.println(" ");
        System.out.println("    🌙~~~~~♥~~~~~♥~~~~~♥~~~~~♥~~~~~♥~~~~~♥~~~~~🌙");
    }

    public static void printTask() {
        System.out.println("    Here are the tasks in your list:");
        int i = 0;
        while (i < taskNumber) {
            System.out.println("    " + (i + 1) + "." + tasks[i].toString());
            i++;
        }
    }

    public static void mark(String sentence) {
        String[] markInstructions = sentence.split(" ");
        int toBeMarked = Integer.parseInt(markInstructions[1]) - 1;
        if (toBeMarked >= taskNumber) {
            System.out.println("You have not created Task " + (toBeMarked + 1) + " yet. Jiayous. I will always support you. ฅ •ﻌ•♡");
        } else if (markInstructions[0].equals("mark")) {
            tasks[toBeMarked].setDone();
            System.out.println("    Good job! I've marked this task as done:");
            System.out.println("    " + tasks[toBeMarked]);
        } else if (markInstructions[0].equals("unmark")) {
            tasks[toBeMarked].setNotDone();
            System.out.println("    Sure~ I've marked this task as not done yet:");
            System.out.println("    " + tasks[toBeMarked]);
        }
    }

    public static void instructions() {
        Scanner in = new Scanner(System.in);
        String message;
        message = in.nextLine();
        lineBreaker();
        while (message != null) {
            if (message.equals("bye")) {
                exit();
                return;
            } else if (message.equals("list")) {
                printTask();
                lineBreaker();
            } else if (message.contains("unmark") || message.contains("mark")) {
                mark(message);
                lineBreaker();
            } else {
                addTask(message);
                lineBreaker();
            }
            message = in.nextLine();
            lineBreaker();
        }
    }

    public static void main(String[] args) {
        initialise();
        instructions();
    }
}
