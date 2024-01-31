import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;


public class Duke {

    public static void  printTasks(List<StringBuilder> tasks) {
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.printf("%s. %s%n",(i + 1),
                    tasks.get(i).toString());
        }

    }
    public static void main(String[] args) {
        String chatbot = "Jing Xiang";
        List<StringBuilder> tasks = new ArrayList<StringBuilder>();
        Scanner input = new Scanner(System.in);
        System.out.println("Hello clown I am " + chatbot);
        String line = input.nextLine();
        while (!line.equals("bye")) {
            System.out.println("stop clowning");
            if (line.isEmpty()) {
                line =  input.nextLine();
                continue;
            }
            if (line.equals("list")) {
                printTasks(tasks);
                line =  input.nextLine();
                continue;
            }
            if (line.startsWith("mark")) {
                int index = line.charAt(line.length() - 1) - '0';
                if (index > tasks.size()) {
                    System.out.println("index out of bounds you clown");
                }
                else {
                    tasks.get(index - 1).setCharAt(1,'X');
                    printTasks(tasks);
                }
                line =  input.nextLine();
                continue;
            }

            if (line.startsWith("unmark")) {
                int index = line.charAt(line.length() - 1) - '0';
                if (index > tasks.size()) {
                    System.out.println("index out of bounds you clown");
                }
                else {
                    tasks.get(index - 1).setCharAt(1,' ');
                    printTasks(tasks);
                }
                line =  input.nextLine();
                continue;
            }
            tasks.add(new StringBuilder("[ ] " + line));
            System.out.printf("Added: %s%n",line);
            line =  input.nextLine();
        }
        System.out.println("Hope to see you soon");
    }
}
