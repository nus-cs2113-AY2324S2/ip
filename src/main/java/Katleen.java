import java.util.Scanner;

public class Katleen {
    public static final String LINE = "____________________________________________________________";

    public static void main(String[] args) {
        System.out.println(LINE);
        System.out.println("Hello! I'm Katleen.");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        Task[] tasks = new Task[100];

        Scanner in = new Scanner(System.in);
        String text = in.nextLine();
        while (!text.equals("bye")) {
            String[] textSplit = text.split(" ");
            String cmdWord = textSplit[0];
            System.out.println(LINE);
            if (cmdWord.equals("mark")) {
                String index = textSplit[1];
                int i = Integer.parseInt(index) - 1;
                tasks[i].setDone(true);
                System.out.println("Well done! This task is marked as done:");
                tasks[i].printTask();
            } else if (cmdWord.equals("unmark")) {
                String index = textSplit[1];
                int i = Integer.parseInt(index) - 1;
                tasks[i].setDone(false);
                System.out.println("This task is marked as undone:");
                tasks[i].printTask();
            } else if (cmdWord.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < Task.getTaskCount(); i++) {
                    System.out.print((i+1) + ". ");
                    tasks[i].printTask();
                }
            } else if (cmdWord.equals("todo")) {
                System.out.println("Added: ");
                int i = Task.getTaskCount();
                ToDo task = new ToDo(text);
                tasks[i] = task;
                tasks[i].printTask();
            } else if (cmdWord.equals("deadline")) {
                System.out.println("Added: ");
                int i = Task.getTaskCount();
                String by = text.substring(text.indexOf("/by")).trim();
                Deadline task = new Deadline(text, by);
                tasks[i] = task;
                tasks[i].printTask();
            }else {
                System.out.println("Invalid command, please try again");
            }
            System.out.println(LINE);
            text = in.nextLine();
        }
        System.out.println(LINE);
        System.out.println("Bye, have a nice day!");
        System.out.println(LINE);
    }

}
