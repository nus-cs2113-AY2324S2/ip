import java.util.Scanner;

public class KuroBot {

    private static Task[] tasks = new Task[100];
    private static int taskNum = 0;
    private static boolean isStart;
    private static final int LINE_LEN = 60;
    private static final String LINE =  "-".repeat(LINE_LEN);

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

    private static void start() {
        System.out.println(LINE);
        System.out.println("Hello! I'm KuroBot\n" + "What can I do for you?");
        System.out.println(LINE);
        isStart = true;
    }

    private static void printAddedTask(Task task) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.printTask());
        System.out.println("Now you have " + taskNum + " tasks in the list.");
        System.out.println(LINE);
    }

    private static void addTodo(String userInput) {
        Todo task = new Todo(userInput);
        tasks[taskNum++] = task;
        printAddedTask(task);
    }

    private static void addDeadline(String userInput) {
        //process input to get due date
        String[] words = userInput.split("/by", 2);
        String taskName = words[0];
        String by = words[1].strip();

        Deadline task = new Deadline(taskName, by);
        tasks[taskNum++] = task;
        printAddedTask(task);
    }

    private static void addEvent(String userInput) {
        //process input to get the duration
        String[] words = userInput.split("/from",2);
        String taskName = words[0];
        String[] period = words[1].split("/to",2);
        String from = period[0].strip();
        String to = period[1].strip();

        Event task = new Event(taskName, from, to);
        tasks[taskNum++] = task;
        printAddedTask(task);
    }

    private static void end() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
        System.out.println(LOGO);
        isStart = false;
    }

    private static void markTask(String userInput, boolean status) {
        //get the task number from input
        int i = Integer.parseInt(userInput);
        if (status) {
            tasks[i - 1].mark();
        } else {
            tasks[i - 1].unmark();
        }
    }

    public static void main(String[] args) {

        //display welcome message
        start();

        Scanner in = new Scanner(System.in);
        while (isStart) {
            String input = in.nextLine();
            //extract command keyword from input
            String[] words = input.split(" ",2);
            String command = words[0];
            switch (command) {
            case "bye":
                end();
                in.close();
                break;
            case "list":
                printTasks();
                break;
            case "mark":
                markTask(words[1],true);
                break;
            case "unmark":
                markTask(words[1],false);
                break;
            case "todo":
                addTodo(words[1]);
                break;
            case "deadline":
                addDeadline(words[1]);
                break;
            case "event":
                addEvent(words[1]);
                break;
            default:
                System.out.println(LINE);
                System.out.println("Please enter valid keyword");
                System.out.println(LINE);
                break;
            }
        }
    }
}
