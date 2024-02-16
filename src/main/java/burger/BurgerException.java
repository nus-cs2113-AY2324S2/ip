package burger;

import static burger.Burger.printLine;

public class BurgerException extends Exception{
    public static void printInvalidDeadlineInputMessage() {
        printLine();
        System.out.println("Oi, the input is wrong!");
        System.out.println("Format: deadline {deadline task} /by {date}");
        printLine();
    }

    public static void printInvalidEventInputMessage() {
        printLine();
        System.out.println("Oi, the input is wrong!");
        System.out.println("Format: event {event task} /from {date} /to {date}");
        printLine();
    }

    public static void printEmptyDescription() {
        printLine();
        System.out.println("UwU~ Where is your description?");
        System.out.println("Format: {command} {description}");
        printLine();
    }

    public static void printOutOfIndexMessage() {
        printLine();
        System.out.println("Tsk... Out of index, do you know how to count?");
        printLine();
    }
}
