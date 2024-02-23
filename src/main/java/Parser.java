import Quokka.exceptions.QuokkaException;
import Quokka.tasks.Deadline;
import Quokka.tasks.Event;
import Quokka.tasks.Todo;

public class Parser {
    public static Todo parseTodoTask(String userInput) {
        try {
            String description = userInput.substring("todo".length()).trim();
            if (description.isEmpty()) {
                throw new QuokkaException("Please provide a description for the todo task.");
            }
            return new Todo(description, false);
        } catch (QuokkaException e) {
            System.out.println("    Error: " + e.getMessage());
            return null;
        }
    }

    public static Deadline parseDeadlineTask(String userInput) {
        try {
            String[] parts = userInput.split("/by", 2);
            if (parts.length == 2) {

                String description = parts[0].substring("deadline".length()).trim();
                String by = parts[1].trim();
                if (description.isEmpty() || by.isEmpty()) {
                    throw new QuokkaException("Please provide both description and deadline for the task.");
                }

                return new Deadline(description, by, false);
            } else {
                throw new QuokkaException("Invalid deadline format. Please use: deadline [description] /by [date/time]");
            }
        } catch (QuokkaException e) {
            System.out.println("     Error: " + e.getMessage());
            return null;
        }
    }

    public static Event parseEventTask(String userInput) {
        try {
            String[] parts = userInput.split("/from", 2);
            if (parts.length == 2) {
                String description = parts[0].substring("event".length()).trim();
                String[] dateTimes = parts[1].split("/to", 2);
                if (dateTimes.length == 2) {
                    String from = dateTimes[0].trim();
                    String to = dateTimes[1].trim();
                    if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        throw new QuokkaException("Please provide description, start time, and end time for the event task.");
                    }
                    return new Event(description, from, to, false);
                } else {
                    throw new QuokkaException("Invalid event format. Please use: event [description] /from [start] /to [end]");
                }
            } else {
                throw new QuokkaException("Invalid event format. Please use: event [description] /from [start] /to [end]");
            }
        } catch (QuokkaException e) {
            System.out.println("    Error: " + e.getMessage());
            return null;
        }
    }
}
