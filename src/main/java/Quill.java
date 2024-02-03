import java.util.Scanner;
public class Quill {
    public static void main(String[] args) {
        String horizontalLine = "\n____________________________________________________________\n";
        String name = "Quill";
        String line;
        Task[] tasks = new Task[100];
        Scanner in = new Scanner(System.in);

        System.out.println(horizontalLine + "Hello! I'm " + name + ".");
        System.out.println("What can i do for you?" + horizontalLine);

        line = in.nextLine();

        while(true) {
            String command;
            int taskNumber =  -1;
            int index = -1;
            if (line.contains(" ")) {
                index = line.indexOf(" ");;
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
                taskNumber = Integer.parseInt(line) - 1;
                tasks[taskNumber].markAsDone();
                System.out.println(horizontalLine + "Nice! I've marked this task as done:");
                System.out.println(tasks[taskNumber].toString() + horizontalLine);
                break;
            case "unmark":
                taskNumber = Integer.parseInt(line) - 1;
                tasks[taskNumber].markAsNotDone();
                System.out.println(horizontalLine + "OK, I've marked this task as not done yet:");
                System.out.println(tasks[taskNumber].toString() + horizontalLine);
                break;
            case "todo":
                taskNumber = Task.getTotalTasks();
                tasks[taskNumber] = new Todo(line);
                System.out.println(horizontalLine + "Got it. I've added this task:");
                System.out.println(tasks[taskNumber].toString());
                System.out.println("Now you have " + Task.getTotalTasks() + " tasks in the list." + horizontalLine);
                break;
            case "deadline":
                taskNumber = Task.getTotalTasks();
                tasks[taskNumber] = new Deadline(line);
                System.out.println(horizontalLine + "Got it. I've added this task:");
                System.out.println(tasks[taskNumber].toString());
                System.out.println("Now you have " + Task.getTotalTasks() + " tasks in the list." + horizontalLine);
                break;
            case "event":
                taskNumber = Task.getTotalTasks();
                tasks[taskNumber] = new Event(line);
                System.out.println(horizontalLine + "Got it. I've added this task:");
                System.out.println(tasks[taskNumber].toString());
                System.out.println("Now you have " + Task.getTotalTasks() + " tasks in the list." + horizontalLine);
                break;
            default:
                break;
            }
            line = in.nextLine();
        }
    }
}
