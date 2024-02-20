import Event.Event;
import Event.Deadline;
import Event.ToDo;
import Event.Task;
import RuntimeSupport.DukeException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class Duke {
    static final String FILE_PATH = "./duke.txt";
    static String BREAK_LINE = "____________________________________________________________";
    static ArrayList<Task> tasks = new ArrayList<>();
    static String line = "";
    public static void main(String[] args) {

        loadTasks();
        System.out.println(BREAK_LINE + "\nHello! I'm 550W.\nWhat can I do for you?\n" + BREAK_LINE);
        Scanner in = new Scanner(System.in);

        while (true) {
            try {
                line = in.nextLine();
                System.out.println(BREAK_LINE);

                if (line.equals("bye")) {
                    saveTasks(); //Save tasks before exiting.
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

                saveTasks();

            } catch (Exception error) {

                DukeException.handleException(error, line);
            }
        }

        System.out.println("Bye. Hope to see you again soon! \uD83D\uDE0A\n" + BREAK_LINE);
    }

    private static void saveTasks() {
        try {

            FileWriter writer = new FileWriter(FILE_PATH, false);

            for (Task task : tasks) {

                String type = "";
                if (task instanceof ToDo) {
                    type = "T";
                } else if (task instanceof Deadline) {
                    type = "D";
                } else if (task instanceof Event) {
                    type = "E";
                }

                String status = task.isDone ? "1" : "0"; //Marked as 1 if it is done and 0 if it is not done.
                String details = task.description;

                String extra = "";
                if (task instanceof Deadline) {

                    extra = ((Deadline)task).by; //Account for the /by part of the deadline task input.
                } else if (task instanceof Event) {

                    extra = ((Event)task).from + " to " + ((Event)task).to; //Account for the /from and /to part of the event input.
                }

                String lineToSave = type + " | " + status + " | " + details;
                if (!extra.isEmpty()) {
                    lineToSave += " | " + extra; //Generate the lines to be saved in the txt file.
                }

                writer.write(lineToSave + System.lineSeparator());
            }

            writer.close(); //To close up writing operation.
        } catch (Exception error) {

            DukeException.handleException(error, line);
        }
    }

    private static void loadTasks() {

        try {

            File file = new File(FILE_PATH);

            if (!file.exists()) {
                return; // Exit if file doesn't exist.
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(" \\| ");
                Task task = null;

                switch (parts[0]) {

                case "T":
                    task = new ToDo("todo " + parts[2]);
                    break;
                case "D":
                    task = new Deadline("deadline " + parts[2] + " /by " + parts[3]);
                    break;
                case "E":
                    task = new Event("event " + parts[2] + " /from " + parts[3].split(" to ")[0] + " /to " + parts[3].split(" to ")[1]);
                    break;
                }

                if (task != null) {

                    if ("1".equals(parts[1])) {
                        task.markAsDone();
                    }

                    tasks.add(task);
                }
            }

            reader.close();
        } catch (Exception error) {

            DukeException.handleException(error, line);
        }
    }
}
