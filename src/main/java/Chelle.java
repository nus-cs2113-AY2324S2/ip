import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    private String taskName;
    private boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isDone() {
        return done;
    }

    public void markDone() {
        this.done = true;
    }

    public void markUndone() {
        this.done = false;
    }
}

public class Chelle {
    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();

        System.out.println("Hello! I'm Chelle.\nI like to talkity talkity talk!");

        while (true) {
            System.out.print("You: ");
            String userInput = Sc.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("Chelle: Bye! Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                System.out.println("Chelle: ");
                displayTasks(taskList);
            } else if (userInput.startsWith("mark")) {
                markTask(userInput, taskList);
            } else if (userInput.startsWith("unmark")) {
                unmarkTask(userInput, taskList);
            } else {
                taskList.add(new Task(userInput));
                System.out.println("Chelle: added: " + userInput);
            }
        }

        Sc.close();
    }

    private static void displayTasks(List<Task> taskList) {
        if (taskList.isEmpty()) {
            System.out.println("List is empty.");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                String status = task.isDone() ? "[X]" : "[ ]";
                System.out.println((i + 1) + ". " + status + " " + task.getTaskName());
            }
        }
    }

    private static void markTask(String userInput, List<Task> taskList) {
        String[] parts = userInput.split(" ");
        if (parts.length == 2) {
            try {
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskList.size()) {
                    taskList.get(taskIndex).markDone();
                    System.out.println("Chelle: Nice! I've marked this task as done:\n        [X]  " +
                            taskList.get(taskIndex).getTaskName());
                } else {
                    System.out.println("Chelle: Invalid task index.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Chelle: Invalid task index.");
            }
        } else {
            System.out.println("Chelle: Invalid command format.");
        }
    }

    private static void unmarkTask(String userInput, List<Task> taskList) {
        String[] parts = userInput.split(" ");
        if (parts.length == 2) {
            try {
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskList.size()) {
                    taskList.get(taskIndex).markUndone();
                    System.out.println("Chelle: OK, I've marked this task as not done yet:\n        [ ]  " +
                            taskList.get(taskIndex).getTaskName());
                } else {
                    System.out.println("Chelle: Invalid task index.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Chelle: Invalid task index.");
            }
        } else {
            System.out.println("Chelle: Invalid command format.");
        }
    }
}