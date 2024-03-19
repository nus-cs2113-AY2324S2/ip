import java.util.ArrayList;

/**
 * Represents a command to execute various tasks.
 */
public class Command {

    private final CommandType type;

    private final String[] args;

    /**
     * Constructs a command with the specified type and arguments.
     *
     * @param type The type of command.
     * @param args The arguments for the command (example: the index of the task).
     */
    public Command(CommandType type, String[] args) {
        this.type = type;
        this.args = args;
    }

    /**
     * Executes the specific type of command based on its type.
     * This method handles various commands such as adding tasks,
     * listing tasks, marking tasks as completed, unmarking tasks,
     * deleting tasks, finding tasks, and exiting the program.
     * Each command type is processed differently.
     *
     * @param tasks The current total list of task.
     */
    public void execute(TaskList tasks) {
        try {
            switch (type) {
            case TODO:
            case DEADLINE:
            case EVENT:
                if (args.length < 1) {
                    throw new StringIndexOutOfBoundsException();
                } else if (tasks.getSize() >= 100) {
                    throw new ArrayIndexOutOfBoundsException("Task list is full.");
                }
                addTask(type, String.join(" ", args), tasks.getAllTasks(), tasks.getSize());
                Storage.saveTasks(tasks.getSize(), tasks.getAllTasks());
                break;
            case LIST:
                Ui.printTasks(tasks.getAllTasks(), tasks.getSize());
                break;
            case MARK:
                if (args.length < 1) {
                    throw new IllegalArgumentException("Missing task index to mark.");
                }
                markTask(args, tasks.getAllTasks());
                Storage.saveTasks(tasks.getSize(), tasks.getAllTasks());
                break;
            case UNMARK:
                if (args.length < 1) {
                    throw new IllegalArgumentException("Missing task index to unmark.");
                }
                unmarkTask(args, tasks.getAllTasks());
                Storage.saveTasks(tasks.getSize(), tasks.getAllTasks());
                break;
            case DELETE:
                if (args.length < 1) {
                    throw new IllegalArgumentException("Missing task index to delete.");
                } else if ((Integer.parseInt(args[0])) > tasks.getSize()) {
                    throw new IllegalArgumentException("Invalid task index to delete.");
                }
                deleteTask(tasks.getAllTasks(), args);
                Storage.saveTasks(tasks.getSize(), tasks.getAllTasks());
                break;
            case FIND:
                if (args.length < 1) {
                    throw new StringIndexOutOfBoundsException();
                }
                findTask(tasks.getAllTasks(), args);
                break;
            case BYE:
                Ui.bidFarewell();
                break;
            default:
                throw new IllegalArgumentException("Unrecognized command.");
            }
        } catch (Exception e) {
            MavisException.handleException(e, type.name());
        }
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return type == CommandType.BYE;
    }

    /**
     * Finds tasks matching the given search query.
     *
     * @param listOfTasks The list of tasks to search.
     * @param args The search query arguments.
     */
    private static void findTask(ArrayList<Task> listOfTasks, String[] args) {
        String joinedArgs = String.join(" ", args);
        boolean taskFound = false;
        Ui.showDividerLine();
        System.out.println("Searching the annals of time for tasks matching your query...");
        for (int i = 0; i < listOfTasks.size(); i++) {
            if (listOfTasks.get(i).description.toLowerCase().contains(joinedArgs.toLowerCase())) {
                taskFound = true;
                Ui.listTask(i, listOfTasks.get(i));
            }
        }
        if (!taskFound) {
            System.out.println("No tasks found matching your query. The sands of time remain undisturbed.");
        }
        Ui.showDividerLine();
    }

    /**
     * Deletes the task at the specified index from the list of tasks.
     *
     * @param listOfTasks The list of tasks.
     * @param splitInput The task index to delete.
     */
    private static void deleteTask(ArrayList<Task> listOfTasks, String[] splitInput) {
        int taskIndex = Integer.parseInt(splitInput[0]) - 1;
        System.out.println("Time erodes all, and hence this task has been erased too:");
        Ui.listTask(taskIndex, listOfTasks.get(taskIndex));
        System.out.println("Your roster now bears " + (listOfTasks.size() - 1) + " endeavors.");
        listOfTasks.remove(taskIndex);
    }

    /**
     * Marks the task at the specified index as completed.
     *
     * @param splitInput The task index to mark as completed, extracted from the user arguments
     * @param listOfTasks The ArrayList containing the total list of tasks.
     */
    private static void markTask(String[] splitInput, ArrayList<Task> listOfTasks) {
        int taskIndex = Integer.parseInt(splitInput[0]) - 1;
        listOfTasks.get(taskIndex).markAsCompleted();
        System.out.println("Your command has been executed."
                + "Behold the task, now marked as completed:");
        Ui.listTask(taskIndex, listOfTasks.get(taskIndex));
    }

    /**
     * Unmarks the task at the specified index as not completed.
     *
     * @param splitInput The task index to unmark, extracted from the user arguments
     * @param listOfTasks The ArrayList containing the total list of tasks.
     */
    private static void unmarkTask(String[] splitInput, ArrayList<Task> listOfTasks) {
        int taskIndex = Integer.parseInt(splitInput[0]) - 1;
        listOfTasks.get(taskIndex).markAsNotCompleted();
        System.out.println("Reversing the flow of space and time to undo the task...");
        System.out.println("Here is the task you just marked as not completed:");
        Ui.listTask(taskIndex, listOfTasks.get(taskIndex));
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param type The type of task to add.
     * @param inputToEcho The task description.
     * @param listOfTasks The ArrayList containing the total list of tasks.
     * @param listOfTasksSize The total number of tasks in listOfTasks
     */
    private static void addTask(CommandType type, String inputToEcho, ArrayList<Task> listOfTasks, int listOfTasksSize) {
        Task newTask;
        if (type == CommandType.TODO) {
            newTask = new ToDo(inputToEcho);
        } else if (type == CommandType.DEADLINE) {
            newTask = new Deadline(inputToEcho);
        } else { // event
            newTask = new Event(inputToEcho);
        }
        listOfTasks.add(listOfTasksSize, newTask);
        Ui.showNewlyAddedTask(newTask, listOfTasksSize + 1);
    }
}
