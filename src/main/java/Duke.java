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
            String[] words = line.split(" ", 2);
            if (line.startsWith(todo)) {
                try {
                    tasks.add(new ToDo(words[1]));
                } catch(IndexOutOfBoundsException e) {
                    System.out.println("clown! add something after todo!");
                }
                continue;
            }

            if (line.startsWith(deadline)) {
                try {
                    tasks.add(new Deadline(words[1]));
                } catch(IndexOutOfBoundsException e) {
                    System.out.println("clown! add something after deadline!");
                }
                continue;
            }

            if (line.startsWith(event)) {
                try {
                    tasks.add(new Event(words[1]));
                } catch(IndexOutOfBoundsException e) {
                    System.out.println("clown! add something after event!");
                }
                continue;
            }
            if (line.startsWith("mark")) {
                try {
                    int listIndex = Integer.parseInt(words[1]);
                    Task markedTask = tasks.get(listIndex - 1).markTask();
                    tasks.set(listIndex - 1, markedTask);
                    printTasks(tasks);
                }
                catch (NumberFormatException e) {
                    System.out.println("You are not marking an index!!!");
                }
                catch(IndexOutOfBoundsException e) {
                    System.out.println(e + " clownnnnn");
                }
                continue;
            }

            if (line.startsWith("unmark")) {
                try {
                    int listIndex = Integer.parseInt(words[1]);
                    Task unmarkedTask = tasks.get(listIndex - 1).unmarkTask();
                    tasks.set(listIndex - 1, unmarkedTask);
                    printTasks(tasks);
                }
                catch (NumberFormatException e) {
                    System.out.println("You are not unmarking an index!!!");
                }
                catch(IndexOutOfBoundsException e) {
                    System.out.println(e + " clownnnnn");
                }
                continue;
            }
            try {
                throw new JxExceptions("gibberish");
            } catch(JxExceptions e) {
                System.out.println(e.getMessage());
            }




        }
        System.out.println("Hope to see you soon");
    }
}
