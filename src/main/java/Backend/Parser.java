package Backend;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;
import java.util.Scanner;

public class Parser {

    public static Command parse(Scanner scanner) {
        // Reading the input
        String input = scanner.nextLine();

        if (input.equals("Bye") || input.equals("bye")) {
            scanner.close();
            Command c = new ByeCommand();
            return c;
        } else if (input.equals("List") || input.equals("list")) {
            Command c = new ListCommand();
            return c;
        } else if (input.startsWith("unmark") || input.startsWith("Unmark")) {
            try {
                StringValidator.validateUnmarkFormat(input);
                int taskNum = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
                Command c = new UnMarkCommand(taskNum);
                return c;
            } catch (InvalidFormatException e) {
                System.out.println(e.getMessage());
            }
        } else if (input.startsWith("mark") || input.startsWith("Mark")) {
            try {
                StringValidator.validateMarkFormat(input);
                int taskNum = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
                Command c = new MarkCommand(taskNum);
                return c;
            } catch (InvalidFormatException e) {
                System.out.println(e.getMessage());
            }
        } else if (input.startsWith("delete") || input.startsWith("Delete")) {
            try {
                StringValidator.validateDeleteFormat(input);
                int taskNum = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
                Command c = new DeleteCommand(taskNum);
                return c;
            } catch (InvalidFormatException e) {
                System.out.println(e.getMessage());
            }
        } else if (input.startsWith("todo") || input.startsWith("Todo")) {
            try {
                StringValidator.validateTodoFormat(input);
                Todo todo = new Todo(Todo.parse(input));
                Command c = new AddCommand(todo);
                return c;
            } catch (InvalidFormatException e) {
                System.out.println(e.getMessage());
            }
        } else if (input.startsWith("deadline") || input.startsWith("Deadline")) {
            try {
                StringValidator.validateDeadlineFormat(input);
                Deadline deadline = new Deadline(Deadline.parseName(input), Deadline.parseDate(input));
                Command c = new AddCommand(deadline);
                return c;
            } catch (InvalidFormatException e) {
                System.out.println(e.getMessage());
            }
        } else if (input.startsWith("event") || input.startsWith("Event")) {
            try {
                StringValidator.validateEventFormat(input);
                Event event = new Event(Event.parseName(input), Event.parseStart(input), Event.parseEnd(input));
                Command c = new AddCommand(event);
                return c;
            } catch (InvalidFormatException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Unknown Command: " + input + "\n");
        }
        return new Command();
    }
}


