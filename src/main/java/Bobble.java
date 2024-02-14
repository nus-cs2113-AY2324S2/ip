import java.util.Scanner;

public class Bobble {

    public static final String LINE_WRAP = "____________________________________________________________\n";

    public static void main(String[] args) {
        start();
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        Task[] tasklist = new Task[100];
        int taskCount = 0;
        String command;

        while (!userInput.equals("bye")) {
            String[] UserInputs = getCommandAndDesc(userInput);
            command = UserInputs[0];
            try {
                switch (command) {
                case "list":
                    listResponse(tasklist, taskCount);
                    break;
                case "todo":
                    tasklist[taskCount] = new ToDo(UserInputs[1]);
                    addTaskResponse(tasklist[taskCount], taskCount);
                    taskCount++;
                    break;
                case "deadline":
                    String[] parts = UserInputs[1].split("/by");
                    tasklist[taskCount] = new Deadline(parts[0], parts[1]);
                    addTaskResponse(tasklist[taskCount], taskCount);
                    taskCount++;
                    break;
                case "event":
                    parts = UserInputs[1].split("/from");
                    String[] duration = parts[1].split("/to");
                    tasklist[taskCount] = new Event(parts[0], duration[0], duration[1]);
                    addTaskResponse(tasklist[taskCount], taskCount);
                    taskCount++;
                    break;
                case "mark":
                    int taskNumber = Integer.parseInt(userInput.substring(5)) - 1;
                    tasklist[taskNumber].setDone(true);
                    markResponse(tasklist[taskNumber]);
                    break;
                case "unmark":
                    taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
                    tasklist[taskNumber].setDone(false);
                    unmarkResponse(tasklist[taskNumber]);
                    break;
                default:
                    throw new DukeExceptionCommand();
                }
            } catch (DukeExceptionCommand e) {
                System.out.println(LINE_WRAP +
                        "OOPS!! I'm sorry, but I don't know what that means :-(\n" +
                        LINE_WRAP);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(LINE_WRAP +
                        "OOPS!! One or more fields of a(n) " + command + " cannot be empty.\n" +
                        LINE_WRAP);
            }
            userInput = input.nextLine();
        }
        goodbye();
    }

    private static void listResponse(Task[] tasklist,int taskCount) {
        System.out.println(LINE_WRAP + "Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + tasklist[i].toString());
        }
        System.out.println(LINE_WRAP);
    }

    private static void markResponse(Task tasklist) {
        System.out.println("Nice! I've marked this task as done:\n" +
                tasklist.toString() + "\n" + LINE_WRAP);
    }

    private static void unmarkResponse(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" +
                task.toString() + "\n" + LINE_WRAP);
    }

    public static String[] getCommandAndDesc(String input) {
        String[] userInput = input.split(" ", 2);
        return userInput;
    }

    public static void addTaskResponse(Task task, int taskCount) {
        System.out.println(LINE_WRAP +
                "Got it. I've added this task: \n  " +
                task.toString() +
                "\nNow you have " + (taskCount + 1) + " task(s) in the list.\n" +
                LINE_WRAP);
    }

    //Greets user
    public static void start() {
        System.out.println(LINE_WRAP +
                "Hello! I'm Bobble.\n" +
                "What can I do for you?\n" +
                LINE_WRAP);
    }

    //Exits
    public static void goodbye() {
        System.out.println(LINE_WRAP +
                "Bye. Hope to see you again soon!\n" + LINE_WRAP);
    }

}
