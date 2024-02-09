import java.util.Scanner;
public class Quill {
    public static final String horizontalLine = "\n____________________________________________________________\n";
    public static void printAddTask(Task tasks) {
        System.out.println(horizontalLine + "Got it. I've added this task:");
        System.out.println(tasks.toString());
        System.out.println("Now you have " + Task.getTotalTasks() + " tasks in the list." + horizontalLine);
    }

    public static void performMarkOrUnmark(String line, Task[] tasks, boolean isDone) {
        try {
            int taskNumber = Integer.parseInt(line) - 1;
            if (isDone) {
                tasks[taskNumber].markAsDone();
                System.out.println(horizontalLine + "Nice! I've marked this task as done:");
            } else {
                tasks[taskNumber].markAsNotDone();
                System.out.println(horizontalLine + "OK, I've marked this task as not done yet:");
            }
            System.out.println(tasks[taskNumber].toString() + horizontalLine);
        } catch (NullPointerException e) {
            System.out.println("Hey, wake up! That task? Non-existent. Try something real.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("1 to 100 only. Seriously, no zeros, no hundreds. Got it?");
        } catch (NumberFormatException e) {
            System.out.println("Listen up! Numbers only, got it? Don't bother with anything else");
        }
    }

    public static void main(String[] args) throws QuillException{
        String name = "Quill";
        int MAX_TASKS = 100;

        String line;
        Task[] tasks = new Task[MAX_TASKS];
        Scanner in = new Scanner(System.in);

        System.out.println(horizontalLine + "Hello! I'm " + name + ".");
        System.out.println("What can i do for you?" + horizontalLine);

        line = in.nextLine();


        while(true) {
            String command;
            int taskNumber = Task.getTotalTasks();
            int index;
            if (line.contains(" ")) {
                index = line.indexOf(" ");
                command = line.substring(0, index);
                line = line.substring(index + 1);
            } else {
                command = line;
            }
            switch(command) {
            case "bye":
                System.out.println(horizontalLine + "Bye! Hope to see you again soon!" + horizontalLine);
                return;
            case "list":
                System.out.println(horizontalLine + "Here are the tasks in your list:\n");
                for (int i = 0; i < Task.getTotalTasks(); i++) {
                    System.out.println(i + 1 + "." + tasks[i].toString());
                }
                System.out.println(horizontalLine);
                break;
            case "mark":
                performMarkOrUnmark(line, tasks, true);
                break;
            case "unmark":
                performMarkOrUnmark(line, tasks, false);
                break;
            case "todo":
                tasks[taskNumber] = new Todo(line);
                printAddTask(tasks[taskNumber]);
                break;
            case "deadline":
                tasks[taskNumber] = new Deadline(line);
                printAddTask(tasks[taskNumber]);
                break;
            case "event":
                tasks[taskNumber] = new Event(line);
                printAddTask(tasks[taskNumber]);
                break;
            default:
                break;
            }
            line = in.nextLine();
        }
    }
}
