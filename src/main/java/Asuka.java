import java.util.Scanner;

enum Commands {
    list, mark, unmark, add, bye
}

public class Asuka {

    public static final int MAX_TASKS = 100;
    public static final String BREAKLINE = "____________________________________________________________";

    public static void main(String[] args) {
        // Welcome message
        printWelcomeMessage();

        //Initialise
        Commands command = null;
        int taskCount = 0;
        Task[] tasks = new Task[MAX_TASKS];
        Scanner myObj = new Scanner(System.in);

        //Loop through different commands, until "bye" command
        while (command != Commands.bye) {

            // Read input
            String input = myObj.nextLine();
            String[] inputs = input.split(" ");

            // Determine command
            command = getCommands(input, inputs);
            if (command == null) {
                printInvalidCommand();
                continue;
            }

            // Execute command
            switch (command) {
            case mark:
                mark(inputs, taskCount, tasks);
                break;
            case unmark:
                unmark(inputs, taskCount, tasks);
                break;
            case list:
                list(taskCount, tasks);
                break;
            case add:
                taskCount = updateTaskCount(input, tasks, taskCount);
                break;
            case bye:
                bye();
                myObj.close();
                myObj = null;
                break;
            default:
                break;
            }
        }
    }

    private static int updateTaskCount(String input, Task[] tasks, int taskCount) {
        String[] inputs;
        System.out.println(BREAKLINE);
        inputs = input.split(" ", 2);
        if (inputs[0].equalsIgnoreCase("todo")) {
            tasks[taskCount] = new Todo(inputs[1]);
        } else if (inputs[0].equalsIgnoreCase("deadline")) {
            inputs = inputs[1].split("/", 2);
            if (inputs.length != 2)
            {
                System.out.println("Incorrect format of time specification.");
                System.out.println(BREAKLINE);
                return taskCount;
            }
            tasks[taskCount] = new Deadline(inputs[0] + "(" + inputs[1] + ")");
        } else {
            inputs = inputs[1].split("/", 3);
            if (inputs.length != 3)
            {
                System.out.println("Incorrect format of time specification.");
                System.out.println(BREAKLINE);
                return taskCount;
            }
            tasks[taskCount] = new Event(inputs[0] + "(" + inputs[1] + inputs[2] + ")");
        }
        taskCount++;
        System.out.println("Got it. I've added this task:");
        tasks[taskCount - 1].printTask();
        if (taskCount == 1) {
            System.out.println("Now you got " + taskCount + " task in the list.");
        } else {
            System.out.println("Now you got " + taskCount + " tasks in the list.");
        }
        System.out.println(BREAKLINE);
        return taskCount;
    }

    private static void printInvalidCommand() {
        System.out.println(BREAKLINE);
        System.out.println("Invalid command");
        System.out.println(BREAKLINE);
    }

    private static void bye() {
        System.out.println(BREAKLINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(BREAKLINE);
    }

    private static void list(int taskCount, Task[] tasks) {
        System.out.println(BREAKLINE);
        for (int j = 0; j < taskCount; j++) {
            System.out.print(j + 1 + ". ");
            tasks[j].printTask();
        }
        System.out.println(BREAKLINE);
    }

    private static void unmark(String[] inputs, int taskCount, Task[] tasks) {
        int taskNumber;
        taskNumber = Integer.parseInt(inputs[1]);
        System.out.println(BREAKLINE);
        if (taskNumber > 0 && taskNumber <= taskCount) {

            System.out.println("Nice! I've marked this task as undone: ");
            tasks[taskNumber - 1].markAsUndone();
            tasks[taskNumber - 1].printTask();
        }
        else {
            System.out.println("Task index out of bound, the total number of task(s) you have now is: " + taskCount);
        }
        System.out.println(BREAKLINE);
    }

    private static void mark(String[] inputs, int taskCount, Task[] tasks) {
        int taskNumber;
        taskNumber = Integer.parseInt(inputs[1]);
        System.out.println(BREAKLINE);
        if (taskNumber > 0 && taskNumber <= taskCount) {
            System.out.println("Nice! I've marked this task as done: ");
            tasks[taskNumber - 1].markAsDone();
            tasks[taskNumber - 1].printTask();
        }
        else {
            System.out.println("Task index out of bound, the total number of task(s) you have now is: " + taskCount);
        }
        System.out.println(BREAKLINE);
    }

    private static Commands getCommands(String input, String[] inputs) {
        if ((inputs[0].equalsIgnoreCase("todo")) || (inputs[0].equalsIgnoreCase("deadline")) || (inputs[0].equalsIgnoreCase("event"))){
            if (inputs.length == 1)
            {
                return null;
            }
            return Commands.add;
        }
        if (inputs.length > 2) {
            return null;
        } else if (inputs.length == 2 && !inputs[0].equalsIgnoreCase("mark") && !inputs[0].equalsIgnoreCase("unmark")) {
            return null;
        }
        if (inputs.length == 2)
        {
            if (inputs[0].equalsIgnoreCase("mark") && Integer.parseInt(inputs[1]) <= MAX_TASKS && Integer.parseInt(inputs[1]) > 0) {
                return Commands.mark;
            } else if (inputs[0].equalsIgnoreCase("unmark") && Integer.parseInt(inputs[1]) <= MAX_TASKS && Integer.parseInt(inputs[1]) > 0) {
                return Commands.unmark;
            } else {
                return null;
            }
        }
        if (input.equalsIgnoreCase("bye")) {
            return Commands.bye;
        } else if (input.equalsIgnoreCase("list")) {
            return Commands.list;
        }
        return null;
    }

    private static void printWelcomeMessage() {
        System.out.println(BREAKLINE);
        System.out.println("Hello! I'm Asuka\nWhat can I do for you?");
        System.out.println(BREAKLINE);
    }
}
