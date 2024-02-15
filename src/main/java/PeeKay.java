import java.util.Scanner;

public class PeeKay {

    static String line = "\t____________________________________________________________";
    static Task[] tasks = new Task[100];
    static int count = 0;

    public static void echo(String input) {
        System.out.println("\t Got it. I've added this task: ");
        System.out.println(("\t   " + tasks[count - 1].toString()));
        String task = (count == 1) ? "task " : "tasks ";
        System.out.println("\t Now you have " + count + " " + task + "in the tasks.");
    }

    public static void sayBye() {
        System.out.println("\t Bye. Hope to see you again soon!");
    }

    public static void createTodo(String input){
        tasks[count] = new ToDo(input.substring(input.indexOf("todo ") + 5));
        count++;
        echo(input);
    }

    public static void createDeadline(String input){
        String description = input.replaceFirst("deadline ", "");
        int by = description.indexOf("/");
        String Date = description.substring(by + 4);
        tasks[count] = new Deadline(description.substring(0, by - 1), Date);
        count++;
        echo(input);
    }

    public static void createEvent(String input){
        String description = input.replaceFirst("event ", "");
        int from = description.indexOf("/from");
        int by = description.indexOf("/to");
        String startDate = description.substring(from + 6, by - 1);
        String endDate = description.substring(by + 4);
        tasks[count] = new Event(description.substring(0, from - 1), startDate, endDate);
        count++;
        echo(input);
    }

    public static void handleInput(String input) {
        if (input.contains("todo")) {
            createTodo(input);
        } else if (input.contains("deadline")) {
            createDeadline(input);
        } else if (input.contains("event")) {
            createEvent(input);
        } else {
            sayBye();
        }
    }

    public static int getIndexToMark(String input) {
        int idx = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
        return idx - 1;
    }

    public static void listItems() {
        System.out.println("\t Here are the tasks in your list:");
        for (int x = 0; x < count; x++) {
            System.out.println("\t  " + (x + 1) + "." + tasks[x].toString());
        }
    }

    public static void unmarkItem(String input) {
        int idx = getIndexToMark(input);
        tasks[idx].setDone(false);
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.println("\t " + tasks[idx].toString());
    }

    public static void markItem(String input) {
        int idx = getIndexToMark(input);
        tasks[idx].setDone(true);
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t " + tasks[idx].toString());
    }

    public static void chat() {
        String input;
        do {
            input = new Scanner(System.in).nextLine();
            System.out.println(line);
            if (input.equals("list")) {
                listItems();
            } else if (input.contains("unmark")) {
                unmarkItem(input);
            } else if (input.contains("mark")) {
                markItem(input);
            } else {
                handleInput(input);
            }
            System.out.println(line);
        } while (!input.equals("bye"));
    }

    public static void main(String[] args) {

        System.out.println(line);
        System.out.println("\t Hello! I'm PeeKay");
        System.out.println("\t What can I do for you?");
        System.out.println(line);
        chat();
    }
}




