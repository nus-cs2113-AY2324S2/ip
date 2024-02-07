import java.util.Scanner;
import java.util.ArrayList;

public class Helpy {
    public static final String HORIZONTAL_LINE = "______________________\n";

    public static void greetUser() {
        String name = "░▒█░▒█░▒█▀▀▀░▒█░░░░▒█▀▀█░▒█░░▒█\n" +
                "░▒█▀▀█░▒█▀▀▀░▒█░░░░▒█▄▄█░▒▀▄▄▄▀\n" +
                "░▒█░▒█░▒█▄▄▄░▒█▄▄█░▒█░░░░░░▒█░░\n";
        System.out.println(HORIZONTAL_LINE
                + "Greetings, I am\n" + name);
        System.out.println("How can I help you?\n" + HORIZONTAL_LINE);
    }

    public static void listTasks(ArrayList<Task> taskList) {
        int label = 1;

        System.out.print(HORIZONTAL_LINE);
        System.out.println("These are the tasks in your list:");
        for (Task task : taskList) {
            String statusIcon = task.getStatusIcon();
            System.out.print(label);
            System.out.println("." + statusIcon + " " + task.getTaskName());
            label++;
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printIndexError() {

        System.out.print(HORIZONTAL_LINE);
        System.out.println("Invalid task number. "
                + "Please check the task number again.");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void markTask(Task task) {
        task.setDone(true);

        System.out.print(HORIZONTAL_LINE);
        System.out.println("Good job! I've marked this task as done:");
        System.out.println("\t[X] " + task.getTaskName());
        System.out.println(HORIZONTAL_LINE);
    }

    public static void unmarkTask(Task task) {
        task.setDone(false);

        System.out.print(HORIZONTAL_LINE);
        System.out.println("Ok, this task has been marked as not done yet:");
        System.out.println("\t[ ] " + task.getTaskName());
        System.out.println(HORIZONTAL_LINE);
    }

    public static void addTask(String command, ArrayList<Task> taskList) {
        if (command.startsWith("mark ")) {
            String taskNum = command.substring(5);
            taskNum = taskNum.trim();
            int taskIndex = Integer.parseInt(taskNum) - 1;
            int arrayLength = taskList.toArray().length;

            // Check if index is out of bounds
            if ( taskIndex < 0 || taskIndex > arrayLength - 1) {
                printIndexError();
                return;
            }
            markTask(taskList.get(taskIndex));
            return;
        }

        if (command.startsWith("unmark ")) {
            String taskNum = command.substring(7);
            taskNum = taskNum.trim();
            int taskIndex = Integer.parseInt(taskNum) - 1;
            int arrayLength = taskList.toArray().length;

            // Check if index is out of bounds
            if ( taskIndex < 0 || taskIndex > arrayLength - 1) {
                printIndexError();
                return;
            }
            unmarkTask(taskList.get(taskIndex));
            return;
        }

        Task newTask = new Task(command);
        taskList.add(newTask);

        System.out.print(HORIZONTAL_LINE);
        System.out.println("I have added: " + command);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void goodbyeUser() {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("Goodbye, see you next time!");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void main(String[] args) {
        greetUser();

        Scanner in = new Scanner(System.in);
        String command = "";
        ArrayList<Task> taskList = new ArrayList<>();

        while (!command.equals("bye")) {
            command = in.nextLine();
            switch (command) {
            case "bye":
                goodbyeUser();
                break;
            case "list":
                listTasks(taskList);
                break;
            default:
                addTask(command, taskList);
                break;
            }
        }
    }
}
