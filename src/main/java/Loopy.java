import java.util.ArrayList;
import java.util.Scanner;

public class Loopy {
    private static ArrayList<Task> tasks = new ArrayList<>(); // Create task list array

    public static void main(String[] args) {
        String logo = " __                \n"
                + "| |    ____ ____ ____ \n"
                + "| |   | |-| ||-| | _ \\\n"
                + "| |___| |_| ||_| | __/\n"
                + "|____/ \\__/ \\__/_| | \n";
        System.out.println("Hello! I'm Loopy!\n" + logo);
        System.out.println("What can I do for you?\n");

        Scanner taskScanner = new Scanner(System.in);  // Create a Scanner object

        while (true) {
            String task = taskScanner.nextLine();
            processTask(task);
            if (task.equals("bye")) {
                System.out.println("Bye! Hope to see you again.\n");
                break;
            }
        }
    }

    private static void processTask(String task) {
        if (task.equals("list")) {
            displayTaskList();
        } else if (task.startsWith("mark ")) {
            markTaskAsDone(task);
        } else if (task.startsWith("unmark ")) {
            markTaskAsUndone(task);
        } else if (task.startsWith("todo")){
            addTodo(task); // Create Task object and add it to the list
        } else if (task.startsWith("deadline")){
            addDeadline(task);
        } else if (task.startsWith("event")){
            addEvent(task);
        }
    }

    private static void addTodo(String task) {
       // if (!task.getDescription().equals("bye")) {
            //tasks.add(task);

            String description = task.substring(5, task.length());
            tasks.add(new TodoTask(description));

            System.out.println("Got it. I've added this task: ");
            System.out.println(tasks.get(tasks.size() - 1));
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        //}
    }
    private static void addDeadline(String task) {
       //separate the input into 2 substrings: description and deadline
        int splitPosition = task.indexOf("/");
        String description = task.substring(9, splitPosition);
        String deadline = task.substring(splitPosition + 4, task.length());

        tasks.add(new DeadlineTask(description,deadline));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addEvent(String task){
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

    private static void markTaskAsDone(String task) {
        int taskIndex = Integer.parseInt(task.substring(5)) - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task currentTask = tasks.get(taskIndex);
            currentTask.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(" " + currentTask);
        } else {
            System.out.println("Invalid task index. Please provide a valid index.");
        }
    }

    private static void markTaskAsUndone(String task) {
        int taskIndex = Integer.parseInt(task.substring(7)) - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task currentTask = tasks.get(taskIndex);
            currentTask.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(" " + currentTask);
        } else {
            System.out.println("Invalid task index. Please provide a valid index.");
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

