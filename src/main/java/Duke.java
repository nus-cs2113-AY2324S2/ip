import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static final int TODO_DESCRIPTION_START_INDEX = 5;
    public static final int DEADLINE_DESCRIPTION_START_INDEX = 9;
    public static final int DEADLINE_BY_SPACE_LENGTH = 4;
    public static final int EVENT_DESCRIPTION_START_INDEX = 6;
    public static final int EVENT_FROM_SPACE_LENGTH = 6;
    public static final int EVENT_TO_SPACE_LENGTH = 4;
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
        TaskType taskType;
        String taskTypeCode = null;

        while (!(line.toUpperCase().contains("BYE"))) {
            lineWords = line.split(" ");
            if (line.equalsIgnoreCase("LIST")) {
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < todosCount; i += 1) {
                    isDone = todos[i].getTaskStatus();
                    taskType = todos[i].getTaskType();

                    switch(taskType) {
                    case TODO:
                        taskTypeCode = "T";
                        break;
                    case DEADLINE:
                        taskTypeCode = "D";
                        break;
                    case EVENT:
                        taskTypeCode = "E";
                        break;
                    }

                    System.out.println((i + 1)
                            + "."
                            + "["
                            + taskTypeCode
                            + "]"
                            + "["
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
                if (lineWords[0].equalsIgnoreCase("TODO")) {
                    todos[todosCount] = new Todo(line.substring(TODO_DESCRIPTION_START_INDEX));
                } else if (lineWords[0].equalsIgnoreCase("Deadline")) {
                    int bySlashIndex = line.indexOf("/");
                    String deadlineDescription = line.substring(DEADLINE_DESCRIPTION_START_INDEX, bySlashIndex);
                    String deadlineBy = line.substring(bySlashIndex + DEADLINE_BY_SPACE_LENGTH);
                    todos[todosCount] = new Deadline(deadlineDescription, deadlineBy);
                } else {
                    int fromSlashIndex = line.indexOf("/");
                    int toSlashIndex = line.length() - line.substring(fromSlashIndex + 1).indexOf("/");
                    String eventDescription = line.substring(EVENT_DESCRIPTION_START_INDEX, fromSlashIndex);
                    String eventFrom = line.substring(fromSlashIndex + EVENT_FROM_SPACE_LENGTH, toSlashIndex);
                    String eventTo = line.substring(toSlashIndex + EVENT_TO_SPACE_LENGTH);
                    todos[todosCount] = new Event(eventDescription, eventFrom, eventTo);
                }
                todosCount += 1;
                todos[todosCount - 1].printAdd(todosCount);
            }

            line = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again!");
    }
}
