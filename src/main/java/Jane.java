import java.util.Scanner;
public class Jane {
    public static void processInput(String input, TaskList taskList) {
        String[] inputPart = input.split(" ", 2);
        Task currentTask;
        switch (inputPart[0]) {
        case "todo":
            Todo todo = new Todo(inputPart[1]);
            taskList.addTask(todo);
            System.out.println("Got it. I've added this task:\n" + todo + "\n" +
                    "Now you have " + taskList.getCount() + " tasks in the list.");
            break;
        case "deadline":
            String[] deadlineInput = inputPart[1].split("/", 2);
            Deadline deadline = new Deadline(deadlineInput[0],
                    deadlineInput[1].replace("/", "").replace("by ", ""));
            taskList.addTask(deadline);
            System.out.println("Got it. I've added this task:\n" + deadline + "\n" +
                    "Now you have " + taskList.getCount() + " tasks in the list.");
            break;
        case "event":
            String[] eventInput = inputPart[1].split("/", 3);
            Event event = new Event(eventInput[0],
                    eventInput[1].replace("/", "").replace("from ", ""),
                    eventInput[2].replace("/to", "").replace("to ", ""));
            taskList.addTask(event);
            System.out.println("Got it. I've added this task:\n" + event + "\n" +
                    "Now you have " + taskList.getCount() + " tasks in the list.");
            break;
        case "list":
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.count; i++) {
                currentTask = taskList.list[i];
                System.out.println(currentTask.getSequence() + "." + currentTask);
            }
            break;
        case "mark":
            currentTask = taskList.list[Integer.parseInt(inputPart[1])-1];
            currentTask.isDone(true);
            System.out.println("Nice! I've marked this task as done:\n" + currentTask);
            break;
        case "unmark":
            currentTask = taskList.list[Integer.parseInt(inputPart[1])-1];
            currentTask.isDone(false);
            System.out.println("OK, I've marked this task as not done yet:\n" + currentTask);
            break;
        default:
            // handle errors for input not starting with the correct keyword
            System.out.println("Usage: Please enter in the form of:\n" + "todo <TASK>\n" +
                    "deadline <TASK>\n" + "event <TASK>\n" + "mark <TASK_SEQUENCE>\n" + "unmark <TASK_SEQUENCE>");
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
