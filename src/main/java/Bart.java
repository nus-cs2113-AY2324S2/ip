import java.util.NoSuchElementException;
import java.io.IOException;


public class Bart {
    private static final String LINE = "____________________________________________________________";
    private static Storage storage;
    private static Ui ui = new Ui();
    private static final String FILE_PATH = "./data/Bart.txt";
    private static final TaskList tasksList = new TaskList();

    public static void main(String[] args) {
        ui.greetUser();
        try {
            storage = new Storage(FILE_PATH);
        } catch (IOException e) {
            ui.println("Unable to create data file!");
            return;
        }
        storage.loadTasks(tasksList);
        manageTask();
        storage.saveTasks();
        ui.byeUser();
    }

    public static void manageTask() {
        String command;

        while (true) {
            command = ui.getInput();

            if (command.equals("help")) {
                printHelp();
            } else if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                listTasks();
            } else if (command.startsWith("mark")) {
                markTask(command, true);
            } else if (command.startsWith("unmark")) {
                markTask(command, false);
            } else if (command.startsWith("delete")) {
                deleteTask(command);
            } else {
                addNewTask(command);
            }
        }
    }

    private static void addNewTask(String command) {
        String[] commandParts = command.split(" ");
        String tasking = commandParts[0];

        switch (tasking) {
            case "todo":
                try {
                    tasksList.add(new Todo(command));
                } catch (IllegalArgumentException e) {
                    ui.println(LINE + "\nOOPS!!! The description of a todo cannot be empty.\n" + LINE);
                    return;
                }
                break;
            case "deadline":
                try {
                    tasksList.add(new Deadline(command));
                } catch (IllegalArgumentException e) {
                    ui.println(LINE + "\nOOPS!!! The description of a deadline cannot be empty.\n" + LINE);
                    return;
                } catch (NoSuchElementException e) {
                    ui.println(LINE + "\nOOPS!!! Try this format: deadline <task> /by <time>.\n" + LINE);
                    return;
                }
                break;
            case "event":
                try {
                    tasksList.add(new Event(command));
                } catch (IllegalArgumentException e) {
                    ui.println(LINE + "\nOOPS!!! The description of a event cannot be empty.\n" + LINE);
                    return;
                } catch (NoSuchElementException e) {
                    ui.println(LINE + "\nOOPS!!! Try this format: event <task> /from <start_time> /to <end_time>.\n" + LINE);
                    return;
                }
                break;
            default:
                ui.println(LINE + "\nOOPS!!! I'm sorry, but I don't know what that means :-(\n" + LINE);
                return;
        }
        tasksList.get(tasksList.size() - 1).printTask(tasksList.size());
    }

    public static void listTasks() {
        ui.println(LINE + "\nHere are the tasks in your list:");
        //Edge case: If list empty
        if (tasksList.isEmpty()) {
            ui.println("Nothing added here....");
        }
        for (int i = 0; i < tasksList.size(); i++) {
            ui.println("  "+ (i + 1) + "." + tasksList.get(i).toString());
        }
        ui.println(LINE);
    }

    public static void markTask(String command, boolean mark) {
        int taskIndex = Integer.parseInt(command.substring(command.indexOf(' ') + 1).trim()) - 1;
        if (taskIndex >= 0 && taskIndex < tasksList.size()) {
            Task task = tasksList.get(taskIndex);
            if (mark) {
                task.markAsDone();
                ui.println(LINE + "\nNice! I've marked this task as done:");
            } else {
                task.markAsUndone();
                ui.println(LINE + "\nOK, I've marked this task as not done yet:");
            }
            ui.println(task.getTaskMark() + " " + task.description + "\n" + LINE );
        } else {
            ui.println(LINE + "\nInvalid task number.\n" + LINE);
        }
    }

    private static void deleteTask(String command) {
        int indexToDelete = Integer.parseInt(command.substring(command.indexOf(' ') + 1).trim()) - 1;
        if (indexToDelete >= 0 && indexToDelete < tasksList.size()) {
            ui.println(LINE + "\nNoted. I've removed this task:");
            ui.println("   "+ tasksList.get(indexToDelete).toString());
            tasksList.remove(indexToDelete);
            ui.println("Now you have " + tasksList.size() + " tasks in the list.\n" + LINE);
        } else {
            ui.println(LINE + "\nInvalid task number.\n" + LINE);
        }
    }

    public static void printHelp() {
        ui.println(LINE + "\n'list' lists all current tasks" +
            "\n'mark <#>' marks tasks with X" +
            "\n'unmark <#>' unmarks tasks by removing the X" +
            "\n'todo <task>' creates a to-do" +
            "\n'deadline <task> /by <time>' creates a task with deadline" +
            "\n'event <task> /from <time> /to <time>' creates a to-do" +
            "\n'bye' to quit\n" + LINE);

    }
}