import java.util.regex.Pattern;
import static java.lang.Integer.parseInt;

public class Parser {
    private static final int BY_PADDING = 3;
    private static final int FROM_PADDING = 5;
    private static final int TITLE_PADDING = 6;

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

    public String todoParser(String line) throws Exception {
        String title;
        try {
            title = line.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("What task are you trying to add??? Please enter a task");
        }
        return title;
    }

    public String[] deadlineParser(String line) throws Exception {
        String title;
        String end;
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
            throw new ArrayIndexOutOfBoundsException("Enter a due date with the initializer /by"); // Throws exception if initializer not found
        }
        end = line.substring(byIndex + BY_PADDING).strip();
        if (end.isBlank()) {
            throw new EmptyInputException("Enter a due date for this task"); // Throws exception if due date is blank
        }
        String[] parsed = {title, end};
        return parsed;
    }

    public String[] eventParser(String line) throws Exception {
        String title;
        String start;
        String end;
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
            throw new StringIndexOutOfBoundsException("Enter the duration with the initializer /from <start> /to <end> or don't try at all"); // Throws exception if initializers not found
        }
        start = line.substring(fromIndex + FROM_PADDING, toIndex).strip();
        end = line.substring(line.indexOf("/to") + BY_PADDING).strip();
        if (start.isBlank() || end.isBlank()) {
            throw new EmptyInputException("When is this event happening?"); // Throws exception if durations are blank
        }
        String[] parsed = {title, start,end};
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

    public String findFromTitleParser(String line) throws Exception {
        if (line.contains("/title")) {
            int index = line.indexOf("/title") + TITLE_PADDING;
            String title = line.substring(index).strip();
            if (title.isBlank()) {
                throw new EmptyInputException("What task are you searching for?");
            }
            return title;
        } else {
            throw new InvalidInputException("Enter a valid title with the initializer /title <keyword>");
        }
    }
}
