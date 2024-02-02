import java.util.Scanner;

public class Katleen {
    public static final String LINE = "____________________________________________________________";

    public static void main(String[] args) {
        System.out.println(LINE);
        System.out.println("Hello! I'm Katleen.");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        Task[] tasks = new Task[100];
        int count = 0;

        Scanner in = new Scanner(System.in);
        String text = in.nextLine();

        while (!text.equals("bye")) {
            System.out.println(LINE);
            if (text.contains("mark")) {
                String index = text.substring(5);
                if (text.contains("unmark")) {
                    index = text.substring(7);
                }
                int i = Integer.parseInt(index) -1;
                tasks[i].toggleDone();
                if (tasks[i].isDone) {
                    System.out.println("Well done! This task is marked as done:");
                } else {
                    System.out.println("This task is marked as undone:");
                }
                tasks[i].printTask();
            } else if (text.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    System.out.print(i + 1 + ". ");
                    tasks[i].printTask();
                }
            } else {
                System.out.println("Added: " + text);
                Task task = new Task(text);
                tasks[count] = task;
                count++;
            }
            System.out.println(LINE);
            text = in.nextLine();
        }
        System.out.println(LINE);
        System.out.println("Bye, have a nice day!");
        System.out.println(LINE);
    }
}
