import java.util.Scanner;
public class Jane {
    public static void main(String[] args) {
        String LOGO = " _____    _____    ____ _    _____ \n" +
                      "|____ |  |     |  |    | |  | ____|\n" +
                      "    | |  |  |  |  | |  | |  | |___ \n" +
                      " _  | |  |  _  |  | |  | |  |  ___|\n" +
                      "| |_| |  | | | |  | |  | |  | |___ \n" +
                      "|_____|  |_| |_|  |_| ___|  |_____|\n";
        static final String SEPARATOR = "____________________________________________________________\n";
        static final String GREET_MESSAGE = "Hello! I am Jane.\nWhat can I do for you?\n";
        static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!\n";

        System.out.print(LOGO + SEPARATOR);
        System.out.print(GREET_MESSAGE + SEPARATOR);

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String[] inputParts = input.split(" ", 2);

        TaskList taskList = new TaskList();

        Task currentTask;

        switch (inputParts[0]) {
        case "bye":
            // exit
            break;
        case "list":
            // print out full list
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.count; i++) {
                currentTask = taskList.list[i];
                System.out.println(currentTask.getSequence() + "." +
                        currentTask.getStatusIcon() + currentTask.getDescription());
            }
            break;
        case "mark":
            // mark task as done
            currentTask = taskList.list[Integer.parseInt(input[1])-1];
            currentTask.isDone(true);
            System.out.println("Nice! I've marked this task as done:\n" +
                    currentTask.getStatusIcon() + currentTask.getDescription());
            break;
        case "unmark":
            // mark task as undone
            break;
        default:
            // adding new task
            Task t = new Task(input);
            taskList.addTask(t);
            System.out.println("added: " + t.getDescription());
            break;
        }
        while (!input.equals("bye")) {
            System.out.print(SEPARATOR);
            /* Task currentTask;
            /if (input.equals("list")) {
                //System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.count; i++) {
                    currentTask = taskList.list[i];
                    System.out.println(currentTask.getIndex() + "." +
                            currentTask.getStatusIcon() + currentTask.getDescription());
                } */
            } else if (input.startsWith("mark")) {
                input = input.replace("mark ", "");
                currentTask = taskList.list[Integer.parseInt(input)-1];
                currentTask.isDone(true);
                System.out.println("Nice! I've marked this task as done:\n" +
                        currentTask.getStatusIcon() + currentTask.getDescription());
            } else if (input.startsWith("unmark")) {
                input = input.replace("unmark ", "");
                currentTask = taskList.list[Integer.parseInt(input)-1];
                currentTask.isDone(false);
                System.out.println("OK, I've marked this task as not done yet:\n" +
                        currentTask.getStatusIcon() + currentTask.getDescription());
            /*} else {
                taskList.addTask(t);
                System.out.println("added: " + t.getDescription());
            }*/
            System.out.print(SEPARATOR);
            input = in.nextLine();
            t = new Task(input);
        }

        System.out.print(SEPARATOR + EXIT_MESSAGE + SEPARATOR);

    }
}
