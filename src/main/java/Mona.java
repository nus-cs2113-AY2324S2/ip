import java.util.Scanner;
public class Mona {

    public static int noOfTasks; //static variable storing the number of tasks present in list

    public static void addTask(String task, String[] list) {
        list[noOfTasks] = task;
        noOfTasks += 1;

        printHorizontalLine();
        System.out.println("added: " + task);
        printHorizontalLine();
    }
    public static void printList(String[] list) {
        printHorizontalLine();

        int index = 1;
        for (String task: list) {
            if (task != null) {
                System.out.println(Integer.toString(index) + ". " + task);
                index += 1;
            }
        }

        printHorizontalLine();
    }
    public static void printHorizontalLine() {
        for (int i = 0; i < 59; i++) {
            System.out.print("_");
        }
        System.out.println("_");
    }
    public static void greet() {
        printHorizontalLine();

        System.out.println("Hello! I'm Mona");
        System.out.println("What can I do for you?");

        printHorizontalLine();
    }
    public static void exit() {
        printHorizontalLine();

        System.out.println("Bye. Hope to see you again soon!");

        printHorizontalLine();
    }
    public static void main(String[] args) {
        String logo = "___  ___                  \n"
                + "|  \\/  |                  \n"
                + "| .  . | ___  _ __   __ _ \n"
                + "| |\\/| |/ _ \\| '_ \\ / _` |\n"
                + "| |  | | (_) | | | | (_| |\n"
                + "\\_|  |_/\\___/|_| |_|\\__,_|\n";
        System.out.println("Hello from\n" + logo);

        greet();

        String[] list = new String[100];
        noOfTasks = 0;

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();         // read in 1st command from user

        while (true) {
            switch(line){
            case ("list"):
                printList(list);
                break;
            case ("bye"):
                exit();
                return;
            default:
                addTask(line, list);
            }
            line = in.nextLine();
        }
    }
}
