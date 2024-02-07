import java.util.Scanner;
import java.util.Arrays;

public class Echo {
    static String break_line = "----------------------------------------";

    public void startEchoing() {
        Task[] list = new Task[1];
        int count = 0;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("What can I do for you?");
            System.out.println("type 'help' for list of instructions.");

            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            switch (parts[0].toLowerCase()) {

            case "list":
                System.out.println(break_line);
                System.out.println("Here are your tasks in your list:");

                for (int i = 0; i < count; i++) {
                    String statusIcon = list[i].getStatusIcon();
                    System.out.println(" " + (i + 1) + ".[" + statusIcon + "]" + list[i]);
                }
                System.out.println(break_line);
                break;

            case "clearlist":
                Arrays.fill(list, null); // Clearing the list
                count = 0; // Resetting the count
                System.out.println(break_line);
                System.out.println("The List has been cleared!");
                System.out.println(break_line);
                break;

            case "bye":
                scanner.close();
                System.out.println("Kkkkkk thanks bye ");
                return;

            case "mark":
                if (parts.length > 1) {
                    int taskNumber = Integer.parseInt(parts[1]) - 1;
                    if (taskNumber >= 0 && taskNumber < count) {
                        list[taskNumber].markAsDone();
                        System.out.println("Yo I have marked task" + (taskNumber + 1) + " as done.");
                        System.out.println(break_line);
                        ;
                    } else {
                        System.out.println("Invalid task number.");
                        System.out.println(break_line);
                    }
                }
                break;

            case "unmark":
                if (parts.length > 1) {
                    int taskNumber = Integer.parseInt(parts[1]) - 1;
                    if (taskNumber >= 0 && taskNumber < count) {
                        list[taskNumber].unmarkAsDone();
                        System.out.println("Yo I have unmarked task" + (taskNumber + 1));
                        System.out.println(break_line);
                    } else {
                        System.out.println("Invalid task number.");
                        System.out.println(break_line);
                    }
                }
                break;

            case "help":
                System.out.println(break_line);
                System.out.println("'bye' to exit ");
                System.out.println("'list' to display the list");
                System.out.println("'clearlist' to clear the list ");
                System.out.println("'mark' plus a number to mark that task as done");
                System.out.println("'unmark' plus a number to unmark that task.");
                System.out.println("'help' for list of instructions.");
                System.out.println(break_line);
                break;


            default:
                if (count == list.length) {
                    list = Arrays.copyOf(list, count * 2);
                }
                list[count] = new Task(input);
                count++;
                System.out.println(break_line);
                System.out.println("Added: " + input);
                System.out.println(break_line);
            }
        }
    }

}