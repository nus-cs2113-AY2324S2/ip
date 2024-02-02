import java.util.Scanner;

public class KuroBot {

    private static Task[] tasks = new Task[100];
    private static int taskNum = 0;

    private static final String LINE =  "-".repeat(60);

    private static final String LOGO =
            " ___   ___    ___    ___ \n"
                    + "|   |/   /   |  |   |  | \n"
                    + "|       /    |  |   |  | \n"
                    + "|   |\\   \\   |_ |___| _| \n"
                    + "|___| \\___\\    |_____|   \n";


    private static void printTasks() {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskNum; i ++){
            System.out.println(i+1 + "." + tasks[i].printTask());
        }
        System.out.println(LINE);
    }

    private static void intro() {
        System.out.println(LINE);
        System.out.println("Hello! I'm KuroBot\n" + "What can I do for you?");
        System.out.println(LINE);
    }

    private static void printAddedTask(Task task) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.printTask());
        System.out.println("Now you have " + taskNum + " tasks in the list.");
        System.out.println(LINE);
    }

    private static void addTodo(String userInput) {
        String taskName = userInput.substring(5);
        Todo t = new Todo(taskName);
        tasks[taskNum++] = t;
        printAddedTask(t);
    }

    private static void addDeadline(String userInput) {
        String[] words = userInput.split("/");
        String taskName = words[0].substring(9);
        String by = words[1].substring(3);
        Deadline t = new Deadline(taskName, by);
        tasks[taskNum++] = t;
        printAddedTask(t);
    }

    private static void addEvent(String userInput) {
        String[] words = userInput.split("/");
        String taskName = words[0].substring(6);
        String from = words[1].substring(5);
        String to = words[2].substring(3);
        Event t = new Event(taskName, from, to);
        tasks[taskNum++] = t;
        printAddedTask(t);
    }

    private static void end() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
        System.out.println(LOGO);
    }

    private static void markTask(String userInput, boolean status) {
        String[] words = userInput.split(" ");
        int i = Integer.parseInt(words[1]);
        if (status) {
            tasks[i - 1].mark();
        } else {
            tasks[i - 1].unmark();
        }
    }

    public static void main(String[] args) {

        intro();

        Scanner in = new Scanner(System.in);
        while (true) {
            String input = in.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                printTasks();
            } else if (input.startsWith("mark")) {
                markTask(input,true);
            } else if (input.startsWith("unmark")) {
                markTask(input,false);
            } else if (input.startsWith("todo")) {
                addTodo(input);
            } else if (input.startsWith("deadline")) {
                addDeadline(input);
            } else if (input.startsWith("event")) {
                addEvent(input);
            } else {
                System.out.println(LINE);
                System.out.println("Please enter valid keyword");
                System.out.println(LINE);
            }
        }

        end();
    }
}
