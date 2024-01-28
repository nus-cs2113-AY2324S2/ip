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
            switch(line) {
            case "bye":
                System.out.println(horizontalLine + "Bye! Hope to see you again soon!" + horizontalLine);
                return;
            case "list":
                System.out.println(horizontalLine + "Here are the tasks in your list:\n");
                for (int i = 0; i < Task.getTotalTasks(); i++) {
                    System.out.println(i + 1 + ". " + tasks[i].description);
                }
                System.out.println(horizontalLine);
                break;
            case "mark":
                break;
            case "unmark":
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
