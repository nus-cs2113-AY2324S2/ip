import java.util.Scanner;
public class Quill {
    public static void main(String[] args) {
        String horizontalLine = "\n____________________________________________________________\n";
        String name = "Quill";
        String line;
        Task[] tasks = new Task[100];
        Scanner in = new Scanner(System.in);

        System.out.println(horizontalLine + "Hello! I'm " + name + ".\nWhat can i do for you?" + horizontalLine);

        line = in.nextLine();

        while(true) {
            String command;
            int taskNumber =  -1;
            if (line.contains(" ")) {
                int index = line.indexOf(" ");;
                command = line.substring(0, index);
                taskNumber = Integer.parseInt(line.substring(index + 1)) - 1;
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
                    System.out.println(i + 1 + "." + tasks[i].getStatusIcon() + " " + tasks[i].description);
                }
                System.out.println(horizontalLine);
                break;
            case "mark":
                tasks[taskNumber].markAsDone();
                System.out.println(horizontalLine + "Nice! I've marked this task as done:");
                System.out.println(tasks[taskNumber].getStatusIcon() + " " + tasks[taskNumber].description + horizontalLine);
                break;
            case "unmark":
                tasks[taskNumber].markAsNotDone();
                System.out.println(horizontalLine + "OK, I've marked this task as not done yet:");
                System.out.println(tasks[taskNumber].getStatusIcon() + " " + tasks[taskNumber].description + horizontalLine);
                break;
            default:
                System.out.println(horizontalLine + "Added: " + line + horizontalLine);
                tasks[Task.getTotalTasks()] = new Task(line);
                break;
            }
            line = in.nextLine();
        }
    }
}
