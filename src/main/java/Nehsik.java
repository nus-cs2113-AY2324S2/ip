import java.util.Scanner;
public class Nehsik {
    public static final int MAX_TASKS = 100;

    public static void main(String[] args) {
        displayGreetings();
        
        Scanner in = new Scanner(System.in);
        Task[] taskList = new Task[MAX_TASKS];
        int taskIndex = 0;

        while (true) {
            String command = in.nextLine().trim();
            if (command.equals("list")) {
                displayTaskList(taskIndex, taskList);
            } else if (command.startsWith("mark")){
                markTask(command, taskList);
            } else if (command.startsWith("unmark")) {
                unmarkTask(command, taskList);
            }  else if (command.startsWith("todo")) {
                addTodoTask(command, taskList, taskIndex);
                taskIndex = acknowledgeTaskAdded(taskList, taskIndex);
            } else if (command.startsWith("deadline")) {
                addDeadlineTask(command, taskList, taskIndex);
                taskIndex = acknowledgeTaskAdded(taskList, taskIndex);
            } else if (command.startsWith("event")) {
                addEventTask(command, taskList, taskIndex);
                taskIndex = acknowledgeTaskAdded(taskList, taskIndex);
            } else if (command.equals("bye")) {
                displayExitMessage();
                break;
            } else {
                System.out.println("Invalid Command");
            }
        }
    }

    private static void displayTaskList(int taskIndex, Task[] taskList) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskIndex; i++) {
            System.out.println((i + 1) + "." + taskList[i].toString());
        }
        printLine();
    }

    private static void markTask(String command, Task[] taskList) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        int taskNum = Integer.parseInt(command.substring(5)) - 1;
        taskList[taskNum].markAsDone();
        System.out.println(taskList[taskNum].toString());
        printLine();
    }

    private static void unmarkTask(String command, Task[] taskList) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        int taskNum = Integer.parseInt(command.substring(7)) - 1;
        taskList[taskNum].markAsUndone();
        System.out.println(taskList[taskNum].toString());
        printLine();
    }

    private static void addTodoTask(String command, Task[] taskList, int taskIndex) {
        String taskDescription = command.substring(5).trim();
        taskList[taskIndex] = new Todo(taskDescription);
    }

    private static void addDeadlineTask(String command, Task[] taskList, int taskIndex) {
        String taskDescription = command.substring(command.indexOf("deadline ") + 9, command.indexOf("/by ") - 1).trim();
        String by = command.substring(command.indexOf("/by ") + 4).trim();
        taskList[taskIndex] = new Deadline(taskDescription, by);
    }

    private static void addEventTask(String command, Task[] taskList, int taskIndex) {
        String taskDescription = command.substring(command.indexOf("event ") + 6, command.indexOf("/from ") - 1).trim();
        String from = command.substring(command.indexOf("/from ") + 6, command.indexOf("/to ") - 1).trim();
        String to = command.substring(command.indexOf("/to ") + 4).trim();
        taskList[taskIndex] = new Event(taskDescription, from, to);
    }

    private static int acknowledgeTaskAdded(Task[] taskList, int taskIndex) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList[taskIndex].toString());
        System.out.println("Now you have " + (taskIndex + 1) + " tasks in the list");
        taskIndex++;
        printLine();
        return taskIndex;
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }

    private static void displayGreetings() {
        printLine();
        System.out.println("Hello! I'm Nehsik");
        System.out.println("What can I do for you?");
        printLine();
    }

    private static void displayExitMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}
