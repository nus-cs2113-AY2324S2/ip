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
            }
        }

    }



    private static void addTask(String input) {
        if (taskCount >= MAX_TASKS) {
            System.out.println("NO MORE I SHORT TERM MMR");
            return;
        }

        Task newTask = null;
        if (input.toLowerCase().startsWith("todo ")) {
            String description = input.substring(5).trim();
            if (description.isEmpty()) {
                System.out.println("hello fool how can you todo nothing??????");
                return;
            }
            newTask = new ToDo(description, "");
        } else if (input.toLowerCase().startsWith("deadline ")) {
            String[] parts = input.substring(9).split("/by", 2);
            if (parts.length < 2 || parts[0].trim().isEmpty()) {
                System.out.println("if never tell me /by how I know the deadline??? im ded.");
                return; // simple validation
            }
            newTask = new Deadline(parts[0].trim(), parts[1].trim());
        } else if (input.toLowerCase().startsWith("event ")) {
            String[] parts = input.substring(6).split("/from", 2);
            if (parts.length < 2 || parts[0].trim().isEmpty()) {
                System.out.println("you tell me empty event for what");
                return; // simple validation
            }
            String[] timeParts = parts[1].trim().split("/to", 2);
            if (timeParts.length < 2) {
                System.out.println("event no (/from and /to)??? then what time u can go home?");
                return; // Simple validation
            }
            newTask = new Event(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
        } else {
            System.out.println("you dont make any sense");
            return;
        }

        if (newTask != null) {
            tasks[taskCount++] = newTask;
            System.out.println("OKIE I MEMORISED FOR U:\n  " + newTask);
            System.out.println("You have " + taskCount + " remaining things to dododo.");
        }
    }






    private static void displayTasks() {
        if (taskCount == 0) {
            System.out.println("U never tell me anything how I know");
        } else {
            System.out.println("Every time need me to remind you...");
            for (int i = 0; i < taskCount; i++) {
                System.out.println(tasks[i]);
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



