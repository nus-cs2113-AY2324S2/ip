import java.util.Scanner;
public class Mona {
    public static void addTask(String description, Task[] tasks) {
        int index = Task.noOfTasks;
        tasks[index] = new Task(description);

        printHorizontalLine();
        System.out.println("added: " + description);
        printHorizontalLine();
    }
    public static void printUnmarkStatement(int index, Task[] tasks) {
        printHorizontalLine();

        System.out.println("OK,  I've marked this as not done yet:");
        System.out.println("[" + tasks[index].getStatusIcon() + "] "
                + tasks[index].description);

        printHorizontalLine();
    }
    public static void printMarkStatement(int index, Task[] tasks) {
        printHorizontalLine();

        System.out.println("Nice! I've marked this as done:");
        System.out.println("[" + tasks[index].getStatusIcon() + "] "
                + tasks[index].description);

        printHorizontalLine();
    }
    public static void printList(Task[] tasks) {
        printHorizontalLine();

        int index = 1;
        for (Task task: tasks) {
            if (task != null) {
                System.out.println(Integer.toString(index) + "."
                        + "[" + task.getStatusIcon() + "] "
                        + task.description);
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

        Task[] tasks = new Task[100];        //initialize an array of Tasks, to act as a list

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();         // read in 1st command from user

        while (true) {
            switch(line){
            case ("list"):
                printList(tasks);
                break;
            case ("bye"):
                exit();
                return;
            default:
                if (line.startsWith("mark")) {
                    int i = Integer.parseInt(line.substring(5));
                    tasks[i - 1].markAsDone();
                    printMarkStatement(i - 1, tasks);
                }
                else if (line.startsWith("unmark")) {
                    int i = Integer.parseInt(line.substring(7));
                    tasks[i - 1].markAsNotDone();
                    printUnmarkStatement(i - 1, tasks);
                } else {
                    addTask(line, tasks); // if command cannot be extracted, add text to list
                }
            }
            line = in.nextLine();
        }
    }
}
