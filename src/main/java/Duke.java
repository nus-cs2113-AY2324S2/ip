import Event.Event;
import Event.Deadline;
import Event.ToDo;
import Event.Task;
import RuntimeSupport.DukeException;
import java.util.ArrayList;
import java.util.Scanner;
import RuntimeSupport.Storage;

public class Duke {
    static final String FILE_PATH = "./duke.txt";
    static String BREAK_LINE = "____________________________________________________________";
    static ArrayList<Task> tasks = new ArrayList<>();
    static String line = "";

    public static void main(String[] args) {

        Storage storage = new Storage(FILE_PATH);

        try {
            tasks = storage.load();
        } catch (DukeException e) {
            System.out.println("Failed to load tasks: " + e.getMessage());
        }

        System.out.println(BREAK_LINE + "\nHello! I'm 550W.\nWhat can I do for you?\n" + BREAK_LINE);
        Scanner in = new Scanner(System.in);

        while (true) {
            try {
                line = in.nextLine();
                System.out.println(BREAK_LINE);

                if (line.equals("bye")) {
                    try {
                        storage.save(tasks); // Ensure the latest changes are saved before exiting
                    } catch (DukeException e) {
                        System.out.println("Failed to save tasks: " + e.getMessage());
                    }
                    break;
                }

                if (line.startsWith("delete")) {
                    int taskNumber = Integer.parseInt(line.split(" ")[1]) - 1;
                    Task removedTask = tasks.remove(taskNumber);
                    System.out.println("Noted. I've removed this task:\n  " + removedTask);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.\n" + BREAK_LINE);
                    continue;
                }

                if (line.equals("list")) {

                    System.out.println("Abracadabra! Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i).toString());
                    }
                    System.out.println(BREAK_LINE);

                } else if (line.startsWith("unmark")) {

                    int taskMarkNumber = Integer.parseInt(line.split(" ")[1]) - 1;
                    tasks.get(taskMarkNumber).markAsNotDone();
                    System.out.println("All good! We've hit the rewind button and unmarked this task:");
                    System.out.println("  " + tasks.get(taskMarkNumber) + "\n" + BREAK_LINE);

                } else if (line.startsWith("mark")) {

                    int taskMarkNumber = Integer.parseInt(line.split(" ")[1]) - 1;
                    tasks.get(taskMarkNumber).markAsDone();
                    System.out.println("X marks the spot! I've marked this task as done:");
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

                    System.out.println("Oops! I've no clue what that means. Could you enlighten me, please?");
                    System.out.println(BREAK_LINE);
                }

                try {
                    storage.save(tasks);
                } catch (DukeException e) {
                    System.out.println("Failed to save tasks: " + e.getMessage());
                }

            } catch (Exception error) {
                DukeException.handleException(error, line);
            }
        }

        System.out.println("Bye. Hope to see you again soon!\n" + BREAK_LINE);
    }
}