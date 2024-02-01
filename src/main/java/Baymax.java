import java.util.Scanner;

public class Baymax {
    public static void main(String[] args) {
        String name = "Baymax";
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?\n\n");

        Task[] list = new Task[100];
        int num = 0;

        while(true) {
            String text;

            Scanner in = new Scanner(System.in);
            text = in.nextLine();

            if (text.equalsIgnoreCase("bye")) {
                break;
            }
            else if (text.startsWith("mark")) {
                int position = Integer.parseInt(text.substring(5)) - 1;
                list[position].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] " + list[position].description);
            }

            else if (text.startsWith("unmark")) {
                int position = Integer.parseInt(text.substring(7)) - 1;
                list[position].isDone = false;
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[ ] " + list[position].description);
            }

            else if (text.equalsIgnoreCase("list")) {
                for (int i = 0; i < 100; i++) {

                    if(list[i] == null) {
                        break;
                    }
                    System.out.println(i+1 + ". " + "[" + list[i].getStatusIcon() + "] " + list[i].description);
                }
            }
            else {
                System.out.println("added: " + text);

                list[num] = new Task(text);
                num++;
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

}
