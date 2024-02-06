import java.util.Scanner;

public class Alexis {
    public static void main(String[] args) {
        TaskList tasks = new TaskList();
        Scanner in = new Scanner(System.in);

        Console.printWelcomeMessage();

        while (true) {
            String line = in.nextLine();

            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")) {
                System.out.println(Console.LINEBREAK);
                tasks.printTasks();
                System.out.println(Console.LINEBREAK);
            } else {
                System.out.println(Console.LINEBREAK);
                if (line.startsWith("mark")) {
                    tasks.markTask(line);
                } else if (line.startsWith("unmark")) {
                    tasks.unmarkTask(line);
                } else {
                    int firstSpace = line.indexOf(" ");
                    String firstWord = line.substring(0, firstSpace);
                    String description = line.substring(firstSpace);
                    TaskType taskType = TaskType.getTaskType(firstWord);
                    switch (taskType) {
                    case TODO:
                    default:
                        tasks.addTask(TaskType.TODO, description);
                        break;
                    case DEADLINE:
                        tasks.addTask(TaskType.DEADLINE, description);
                        break;
                    case EVENT:
                        tasks.addTask(TaskType.EVENT, description);
                        break;
                    }
                }
                System.out.println(Console.LINEBREAK);
            }
        }
        Console.printGoodbyeMessage();
    }
}
