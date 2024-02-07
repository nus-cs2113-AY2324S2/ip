import java.util.Scanner;

public class Baymax {
    public static void main(String[] args) {
        String name = "Baymax";
        System.out.println("Hello! I'm " + name + ", your personal task manager." + System.lineSeparator() + "What can I do for you today?" + System.lineSeparator() + System.lineSeparator());

        Task[] list = new Task[100];
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
                int position = Integer.parseInt(text.substring(5)) - 1;
                list[position].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] " + list[position].description);
            }

            // "unmark" -- unmark task
            else if (text.startsWith("unmark")) {
                int position = Integer.parseInt(text.substring(7)) - 1;
                list[position].isDone = false;
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[ ] " + list[position].description);
            }

            // "list" -- displays tasks
            else if (text.equalsIgnoreCase("list")) {
                for (int i = 0; i < 100; i++) {

                    if(list[i] == null) {
                        break;
                    }
                    System.out.println(i+1 + ". " + "[" + list[i].type + "] " + "[" + list[i].getStatusIcon() + "] " + list[i].description);
                }
            }

            // "{other words}" -- ADD TASK
            else {
                Task.addTask(text, list, num);
                num++; // need to account for error messages!
            }
        }

        System.out.println("I hope you are satisfied with my service! Goodbye.");
    }

}
