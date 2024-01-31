import java.util.Scanner;
import java.util.ArrayList;

public class Ma {
    private static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object

        ArrayList<Task> list = new ArrayList<>();

        String logo = "🐎  __  __    _    🐎\n"
                + "   |  \\/  |  / \\   \n"
                + "   | |\\/| | / _ \\  \n"
                + "   | |  | |/ ___ \\ \n"
                + "🐎 |_|  |_/_/   \\_\\🐎\n"
                + "What can I help you with？";

        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println("Hello! I'm Ma\nWhat can I do for you?");
        printLine();

        while (true) {
            String userInput = scanner.nextLine();
            printLine();
            if ("bye".equalsIgnoreCase(userInput)) {
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            } else if ("list".equalsIgnoreCase(userInput)) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(" " + (i + 1) + ". " + list.get(i).toString());
                }
                printLine();
            } else if (userInput.startsWith("mark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1; // 获取任务编号
                Task task = list.get(index);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:\n  " + task);
                printLine();
            }else if (userInput.startsWith("unmark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1; // 获取任务编号
                Task task = list.get(index);
                task.markAsUnDone();
                System.out.println("OK, I've marked this task as not done yet:\n  " + task);
                printLine();
            }
            else {
                Task newTask = new Task(userInput);
                list.add(newTask);
                printLine();
                System.out.println("added: " + userInput);
                printLine();
            }
        }
        scanner.close();
    }
}
