import java.util.Scanner;
public class Duke {
    private static String[] tasks = new String[100];
    private static int taskCount = 0;

    public static void addTask(String task){
        tasks[taskCount] = task;
        taskCount++;
    }

    public static void printTasks() {
        int originalCount = taskCount;
        for (String s: tasks){
            if (taskCount > 0) {
                System.out.println(s);
                taskCount--;
            } else {
                break;
            }
        }
        taskCount = originalCount;
    }

    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);

        System.out.println("Good evening. I'm Nocturne.");
        System.out.println("What ails you on this fine day?");
        input = in.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("enter task mode")) {
                System.out.println("Entered task mode.");
                while (!input.equals("exit task mode")) {
                    input = in.nextLine();
                    if (input.equals("list")) {
                        printTasks();
                        continue;
                    }
                    addTask(input);
                    System.out.println("added: " + input);
                }
                System.out.println("Exited task mode.");
            } else {
                System.out.println(input);
                input = in.nextLine();
            }
        }
        System.out.println("Farewell, and may the fortunes be ever in your favour.");
    }
}
