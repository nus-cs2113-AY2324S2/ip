package Yoj.ui;
import Yoj.List.*;
public class Ui {
    public static void printLine() {
        System.out.println("________________________________________");
    }
    public static void printShortLine() {
        System.out.println("_____________");
    }
    public static void printHello() {
        String logo =
                "__   __   ___    _____ \n"
                        + "\\ \\ / /  / _ \\  | ___ |\n"
                        + " \\ Y /  | | | |     | | \n"
                        + "  \\ /   | | | |     | | \n"
                        + "  | |   | |_| |  ___| | \n"
                        + "  |_|    \\___/  |____/          \n";
        printLine();
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm YOJ");
        System.out.println("What can I do for you?");
        printShortLine();
    }
    public static void addTaskMessage() {
        System.out.println("Got it. I've added this task:");
        System.out.println(List.tasks.get(List.tasks.size() - 1));
        System.out.println("Now you have " + List.tasks.size() + " tasks in the list.");
    }
    public static void markDoneMessage() {
        System.out.println("okiee! I've marked this task as done: ");
    }
    public static void markUndoneMessage() {
        System.out.println("alright I've marked this task as not done yet, do rmb to do it soon: ");
    }
    public static void printList() {
        if (List.tasks.size() == 0) {
            System.out.println("the list is currently empty...");
            System.out.println("add your tasks below :)");
        } else {
            for (int i = 0; i < List.tasks.size(); i++) {
                System.out.println(i + 1 + ". " + List.tasks.get(i).toString());
            }
        }
    }
    public static void byeMessage() {
        System.out.println("bye bye!! hope to see u soon :)");
    }
}
