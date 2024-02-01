import java.util.Scanner;

public class Omoh {
    //static List declared here so that whole Omoh class
    //can use the same size 100 array for myTaskList
    private static List myTaskList = new List();

    public static void main(String[] args) {
        printHorizontalLine();
        System.out.println("     Hello! I'm Omoh");
        System.out.println("     What can I do for you?");
        printHorizontalLine();
        readUserInput();
        bye();

    }

    //Method that prints horizontal line using "_" char
    private static void printHorizontalLine() {
        for (int j = 0; j < 4; j++) {
            System.out.print(" ");
        }
        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.println("");
        return;
    }

    //Method reads in what user types
    //If bye is not typed, store it as task
    //If bye is typed, function exits
    public static void readUserInput() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equalsIgnoreCase("bye")) {
            if (line.equalsIgnoreCase("list")) {
                printAllTasks();
                line = in.nextLine();
            } else {
                printHorizontalLine();
                System.out.print("     ");
                myTaskList.setAndPrintTask(line);
                printHorizontalLine();
                line = in.nextLine();
            }
        }
    }

    //Method that echoes whatever user types.
    //Stops when string "bye" is entered.
    public static void echo() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equalsIgnoreCase("bye")) {
            printHorizontalLine();
            System.out.print("     ");
            System.out.println(line);
            printHorizontalLine();
            line = in.nextLine();
        }
    }

    //Method that prints all the tasks stored in myTaskList array
    public static void printAllTasks() {
        printHorizontalLine();
        myTaskList.getAllTasks();
        printHorizontalLine();
    }

    //Method that prints the bye message
    public static void bye() {
        printHorizontalLine();
        System.out.println("     Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}
