import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Loopy {
    private static final String FILE_PATH = "././Loopy.txt"; // File path
    private static ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {

        System.out.println("Hello! I'm Loopy ₍ᐢ•ﻌ•ᐢ₎");

        loadFile(); //load the previously saved file

        System.out.println("What can I do for you?\n");

        Scanner taskScanner = new Scanner(System.in);

        while (true) {
            String task = taskScanner.nextLine();
            processTask(task);
            if (task.equals("bye")) {
                System.out.println("Bye! Hope to see you again ♡ \n");
                break;
            }
        }
    }

    private static void processTask(String task) {
        String[] command = task.split(" ");

        switch (command[0].toLowerCase()){
            case "list":
                displayTaskList();
                break;
            case "mark":
                try {
                    markTaskAsDone(task);
                } catch (LoopyExceptions exceptions) {
                    System.out.println("Warning! " + exceptions.getMessage());
                }
                break;
            case "unmark":
                try {
                    markTaskAsUndone(task);
                } catch (LoopyExceptions exceptions) {
                    System.out.println("Warning! " + exceptions.getMessage());
                }
                break;
            case "todo":
                try {
                    addTodo(task); // Create Task object and add it to the list
                } catch (LoopyExceptions exceptions) {
                    System.out.println("Warning! " + exceptions.getMessage());
                }
                break;
            case "deadline":
                addDeadline(task);
                break;
            case "event":
                addEvent(task);
                break;
            case "delete":
                deleteTask(task);
                break;
            case "find":
                findTask(task);
            case "bye":
                break;
            default:
                System.out.println("I don't know what you're saying...?");
        }
    }

    private static void loadFile(){
        try {
            File file = new File(FILE_PATH);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                switch (parts[0]) {
                    case "T":
                        tasks.add(new TodoTask(parts[2]));
                        break;
                    case "D":
                        tasks.add(new DeadlineTask(parts[2], parts[3]));
                        break;
                    case "E":
                        tasks.add(new EventTask(parts[2], parts[3], parts[4]));
                        break;
                }
                if (parts[1].equals("1")) {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
            }
            scanner.close();
            displayTaskList();
        } catch (FileNotFoundException e) {
            System.out.println("No saved tasks found! Starting a new list\n  ❀•°❀°•❀ ");
        }
    }
    private static void saveFile() {
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdirs(); // Make the directory if it doesn't exist
            }
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
            e.printStackTrace();
        }
    }
    private static void addTodo(String task) throws LoopyExceptions {
        if (task.length() <= 5) {
            throw new LoopyExceptions("Todo cannot be empty!");
        }
        if (task.length() > 5) {
            String description = task.substring(5, task.length());
            tasks.add(new TodoTask(description));
            saveFile();
            System.out.println("Got it. I've added this task: ");
            System.out.println(tasks.get(tasks.size() - 1));
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            throw new LoopyExceptions("Please add a task!");
        }
    }

    private static void addDeadline(String task) {
        //separate the input into 2 substrings: description and deadline
        int splitPosition = task.indexOf("/");
        String description = task.substring(9, splitPosition);
        String deadline = task.substring(splitPosition + 4, task.length());

        tasks.add(new DeadlineTask(description, deadline));
        saveFile();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addEvent(String task) {
        int fromPosition = task.indexOf("from");
        int toPosition = task.indexOf("to");
        String description = task.substring(6, fromPosition - 1);
        String fromDate = task.substring(fromPosition + 5, toPosition - 1);
        String toDate = task.substring(toPosition + 3, task.length());

        tasks.add(new EventTask(description, fromDate, toDate));
        saveFile();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void displayTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            String taskInfo = currentTask.toString();
            System.out.println((i + 1) + ". " + taskInfo);
        }
    }

    private static void markTaskAsDone(String task) throws LoopyExceptions {
        if (task.length() <= 5) {
            throw new LoopyExceptions("Please specify which task to mark.");
        }
        int taskIndex = Integer.parseInt(task.substring(5)) - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task currentTask = tasks.get(taskIndex);
            currentTask.markAsDone();
            saveFile();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(" " + currentTask);
        } else {
            throw new LoopyExceptions("Please specify which task to mark.");
        }
    }


    private static void markTaskAsUndone(String task) throws LoopyExceptions {
        int taskIndex = Integer.parseInt(task.substring(7)) - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task currentTask = tasks.get(taskIndex); //retrieve this current task from tasks
            currentTask.markAsNotDone();
            saveFile();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(" " + currentTask);
        } else {
            throw new LoopyExceptions("Please specify which task to unmark.");
        }
    }

    public static void deleteTask(String task) {
        int taskIndex = Integer.parseInt(task.substring(7)) - 1;
        Task currentTask = tasks.get(taskIndex); //retrieve this current task from tasks
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            System.out.println("I have deleted the task: ");
            System.out.println(currentTask);
            tasks.remove(taskIndex);
            saveFile();
            System.out.println("You now have " + tasks.size() + " tasks left");
        }
    }
    public static void findTask(String task){
        System.out.println("Here are the matching tasks in your list:");
        String description= task.substring(6, task.length());
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            if (currentTask.getDescription().toLowerCase().contains(description.toLowerCase())) {
                count++;
                System.out.println(count + ". " + currentTask);
            }
        }
        if (count == 0) {
            System.out.println("Sorry! I did not find matching tasks.");
        }
    }
}

class TodoTask extends Task{
    public TodoTask(String description){
        super(description);
    }
    @Override
    public String getType() {
        return "T";
    }
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

}
class DeadlineTask extends Task{
    private String deadline;
    public DeadlineTask(String description, String deadline){
        super(description);
        this.deadline = deadline;
    }
    @Override
    public String toString() {
        return "[" + getType() + "]" + "[" + (isDone ? "X" : " ") + "] " + description + " (by: " + deadline + ")";
    }

    @Override
    public String getType() {
        return "D";
    }
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + deadline;
    }

}
class EventTask extends Task {
    private String fromDate, toDate;
    public EventTask(String description, String fromDate, String toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
    @Override
    public String getType() {
        return "E";
    }
    @Override
    public String toString() {
        return "[" + getType() + "]" + "[" + (isDone ? "X" : " ") + "] " + description + " (from: " + fromDate + " to: " + toDate + ")";
    }
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + fromDate + " to " + toDate;
    }

}

