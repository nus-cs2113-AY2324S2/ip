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

        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                String input = in.nextLine();

                if (input.equalsIgnoreCase("bye")) {
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
            } catch (PhoebeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void addTask(String input) throws PhoebeException {
        if (taskCount >= MAX_TASKS) {
            throw new PhoebeException("NO MORE I SHORT TERM MMR");
        }

        input = input.trim();
        Task newTask = null;
        if (input.toLowerCase().startsWith("todo")) {
            String description = input.substring(4).trim();
            if (description.isEmpty()) {
                throw new PhoebeException("hello fool how can you todo nothing??????");
            }
            newTask = new ToDo(description);
        } else if (input.toLowerCase().startsWith("deadline")) {
            String[] parts = input.substring(8).split("/by", 2);
            if (parts.length < 2 || parts[0].trim().isEmpty()) {
                throw new PhoebeException("if never tell me /by how I know the deadline??? im ded.");
            }
            newTask = new Deadline(parts[0].trim(), parts[1].trim());
        } else if (input.toLowerCase().startsWith("event")) {
            String[] parts = input.substring(5).split("/from", 2);
            if (parts.length < 2 || parts[0].trim().isEmpty()) {
                throw new PhoebeException("you tell me empty event for what");
            }
            String[] timeParts = parts[1].trim().split("/to", 2);
            if (timeParts.length < 2) {
                throw new PhoebeException("event no (/from and /to)??? then what time u can go home?");
            }
            newTask = new Event(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
        } else {
            throw new PhoebeException("you dont make any sense");
        }

        tasks[taskCount++] = newTask;
        System.out.println("OKIE I MEMORISED FOR U:\n  " + newTask);
        System.out.println("You have " + taskCount + " remaining things to dododo.");
    }

    private static void displayTasks() throws PhoebeException {
        if (taskCount == 0) {
            throw new PhoebeException("U never tell me anything how I know");
        } else {
            System.out.println("Every time need me to remind you...");
            for (int i = 0; i < taskCount; i++) {
                System.out.println(tasks[i]);
            }
        }
    }

    private static void markTask(String input) throws PhoebeException {
        int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
        if (!isValidIndex(taskIndex)) {
            throw new PhoebeException("u stupid u never tell me this before:");
        }
        tasks[taskIndex].markAsDone();
        System.out.println("YAY GOOD JOB\n  " + tasks[taskIndex]);
    }

    private static void unmarkTask(String input) throws PhoebeException {
        int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
        if (!isValidIndex(taskIndex)) {
            throw new PhoebeException("u stupid u never tell me this before:");
        }
        tasks[taskIndex].markAsUndone();
        System.out.println("Just now say do alr now never do\n  " + tasks[taskIndex]);
    }

    private static boolean isValidIndex(int index) {
        return index >= 0 && index < taskCount;
    }
}


