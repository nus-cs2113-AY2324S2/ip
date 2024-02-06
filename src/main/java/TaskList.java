import java.util.Scanner;
public class TaskList {
    private final Task[] taskList;
    private static final int MAX_TASKS = 100;
    private int taskCount;

    public TaskList() {
        this.taskList = new Task[MAX_TASKS];
        this.taskCount = 0;
    }

    public void addTask(Task task) {
        taskList[taskCount] = task;
        taskCount += 1;
        System.out.println("added: " + task);
    }

    public void listTasks() {
        if (taskCount == 0) {
            System.out.println("Dobby has no tasks :(");
            return;
        }
        System.out.println("List\n~~~~~~~~~~~~~~~~");
        for (int i = 0 ; i < taskCount ; i += 1) {
            System.out.println("  " + (i+1) + ". " + taskList[i]);
        }
        System.out.println("~~~~~~~~~~~~~~~~");
    }

    public void markTask(int taskIndex) {
        Todo.markTask(taskList, taskIndex);
    }

    public void unmarkTask(int taskIndex) {
        Todo.unmarkTask(taskList, taskIndex);
    }

    public void userCommand() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String command = scanner.nextLine().toLowerCase();

            switch (command) {
            case "bye":
                System.out.println("~~~~~~~~~~~~~~~~\nDobby says BYE!");
                return;
            case "list":
                listTasks();
                break;
            default:
                processTaskCommand(command);
                break;
            }
        }
    }

    private void processTaskCommand(String command) {
        String[] commandParts = command.split(" ", 2);
        String taskType = commandParts[0];

        switch (taskType) {
        case "todo":
            addTask(new Task(commandParts[1]));
            break;
        case "mark":
            if (commandParts.length > 1) {
                markTask(Integer.parseInt(commandParts[1]) - 1);
            } else {
                System.out.println("Invalid command format for marking a task. Use 'mark <index>'.");
            }
            break;
        case "unmark":
            if (commandParts.length > 1) {
                unmarkTask(Integer.parseInt(commandParts[1]) - 1);
            } else {
                System.out.println("Invalid command format for unmarking a task. Use 'unmark <index>'.");
            }
            break;
        default:
            addTask(new Task(command)); // Default to addTask if not recognized
            break;
        }
    }



}


