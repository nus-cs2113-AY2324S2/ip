import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int taskCount = 0;

        String input;
        Scanner in = new Scanner(System.in);
        System.out.println("Good evening. I'm Nocturne.");
        System.out.println("What ails you on this fine day?");

        input = in.nextLine();

        while (!input.equals("bye")) {
            String[] commandCheck = input.split(" ");
            if (input.equals("list")) {
                for (int i = 0; i < taskCount; i++){
                    System.out.println((i + 1) + "." + tasks[i]);
                }
            }
            else if(commandCheck[0].equals("mark")) {
                int listIndex = Integer.parseInt(commandCheck[1]);
                System.out.println("Congratulations. I have marked this task as finished:");
                tasks[listIndex - 1].isDone = true;
                System.out.println("  " + tasks[listIndex - 1]);
            }

            else if(commandCheck[0].equals("unmark")) {
                int listIndex = Integer.parseInt(commandCheck[1]);
                System.out.println("Do not neglect your duties. I have marked this task as unfinished:");
                tasks[listIndex - 1].isDone = false;
                System.out.println("  " + tasks[listIndex - 1]);
            }

            else if(commandCheck[0].equals("deadline")) {
                String[] deadlineSeparated = input.split("/");
                Deadline trueDeadline;
                String deadlineName = deadlineSeparated[0].substring(9);
                String by = deadlineSeparated[1].substring(3);
                trueDeadline = new Deadline(deadlineName, by);
                tasks[taskCount] = trueDeadline;
                taskCount++;
                System.out.println("A deadline I see. I have added it:");
                System.out.println("  " + trueDeadline);
            }

            else if(commandCheck[0].equals("todo")) {
                String todoName = input.substring(5);
                Todo trueTodo = new Todo(todoName);
                tasks[taskCount] = trueTodo;
                taskCount++;
                System.out.println("A Todo task I see. I have added it:");
                System.out.println("  " + trueTodo);
            }

            else if(commandCheck[0].equals("event")) {
                String[] eventSeparated = input.split("/");
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
            }
            input = in.nextLine();
        }
        System.out.println("Farewell, and may the fortunes be ever in your favour.");
    }
}