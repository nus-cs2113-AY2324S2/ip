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
        String todo = "todo";
        String deadline = "deadline";
        String event = "event";
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
            if (line.startsWith(todo)) {
                tasks.add(new ToDo(line.substring(todo.length())));
                continue;
            }

            if (line.startsWith(deadline)) {
                tasks.add(new Deadline(line.substring(deadline.length())));
                continue;
            }

            if (line.startsWith(event)) {
                tasks.add(new Event(line.substring(event.length())));
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
        }
        System.out.println("Hope to see you soon");
    }
}
