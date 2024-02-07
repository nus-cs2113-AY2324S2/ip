import java.util.Scanner;

public class Ava {

    public static void main(String[] args) {
        greet();
        mainProcess();
        exit();
    }

    public static void mainProcess() {
        boolean isExit = false;
        Task[] tasks = new Task[100];
        Scanner in = new Scanner(System.in);
        while (!isExit) {
            String task = in.nextLine();
            if (task.equals("bye")) {
                isExit = true;
                continue;
            } else if (task.equals("list")) {
                listTask(tasks);
                continue;
            } else if (task.contains("mark")) {
                markTask(tasks, task);
                continue;
            } else if (task.startsWith("todo")) {
                task = task.replace("todo ", "");
                tasks[Task.getNumberOfTasks()] = new ToDo(task);
            } else if (task.startsWith("deadline")) {
                String[] taskAndDate = trimTask(task, "deadline ");
                tasks[Task.getNumberOfTasks()] = new Deadline(taskAndDate[0], taskAndDate[1]);
            } else if (task.startsWith("event")) {
                String[] taskAndDate = trimTask(task, "event ");
                tasks[Task.getNumberOfTasks()] = new Event(taskAndDate[0], taskAndDate[1], taskAndDate[2]);
            }
            addTask(tasks);
        }
    }

    public static String[] trimTask(String task, String type) {
        task = task.replace(type, "");
        return task.split("/");
    }

    public static void addTask(Task[] tasks) {
        printLine();
        System.out.println("Got it! I've added this task:");
        System.out.println(tasks[Task.getNumberOfTasks() - 1].toString());
        if (Task.getNumberOfTasks() == 1) {
            System.out.println("Now you have " + 1 + " task in the list~~~");
        } else {
            System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in the list~~~");
        }
        printLine();
    }

    public static void markTask(Task[] tasks, String command) {
        printLine();
        boolean isMark = true;
        int taskChanged = 0;
        if (command.startsWith("unmark")) {
            isMark = false;
        }
        if (isMark) {
            command = command.replace("mark ", "");
            taskChanged = Integer.parseInt(command) - 1;
            tasks[taskChanged].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            command = command.replace("unmark ", "");
            taskChanged = Integer.parseInt(command) - 1;
            tasks[taskChanged].markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(tasks[taskChanged].toString());
        printLine();
    }

    public static void listTask(Task[] tasks) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        int noOfTask = 0;
        while (noOfTask < Task.getNumberOfTasks()) {
            System.out.println((noOfTask + 1) + "." + tasks[noOfTask].toString());
            noOfTask += 1;
        }
        printLine();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void greet() {
        printLine();
        System.out.println(" Hello!!! AvavaAVA!!! Here is Ava!!!");
        System.out.println(" Let's have a relaxing and happy chat together!!!");
        System.out.println(" What can I do for you?");
        printLine();
    }

    public static void exit() {
        printLine();
        System.out.println(" Bye!!! Hope to see you again soon!!!");
        printLine();
    }
}
