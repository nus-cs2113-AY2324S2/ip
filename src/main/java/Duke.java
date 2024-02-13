import java.util.Scanner;

public class Duke {
    public static Task[] list = new Task[100];
    public static int taskLength = 0;

    //add a new task
    public static void handleTasks(String input) {
        int index = input.indexOf(" ");
        String taskType = input.substring(0, index);
        String taskContent = input.substring(index);
        String taskFinal = taskContent;

        switch (taskType) {
        case "deadline":
            try {
                String deadlineContent = taskContent.split("/by ")[1];
                taskFinal = taskContent.split("/")[0] + " (by:" + deadlineContent + ")";
            }
            catch (Exception e) {
                System.out.println("Cannot parse deadline start date! Requires [/by]");
            }
            list[taskLength] = new Deadline(taskFinal);
            break;
        case "event":
            try {
                String eventTiming = taskContent.split("/from ")[1];
                String eventFrom = eventTiming.split("/to ")[0];
                String eventTo = eventTiming.split("/to ")[1];
                taskFinal = taskContent.split("/")[0] + " (from: " + eventFrom + "to: " + eventTo + ")";
            }
            catch (Exception e) {
                System.out.println("Cannot parse event start/end date! Requires [/from /to]");
            }
            list[taskLength] = new Event(taskFinal
            );
            break;
        default:
            System.out.println(taskType);
            list[taskLength] = new Todo(taskContent);
        }
        taskLength++;
        System.out.println("____________________________________________________________\n"
                + "Okay, I've added: " + taskFinal + "\n"
                + "____________________________________________________________");
    }

    //show current tasks
    public static void showTasks() {
        System.out.println("____________________________________________________________");
        System.out.println("Your current tasks");
        for (int i = 0; i < taskLength; i++){
            System.out.print((i + 1) + ". ");
            list[i].showTask();
            //System.out.println(list[i].classSpecifics());
        }
        System.out.println("____________________________________________________________");
    }

    public static void mark(int taskNumber){
        System.out.println("Good job! I've marked task " + taskNumber + " as done");
        list[taskNumber - 1].markAsDone();
    }

    public static void unMark(int taskNumber){
        System.out.println("I've marked task " + taskNumber + " as not done");
        list[taskNumber - 1].markNotDone();
    }
    public static void main(String[] args) {
        String chat_name = "Sigma";
        String output = "____________________________________________________________\n"
                + " Hello! I'm " + chat_name + "\n"
                + " What can I do for you?\n"
                +"____________________________________________________________\n";
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
                    if (taskNumber > 0 && taskNumber <= taskLength ) {
                        mark(taskNumber);
                    }
                    else {
                        System.out.println("Out of bounds!");
                    }
                }
                catch (Exception e){
                    System.out.println("Error marking task :/");
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
                handleTasks(input);
            }

            else {
                System.out.println("Sorry, I don't recognise that input");
            }
        }
        System.out.println("____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                +"____________________________________________________________\n");
    }
}

