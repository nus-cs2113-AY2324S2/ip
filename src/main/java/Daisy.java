import java.util.Scanner;

public class Daisy {

    protected static Task[] tasks = new Task[100];
    protected static int task_no = 0;

    public static void main(String[] args) {
        final String INTRO_PROMPT = "Good day! This is Daisy.\nAny task for today?";
        final String EXIT_PROMPT = "Ending prompt received. Remember to keep to the deadlines!";
        final String LINE_BREAK = "____________________________________";

        System.out.println(LINE_BREAK);
        System.out.println(INTRO_PROMPT);
        System.out.println(LINE_BREAK);

        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        while (!command.equals("bye")) {
            System.out.println(LINE_BREAK);
            String[] separate_commands = command.split(" ",2);
            switch (separate_commands[0]) {
                case "list":
                    for (int i = 0; i < task_no; i++) {
                        System.out.println((i + 1) + "." + tasks[i]);
                    }
                    break;
                case "mark":
                    tasks[Integer.parseInt(separate_commands[1])-1].setDone();
                    System.out.println("Congrats on completing the task!");
                    System.out.println(tasks[Integer.parseInt(separate_commands[1])-1]);
                    break;
                case "unmark":
                    tasks[Integer.parseInt(separate_commands[1])-1].setUndone();
                    System.out.println("More time needed for the following task? Sure!");
                    System.out.println(tasks[Integer.parseInt(separate_commands[1])-1]);
                    break;
                case "todo":
                    Todo newTodo = new Todo(separate_commands[1]);
                    addItem(newTodo);
                    break;
                case "deadline":
                    String[] separate_deadlines = separate_commands[1].split(" /by ");
                    Deadline newDeadline = new Deadline(separate_deadlines[0],separate_deadlines[1]);
                    addItem(newDeadline);
                    break;
                case "event":
                    String eventLine = separate_commands[1];
                    int from = eventLine.indexOf(" /from ");
                    int to = eventLine.indexOf(" /to ");
                    Event newEvent = new Event(eventLine.substring(0, from),eventLine.substring(from + " /from ".length(), to), eventLine.substring(to+" /to ".length()));
                    addItem(newEvent);
                    break;
                default:
                    Task newTask = new Task(command);
                    addItem(newTask);
                    break;
            }
            System.out.println(LINE_BREAK);
            command = in.nextLine();
        }
        System.out.println(EXIT_PROMPT);
        System.out.println(LINE_BREAK);
    }

    public static void addItem(Task item) {
        tasks[task_no] = item;
        System.out.println("Task received! The following has been added to your list of todos:\n" + item);
        System.out.println(String.format("Now you have %d tasks in your todo list.",task_no+1));
        task_no++;
    }
}
