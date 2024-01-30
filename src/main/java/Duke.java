import java.util.Scanner;
public class Duke {
    static String break_line = "____________________________________________________________";
    static String done = "[X]";
    static String notDone = "[ ]";

    public static void main(String[] args) {
        System.out.println(break_line + "\nHello! I'm Jeremy.\nWhat can I do for you?\n" + break_line);

        String[] tasks = new String[100];
        String[] doneCheck = new String[100];
        int count = 0;

        Scanner in = new Scanner(System.in);
        String line;

        do {
            line = in.nextLine();
            System.out.println(break_line);

            if (!line.equals("bye")) {
                if (line.equals("list")) {

                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < count; i++) {

                        System.out.println((i + 1) + "." + doneCheck[i] + " " + tasks[i]);
                    }
                    System.out.println(break_line);
                } else if (line.contains("unmark")) {

                    String[] parts = line.split(" ");
                    int taskMarkNumber = Integer.parseInt(parts[1]) - 1;
                    doneCheck[taskMarkNumber] = notDone;
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(doneCheck[taskMarkNumber] + " " + tasks[taskMarkNumber] + "\n" + break_line);
                } else if (line.contains("mark")){

                    String[] parts = line.split(" ");
                    int taskMarkNumber = Integer.parseInt(parts[1]) - 1;
                    doneCheck[taskMarkNumber] = done;
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(doneCheck[taskMarkNumber] + " " + tasks[taskMarkNumber] + "\n" + break_line);
                } else {

                    tasks[count] = line;
                    doneCheck[count] = notDone;
                    System.out.println("added: " + line + "\n" + break_line);
                    count++;
                }
            }
        } while (!line.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!\n" + break_line);
    }
}