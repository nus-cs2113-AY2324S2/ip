import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void addTask(String task){
        tasks[taskCount] = new Task(task);
        taskCount++;
    }

    public static void printTasks() {
        int originalCount = taskCount;
        for (Task s: tasks){
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
                while (true) {
                    input = in.nextLine();
                    if (input.equals("exit task mode")) {
                        break;
                    }
                    if (input.equals("list")) {
                        printTasks();
                        continue;
                    }
                    else if (input.startsWith("mark")) {
                        tasks[Integer.parseInt(input.substring(5))].setDone(true);
                        continue;
                    }
                    if (input.startsWith("unmark")) {
                        tasks[Integer.parseInt(input.substring(7))].setDone(false);
                        continue;
                    }
                    addTask(input);
                    System.out.println("added: " + input);
                }
                System.out.println("Exited task mode.");
                input = in.nextLine();
            } else {
                System.out.println(input);
                input = in.nextLine();
            }
        }
        System.out.println("Farewell, and may the fortunes be ever in your favour.");
    }
}


