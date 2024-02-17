package oley.commands;
import oley.tasks.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Oley {
    public static void initialise() {
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

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void addTask(String sentence) throws InputNotRecognizedException {
        if (sentence.startsWith("deadline")) {
            try {
                tasks.add(new Deadline(sentence.substring(9)));
            } catch (TimingNotFoundException e) {
                System.out.println("    OOPS, we have encountered an error!");
                System.out.println("    A specific deadline would be better for you to complete your task on time! ( •̀ .̫ •́ )✧");
                System.out.println("    You may use /by to indicate the time.");
                return;
            }
        } else if (sentence.startsWith("todo")) {
            tasks.add(new Todo(sentence.substring(5)));
        } else if (sentence.startsWith("event")) {
            try {
                tasks.add(new Event(sentence.substring(6)));
            } catch (TimingNotFoundException e) {
                System.out.println("    OOPS, we have encountered an error!");
                System.out.println("    A specific timing of the event would be clearer! ( •̀ .̫ •́ )✧");
                System.out.println("    You may use /from and /to to indicate the starting and ending time.");
                return;
            }
        }
        if (!(sentence.startsWith("deadline") || sentence.startsWith("todo") || sentence.startsWith("event"))) {
            throw new InputNotRecognizedException();
        }
        System.out.println("    " + "added: " + tasks.get(tasks.size() - 1));
        if (tasks.size() <= 1) {
            System.out.println("    Now you have " + tasks.size() + " task in the list.");
        } else {
            System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        }
    }

    public static void deleteTask(String sentence) {
        String[] deleteInstructions = sentence.split(" ");
        int number = Integer.parseInt(deleteInstructions[1]) - 1;
        String taskToBeDeleted = tasks.get(number).toString();
        tasks.remove(number);
        System.out.println("    Sure! (❛◡❛✿) I have removed this task:");
        System.out.println("    " + taskToBeDeleted);
        if (tasks.size() <= 1) {
            System.out.println("    Now you have " + tasks.size() + " task in the list.");
        } else {
            System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        }
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
        while (i < tasks.size()) {
            System.out.println("    " + (i + 1) + "." + tasks.get(i).toString());
            i++;
        }
    }

    public static void mark(String sentence) {
        String[] markInstructions = sentence.split(" ");
        int toBeMarked = Integer.parseInt(markInstructions[1]) - 1;
        if (toBeMarked >= tasks.size()) {
            System.out.println("    You have not created Task " + (toBeMarked + 1) + " yet. Jiayous. I will always support you. ฅ •ﻌ•♡");
        } else if (markInstructions[0].equals("mark")) {
            if (tasks.get(toBeMarked).checkDone()) {
                System.out.println("    This task has been marked as done already!");
            } else {
                tasks.get(toBeMarked).setDone();
                System.out.println("    Good job! I've marked this task as done:");
                System.out.println("    " + tasks.get(toBeMarked));
            }
        } else if (markInstructions[0].equals("unmark")) {
            if (!tasks.get(toBeMarked).checkDone()) {
                System.out.println("    This task hasn't been done yet!");
            } else {
                tasks.get(toBeMarked).setNotDone();
                System.out.println("    Sure~ I've marked this task as not done yet:");
                System.out.println("    " + tasks.get(toBeMarked));
            }
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
            } else if (message.contains("delete")) {
                try {
                    deleteTask(message);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("    OOPS, we have encountered an error!");
                    System.out.println("    The task you are trying to delete does not exist! (๑•́ ₃•̀๑)");
                }
                lineBreaker();
            } else {
                try {
                    addTask(message);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("    OOPS, we have encountered an error!");
                    System.out.println("    The description of a task cannot be empty! (๑•́ ₃•̀๑)");
                } catch (InputNotRecognizedException e) {
                    System.out.println("    So sorry, I do not understand the commands. I will try to improve!! (ง •̀_•́)ง");
                    System.out.println("    Meanwhile, you can use todo, deadline or event to indicate the type of tasks.");
                }
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
