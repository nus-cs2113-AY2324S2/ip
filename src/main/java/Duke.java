import java.util.Scanner;

public class Duke {
    public static Task[] list = new Task[100];
    public static int taskLength = 0;
    public static final String NEW_LINE = "____________________________________________________________\n";

    //Create a new task
    public static void createTask(String task) {
        list[taskLength] = new Deadline(task);
        taskLength++;
        System.out.println(NEW_LINE + "Okay, I've added: " + task + "\n" + NEW_LINE);
    }

    //Handle different types of tasks
    public static void handleTasks(String input) throws EmptyTaskException{
        int index = input.indexOf(" ");
        String taskType = input.substring(0, index);
        String taskContent = input.substring(index);
        String taskFinal;

        switch (taskType) {
        case "deadline":
            try {
                //Empty deadline exception
                String deadlineContent = taskContent.split("/by ")[0];
                if (deadlineContent.trim().isEmpty()) {
                    throw new EmptyTaskException();
                }
                else {
                    String deadlineTiming = taskContent.split("/by ")[1];
                    taskFinal = taskContent.split("/")[0] + " (by:" + deadlineTiming + ")";
                    createTask(taskFinal);
                }
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("Cannot parse deadline start date!\n"
                + "Format: deadline [task] /by [time]");
            }
            break;

        case "event":
            try {
                //empty event exception
                String eventContent = taskContent.split("/from ")[0];
                if (eventContent.trim().isEmpty()) {
                    throw new EmptyTaskException();
                }
                else {
                    String eventTiming = taskContent.split("/from ")[1];
                    String eventFrom = eventTiming.split("/to ")[0];
                    String eventTo = eventTiming.split("/to ")[1];
                    taskFinal = taskContent.split("/")[0] + " (from: " + eventFrom + "to: " + eventTo + ")";
                    createTask(taskFinal);
                }
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("Cannot parse event start/end date!\n"
                +  "Format: event [task] /from [start time] /to [end time]");
            }
            break;

        default:
            if (taskContent.trim().isEmpty()) {
                throw new EmptyTaskException();
            }
            else {
                createTask(taskContent);
            }
        }
    }

    //show current tasks
    public static void showTasks() {
        System.out.println(NEW_LINE);
        System.out.println("Your current tasks");
        for (int i = 0; i < taskLength; i++){
            System.out.print((i + 1) + ". ");
            list[i].showTask();
            //System.out.println(list[i].classSpecifics());
        }
        System.out.print(NEW_LINE);
    }

    public static void mark(int taskNumber) throws DuplicateMarkException {
        if (list[taskNumber - 1].isDone) {
            throw new DuplicateMarkException();
        }
        else {
            list[taskNumber - 1].markAsDone();
            System.out.println("Good job! I've marked task " + taskNumber + " as done");
        }
    }

    public static void unMark(int taskNumber){
        System.out.println("I've marked task " + taskNumber + " as not done");
        list[taskNumber - 1].markNotDone();
    }
    public static void main(String[] args) {
        String chat_name = "Sigma";
        String output = NEW_LINE + " Hello! I'm " + chat_name + "\n"
                + " What can I do for you?\n" + NEW_LINE;
        System.out.println(output);
        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            if (input.equals("bye")) {
                break;
            }
            else if (input.equals("list")) {
                showTasks();
            }
            else if (input.startsWith("mark ")) {
                try {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]);
                        mark(taskNumber);
                }
                catch (IndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
                    System.out.println("That task does not exist!");
                }
                catch (DuplicateMarkException e) {
                    System.out.println("Task is already marked!");
                }
            }

            else if (input.startsWith("unmark ")) {
                try {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]);
                    if (taskNumber > 0 && taskNumber <= taskLength ) {
                        unMark(taskNumber);
                    }
                    else {
                        System.out.println("Out of bounds!");
                    }
                }
                catch (Exception e){
                    System.out.println("Error marking task :/");
                }
            }

            else if (input.startsWith("todo ") || input.startsWith("deadline ") || input.startsWith("event ")) {
                try {
                    handleTasks(input);
                }
                catch (EmptyTaskException e) {
                    System.out.println("Task cannot be empty!");
                }
            }

            else {
                System.out.println(NEW_LINE + "Sorry, I don't recognise that input\n"
                                    + "Hint: Use todo/event/deadline [task] to list tasks\n" + NEW_LINE );
            }
        }
        System.out.println(NEW_LINE + "Bye. Hope to see you again soon!\n" + NEW_LINE);
    }
}

