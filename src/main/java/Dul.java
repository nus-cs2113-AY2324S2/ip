import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Dul {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static final String filepath = "./data/dul.txt";

    public static void main(String[] args) {
        loadTasksFromFile();

        String logo = " ____        _\n"
                + "|  _ \\ _   _| |\n"
                + "| | | | | | | |\n"
                + "| |_| | |_| | |\n"
                + "|____/ \\__,_|_|\n";
        System.out.println("Hello I'm\n" + logo);
        System.out.println("What can I do for you?");
        String guyInput = "";
        Scanner in = new Scanner(System.in);

        while (!guyInput.equals("bye")) {
            guyInput = in.nextLine();
            listInput(guyInput);
            saveTasksToFile();
        }

        System.out.println("Bye. Hope to see you again soon!");
        in.close();
    }

    public static void loadTasksFromFile() {
        try {
            File file = new File(filepath);
            if (!file.exists()) {
                System.out.println("No existing data file found.");
                return;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] taskDetails = line.split(" \\| ");
                switch (taskDetails[0]) {
                    case "T":
                        tasks.add(new TodoTask(taskDetails[2]));
                        break;
                    case "D":
                        tasks.add(new DeadlineTask(taskDetails[2], taskDetails[3]));
                        break;
                    case "E":
                        tasks.add(new EventTask(taskDetails[2], taskDetails[3], taskDetails[4]));
                        break;
                    default:
                        System.out.println("Invalid task format found in data file: " + line);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    public static void saveTasksToFile() {
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            FileWriter writer = new FileWriter(filepath);
            for (Task task : tasks) {
                if (task instanceof TodoTask) {
                    writer.write("T | " + (task.isDone ? 1 : 0) + " | " + task.description + "\n");
                } else if (task instanceof DeadlineTask) {
                    DeadlineTask deadlineTask = (DeadlineTask) task;
                    writer.write("D | " + (task.isDone ? 1 : 0) + " | " + task.description + " | " + deadlineTask.by + "\n");
                } else if (task instanceof EventTask) {
                    EventTask eventTask = (EventTask) task;
                    writer.write("E | " + (task.isDone ? 1 : 0) + " | " + task.description + " | " + eventTask.from + " | " + eventTask.to + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public static void listInput(String input) {
        String[] command = input.split(" ", 2);
        switch (command[0]) {
            case "list":
                listTasks();
                break;
            case "mark":
                markTaskDone(Integer.parseInt(command[1]) - 1);
                break;
            case "unmark":
                markTaskNotDone(Integer.parseInt(command[1]) - 1);
                break;
            case "todo":
                if (command.length < 2 || command[1].trim().isEmpty()) {
                    System.out.println("OOPS!!! Do not leave the description of a todo empty.");
                } else {
                    addTodoTask(command[1]);
                }
                break;
            case "deadline":
                if (command.length < 2 || !command[1].contains("/by")) {
                    System.out.println("OOPS!!! I need you to give me the description and deadline (/by).");
                } else {
                    addDeadlineTask(command[1]);
                }
                break;
            case "event":
                addEventTask(command[1]);
                break;
            case "delete":
                deleteTask(Integer.parseInt(command[1]) - 1);
                break;
            case "bye":
                break;
            default:
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    public static void addTodoTask(String taskType) {
        tasks.add(new TodoTask(taskType));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void addDeadlineTask(String taskType) {
        String[] parts = taskType.split(" /by ", 2);
        String description = parts[0];
        String by = parts[1];
        tasks.add(new DeadlineTask(description, by));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void addEventTask(String taskType) {
        String[] parts = taskType.split(" /from ", 2);
        String[] eventParts = parts[1].split(" /to ", 2);
        String description = parts[0];
        String from = eventParts[0];
        String to = eventParts[1];
        tasks.add(new EventTask(description, from, to));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void markTaskDone(int index) {
        tasks.get(index).markDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(index).toString());
    }

    public static void markTaskNotDone(int index) {
        tasks.get(index).markNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks.get(index).toString());
    }

    public static void deleteTask(int index) {
        Task removedTask = tasks.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}

class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class DeadlineTask extends Task {
    protected String by;

    public DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

class EventTask extends Task {
    protected String from;
    protected String to;

    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

