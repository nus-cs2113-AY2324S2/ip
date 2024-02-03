import java.util.Scanner;
public class Yuki {

    // create array of Tasks (use of polymorphism)
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void addTask(Task t) {
        tasks[taskCount] = t;
        taskCount++;
        System.out.println(t);
    }

    public static void reportNumberOfTasks() {
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public static void main(String[] args) {
        Utils.printWelcomeMessage();

        // accept user input
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        // variables for usage in main loop
        String command;
        int index_task;
        String[] data;
        String description;

        while (!line.equals("exit")) {
            Utils.printLine();

            command = line.split(" ")[0];

            switch(command) {
            case "list":
                System.out.println("Wake up your idea and do these tasks:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon() + "] "
                            + tasks[i].taskType + " " + tasks[i].description);
                }
                break;

            case "mark":
                index_task = Integer.parseInt(line.split(" ")[1]) - 1;
                tasks[index_task].markAsDone();
                break;

            case "unmark":
                index_task = Integer.parseInt(line.split(" ")[1]) - 1;
                tasks[index_task].markAsUndone();
                break;

            case "todo":
                data = Parser.parseInput(line.substring(command.length()));
                description = data[0];
                addTask(new Todo(description));
                reportNumberOfTasks();
                break;

            case "deadline":
                data = Parser.parseInput(line.substring(command.length()));
                description = data[0] + " (by:" + data[1] + ")";
                addTask(new Deadline(description));
                reportNumberOfTasks();
                break;

            case "event":
                data = Parser.parseInput(line.substring(command.length()));
                description = data[0] + " (from: " + data[1] + " to: " + data[2] + ")";
                addTask(new Event(description));
                reportNumberOfTasks();
                break;

            default:
                Utils.printInstructions();
                break;
            }

            Utils.printLine();
            line = in.nextLine();
        }
        Utils.printExitMessage();
    }
}
