import java.util.Scanner;
public class Duke {
    static String break_line = "____________________________________________________________";

    public static void main(String[] args) {
        System.out.println(break_line + "\nHello! I'm Jeremy.\nWhat can I do for you?\n" + break_line);

        String[] tasks = new String[100];
        int count = 0;

        Scanner in = new Scanner(System.in);
        String line;

        do {
            line = in.nextLine();

            if (!line.equals("bye")) {
                if (line.equals("list")) {
                    // Print the list of tasks
                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                    System.out.println(break_line);
                } else {
                    tasks[count] = line;
                    System.out.println("added: " + line + "\n" + break_line);
                    count++;
                }
            }
        } while (!line.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!\n" + break_line);
    }
}