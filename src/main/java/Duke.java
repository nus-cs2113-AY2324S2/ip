import java.util.Scanner;
public class Duke {
    static String BREAK_LINE = "____________________________________________________________";

    public static void main(String[] args) {

        System.out.println(BREAK_LINE + "\nHello! I'm Jeremy.\nWhat can I do for you?\n" + BREAK_LINE);
        Task[] tasks = new Task[100]; //To store the list of tasks inputted.
        int count = 0;
        Scanner in = new Scanner(System.in);
        String line;

        do {

            line = in.nextLine(); //String Line that reads in the next line of input.
            System.out.println(BREAK_LINE);

            if (!line.equals("bye")) {

                if (line.equals("list")) {

                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < count; i++) {

                        System.out.println((i + 1) + "." + tasks[i].toString());
                    }

                    System.out.println(BREAK_LINE);

                } else if (line.startsWith("unmark")) {

                    int taskMarkNumber = Integer.parseInt(line.split(" ")[1]) - 1;
                    tasks[taskMarkNumber].markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks[taskMarkNumber] + "\n" + BREAK_LINE);

                } else if (line.startsWith("mark")) {

                    int taskMarkNumber = Integer.parseInt(line.split(" ")[1]) - 1;
                    tasks[taskMarkNumber].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks[taskMarkNumber] + "\n" + BREAK_LINE);

                } else {

                    tasks[count] = new Task(line); //Takes in a new line of input.
                    System.out.println("added: " + line + "\n" + BREAK_LINE);
                    count++;
                }
            }
        } while (!line.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!\n" + BREAK_LINE);

    }
}