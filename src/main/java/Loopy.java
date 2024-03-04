import java.util.ArrayList;
import java.util.Scanner;
//import LoopyExceptions.java

public class Loopy {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " __                \n"
                + "| |    ____ ____ ____ \n"
                + "| |   | |-| ||-| | _ \\\n"
                + "| |___| |_| ||_| | __/\n"
                + "|____/ \\__/ \\__/_| | \n";
        System.out.println("Hello! I'm Loopy!\n" + logo);
        System.out.println("What can I do for you?\n");

        Scanner taskScanner = new Scanner(System.in);

        while (true) {
            String task = taskScanner.nextLine();
            processTask(task);
//            System.out.println(task);
            if (task.equals("bye")) {
                System.out.println("Bye! Hope to see you again ♡ \n");
                break;
            }
        }
    }

    private static void processTask(String task) {
        if (task.equals("list")) {
            displayTaskList();
        } else if (task.startsWith("mark")) {
            try {
                markTaskAsDone(task);
            } catch (LoopyExceptions exceptions) {
                System.out.println("Warning! " + exceptions.getMessage());
            }
        } else if (task.startsWith("unmark")) {
            try {
                markTaskAsUndone(task);
            } catch (LoopyExceptions exceptions) {
                System.out.println("Warning! " + exceptions.getMessage());
            }
        } else if (task.startsWith("todo")) {
            try {
                addTodo(task); // Create Task object and add it to the list
            } catch (LoopyExceptions exceptions) {
                System.out.println("Warning! " + exceptions.getMessage());
            }
        } else if (task.startsWith("deadline")) {
            addDeadline(task);
        } else if (task.startsWith("event")) {
            addEvent(task);
        } else if (task.startsWith("delete")) {
            deleteTask(task);
        } else {
            System.out.println("I don't know what you're saying...?");
        }
    }

    private static void addTodo(String task) throws LoopyExceptions {
        if (task.length() <= 5) {
            throw new LoopyExceptions("Todo cannot be empty!");
        }
        if (task.length() > 5) {
            String description = task.substring(5, task.length());
            tasks.add(new TodoTask(description));
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
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(" " + currentTask);
        } else {
            //write some other error
            throw new LoopyExceptions("Please specify which task to mark.");
        }
    }


    private static void markTaskAsUndone(String task) throws LoopyExceptions {
        int taskIndex = Integer.parseInt(task.substring(7)) - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task currentTask = tasks.get(taskIndex); //retrieve this current task from tasks
            currentTask.markAsNotDone();
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
            System.out.println("You now have " + tasks.size() + " tasks left");
        }
    }
}

class Task {
    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getType(){
        return "";
    }
    @Override //converts hexadecimal output to string
    public String toString() {
        return "[" + getType() + "]" + "[" + (isDone ? "X" : " ") + "] " + description;
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
}

