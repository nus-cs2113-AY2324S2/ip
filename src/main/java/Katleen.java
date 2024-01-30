import java.util.Scanner;
public class Katleen {
    public static final String line = "____________________________________________________________";

    public static void main(String[] args) {
        System.out.println(line);
        System.out.println("Hello! I'm Katleen.");
        System.out.println("What can I do for you?");
        System.out.println(line);

        Task[] tasks = new Task[100];
        int count = 0;
        Scanner in = new Scanner(System.in);
        String text = in.nextLine();
        while (!text.equals("bye")) {
            if (text.contains("mark")) {
                String index = text.substring(5);
                System.out.println("TEST " + index);
                if (text.contains("unmark")) {
                    index = text.substring(7);
                }
                int i = Integer.parseInt(index);
                System.out.print(i);
                tasks[i].toggleDone();
                if (tasks[i].isDone) {
                    System.out.println("Well done! This task is marked as done:");
                } else {
                    System.out.println("This task is marked as undone:");
                }
                tasks[i].printTask();
            } else if (text.equals("list")) {
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    System.out.print(i + 1 + ". ");
                    tasks[i].printTask();
                }
                System.out.println(line);
            } else {
                System.out.println(line);
                System.out.print("added: ");
                System.out.println(text);
                Task task = new Task(text);
                tasks[count] = task;
                count++;
                System.out.println(line);
            }
            text = in.nextLine();
            if (text.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye, have a nice day!");
                System.out.println(line);
                return;
            }
        }
    }
}
