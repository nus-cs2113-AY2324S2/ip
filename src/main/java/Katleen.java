import java.sql.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Katleen {
    public static final String LINE = "____________________________________________________________";

    public static void main(String[] args) throws IncompleteTaskException, UnrecognizedCommandException {
        System.out.println(LINE);
        System.out.println("Hello! I'm Katleen.");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        ArrayList<Task> tasks = new ArrayList<Task>();

        Scanner in = new Scanner(System.in);
        String text = "";
        while (!text.equals("bye")) {
            text = in.nextLine();
            System.out.println(LINE);
            parseString(text, tasks);
            System.out.println(LINE);
        }
    }

    private static void parseString(String input, ArrayList<Task> tasks)
            throws IncompleteTaskException, UnrecognizedCommandException {
        String[] splitInput = input.split(" ");
        String cmdWord = splitInput[0];
        int taskCount = Task.getTaskCount();
        try {
            switch (cmdWord) {
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.print((i+1) + ". ");
                    tasks.get(i).printTask();
                }
                break;
            case "bye":
                System.out.println(LINE);
                System.out.println("Bye, have a nice day!");
                System.out.println(LINE);
                break;
            case "mark":
            case "unmark":
                String index = splitInput[1];
                int i = Integer.parseInt(index) - 1;
                if (cmdWord.equals("mark")) {
                    tasks.get(i).setDone(true);
                    break;
                }
                tasks.get(i).setDone(false);
                break;
            case "todo":
            case "deadline":
            case "event":
                Task task = getTask(input, cmdWord, splitInput);
                if (task == null) {
                    throw new UnrecognizedCommandException();
                }
                tasks.set(taskCount, task);
                tasks.get(taskCount).printTask();
                break;
            default:
                throw new UnrecognizedCommandException();
            }
        } catch (IncompleteTaskException e) {
            System.out.println("Excuse me, you're missing the deadline or duration.");
        } catch (UnrecognizedCommandException e) {
            System.out.println("Invalid command, please try again");
        }
    }

    private static Task getTask(String input, String cmdWord, String[] splitInput) throws IncompleteTaskException {
        Task task = null;
        if (cmdWord.equals("todo")) {
            task = new ToDo(input.replace("todo ", "").trim());
        }
        if (splitInput.length < 3) {
            throw new IncompleteTaskException();
        }
        if (cmdWord.equals("deadline")) {
            int due = input.indexOf("/by");
            String by = input.substring(due + 3).trim();
            String deadline = input.substring(8, due).trim();
            task = new Deadline(deadline, by);
        }
        if (cmdWord.equals("event")) {
            int splitFrom = input.indexOf("/from");
            int splitTo = input.indexOf("/to");
            String from = input.substring(splitFrom + 5, splitTo).trim();
            String to = input.substring(splitTo + 3).trim();
            String event = input.substring(5, splitFrom).trim();
            task = new Event(event, from, to);
        }
        return task;
    }

}
