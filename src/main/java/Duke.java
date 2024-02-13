import java.util.Scanner;

public class Duke {
    static String BREAK_LINE = "____________________________________________________________";

    public static void main(String[] args) {

        System.out.println(BREAK_LINE + "\nHello! I'm 550W.\nWhat can I do for you?\n" + BREAK_LINE);
        Task[] tasks = new Task[100];
        int count = 0;
        Scanner in = new Scanner(System.in);
        String line = "";

        while (true) {
            try {
                line = in.nextLine();
                System.out.println(BREAK_LINE);

                if (line.equals("bye")) {
                    break; //Break out of the loop immediately when bye command is entered, quitting the programme.
                }

                if (line.equals("list")) {

                    System.out.println("Abracadabra! \uD83C\uDF1F Here are the tasks in your list:");
                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1) + "." + tasks[i].toString());
                    }
                    System.out.println(BREAK_LINE);

                } else if (line.startsWith("unmark")) {

                    int taskMarkNumber = Integer.parseInt(line.split(" ")[1]) - 1;
                    tasks[taskMarkNumber].markAsNotDone();
                    System.out.println("All good! We've hit the rewind button and unmarked this task \uD83D\uDD04:");
                    System.out.println("  " + tasks[taskMarkNumber] + "\n" + BREAK_LINE);

                } else if (line.startsWith("mark")) {

                    int taskMarkNumber = Integer.parseInt(line.split(" ")[1]) - 1;
                    tasks[taskMarkNumber].markAsDone();
                    System.out.println("X marks the spot! \uD83C\uDF89 I've marked this task as done:");
                    System.out.println("  " + tasks[taskMarkNumber] + "\n" + BREAK_LINE);

                } else if (line.startsWith("todo")) {

                    tasks[count] = new ToDo(line);
                    tasks[count].print(count);
                    count++;

                } else if (line.startsWith("deadline")) {

                    tasks[count] = new Deadline(line);
                    tasks[count].print(count);
                    count++;

                } else if (line.startsWith("event")) {

                    tasks[count] = new Event(line);
                    tasks[count].print(count);
                    count++;

                } else {

                    System.out.println("Oops! I've no clue what that means. Could you enlighten me, please? \uD83E\uDD16\uD83D\uDCA1");
                    System.out.println(BREAK_LINE);
                }
            } catch (Exception error) {

                DukeException.handleException(error, line);
            }
        }

        System.out.println("Bye. Hope to see you again soon! \uD83D\uDE0A\n" + BREAK_LINE);
    }
}
