import java.util.Scanner;

public class Baymax {
    public static void main(String[] args) {
        String name = "Baymax";
        System.out.println("Hello! I'm " + name + ", your personal task manager." + System.lineSeparator()
                + "What can I do for you today?" + System.lineSeparator() + System.lineSeparator());

        Task[] tasks = new Task[100];
        int num = 0;
        String text;

        while(true) {

            Scanner in = new Scanner(System.in);
            text = in.nextLine();

            // "bye" -- terminate
            if (text.equalsIgnoreCase("bye")) {
                break;
            }

            // "mark" -- mark task
            else if (text.startsWith("mark")) {
                int position = Integer.parseInt(text.substring("mark".length() + 1)) - 1;
                tasks[position].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] " + tasks[position].description);
            }

            // "unmark" -- unmark task
            else if (text.startsWith("unmark")) {
                int position = Integer.parseInt(text.substring("unmark".length() + 1)) - 1;
                tasks[position].isDone = false;
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[ ] " + tasks[position].description);
            }

            // "tasks" -- displays tasks
            else if (text.equalsIgnoreCase("list")) {
                for (int i = 0; i < 100; i++) {

                    if(tasks[i] == null) {
                        break;
                    }
                    System.out.println(i+1 + ". " + "[" + tasks[i].type + "] " + "[" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
                }
            }

            // ADD TASK
            else if (text.startsWith(("todo")) || text.startsWith("deadline") || text.startsWith("event")) {
                Task.addTask(text,tasks,num);
                num++;
            }

            // "{other words}" -- REJECT
            else {
                System.out.println("OH NOOO! I don't know what that means.");
            }
        }

        System.out.println("I hope you are satisfied with my service! Goodbye.");
    }

}
