package util;

import tasks.Task;

public class Ui {

    public static void greetingMessage() {
        System.out.println("Good evening. I'm Nocturne.");
        System.out.println("What ails you on this fine day?");
    }

    public static void farewellMessage() {
        System.out.println("Farewell. And may the fortunes be ever in your favour.");
    }

    public static void indexOutOfBoundsMessage() {
        System.out.println("You are trying to access forbidden territory. Tread carefully.");
    }

    public static void emptyListMessage() {
        System.out.println("Your list is empty. Try again, when you have become more productive.");
    }

    public static void missingSlashMessage() {
        System.out.println("You are missing a /. Do not let this happen again.");
    }

    public static void printTask(Task task) {
        System.out.println("  " + task);
    }
}
