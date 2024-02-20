import java.util.ArrayList;
import java.util.Scanner;

public class MsChatty {
    public static void printTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print((i+1) + ".");
            tasks.get(i).printTask();
        }
    }

    public static void markAndUnmarkTask(ArrayList<Task> tasks, int taskNumber, String[] arrayOfCommand) {
        if (arrayOfCommand[0].equals("mark")) {
            System.out.println("Nice! I've marked this task as done:");
            tasks.get(taskNumber).markAsDoneOrNotDone(arrayOfCommand);
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
            tasks.get(taskNumber).markAsDoneOrNotDone(arrayOfCommand);
        }
        System.out.print(" ");
        tasks.get(taskNumber).printTask();
    }

    public static void handleMarkAndUnmarkRequest(String userCommand, String[] arrayOfCommand, ArrayList<Task> tasks, int taskCount) {
        arrayOfCommand = userCommand.split(" ", 2);
        try {
            if (arrayOfCommand.length < 2 || arrayOfCommand[1].isEmpty()) {
                throw new StringIndexOutOfBoundsException();
            }
            int taskNumber = Integer.parseInt(arrayOfCommand[1]);
            if (taskNumber > taskCount) {
                throw new NullPointerException();
            }
            markAndUnmarkTask(tasks, taskNumber - 1, arrayOfCommand);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.print(Guide.MARK_AND_UNMARK_REQUEST_FORMAT);
        } catch (NullPointerException e) {
            System.out.print(Guide.OUT_OF_BOUND);
        }
    }

    public static void handleTodoDeadlineAndEvent(String userCommand, int taskCount, String[] arrayOfCommand, ArrayList<Task> tasks) {
        if (userCommand.startsWith("todo")) {
            arrayOfCommand = userCommand.split(" ", 2);
            Todo todo =  new Todo(arrayOfCommand[1]);
            tasks.add(todo);
        }

        if (userCommand.startsWith("deadline")) {
            arrayOfCommand = userCommand.split("deadline | /by");
            Deadline deadline =  new Deadline(arrayOfCommand[1], arrayOfCommand[2]);
            tasks.add(deadline);
        }

        if (userCommand.startsWith("event")) {
            arrayOfCommand = userCommand.split("event | /from | /to ");
            Event event =  new Event(arrayOfCommand[1], arrayOfCommand[2],arrayOfCommand[3]);
            tasks.add(event);
        }

        System.out.println("Got it. I've added this task:");
        tasks.get(taskCount).printTask();
    }

    public static void removeTask(ArrayList<Task> tasks, int taskNumber) {
        System.out.println("Sure. I've removed this task: ");
        tasks.get(taskNumber).printTask();
        tasks.remove(taskNumber);
    }
    public static void handleCommand(String userCommand) {
        ArrayList<Task> tasks = new ArrayList<>();
        String[] arrayOfCommand = new String[4];
        int taskCount = 0;
        Scanner in = new Scanner(System.in);

        while (!userCommand.equals("bye")) {
            String[] words = userCommand.split(" ");
            try {
                switch (words[0]) {
                case "list":
                    printTasks(tasks);
                    userCommand = in.nextLine();
                    break;
                case "unmark":
                    handleMarkAndUnmarkRequest(userCommand, arrayOfCommand, tasks, taskCount);
                    userCommand = in.nextLine();
                    break;
                case "mark":
                    handleMarkAndUnmarkRequest(userCommand, arrayOfCommand, tasks, taskCount);
                    userCommand = in.nextLine();
                    break;
                case "delete":
                    removeTask(tasks, Integer.parseInt(words[1])-1);
                    taskCount--;
                    System.out.println("Now you have " + taskCount + " task(s) in the list.");
                    userCommand = in.nextLine();
                    break;
                case "todo":
                    handleTodoDeadlineAndEvent(userCommand, taskCount, arrayOfCommand, tasks);
                    taskCount++;
                    System.out.println("Now you have " + taskCount + " task(s) in the list.");
                    userCommand = in.nextLine();
                    break;
                case "deadline":
                    handleTodoDeadlineAndEvent(userCommand, taskCount, arrayOfCommand, tasks);
                    taskCount++;
                    System.out.println("Now you have " + taskCount + " task(s) in the list.");
                    userCommand = in.nextLine();
                    break;
                case "event":
                    handleTodoDeadlineAndEvent(userCommand, taskCount, arrayOfCommand, tasks);
                    taskCount++;
                    System.out.println("Now you have " + taskCount + " task(s) in the list.");
                    userCommand = in.nextLine();
                    break;
                default:
                    System.out.println(Guide.REQUESTS_FORMAT);
                    userCommand = in.nextLine();
                }
            } catch (IndexOutOfBoundsException e) {
                switch (words[0]) {
                case "delete":
                    System.out.println(Guide.DELETE_REQUEST_FORMAT);
                    userCommand = in.nextLine();
                    break;
                case "todo":
                    System.out.println(Guide.TODO_REQUEST_FORMAT);
                    userCommand = in.nextLine();
                    break;
                case "deadline":
                    System.out.println(Guide.DEADLINE_REQUEST_FORMAT);
                    userCommand = in.nextLine();
                    break;
                case "event":
                    System.out.println(Guide.EVENT_REQUEST_FORMAT);
                    userCommand = in.nextLine();
                    break;
                default:
                    break;
                }
            }
        }
        System.out.println("Bye! Hope to see you again :)");
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Ms Chatty :)");
        System.out.println("What can I do for you?");

        Scanner in = new Scanner(System.in);
        String userCommand = in.nextLine();
        handleCommand(userCommand);
    }
}