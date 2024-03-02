import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import static java.lang.Integer.parseInt;

public class Parser {

    public String checkKey(String line) throws Exception {
        // Checks the keywords and runs the corresponding responses
        String[] phrases = line.split(" ", 2);
        return phrases[0].toLowerCase();
    }

    public int parseIndexToMark(String line) throws Exception {
        int index;
        try {
            index = parseInt(line.split(" ")[1]) - 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Which task are u referring to");
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Enter a valid task number to mark");
        }
        return index;
    }

    public LocalDateTime dateAndTimeParser(String line) {
        LocalDateTime date;
        try {
            date = LocalDateTime.parse(line);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Enter a date in the format <YYYY-MM-DD>T<HH-MM>", e.getParsedString(), e.getErrorIndex());
        }
        return date;
    }

    public String todoParser(String line) throws Exception {
        String title;
        try {
            title = line.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("What task are you trying to add??? Please enter a task");
        }
        return title;
    }

    public ArrayList<Object> deadlineParser(String line) throws Exception {
        String title;
        LocalDateTime end;
        try {
            title = line.split("/")[0].split(" ", 2)[1].strip();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("What task are you trying to add??? Please enter a task");
        }
        if (title.isBlank()) {
            throw new EmptyInputException("Why is the task description blank!"); // Throws exception if task is blank
        }
        int byIndex = line.indexOf("/by");
        if (byIndex == -1) {
            throw new ArrayIndexOutOfBoundsException("Enter a due date with the initializer /by <YYYY-MM-DD>T<HH-MM>"); // Throws exception if initializer not found
        }
        end = dateAndTimeParser(line.substring(byIndex + 3).strip());
//        if (end.isBlank()) {
//            throw new EmptyInputException("Enter a due date for this task"); // Throws exception if due date is blank
//        }
        ArrayList<Object> parsed = new ArrayList<>();
        parsed.add(title);
        parsed.add(end);
        return parsed;
    }

    public ArrayList<Object> eventParser(String line) throws Exception {
        String title;
        LocalDateTime start;
        LocalDateTime end;
        try {
            title = line.split("/")[0].split(" ", 2)[1].strip();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("What task are you trying to add??? Please enter a task");
        }
        int fromIndex = line.indexOf("/from");
        int toIndex = line.indexOf("/to");
        if (title.isBlank()) {
            throw new EmptyInputException("Why is the task description blank!"); // Throws exception if task is blank
        }
        if (fromIndex == -1 || toIndex == -1) {
            throw new StringIndexOutOfBoundsException("Enter the duration with the initializer /from <YYYY-MM-DD>T<HH-MM> /to <YYYY-MM-DD>T<HH-MM> or don't try at all"); // Throws exception if initializers not found
        }
        start = dateAndTimeParser(line.substring(fromIndex + 5, toIndex).strip());
        end = dateAndTimeParser(line.substring(line.indexOf("/to") + 3).strip());
//        if (start.isBlank() || end.isBlank()) {
//            throw new EmptyInputException("When is this event happening?"); // Throws exception if durations are blank
//        }
        ArrayList<Object> parsed = new ArrayList<>();
        parsed.add(title);
        parsed.add(start);
        parsed.add(end);
        return parsed;
    }

    public int deleteParser(String line) throws Exception {
        Pattern pattern = Pattern.compile("^[0-9]+$");
        String taskNumber;
        int taskIndex;
        try {
            taskNumber = line.split(" ", 2)[1];
            if (!pattern.matcher(taskNumber).matches()) {
                throw new InvalidInputException("That's not  number, enter a proper task number");
            }
            taskIndex = Integer.parseInt(taskNumber);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Which task you trying to delete?");
        }
        return taskIndex;
    }
}
