import java.util.Scanner;
import java.util.ArrayList;

public class Alice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String line = "____________________________________________________________";

        System.out.println(line);
        System.out.println("yoo i'm alice! ur virtual bestie here to keep track of ur vibes");
        System.out.println("what's poppin? u can tell me stuff to remember or type 'bye' to dip");
        System.out.println(line);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println("bye bestie! catch ya later, and remember to stay hydrated <3");
                System.out.println(line);
                break;
            } else if (input.startsWith("list")) {
                System.out.println(line);
                if (tasks.isEmpty()) {
                    System.out.println("this list is emptier than my motivation on a monday morning lol");
                } else {
                    System.out.println("aight here's what's up:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i).toString());
                    }
                }
                System.out.println(line);
            } else if (input.startsWith("todo ")) {
                handleTodo(input, tasks, line);
            } else if (input.startsWith("mark") || input.startsWith("unmark")) {
                handleMarkUnmark(input, tasks, line);
            } else if (input.startsWith("deadline ")) {
                handleDeadline(input, tasks, line);
            } else if (input.startsWith("event ")) {
                handleEvent(input, tasks, line);
            } else {
                System.out.println(line);
                System.out.println("Sorry, I didn't understand that. Please try a valid command.");
                System.out.println(line);
            }
        }
        scanner.close();
    }

    private static void handleTodo(String input, ArrayList<Task> tasks, String line) {
        String description = input.substring(5);
        Todo newTask = new Todo(description);
        tasks.add(newTask);
        System.out.println(line);
        System.out.println("aight i gotchu bestie, added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        System.out.println(line);
    }

    private static void handleMarkUnmark(String input, ArrayList<Task> tasks, String line) {
        int taskIndex = Integer.parseInt(input.replaceAll("\\D", "")) - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task task = tasks.get(taskIndex);
            if (input.startsWith("mark")) {
                task.markAsDone();
                System.out.println(line);
                System.out.println("yas queen, marked it off! we are crushing it frfr:");
            } else {
                task.markAsUndone();
                System.out.println(line);
                System.out.println("aight, took a lil step back, unmarked this one:");
            }
            System.out.println(task);
        } else {
            System.out.println(line);
            System.out.println("Oops! That task doesn't exist. Try a valid task number.");
        }
        System.out.println(line);
    }

    private static void handleDeadline(String input, ArrayList<Task> tasks, String line) {
        String[] parts = input.substring(9).split(" /by ", 2);
        Task newTask = new Deadline(parts[0], parts[1]);
        tasks.add(newTask);
        System.out.println(line);
        System.out.println("ooh we're on a deadline kinda vibe? aight added it in:");
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        System.out.println(line);
    }

    private static void handleEvent(String input, ArrayList<Task> tasks, String line) {
        String[] parts = input.substring(6).split(" /from ", 2);
        String[] timeParts = parts[1].split(" /to ", 2);

        Task newTask = new Event(parts[0], timeParts[0], timeParts[1]);
        tasks.add(newTask);
        System.out.println(line);
        System.out.println("got an event noted down, don't forget:");
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        System.out.println(line);
    }
}
