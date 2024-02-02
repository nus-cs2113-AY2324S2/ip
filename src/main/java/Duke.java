import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;


public class Duke {

    public static void  printTasks(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.printf("%s. %s%n",(i + 1), tasks.get(i));
        }

    }
    public static void main(String[] args) {
        String chatbot = "Jing Xiang";
        List<Task> tasks = new ArrayList<Task>();
        Scanner input = new Scanner(System.in);
        System.out.println("Hello clown I am " + chatbot);
        while (true) {
            System.out.println("stop clowning and type sth");
            String line = input.nextLine();
            if (line.equals("bye")) {
                break;
            }
            if (line.isEmpty()) {
                continue;
            }
            if (line.equals("list")) {
                printTasks(tasks);
                continue;
            }
            int listIndex = line.charAt(line.length() - 1) - '0';
            if (line.startsWith("mark")) {
                try {
                    Task markedTask = tasks.get(listIndex - 1).markTask();
                    tasks.set(listIndex - 1, markedTask);
                    printTasks(tasks);
                }
                catch(IndexOutOfBoundsException e) {
                    System.out.println(e + " clownnnnn");
                }
                continue;
            }

            if (line.startsWith("unmark")) {
                try {
                    Task unmarkedTask = tasks.get(listIndex - 1).unmarkTask();
                    tasks.set(listIndex - 1, unmarkedTask);
                    printTasks(tasks);
                }
                catch(IndexOutOfBoundsException e) {
                    System.out.println(e + " clownnnnn");
                }
                continue;
            }
            tasks.add(new Task(line));
            System.out.printf("Added: %s%n",line);
        }
        System.out.println("Hope to see you soon");
    }
}
