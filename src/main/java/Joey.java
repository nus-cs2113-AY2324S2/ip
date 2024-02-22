import java.util.Scanner;


public class Joey {
    private static final String DASHED_LINE = "____________________________________________________________";
    public static void main(String[] args) throws JoeyException {
        System.out.println("Hello! I'm Joey");
        System.out.println("What can I do for you?");
        System.out.println(DASHED_LINE);

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            String userCommand = scanner.nextLine();
            System.out.println(DASHED_LINE);

            if (userCommand.equalsIgnoreCase("bye")) {
                System.out.println(" bye bye, take care:)!");
                System.out.println(DASHED_LINE);
                break;
            } else if (userCommand.equalsIgnoreCase("list")) {
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    Task task = tasks[i];
                    System.out.println(" " + (i + 1) + "." + task.getType() + "[" + (task.isDone() ? "X" : " ") + "] " + task.getDescription());
                }
                System.out.println(DASHED_LINE);
            } else if (userCommand.startsWith("todo")) {
                try {
                    String description = userCommand.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new JoeyException("Hey girlie, the description of the todo can't be empty. Follow this format instead: todo buy eggs");
                    }
                    tasks[taskCount] = new Todo(description);
                    taskCount++;
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks[taskCount - 1]);
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                    System.out.println(DASHED_LINE);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                    System.out.println(DASHED_LINE);
                } catch (JoeyException e) {
                    System.out.println("OOPS!!! " + e.getMessage());
                    System.out.println(DASHED_LINE );
                }
            } else if (userCommand.startsWith("deadline")) {
                try {
                    String[] parts = userCommand.substring(9).split("/by");
                    if (parts.length != 2) {
                        throw new JoeyException("Hey girlie, please provide both description and deadline for the deadline task. Follow this format instead: deadline return book /by Sunday");
                    }

                    String description = parts[0].trim();
                    String by = parts[1].trim();

                    if (description.isEmpty() || by.isEmpty()) {
                        throw new JoeyException("Hey girlie, the description and deadline for the deadline task can't be empty. Follow this format instead: deadline return book /by Sunday");
                    }

                    tasks[taskCount] = new Deadline(description, by);
                    taskCount++;
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks[taskCount - 1]);
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                    System.out.println(DASHED_LINE);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! Hey girlie, please provide both description and deadline for the deadline task. Follow this format instead: deadline return book /by Sunday");
                    System.out.println(DASHED_LINE);
                } catch (JoeyException e) {
                    System.out.println("OOPS!!! " + e.getMessage());
                    System.out.println(DASHED_LINE);
                }
            } else if (userCommand.startsWith("event")) {
                try {
                    String[] parts = userCommand.split("/from");
                    if (parts.length != 2) {
                        throw new JoeyException("Hey girlie, please provide both description, start time, and end time for the event task. Follow this format instead: event project meeting /from Mon 2pm /to 4pm");
                    }

                    String description = parts[0].substring(6).trim();
                    String[] timingParts = parts[1].split("/to");
                    if (timingParts.length != 2) {
                        throw new JoeyException("Hey girlie, please provide both description, start time, and end time for the event task. Follow this format instead: event project meeting /from Mon 2pm /to 4pm");
                    }

                    String from = timingParts[0].trim();
                    String to = timingParts[1].trim();

                    if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        throw new JoeyException("Hey girlie, please provide both description, start time, and end time for the event task. Follow this format instead: event project meeting /from Mon 2pm /to 4pm");
                    }

                    tasks[taskCount] = new Event(description, from, to);
                    taskCount++;
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks[taskCount - 1]);
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                    System.out.println(DASHED_LINE);
                } catch (JoeyException e) {
                    System.out.println("OOPS!!! " + e.getMessage());
                    System.out.println(DASHED_LINE);
                }
            } else if (userCommand.startsWith("mark")) {
                int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    Task task = tasks[taskIndex];
                    task.markDone();
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   [" + (task.isDone() ? "X" : " ") + "] " + task.getDescription());
                    System.out.println(DASHED_LINE);
                } else {
                    System.out.println(" Task not found. Please enter a valid task number OR enter 'list' to view your current list:)");
                    System.out.println(DASHED_LINE);
                }
            } else if (userCommand.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    Task task = tasks[taskIndex];
                    task.markNotDone();
                    System.out.println(" okay, I have marked this task as not done yet:");
                    System.out.println("   [" + (task.isDone() ? "X" : " ") + "] " + task.getDescription());
                    System.out.println(DASHED_LINE);
                } else {
                    System.out.println(" Task not found. Please enter a valid task number OR enter 'list' to view your current list:)");
                    System.out.println(DASHED_LINE);
                }
            } else {
                tasks[taskCount] = new Task(userCommand);
                taskCount++;
                System.out.println(" added: " + userCommand);
                System.out.println(DASHED_LINE);
            }
        }
        scanner.close();
    }
}