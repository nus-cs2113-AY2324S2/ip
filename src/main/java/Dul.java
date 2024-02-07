import java.util.Scanner;

public class Dul {
    public static final int maximumTASKS = 100;
    public static Task[] tasks = new Task[maximumTASKS];
    public static int taskCount = 0;

    public static void main(String[] args) {
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
        }
        System.out.println("Bye. Hope to see you again soon!");
        in.close();
    }

    public static void listInput(String input) {
        String[] command = input.split(" ");
        switch (command[0]) {
            case "list":
                listTasks();
                break;
            case "mark":
                markTaskAsDone(Integer.parseInt(command[1]) - 1);
                break;
            case "unmark":
                markTaskAsNotDone(Integer.parseInt(command[1]) - 1);
                break;
            case "bye":
                break;
            default:
                addTask(input);
                break;
        }
    }

    public static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + tasks[i].toString());
        }
    }

    public static void addTask(String taskDescription) {
            tasks[taskCount] = new Task(taskDescription);
            taskCount++;
            System.out.println("added: " + taskDescription);
    }

    public static void markTaskAsDone(int index) {
        tasks[index].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks[index].toString());
    }

    public static void markTaskAsNotDone(int index) {
        tasks[index].markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks[index].toString());
    }
}

class Task {
    protected String description;
    protected boolean isDone;

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

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}

