import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> taskList;
    public TaskList() {
        taskList = new ArrayList<>();
    }
    public static void printTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print((i + 1) + ".");
            tasks.get(i).printTask();
        }
    }

    public static void markAndUnmarkTask(ArrayList<Task> tasks, int taskNumber, String[] arrayOfCommand) {
        if (arrayOfCommand[0].equals("mark")) {
            System.out.println("Nice! I've marked this task as done:");
            tasks.get(taskNumber).markAsDoneOrNotDone(arrayOfCommand);
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
            tasks.get(taskNumber).markAsDoneOrNotDone(arrayOfCommand);
        }
        System.out.print(" ");
        tasks.get(taskNumber).printTask();
    }

    public static void handleMarkAndUnmarkRequest(String userCommand, String[] arrayOfCommand, ArrayList<Task> tasks, int taskCount) {
        arrayOfCommand = userCommand.split(" ", 2);
        try {
            if (arrayOfCommand.length < 2 || arrayOfCommand[1].isEmpty()) {
                throw new StringIndexOutOfBoundsException();
            }
            int taskNumber = Integer.parseInt(arrayOfCommand[1]);
            if (taskNumber > taskCount) {
                throw new NullPointerException();
            }
            markAndUnmarkTask(tasks, taskNumber - 1, arrayOfCommand);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.print(Guide.MARK_AND_UNMARK_REQUEST_FORMAT);
        } catch (NullPointerException e) {
            System.out.print(Guide.OUT_OF_BOUND);
        }
    }

    public static void handleTodoDeadlineAndEvent(String userCommand, String[] arrayOfCommand, ArrayList<Task> tasks) {
        if (userCommand.startsWith("todo")) {
            arrayOfCommand = userCommand.split(" ", 2);
            Todo todo = new Todo(arrayOfCommand[1]);
            tasks.add(todo);
        }

        if (userCommand.startsWith("deadline")) {
            arrayOfCommand = userCommand.split("deadline | /by");
            Deadline deadline = new Deadline(arrayOfCommand[1], arrayOfCommand[2]);
            tasks.add(deadline);
        }

        if (userCommand.startsWith("event")) {
            arrayOfCommand = userCommand.split("event | /from | /to ");
            Event event = new Event(arrayOfCommand[1], arrayOfCommand[2], arrayOfCommand[3]);
            tasks.add(event);
        }

        System.out.println("Got it. I've added this task:");
        tasks.get(tasks.size() - 1).printTask();
    }

    public static void removeTask(ArrayList<Task> tasks, int taskNumber) {
        System.out.println("Sure. I've removed this task: ");
        tasks.get(taskNumber).printTask();
        tasks.remove(taskNumber);
    }
}
