import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private final ArrayList<Task> taskList;
    private final int MAX_TASKS = 100;
    private int taskCount;
    private final FileManager taskFileManager;


    public TaskList() {
        this.taskList = new ArrayList<>();
        this.taskCount = 0;
        this.taskFileManager = new FileManager();

        //taskFileManager.loadTasksFromFile(this);
    }

    public Task get(int index) {
        if (index >= 0 && index < taskCount) {
            return taskList.get(index);
        } else {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + taskCount);
        }
    }

    public int size() {
        return taskCount;
    }

    /**
     *  Seperates the unser input into the type of task
     *  and adds them to the list
     *
     * @param command user command to handle
     *
     **/
    private void processTaskCommand(String command) {
        try {
            String[] commandParts = command.split(" ", 2);
            String taskType = commandParts[0];

            switch (taskType) {
            case "todo":
                addTask(new Task(commandParts[1]), true);
                break;
            case "deadline":
                addDeadlineTask(commandParts, true);
                break;
            case "event":
                addEvent(commandParts, true);
                break;
            case "mark":
                markTask(commandParts);
                break;
            case "unmark":
                unmarkTask(commandParts);
                break;
            case "delete":
                deleteTask(Integer.parseInt(commandParts[1])-1);
                break;
            default:
                System.out.println("☹ Dobby does not understand."); // Default to addTask if not recognised
                break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid command format. Provide valid input");
        } catch (NumberFormatException e) {
            System.out.println("Invalid command format. Input a valid number");
        }
    }

    /**
     *  Adds task of "deadline" type
     *
     * @param commandParts user command to handle
     *
     **/
    public void addDeadlineTask(String[] commandParts, boolean userAdded) {
        String[] deadlineParts = commandParts[1].split("/by", 2);
        if (deadlineParts.length != 2) {
            System.out.println("Invalid deadline format! Use: deadline <<description>> /by <<deadline>>.");
        } else {
            addTask(new Deadline(deadlineParts[0], deadlineParts[1]), userAdded);
        }
    }

    /**
     *  Adds task of "event" type
     *
     * @param commandParts user command to handle
     *
     **/
    public void addEvent(String[] commandParts, boolean userAdded) {
        String[] eventparts = commandParts[1].split("/from", 2);
        if (eventparts.length != 2) {
            System.out.println("Invalid deadline format! Use: deadline <<description>> /by <<deadline>>.");
        } else {
            String[] endDateParts = eventparts[1].split("/to", 2);
            if (endDateParts.length != 2) {
                System.out.println("☹ OOPS!!! Invalid event format. " +
                        "Use 'event <description> /from <start date/time> /to <end date/time>'.");
            } else {
                addTask(new Events(eventparts[0], endDateParts[0], endDateParts[1]), userAdded );
            }
        }
    }

    /**
     *  marks specified task
     *
     *  @param commandParts user command to handle
     *
     **/
    private void markTask(String[] commandParts) {
        if (commandParts.length == 1 || !isValidIndex(Integer.parseInt(commandParts[1]) - 1)) {
            System.out.println("Invalid command format for marking a task. Use 'mark <index>'.");
        } else {
            markTaskIndex(Integer.parseInt(commandParts[1]) - 1);
        }
    }

    /**
     *  Unmarks specified task*
     *
     * @param commandParts user command to handle
     *
     **/
    private void unmarkTask(String[] commandParts) {
        if (commandParts.length == 1 || !isValidIndex(Integer.parseInt(commandParts[1]) - 1)) {
            System.out.println("Invalid command format for marking a task. Use 'mark <index>'.");
        } else {
            unmarkTaskIndex(Integer.parseInt(commandParts[1]) - 1);
        }
    }

    /**
     * Adds user input into taskList
     *
     * @param task input given by user
     */
    public void addTask(Task task, boolean userAdded) {
        try {
            if (taskList.size() < MAX_TASKS) {
                taskList.add(taskCount, task);
                taskCount += 1;
                taskFileManager.saveTasksToFile(this);
                if (userAdded) {
                    System.out.println("added: " + task);
                }
            } else {
                throw new TaskListFullException("TaskList if full");
            }
        } catch (TaskListFullException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void deleteTask(int index) {
        if (isValidIndex(index)) {
            Task deletedTask = taskList.remove(index);
            taskCount -= 1;
            System.out.println("Okay, Doby has removed this task:");
            System.out.println(" " + deletedTask);
            System.out.println("You now have " + taskList.size() + " tasks.");
        } else {
            System.out.println("Invalid  number");
        }
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
            System.out.println("  " + (i+1) + ". [" + taskList.get(i).getType() + "]" + taskList.get(i));
        }
        System.out.println("~~~~~~~~~~~~~~~~");
    }

    /**
     *  Checks if user inputted index is valid
     *
     * @param index integer input by user
     * @return  True if valid index and false if invalid
     *
     **/
    private Boolean isValidIndex(int index) {
        return index >= 0 && index < taskCount;
    }

    /**
     * 1. Checks if user inputs a valid index if valid
     * marks the stated task and shows which task is marked
     *
     * @param taskIndex index of task stored in array
     */
    public void markTaskIndex(int taskIndex) {
        if (!isValidIndex(taskIndex)) {
            System.out.println("Invalid number! Please try again");
        } else if (taskList.get(taskIndex).isDone()) {
            System.out.println("ERROR: task is already marked");
        } else {

            taskList.get(taskIndex).markTask();
            taskFileManager.saveTasksToFile(this);
            System.out.println("OK, Dobby has marked this task as done:");
            System.out.println("  " + taskList.get(taskIndex));
            System.out.println("~~~~~~~~~~~~~~~~");
            }
    }

    /**
     * Checks if user inputs a valid index
     * if valid, unmarks the stated task and shows the unmarked task
     *
     * @param taskIndex index of task stored in array
     */
    public void unmarkTaskIndex(int taskIndex) {
        if (!isValidIndex(taskIndex)) {
            System.out.println("Invalid number! Please try again");
        } else if (!taskList.get(taskIndex).isDone()) {
            System.out.println("The task is already unmarked");
        } else {

            taskList.get(taskIndex).unmarkTask();
            taskFileManager.saveTasksToFile(this);
            System.out.println("OK, Dobby marked this task as not done:");
            System.out.println("  " + taskList.get(taskIndex));
            printLineBreak();
        }
    }

    private static void printLineBreak() {
        System.out.println("~~~~~~~~~~~~~~~~");
    }

    public void userCommand() {
        while(true) {
            String command;
            Scanner in = new Scanner(System.in);
            command = in.nextLine().toLowerCase();
            String[] commandParts = command.split(" ", 2);

            switch (commandParts[0]) {
            case "bye":
                System.out.println("~~~~~~~~~~~~~~~~\nDobby say's BYE!");
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



}


