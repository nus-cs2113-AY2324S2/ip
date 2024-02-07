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

            case "todo":
                if (parts.length < 2) {
                    System.out.println("Todo description is missing.");
                    break;
                }
                if (count == list.length) {
                    list = Arrays.copyOf(list, count * 2);
                }
                // Joining all parts of the input after the first part (which is "todo")
                String todoDescription = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));
                list[count] = new ToDo(todoDescription);
                count++;
                System.out.println(break_line);
                System.out.println("Got it. I've added this task:");
                System.out.println("  [T][ ] " + todoDescription);
                System.out.println("Now you have " + count + " tasks in the list.");
                System.out.println(break_line);
                break;

            case "deadline":
                if (!input.toLowerCase().startsWith("deadline")) {
                    // Handle this as a generic task or provide a specific message
                    System.out.println("Command not recognized. Please use 'deadline' for deadline tasks.");
                    break;
                }

                String deadlineInput = input.substring("deadline".length()).trim();
                if (!deadlineInput.contains("/by ")) {
                    System.out.println("Deadline time is missing. Please use '/by' to specify the deadline.");
                    break;
                }

                String[] deadlineParts = deadlineInput.split(" /by ", 2);
                if (count == list.length) {
                    list = Arrays.copyOf(list, count * 2);
                }

                list[count] = new Deadline(deadlineParts[0], deadlineParts[1]);
                count++;
                System.out.println(break_line);
                System.out.println("Got it. I've added this task:");
                System.out.println("  [D][ ] " + deadlineParts[0] + " (by: " + deadlineParts[1] + ")");
                System.out.println("Now you have " + count + " tasks in the list.");
                System.out.println(break_line);
                break;

            case "event":
                if (!input.toLowerCase().startsWith("event")) {
                    // Handle this as a generic task or provide a specific message
                    System.out.println("Command not recognized. Please use 'event' for event tasks.");
                    break;
                }

                String eventInput = input.substring("event".length()).trim();
                if (!eventInput.contains("/from ") || !eventInput.contains("/to ")) {
                    System.out.println("Event time details are missing. Please use '/from' and '/to' to specify the event timing.");
                    break;
                }

                String[] eventParts = eventInput.split(" /from ", 2);
                String[] timeParts = eventParts[1].split(" /to ", 2);
                if (count == list.length) {
                    list = Arrays.copyOf(list, count * 2);
                }

                list[count] = new Event(eventParts[0], timeParts[0], timeParts[1]);
                count++;
                System.out.println(break_line);
                System.out.println("Got it. I've added this task:");
                System.out.println("  [E][ ] " + eventParts[0] + " (from: " + timeParts[0] + " to: " + timeParts[1] + ")");
                System.out.println("Now you have " + count + " tasks in the list.");
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
                System.out.println("'todo [description]' to add a new ToDo task");
                System.out.println("'deadline [description] /by [time]' to add a new Deadline");
                System.out.println("'event [description] /from [start time] /to [end time]' to add a new Event");
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