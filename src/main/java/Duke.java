import java.util.Scanner;

public class Duke {

    static Scanner sc = new Scanner(System.in);
    static String input;
    static int numTasks = 0;
    static final String HORIZONTAL_LINE = "\t_____________________________________________________________________\n";
    static Task[] tasks = new Task[100];

    private static String messageFormat(String message) {
        return HORIZONTAL_LINE + "\t " + message + System.lineSeparator() + HORIZONTAL_LINE;
    }

    private static void printHi() {
        System.out.print(messageFormat("Awakening.... \n\t Hi! I'm Faiz!\n\t What can I do for you?"));
    }

    private static void printBye() {
        System.out.print(messageFormat("Reformation.... \n\t Bye! Hope to see you again soon!"));
    }

    private static void echo() {
        String msg = (numTasks > 1) ? "tasks" : "task";
        System.out.print(messageFormat("Got it. I've added this task: \n\t   " + tasks[numTasks - 1].toString()
                + "\n\t Now you have " + numTasks + " " + msg + " in the list."));
    }

    private static void listTasks(Task[] tasks) {
        System.out.print(HORIZONTAL_LINE + "\t Here are the tasks in your list:\n");
        for (int i = 0; i < numTasks; i++) {
            System.out.println("\t " + (i + 1) + "." + tasks[i].toString());
        }
        System.out.print(HORIZONTAL_LINE);
    }

    private static void markAsDone(int index) {
        tasks[index].markAsDone();
        System.out.print(messageFormat("Nice! I've marked this task as done:\n\t   "
                + tasks[index].toString()));
    }

    private static void markAsUndone(int index) {
        tasks[index].markAsUndone();
        System.out.print(messageFormat("OK, I've marked this task as not done yet:\n\t   "
                + tasks[index].toString()));
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
            try {
                switch (words[0]) {
                case "bye":
                    printBye();
                    sc.close();
                    return;
                case "list":
                    listTasks(tasks);
                    continue;
                case "mark":
                    if (words.length == 1) {
                        throw new DukeException("Exceed Charge.... \n\t " +
                                "OOPS!!! The index of a task to be marked as done cannot be empty.");
                    }
                    int doneIndex = Integer.parseInt(words[1]) - 1;
                    if (doneIndex < 0 || doneIndex >= numTasks) {
                        throw new DukeException("Exceed Charge.... \n\t " +
                                "OOPS!!! Unable to mark invalid task as done.");
                    }
                    markAsDone(doneIndex);
                    continue;
                case "unmark":
                    if (words.length == 1) {
                        throw new DukeException("Exceed Charge.... \n\t " +
                                "OOPS!!! The index of a task to be marked as undone cannot be empty.");
                    }
                    int undoneIndex = Integer.parseInt(words[1]) - 1;
                    if (undoneIndex < 0 || undoneIndex >= numTasks) {
                        throw new DukeException("Exceed Charge.... \n\t " +
                                "OOPS!!! Unable to mark invalid task as undone.");
                    }
                    markAsUndone(undoneIndex);
                    continue;
                case "todo":
                    //add todo
                    if (words.length == 1) {
                        throw new DukeException("Exceed Charge.... \n\t " +
                                "OOPS!!! The description of a todo task cannot be empty.");
                    }
                    addTodo(input);
                    continue;
                case "deadline":
                    //add deadline
                    if (words.length == 1) {
                        throw new DukeException("Exceed Charge.... \n\t " +
                                "OOPS!!! The description of a deadline task cannot be empty.");
                    }
                    addDeadline(input);
                    continue;
                case "event":
                    //add event
                    if (words.length == 1) {
                        throw new DukeException("Exceed Charge.... \n\t " +
                                "OOPS!!! The description of an event task cannot be empty.");
                    }
                    addEvent(input);
                    continue;
                default:
                    throw new DukeException("ERROR.... \n\t A thousand apologies, but I don't know what that means.");
                }
            } catch (DukeException e) {
                System.out.print(messageFormat(e.getMessage()));
            }
        }
    }
}
