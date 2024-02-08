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
            System.out.print(label + ".");
            System.out.println(task);
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
        System.out.println("\t" + task);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void unmarkTask(Task task) {
        task.setDone(false);

        System.out.print(HORIZONTAL_LINE);
        System.out.println("Ok, this task has been marked as not done yet:");
        System.out.println("\t" + task);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void addMessage(ArrayList<Task> taskList) {
        int numOfTasks = taskList.toArray().length;
        Task theTask = taskList.get(numOfTasks - 1);
        System.out.print(HORIZONTAL_LINE);
        System.out.println("Ok I just added: ");
        System.out.println("\t" + theTask);

        if (numOfTasks == 1) {
            System.out.println("There is 1 task in the list.");
        } else {
            System.out.println("There are " + numOfTasks
                    + " tasks in the list.");
        }
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

        if (command.startsWith("todo")) {
            Todo newTodo = new Todo(command);
            taskList.add(newTodo);
        } else if (command.startsWith("deadline")) {
            Deadline newDeadline = new Deadline(command);
            taskList.add(newDeadline);
        } else if (command.startsWith("event")) {
            Event newEvent = new Event(command);
            taskList.add(newEvent);
        } else {
            Task newTask = new Task(command);
            taskList.add(newTask);
        }
        addMessage(taskList);
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
