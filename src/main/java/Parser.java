public class Parser {
    public static String parseCommand(String input) throws DukeException {
        input = input.trim();
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        return command;
    }

    public static String[] parseDetails(String input) throws DukeException {
        input = input.trim();
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            throw new DukeException("Missing task description!");
        }
        return parts;
    }

    public static String[] parseDeadlineDetails(String input) throws DukeException {
        String[] parts = input.split("/by", 2);
        if (parts.length < 2) {
            throw new DukeException("Invalid command format. Usage: deadline <description> /by <date>");
        }
        String[] details = new String[2];
        details[0] = parts[0].substring(9).trim();
        details[1] = parts[1].trim();
        return details;
    }

    public static String[] parseEventDetails(String input) throws DukeException {
        String[] parts = input.split("/from", 2);
        if (parts.length < 2) {
            throw new DukeException("Invalid command format. Usage: event <description> /from <start> /to <end>");
        }
        String[] eventParts = parts[1].split("/to", 2);
        if (eventParts.length < 2) {
            throw new DukeException("Invalid command format. Usage: event <description> /from <start> /to <end>");
        }
        String[] details = new String[3];
        details[0] = parts[0].substring(5).trim();
        details[1] = eventParts[0].trim();
        details[2] = eventParts[1].trim();
        return details;
    }
}
