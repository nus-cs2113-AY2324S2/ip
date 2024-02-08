import java.util.Scanner;

public class PeeKay {
    static String indent = "     ";
    static String line = "    ____________________________________________________________";
    static Task[] list = new Task[100];
    static int count = 0;

    public static void echo(String input) {
        if (input.equals("bye")) {
            System.out.println(indent + "Bye. Hope to see you again soon!");
        } else {
            System.out.println(indent + "Got it. I've added this task: ");
            System.out.println((indent + "  " + list[count - 1].toString()));
            String task = count == 1 ? "task " : "tasks ";
            System.out.println(indent + "Now you have " + count + " " + task + "in the list.");
        }
    }

    public static void createTask(String input) {
        if (input.contains("todo")) {
            list[count] = new ToDo(input.substring(input.indexOf("todo ") + 5));
            count ++;
            echo(input);
        } else if (input.contains("deadline")) {
            String description = input.replaceFirst("deadline ", "");
            int by = description.indexOf("/");
            String Date = description.substring(by + 4);
            list[count] = new Deadline(description.substring(0, by - 1), Date);
            count++;
            echo(input);
        } else if (input.contains("event")){
            String description = input.replaceFirst("event ", "");
            int from = description.indexOf("/from");
            int by = description.indexOf("/to");
            String startDate = description.substring(from + 6, by - 1);
            String endDate = description.substring(by + 4);
            list[count] = new Event(description.substring(0, from - 1), startDate, endDate);
            count++;
            echo(input);
        }else {
            echo(input);
        }
    }

    public static int getIndexToMark(String input) {
        int idx = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
        return idx - 1;
    }

    public static void chat() {
        String input;
        do {
            input = new Scanner(System.in).nextLine();
            System.out.println(line);
            if (input.equals("list")) {
                System.out.println(indent + "Here are the tasks in your list:");
                for (int x = 0; x < count; x++) {
                    System.out.println(indent + " " + (x + 1) + "." + list[x].toString());
                }
            } else if (input.contains("unmark")) {
                int idx = getIndexToMark(input);
                list[idx].setDone(false);
                System.out.println(indent + "OK, I've getIndexToMed this task as not done yet:");
                System.out.println(indent + list[idx].toString());
            } else if (input.contains("mark")) {
                int idx = getIndexToMark(input);
                list[idx].setDone(true);
                System.out.println(indent + "Nice! I've marked this task as done:");
                System.out.println(indent + list[idx].toString());
            } else {
                createTask(input);
            }
            System.out.println(line);
        } while (!input.equals("bye"));
    }

    public static void main(String[] args) {

        System.out.println(line);
        System.out.println(indent + "Hello! I'm PeeKay");
        System.out.println(indent + "What can I do for you?");
        System.out.println(line);
        chat();
    }
}




