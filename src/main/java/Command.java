import java.util.ArrayList;

public class Command {

    private final CommandType type;
    private final String[] args;

    public Command(CommandType type, String[] args) {
        this.type = type;
        this.args = args;
    }

    public void execute(TaskList tasks) {
        try {
            switch (type) {
            case TODO:
            case DEADLINE:
            case EVENT:
                if (args.length < 1) {
                    throw new StringIndexOutOfBoundsException();
                } else if (tasks.getSize() >= 100) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                addTask(type, String.join(" ", args), tasks.getAllTasks(), tasks.getSize());
                Storage.saveTasks(tasks.getSize(), tasks.getAllTasks());
                break;
            case LIST:
                Ui.printTasks(tasks.getAllTasks(), tasks.getSize());
                break;
            case MARK:
                if (args.length < 1) {
                    throw new StringIndexOutOfBoundsException();
                }
                markTask(args, tasks.getAllTasks());
                Storage.saveTasks(tasks.getSize(), tasks.getAllTasks());
                break;
            case UNMARK:
                if (args.length < 1) {
                    throw new StringIndexOutOfBoundsException();
                }
                unmarkTask(args, tasks.getAllTasks());
                Storage.saveTasks(tasks.getSize(), tasks.getAllTasks());
                break;
            case DELETE:
                if (args.length < 1) {
                    throw new StringIndexOutOfBoundsException();
                } else if ((Integer.parseInt(args[0])) > tasks.getSize()) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                deleteTask(tasks.getAllTasks(), args);
                Storage.saveTasks(tasks.getSize(), tasks.getAllTasks());
                break;
            case BYE:
                break;
            default:
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            MavisException.handleException(e, type.name());
        }
    }

    public boolean isExit() {
        return type == CommandType.BYE;
    }
    private static void deleteTask(ArrayList<Task> listOfTasks, String[] splitInput) {
        int taskIndex = Integer.parseInt(splitInput[0]) - 1;
        System.out.println("Time erodes all, and hence this task has been erased too:");
        Ui.listTask(taskIndex, listOfTasks.get(taskIndex));
        System.out.println("Your roster now bears " + (listOfTasks.size() - 1) + " endeavors.");

        listOfTasks.remove(taskIndex);
    }

    private static void markTask(String[] splitInput, ArrayList<Task> listOfTasks) {
        int taskIndex = Integer.parseInt(splitInput[0]) - 1;
        listOfTasks.get(taskIndex).markAsCompleted();
        System.out.println("Your command has been executed."
                + "Behold the task, now marked as completed:");
        Ui.listTask(taskIndex, listOfTasks.get(taskIndex));
    }

    private static void unmarkTask(String[] splitInput, ArrayList<Task> listOfTasks) {
        int taskIndex = Integer.parseInt(splitInput[0]) - 1;
        listOfTasks.get(taskIndex).markAsNotCompleted();
        System.out.println("Reversing the flow of space and time to undo the task...");
        System.out.println("Here is the task you just marked as not completed:");
        Ui.listTask(taskIndex, listOfTasks.get(taskIndex));
    }

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

