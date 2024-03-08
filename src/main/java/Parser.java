import java.util.ArrayList;

/**
 * The {@code Parser} class handles the parsing of input lines and creating corresponding to-do items.
 */
public class Parser {

    public static final int BEGIN_INDEX = 7;

    /**
     * Processes a saved line from the file and creates a corresponding to-do item.
     * deals with 3 types and store them in todolist
     * first the function decide the type ,the it extracts description , start time and end time according to the
     * format
     *
     * @param line     The saved line to be processed
     * @param todolist The list of to-do items to which the created item will be added
     */
    public static void ProcessSavedLine(String line, ArrayList todolist) {
        String Done = "|" + "([x ])" + "|";

        Boolean isDone;
        if (line.contains("| |")) {
            isDone = false;
        } else if (line.contains("|x|")) {
            isDone = true;
        } else {
            System.out.println("Unexpected format");
            return;
        }
        String description;
        String starttime;
        String endtime;

        if (line.contains("|T|")) {
            String parts = line.substring(BEGIN_INDEX);
            description = parts;
            Todo task = new Todo(isDone, description.trim());
            todolist.add(task);
        } else if (line.contains("|D|")) {
            String parts = line.substring(BEGIN_INDEX);
            String[] contents = parts.split("by:", 2);
            description = contents[0].replace("(", "");
            endtime = contents[1].replace(")", "");
            Deadline task = new Deadline(isDone, description.trim(), endtime.trim());
            todolist.add(task);
        } else if (line.contains("|E|")) {
            String parts = line.substring(BEGIN_INDEX);
            String[] contents = parts.split("from:", 2);
            description = contents[0].replace("(", "");
            String[] time = contents[1].split("by:", 2);
            starttime = time[0].replace("(", "").replace(")", "");
            endtime = time[1].replace("(", "").replace(")", "");
            Event task = new Event(isDone, description.trim(), endtime.trim(), starttime.trim());
            todolist.add(task);
        } else {
            System.out.println("Unexpected format, skipping");
        }
    }

    /**
     * Adds a to-do item to the list with the given description.
     *
     * @param description The description of the to-do item to be added
     * @return A message indicating that the to-do item has been added
     */
    static String AddTodo(String description) {
        Todo todo = new Todo(false, description);
        ListManager.todolist.add(todo);
        return "Todo added\n";
    }

    /**
     * Adds a deadline item to the list with the given description and end time.
     *
     * @param description The description of the deadline item to be added
     * @return A message indicating that the deadline item has been added
     * @throws InvalidTimeForm If the time format is invalid
     */
    static String AddDeadline(String description) throws InvalidTimeForm {
        String[] part = (description.trim()).split("/by");

        if (part.length > 2) {
            throw new InvalidTimeForm(); // two or more "/by"
        }

        Deadline deadline = new Deadline(false, part[0].trim(), part[1].trim());
        ListManager.todolist.add(deadline);
        return "Deadline added\n";
    }

    /**
     * Adds an event item to the list with the given description, start time, and end time.
     *
     * @param description The description of the event item to be added
     * @return A message indicating that the event item has been added
     * @throws InvalidTimeForm If the time format is invalid
     */
    static String AddEvent(String description) throws InvalidTimeForm {

        String[] part = (description.trim()).split("/from");
        if (part.length > 2) {
            throw new InvalidTimeForm();
        }
        String EventDescription = part[0].trim();
        String[] content = (part[1].trim()).split("/to");

        if (content.length > 2) {
            throw new InvalidTimeForm();
        }
        String end = content[1].trim();
        String start = content[0].trim();

        Event event = new Event(false, EventDescription, end, start);
        ListManager.todolist.add(event);
        return "Event added\n";
    }
}
