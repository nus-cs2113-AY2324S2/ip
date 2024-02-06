import java.util.Scanner;
public class Jane {
    public static void processInput(String input, TaskList taskList) {
        String[] inputPart = input.split(" ", 2);
        Task currentTask;
        switch (inputPart[0]) {
        case "list":
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.count; i++) {
                currentTask = taskList.list[i];
                System.out.println(currentTask.getSequence() + "." +
                        currentTask.getStatusIcon() + currentTask.getDescription());
            }
            break;
        case "mark":
            currentTask = taskList.list[Integer.parseInt(inputPart[1])-1];
            currentTask.isDone(true);
            System.out.println("Nice! I've marked this task as done:\n" +
                    currentTask.getStatusIcon() + currentTask.getDescription());
            break;
        case "unmark":
            currentTask = taskList.list[Integer.parseInt(inputPart[1])-1];
            currentTask.isDone(false);
            System.out.println("OK, I've marked this task as not done yet:\n" +
                    currentTask.getStatusIcon() + currentTask.getDescription());
            break;
        default:
            currentTask = new Task(input);
            taskList.addTask(currentTask);
            System.out.println("added: " + currentTask.getDescription());
            break;
        }
    }
    public static void main(String[] args) {
        String LOGO = " _____    _____    ____ _    _____ \n" +
                      "|____ |  |     |  |    | |  | ____|\n" +
                      "    | |  |  |  |  | |  | |  | |___ \n" +
                      " _  | |  |  _  |  | |  | |  |  ___|\n" +
                      "| |_| |  | | | |  | |  | |  | |___ \n" +
                      "|_____|  |_| |_|  |_| ___|  |_____|\n";
        String SEPARATOR = "____________________________________________________________\n";
        String GREET_MESSAGE = "Hello! I am Jane.\nWhat can I do for you?\n";
        String EXIT_MESSAGE = "Bye. Hope to see you again soon!\n";

        System.out.print(LOGO + SEPARATOR);
        System.out.print(GREET_MESSAGE + SEPARATOR);

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        TaskList taskList = new TaskList();

        while (!input.equals("bye")) {
            System.out.print(SEPARATOR);
            processInput(input, taskList);
            System.out.print(SEPARATOR);
            input = in.nextLine();
        }

        System.out.print(SEPARATOR + EXIT_MESSAGE + SEPARATOR);

    }
}
