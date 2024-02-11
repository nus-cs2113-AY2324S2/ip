import java.util.Scanner;

public class Duke {
    public static final int MAX_TASK = 100;

    public static final int TODO_DESCRIPTION_START_INDEX = 5;
    public static final int DEADLINE_DESCRIPTION_START_INDEX = 9;
    public static final int DEADLINE_BY_SPACE_LENGTH = 4;
    public static final int EVENT_DESCRIPTION_START_INDEX = 6;
    public static final int EVENT_FROM_SPACE_LENGTH = 6;
    public static final int EVENT_TO_SPACE_LENGTH = 4;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        greetings();

        Task[] todos = new Task[MAX_TASK];
        int todosCount = 0;

        String line;
        line = in.nextLine();
        String[] lineWords;
        String command;

        while (!(line.toUpperCase().contains("BYE"))) {
            lineWords = line.split(" ");
            command = lineWords[0];
            if (line.equalsIgnoreCase("LIST")) {
                processList(todosCount, todos);
            } else if (command.equalsIgnoreCase("MARK")) {
                processMark(todos, lineWords);
            } else if (command.equalsIgnoreCase("UNMARK")) {
                processUnmark(todos, lineWords);
            } else {
                processAddTask(command, todos, todosCount, line);
                todosCount += 1;
                todos[todosCount - 1].printAdd(todosCount);
            }

            line = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again!");
    }

    private static void processUnmark(Task[] todos, String[] lineWords) {
        todos[Integer.parseInt(lineWords[1]) - 1].unmarkAsDone();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println("  "
                + "[ ] "
                + todos[Integer.parseInt(lineWords[1]) - 1].getTaskDescription());
    }

    private static void processMark(Task[] todos, String[] lineWords) {
        todos[Integer.parseInt(lineWords[1]) - 1].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + "[X] "
                + todos[Integer.parseInt(lineWords[1]) - 1].getTaskDescription());
    }

    private static void processList(int todosCount, Task[] todos) {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < todosCount; i += 1) {
            todos[i].printTask(i);
        }
    }

    private static void processAddTask(String command, Task[] todos, int todosCount, String line) {
        if (command.equalsIgnoreCase("TODO")) {
            createNewTodo(todos, todosCount, line);
        } else if (command.equalsIgnoreCase("Deadline")) {
            createNewDeadline(todos, todosCount, line);
        } else {
            createNewEvent(todos, todosCount, line);
        }
    }

    private static void createNewTodo(Task[] todos, int todosCount, String line) {
        todos[todosCount] = new Todo(line.substring(TODO_DESCRIPTION_START_INDEX));
    }

    private static void createNewDeadline(Task[] todos, int todosCount, String line) {
        int bySlashIndex = line.indexOf("/");
        String deadlineDescription = line.substring(DEADLINE_DESCRIPTION_START_INDEX, bySlashIndex);
        String deadlineBy = line.substring(bySlashIndex + DEADLINE_BY_SPACE_LENGTH);
        todos[todosCount] = new Deadline(deadlineDescription, deadlineBy);
    }

    private static void createNewEvent(Task[] todos, int todosCount, String line) {
        int fromSlashIndex = line.indexOf("/");
        String substringBeforeFrom = line.substring(0, fromSlashIndex + 1);

        int toSlashIndex = substringBeforeFrom.length()
                + line.substring(fromSlashIndex + 1).indexOf("/");
        String eventDescription = line.substring(EVENT_DESCRIPTION_START_INDEX, fromSlashIndex);
        String eventFrom = line.substring(fromSlashIndex + EVENT_FROM_SPACE_LENGTH, toSlashIndex);
        String eventTo = line.substring(toSlashIndex + EVENT_TO_SPACE_LENGTH);
        todos[todosCount] = new Event(eventDescription, eventFrom, eventTo);
    }

    private static void greetings() {
        System.out.println("Hello! I'm Gary");
        System.out.println("                    0   0   ______");
        System.out.println("                    | v | /oooooooo\\");
        System.out.println("                    |   |/OOOOOOOOOO\\");
        System.out.println("                    ===================");
        System.out.println("|   |   |   |   |   |   |   |   |   |   |   |");
        System.out.println("What can I do for you?");
    }
}
