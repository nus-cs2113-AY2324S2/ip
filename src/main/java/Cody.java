import java.util.Scanner;
public class Cody {

    private static void createList() {
        Task[] tasks = new Task[100];
        int taskCount = 0;

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printList(tasks, taskCount);
            } else if (input.startsWith("mark")) {
                String[] words = input.split(" ");
                int index = Integer.parseInt(words[1]) - 1;
                tasks[index].markTask(true);
                markDone(tasks, index);
            } else if (input.startsWith("unmark")) {
                String[] words = input.split(" ");
                int index = Integer.parseInt(words[1]) - 1;
                tasks[index].markTask(false);
                markUndone(tasks, index);
            } else {
                Task task = new Task(input);
                addTask(tasks, task, taskCount);
                taskCount++;
            }
            input = in.nextLine();
        }
    }

    public static void printList(Task[] tasks, int taskCount) {
        System.out.println("______________________________________________________________\n"
                + " Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
        }
        System.out.println("_____________________________________________________________");
    }

    public static void markDone(Task[] tasks, int index) {
        System.out.println("______________________________________________________________\n"
                + " Good job! I've marked this task as done:\n"
                + " [X] " + tasks[index].getDescription() + "\n"
                + "_____________________________________________________________");
    }

    public static void markUndone(Task[] tasks, int index) {
        System.out.println("______________________________________________________________\n"
                + " Okay! I've marked this task as not done yet:\n"
                + " [ ] " + tasks[index].getDescription() + "\n"
                + "_____________________________________________________________");
    }

    public static void addTask(Task[] tasks, Task task, int taskCount) {
        tasks[taskCount] = task;
        System.out.println("______________________________________________________________\n"
                + " Got it. I've added this task to your list:\n"
                + "   [ ] " + task.getDescription() + "\n"
                + "_____________________________________________________________");
    }

    private static void greet() {
        String greet = " ____________________________________________________________\n"
                + " Hey there! I'm Cody, your personal task manager\n"
                + " Tell me your tasks and I will create a task list for you\n"
                + "_____________________________________________________________\n";
        System.out.println(greet);
    }

    private static void exit() {
        String exit = "_____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "_____________________________________________________________";
        System.out.println(exit);
    }

    public static void main(String[] args) {
        greet();
        createList();
        exit();
    }
}

