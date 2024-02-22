import java.util.Scanner;
public class Nehsik {
    public static final int MAX_TASKS = 100;
    public static final int MARK_TASK_INDEX = 5;
    public static final int UNMARK_TASK_INDEX = 7;
    public static final int TODO_DESCRIPTION_POSITION = 5;

    public static void main(String[] args) {
        displayGreetings();
        
        Scanner in = new Scanner(System.in);
        Task[] taskList = new Task[MAX_TASKS];
        int currTaskCount = 0;

        while (true) {
            try {
                String command = in.nextLine().trim();
                if (command.equals("list")) {
                    displayTaskList(currTaskCount, taskList);
                } else if (command.startsWith("mark")){
                    markTask(command, taskList);
                } else if (command.startsWith("unmark")) {
                    unmarkTask(command, taskList);
                }  else if (command.startsWith("todo")) {
                    addTodoTask(command, taskList, currTaskCount);
                    currTaskCount = acknowledgeTaskAdded(taskList, currTaskCount);
                } else if (command.startsWith("deadline")) {
                    addDeadlineTask(command, taskList, currTaskCount);
                    currTaskCount = acknowledgeTaskAdded(taskList, currTaskCount);
                } else if (command.startsWith("event")) {
                    addEventTask(command, taskList, currTaskCount);
                    currTaskCount = acknowledgeTaskAdded(taskList, currTaskCount);
                } else if (command.equals("bye")) {
                    displayExitMessage();
                    break;
                } else {
                    throw new NehsikException("Invalid Command");
                }
            } catch (NehsikException e) {
                printLine();
                System.out.println(e.getMessage());
                printLine();
            }
        }

        in.close();
    }

    private static void displayTaskList(int currTaskCount, Task[] taskList) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < currTaskCount; i++) {
            System.out.println((i + 1) + "." + taskList[i].toString());
        }
        printLine();
    }

    private static void markTask(String command, Task[] taskList) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        int taskNum = Integer.parseInt(command.substring(MARK_TASK_INDEX)) - 1;
        taskList[taskNum].markAsDone();
        System.out.println(taskList[taskNum].toString());
        printLine();
    }

    private static void unmarkTask(String command, Task[] taskList) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        int taskNum = Integer.parseInt(command.substring(UNMARK_TASK_INDEX)) - 1;
        taskList[taskNum].markAsUndone();
        System.out.println(taskList[taskNum].toString());
        printLine();
    }

    private static void addTodoTask(String command, Task[] taskList, int currTaskCount) throws NehsikException {
        if (command.length() < TODO_DESCRIPTION_POSITION) {
            throw new NehsikException("The description of a todo cannot be empty");
        }

        if (command.charAt(TODO_DESCRIPTION_POSITION - 1) != ' ') {
            throw new NehsikException("Invalid command");
        }

        String taskDescription = command.substring(TODO_DESCRIPTION_POSITION).trim();
        taskList[currTaskCount] = new Todo(taskDescription);
    }

    private static void addDeadlineTask(String command, Task[] taskList, int currTaskCount) {
        int descriptionStartPosition = command.indexOf("deadline ") + 9;
        //if (command.length() <=)
        int descriptionEndPosition = command.indexOf("/by ") - 1;
        String taskDescription = command.substring(descriptionStartPosition, descriptionEndPosition).trim();

        int byStringPosition = command.indexOf("/by ") + 4;
        String by = command.substring(byStringPosition).trim();

        taskList[currTaskCount] = new Deadline(taskDescription, by);
    }

    private static void addEventTask(String command, Task[] taskList, int currTaskCount) {
        int descriptionStartPosition = command.indexOf("event ") + 6;
        int descriptionEndPosition = command.indexOf("/from ") - 1;
        String taskDescription = command.substring(descriptionStartPosition, descriptionEndPosition).trim();

        int fromStringStartPosition = command.indexOf("/from ") + 6;
        int fromStringEndPosition = command.indexOf("/to ") - 1;
        String from = command.substring(fromStringStartPosition, fromStringEndPosition).trim();

        int toStringPosition = command.indexOf("/to ") + 4;
        String to = command.substring(toStringPosition).trim();

        taskList[currTaskCount] = new Event(taskDescription, from, to);
    }

    private static int acknowledgeTaskAdded(Task[] taskList, int currTaskCount) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList[currTaskCount].toString());
        System.out.println("Now you have " + (currTaskCount + 1) + " tasks in the list");
        currTaskCount++;
        printLine();
        return currTaskCount;
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
