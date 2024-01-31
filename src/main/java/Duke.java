import java.util.Scanner;
public class Duke {
    static String BREAK_LINE = "____________________________________________________________";
    static String DONE = "[X]";
    static String NOT_DONE = "[ ]";

    public static void main(String[] args) {
        System.out.println(BREAK_LINE + "\nHello! I'm Jeremy.\nWhat can I do for you?\n" + BREAK_LINE);

        String[] tasks = new String[100];
        String[] doneCheck = new String[100];
        int count = 0;

        Scanner in = new Scanner(System.in);
        String line;

        do {
            line = in.nextLine();
            System.out.println(BREAK_LINE);

            if (!line.equals("bye")) {
                if (line.equals("list")) {

                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < count; i++) {

                        System.out.println((i + 1) + "." + doneCheck[i] + " " + tasks[i]);
                    }
                    System.out.println(BREAK_LINE);
                } else if (line.contains("unmark")) {

                    String[] parts = line.split(" ");
                    int taskMarkNumber = Integer.parseInt(parts[1]) - 1;
                    doneCheck[taskMarkNumber] = NOT_DONE;
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(doneCheck[taskMarkNumber] + " " + tasks[taskMarkNumber] + "\n" + BREAK_LINE);
                } else if (line.contains("mark")){

                    String[] parts = line.split(" ");
                    int taskMarkNumber = Integer.parseInt(parts[1]) - 1;
                    doneCheck[taskMarkNumber] = DONE;
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(doneCheck[taskMarkNumber] + " " + tasks[taskMarkNumber] + "\n" + BREAK_LINE);
                } else {

                    tasks[count] = line;
                    doneCheck[count] = NOT_DONE;
                    System.out.println("added: " + line + "\n" + BREAK_LINE);
                    count++;
                }
            }
        } while (!line.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!\n" + BREAK_LINE);
    }
}