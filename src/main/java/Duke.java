import Event.Event;
import Event.Deadline;
import Event.ToDo;
import Event.Task;
import RuntimeSupport.DukeException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static String BREAK_LINE = "____________________________________________________________";

    public static void main(String[] args) {

        System.out.println(BREAK_LINE + "\nHello! I'm 550W.\nWhat can I do for you?\n" + BREAK_LINE);
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        String line = "";

        while (true) {
            try {
                line = in.nextLine();
                System.out.println(BREAK_LINE);

                if (line.equals("bye")) {
                    break; //Break out of the loop immediately when bye command is entered, quitting the program.
                }

                if (line.startsWith("delete")) {
                    int taskNumber = Integer.parseInt(line.split(" ")[1]) - 1;
                    Task removedTask = tasks.remove(taskNumber);
                    System.out.println("Noted. I've removed this task:\n  " + removedTask);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.\n" + BREAK_LINE);
                    continue;
                }

                if (line.equals("list")) {

                    System.out.println("Abracadabra! \uD83C\uDF1F Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i).toString());
                    }
                    System.out.println(BREAK_LINE);

                } else if (line.startsWith("unmark")) {

                    int taskMarkNumber = Integer.parseInt(line.split(" ")[1]) - 1;
                    tasks.get(taskMarkNumber).markAsNotDone();
                    System.out.println("All good! We've hit the rewind button and unmarked this task \uD83D\uDD04:");
                    System.out.println("  " + tasks.get(taskMarkNumber) + "\n" + BREAK_LINE);

                } else if (line.startsWith("mark")) {

                    int taskMarkNumber = Integer.parseInt(line.split(" ")[1]) - 1;
                    tasks.get(taskMarkNumber).markAsDone();
                    System.out.println("X marks the spot! \uD83C\uDF89 I've marked this task as done:");
                    System.out.println("  " + tasks.get(taskMarkNumber) + "\n" + BREAK_LINE);

                } else if (line.startsWith("todo")) {

                    Task newTask = new ToDo(line);
                    tasks.add(newTask);
                    newTask.print(tasks.size());

                } else if (line.startsWith("deadline")) {

                    Task newTask = new Deadline(line);
                    tasks.add(newTask);
                    newTask.print(tasks.size());

                } else if (line.startsWith("event")) {

                    Task newTask = new Event(line);
                    tasks.add(newTask);
                    newTask.print(tasks.size());

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
