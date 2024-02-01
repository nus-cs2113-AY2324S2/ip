import java.util.Scanner;

public class Faiz {

    static Scanner sc = new Scanner(System.in);
    static String input;
    static int numTasks = 0;
    private static final String HORIZONTAL_LINE = "\t_________________________________________\n";
    static Task[] tasks = new Task[100];

    private static void printHi() {
        System.out.println(HORIZONTAL_LINE + "\t Hello! I'm Faiz!\n\t What can I do for you?\n" + HORIZONTAL_LINE);
    }

    private static void printBye() {
        System.out.println(HORIZONTAL_LINE + "\t Bye. Hope to see you again soon!\n" + HORIZONTAL_LINE);
    }

    private static void echo() {
        String msg = (numTasks > 1) ? "tasks" : "task";
        System.out.println(HORIZONTAL_LINE + "\t Got it. I've added this task: \n\t   " + tasks[numTasks - 1].toString()
                + "\n\t Now you have " + numTasks + " " + msg + " in the list.\n" + HORIZONTAL_LINE);
    }

    private static void listTasks(Task[] tasks) {
        System.out.print(HORIZONTAL_LINE + "\t Here are the tasks in your list:\n");
        for (int i = 0; i < numTasks; i++) {
            System.out.println("\t " + (i + 1) + "." + tasks[i].toString());
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private static void markAsDone(int index) {
        tasks[index].markAsDone();
        System.out.print(HORIZONTAL_LINE + "\t Nice! I've marked this task as done:\n\t   "
                + tasks[index].toString() + System.lineSeparator() + HORIZONTAL_LINE);
    }

    private static void markAsUndone(int index) {
        tasks[index].markAsUndone();
        System.out.print(HORIZONTAL_LINE + "\t OK, I've marked this task as not done yet:\n\t   "
                + tasks[index].toString() + System.lineSeparator() + HORIZONTAL_LINE);
    }

    private static void addTodo(String input) {
        tasks[numTasks] = new Todo(input.substring(5));
        numTasks++;
        echo();
    }

    private static void addDeadline(String input) {
        String[] words = input.split("/by ");
        tasks[numTasks] = new Deadline(input.substring(9, input.indexOf("/")), words[1]);
        numTasks++;
        echo();
    }

    private static void addEvent(String input) {
        String[] words = input.split("/");
        tasks[numTasks] = new Event(input.substring(6, input.indexOf("/")), words[1].substring(5), words[2].substring(3));
        numTasks++;
        echo();
    }

    public static void main(String[] args) {
        printHi();
        while (true) {
            input = sc.nextLine();
            String[] words = input.split(" ");
            switch (words[0]) {
            case "bye":
                printBye();
                return;
            case "list":
                listTasks(tasks);
                continue;
            case "mark":
                markAsDone(Integer.parseInt(words[1]) - 1);
                continue;
            case "unmark":
                markAsUndone(Integer.parseInt(words[1]) - 1);
                continue;
            case "todo":
                //add todo
                addTodo(input);
                continue;
            case "deadline":
                //add deadline
                addDeadline(input);
                continue;
            case "event":
                //add event
                addEvent(input);
                continue;
            default:
                throw new IllegalStateException("Unexpected value: " + words[0]);
            }
        }
    }
}
