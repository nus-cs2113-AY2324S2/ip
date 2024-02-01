import java.util.Scanner;

public class Task {

    private String[] taskList;
    private static int MAX_TASKS = 100;
    protected boolean[] isMarked;
    private int taskCount;


    public Task() {
        this.taskList = new String[MAX_TASKS];
        this.isMarked = new boolean[MAX_TASKS];
        this.taskCount = 0;
    }

    /**
     * Adds user input into taskList
     *
     * @param task input given by user
     */
    public void addTask(String task) {
        taskList[taskCount] = task;
        isMarked[taskCount] = false;
        taskCount += 1;
        System.out.println("added: " + task);
    }

    /**
     * list out current tasks and displays task status
     */
    public void listTasks() {
        if (taskCount == 0) {
            System.out.println("Dobby has no tasks :(");
            return;
        }
        System.out.println("List\n~~~~~~~~~~~~~~~~");
        for (int i = 0 ; i < taskCount ; i += 1) {
            System.out.println("  " + (i+1) + ". " + getStatusIcon(i) + " " + taskList[i]);
        }
        System.out.println("~~~~~~~~~~~~~~~~");
    }

    /**
     * returns "[X]" if task is mark done and
     * "[ ]" if not marked
     *
     * @param taskIndex index of task stored in array
     * @return task status
     */
    public String getStatusIcon(int taskIndex) {
        if (isMarked[taskIndex]) {
            return "[X]";
        }
        return "[ ]";
    }

    /**
     * 1. Checks if user inputs a valid index if valid
     * marks the stated task and shows which task is marked
     *
     * @param taskIndex index of task stored in array
     */
    public void markTask(int taskIndex) {
        if (taskIndex < 1 || taskIndex > taskCount) {
            System.out.println("Invalid number> Please try again");
        } else if (isMarked[taskIndex-1]) {
            System.out.println("ERROR: task is already marked");
        } else {
            isMarked[taskIndex-1] = true;
            System.out.println("OK, I've marked this task as done:");
            System.out.println("  " + getStatusIcon(taskIndex - 1) + " " + taskList[taskIndex - 1]);
            System.out.println("~~~~~~~~~~~~~~~~");
        }
    }

    /**
     * Checks if user inputs a valid index
     * if valid, unmarks the stated task and shows the unmarked task
     *
     * @param taskIndex index of task stored in array
     */
    public void unmarkTask(int taskIndex) {
        if (taskIndex < 1 || taskIndex > taskCount) {
            System.out.println("Invalid number> Please try again");
        } else if (!isMarked[taskIndex-1]) {
            System.out.println("The task is already unmarked");
        } else {
            isMarked[taskIndex-1] = false;
            System.out.println("OK, I've marked this task as not done:");
            System.out.println("  " + getStatusIcon(taskIndex - 1) + " " + taskList[taskIndex - 1]);
            System.out.println("~~~~~~~~~~~~~~~~");
        }
    }

    public void userCommand() {
        while(true) {
            String command;
            Scanner in = new Scanner(System.in);
            command = in.nextLine().toLowerCase();

            switch (command) {
            case "bye":
                System.out.println("~~~~~~~~~~~~~~~~\nDobby say's BYE!");
                return;
            case "list":
                listTasks();
                break;
            default:
                if (command.startsWith("add")) {
                    addTask(command.substring(4).trim());
                } else if (command.startsWith("mark")) {
                    int taskIndex = Integer.parseInt(command.split(" ")[1]);
                    markTask(taskIndex);
                } else if (command.startsWith("unmark")) {
                    int taskIndex = Integer.parseInt(command.split(" ")[1]);
                    unmarkTask(taskIndex);
                } else {
                    addTask(command);
                }
                break;
            }

        }
    }



}
