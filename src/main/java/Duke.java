import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Gary");
        System.out.println("What can I do for you?");

        Task[] todos = new Task[100];
        int todosCount = 0;

        String line;
        line = in.nextLine();
        String[] lineWords;
        Boolean isDone;

        while (!(line.toUpperCase().contains("BYE"))) {
            lineWords = line.split(" ");
            if (line.equalsIgnoreCase("LIST")) {
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < todosCount; i += 1) {
                    isDone = todos[i].getTaskStatus();
                    System.out.println((i + 1)
                            + "." + "["
                            + (isDone ? "X" : " ")
                            + "] "
                            + todos[i].getTaskDescription());
                }
            } else if (lineWords[0].equalsIgnoreCase("MARK")) {
                todos[Integer.parseInt(lineWords[1]) - 1].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  "
                        + "[X] "
                        + todos[Integer.parseInt(lineWords[1]) - 1].getTaskDescription());
            } else if (lineWords[0].equalsIgnoreCase("UNMARK")) {
                todos[Integer.parseInt(lineWords[1]) - 1].unmarkAsDone();
                System.out.println("Ok, I've marked this task as not done yet:");
                System.out.println("  "
                        + "[ ] "
                        + todos[Integer.parseInt(lineWords[1]) - 1].getTaskDescription());
            } else {
                System.out.println("added: " + line);
                todos[todosCount] = new Task(line);
                todosCount += 1;
            }

            line = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again!");
    }
}
