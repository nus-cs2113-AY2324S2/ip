import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Bart {
    private static final String LINE = "____________________________________________________________";
    private static Ui ui = new Ui();
    private static final String FILE_PATH = "./data/Bart.txt";
    private static final ArrayList<Task> tasksList = new ArrayList<>();

    public static void main(String[] args) {
        loadTasks();
        ui.greetUser();
        manageTask();
        saveTasks();
        byeUser();
    }

    private static void loadTasks() {
        try {
            File file = new File(FILE_PATH);

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");

                String taskType = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String taskDescription = parts[2];

                Task task = null;
                switch (taskType) {
                    case "T":
                        task = new Todo("todo" + taskDescription);
                        break;
                    case "D":
                        task = new Deadline("deadline" + taskDescription);
                        break;
                    case "E":
                        task = new Event("event" + taskDescription);
                        break;
                    default:
                        ui.println("Unknown task type: " + taskType);
                        continue;
                }
                if (isDone) {
                    task.markAsDone();
                }
                tasksList.add(task);
            }
            scanner.close();
        }  catch (ArrayIndexOutOfBoundsException e) {
            ui.println("No file loaded, creating new load file");
        } catch (FileNotFoundException e) {
            ui.println("Data file does not exist. Starting with an empty task list.");
        }
    }

    private static void saveTasks() {
        File file = new File("./data/Bart.txt");

        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (FileWriter writer = new FileWriter(file)) {
            for (Task task: Task.getAllTasks()) {
                if (task != null) {
                    String description = task.description;
                    String mark = task.isDone ? "1" : "0";
                    String taskType = task.getTaskType().replace("]", "").replace("[", "").trim();
                    writer.write(taskType + " | " + mark + " | " + description + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            ui.println("Saving error: " + e.getMessage());
        }
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
        int taskIndex = Integer.parseInt(command.substring(command.indexOf(' ') + 1).trim()) - 1;
        if (taskIndex >= 0 && taskIndex < tasksList.size()) {
            Task deletedTask = tasksList.remove(taskIndex);
            ui.println(LINE + "\nNoted. I've removed this task:\n");
            ui.println(deletedTask.toString());
            ui.println("Now you have " + tasksList.size() + " tasks in the list.\n");
        } else {
            ui.println(LINE + "\nInvalid task number.\n" + LINE);
        }
    }

    private static void byeUser() {
        ui.println(LINE + "\nBye. Hope to see you again soon!\n" + LINE);
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