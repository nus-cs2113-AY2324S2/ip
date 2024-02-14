import java.util.Scanner;
public class Bobby {
    public static String obtainTodoDescription(String input) throws BobbyException {
        if (input.length() < 5 || input.substring(5).trim().isEmpty()) {
            throw new BobbyException();
        }
        return input.substring(5);
    }

    public static void main(String[] args) {
        int counter = 0;
        boolean isExit = false;
        Task[] tasks = new Task[100];
        Scanner in = new Scanner(System.in);
        System.out.println("Hello I'm Bobby\n" + "What can I do for you?");

        while (!isExit) {
            String input = in.nextLine();
            String command;
            String description;
            String by;
            String from;
            if (input.indexOf(' ') > 0) {
                command = input.substring(0, input.indexOf(' '));
            } else {
                command = input;
            }
            int entry;
            switch (command) {
            case "bye":
                System.out.println("See you again soon!");
                isExit = true;
                break;
            case "mark":
                entry = Integer.parseInt(input.substring(5));
                if (entry > 0 && entry <= counter) {
                    tasks[entry - 1].setDone(true);
                    System.out.println("Marked as done");
                    System.out.println(entry + "." + tasks[entry - 1]);
                }
                break;
            case "unmark":
                entry = Integer.parseInt(input.substring(7));
                if (entry > 0 && entry <= counter) {
                    tasks[entry - 1].setDone(false);
                    System.out.println("Unmarked");
                    System.out.println(entry + "." + tasks[entry - 1]);
                }
                break;
            case "list":
                for (int i = 0; i < counter; i += 1) {
                    System.out.println((i + 1) + "." + tasks[i]);
                }
                break;
            case "todo":
                try {
                    description = obtainTodoDescription(input);
                } catch (BobbyException e) {
                    System.out.println("Please enter a valid task.");
                    break;
                }
                tasks[counter] = new Todo(description);
                System.out.println("Okay, added:\n" + tasks[counter]);
                counter += 1;
                System.out.println("Now you have " + counter + " task(s) in the list.");
                break;
            case "deadline":
                if (!input.contains("/by")) {
                    System.out.println("Please enter a valid deadline.");
                    break;
                }
                try {
                    description = input.substring(9, input.indexOf("/by") - 1);
                    by = input.substring(input.indexOf("/by") + 4);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Please enter a valid deadline task in this format:");
                    System.out.println("deadline Return Book /by Sunday");
                    break;
                }
                tasks[counter] = new Deadline(description, by);
                System.out.println("Okay, added:\n" + tasks[counter]);
                counter += 1;
                System.out.println("Now you have " + counter + " task(s) in the list.");
                break;
            case "event":
                if (!input.contains("/to") || !input.contains("/from")) {
                    System.out.println("Please enter a valid start and end date.");
                    break;
                }
                try {
                    description = input.substring(6, input.indexOf("/from") - 1);
                    by = input.substring(input.indexOf("/to") + 4);
                    from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Please enter a valid event in this format:");
                    System.out.println("event Project Meeting /from Mon 2pm /to 4pm");
                    break;
                }
                tasks[counter] = new Event(description, by, from);
                System.out.println("Okay, added:\n" + tasks[counter]);
                counter += 1;
                System.out.println("Now you have " + counter + " task(s) in the list.");
                break;
            default:
                System.out.println("Sorry, I didn't quite understand that.\nPlease enter a valid command.");
                break;
            }
        }
    }
}