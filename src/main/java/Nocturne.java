import java.util.Scanner;

public class Nocturne {
    public static void main(String[] args) throws NocturneException {
        Task[] tasks = new Task[100];
        int taskCount = 0;

        String input;
        Scanner in = new Scanner(System.in);
        System.out.println("Good evening. I'm Nocturne.");
        System.out.println("What ails you on this fine day?");

        input = in.nextLine();
        while (!input.equals("bye")) {
            String[] commandCheck = input.split(" ");
            switch (commandCheck[0]) {
                case "list":
                    if (taskCount == 0) {
                        throw new NocturneException("An empty list begets an empty mind.");
                    }
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + "." + tasks[i]);
                    }
                    break;

                case "mark": {
                    if (commandCheck.length < 2 | commandCheck.length > taskCount + 1) {
                        throw new NocturneException("Your list is either empty or your brain is.");
                    }
                    int listIndex = Integer.parseInt(commandCheck[1]);
                    System.out.println("Congratulations. I have marked this task as finished:");
                    tasks[listIndex - 1].isDone = true;
                    System.out.println("  " + tasks[listIndex - 1]);
                    break;
                }
                case "unmark": {
                    if (commandCheck.length < 2 | commandCheck.length > taskCount + 1) {
                        throw new NocturneException("Your list is either empty or your brain is.");
                    }
                    int listIndex = Integer.parseInt(commandCheck[1]);
                    System.out.println("Do not neglect your duties. I have marked this task as unfinished:");
                    tasks[listIndex - 1].isDone = false;
                    System.out.println("  " + tasks[listIndex - 1]);
                    break;
                }
                case "deadline":
                    String[] deadlineSeparated = input.split("/");
                    if (deadlineSeparated.length != 3) {
                        throw new NocturneException("Take your / back, and only put 2!");
                    }
                    Deadline trueDeadline;
                    String deadlineName = deadlineSeparated[0].substring(9);
                    String by = deadlineSeparated[1].substring(3);
                    trueDeadline = new Deadline(deadlineName, by);
                    tasks[taskCount] = trueDeadline;
                    taskCount++;
                    System.out.println("A deadline I see. I have added it:");
                    System.out.println("  " + trueDeadline);
                    break;

                case "todo":
                    String todoName = input.substring(5);
                    Todo trueTodo = new Todo(todoName);
                    tasks[taskCount] = trueTodo;
                    taskCount++;
                    System.out.println("A Todo task I see. I have added it:");
                    System.out.println("  " + trueTodo);
                    break;


                case "event":
                    String[] eventSeparated = input.split("/");
                    if (eventSeparated.length != 3) {
                        throw new NocturneException("Take your / back, and only put 2!");
                    }
                    Event trueEvent;
                    String eventName = eventSeparated[0].substring(6);
                    String from = eventSeparated[1].trim();
                    from = from.substring(5);
                    String to = eventSeparated[2].substring(3);
                    trueEvent = new Event(eventName, from, to);
                    tasks[taskCount] = trueEvent;
                    taskCount++;
                    System.out.println("An event I see. I have added it:");
                    System.out.println("  " + trueEvent);
                    break;

                default:
                    throw new NocturneException("Your command is invalid. Try again.");
            }
            input = in.nextLine();
        }
        System.out.println("Farewell, and may the fortunes be ever in your favour.");
    }
}
