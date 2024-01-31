import java.util.Scanner;


public class Phoebe {
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;
    public static void main(String[] args) {
        String greet = "\n" +
                "█▀█ █░█ █▀█ █▀▀ █▄▄ █▀▀\n" +
                "█▀▀ █▀█ █▄█ ██▄ █▄█ ██▄\n" + "HELLOOOO WHATCHA DOING???????";
        String exit = "byebye\n" + "ฅ^•ﻌ•^ฅ";
        System.out.println(greet);

        String input;
        Scanner in  = new Scanner(System.in);
        while (true) {
            input = in.nextLine();

            if (input.equalsIgnoreCase("bye")) {     ///// level1echo/////////
                System.out.println(exit);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                displayTasks();
            } else if (input.toLowerCase().startsWith("mark ")) {
                markTask(input);
            } else if (input.toLowerCase().startsWith("unmark ")) {
                unmarkTask(input);
            } else {
                addTask(input);
                System.out.println("added: " + input + "\n");
            }
        }

    }

    private static void addTask(String taskDescription) {
        if (taskCount < MAX_TASKS) {
            Task newTask = new Task(taskDescription);
            tasks[taskCount++] = newTask;
        } else {
            System.out.println("NO MORE I SHORT TERM MMR");
        }
    }


    private static void displayTasks() {
        if (taskCount == 0) {
            System.out.println("U never tell me anything how I know");
        } else {
            System.out.println("Every time need me to remind you...");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
            }
        }
    }


    private static void markTask(String input) {

        int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
        if (isValidIndex(taskIndex)) {
            tasks[taskIndex].markAsDone();
            System.out.println("YAY GOOD JOB\n  " + tasks[taskIndex]);
        } else {
            System.out.println("u stupid u never tell me this before:");
        }

    }

    private static void unmarkTask(String input) {

        int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
        if (isValidIndex(taskIndex)) {
            tasks[taskIndex].markAsUndone();
            System.out.println("Just now say do alr now never do\n  " + tasks[taskIndex]);
        } else {
            System.out.println("u stupid u never tell me this before:");
        }

    }

    private static boolean isValidIndex(int index) {
        return index >= 0 && index < taskCount;
    }
}



