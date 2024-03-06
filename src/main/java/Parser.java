public class Parser {

    public static String getCommand(String line) { //get the command at the start of input
        return line.split(" ")[0];
    }

    public static boolean stringIsEmpty(String line) {
        return line.trim().isEmpty();
    }

    public static boolean isInvalidCommand(String description) { //check for invalid commands to throw exception in main
        return !description.contains("list") && !description.contains("event") && !description.contains("deadline")
                && !description.contains("todo") && !description.contains("mark")
                && !description.contains("delete") && !description.contains("find");
    }

    public static int getTaskIndex(String description) throws DukeException {
        String indexToMark = description.substring(description.lastIndexOf(" ") + 1);
        try {
            Integer.parseInt(indexToMark);
        }
        catch (NumberFormatException n) {
            throw new DukeException("Hey, the description is empty!");
        }
        int taskIndex = Integer.parseInt(indexToMark) - 1;
        if(taskIndex < 0) {
            throw new DukeException("Hey, the index is out of bounds!");
        }
        return taskIndex;
    }

    public static boolean isTask(String description) {
        return description.startsWith("todo") || description.startsWith("event") || description.startsWith("deadline");
    }

    public static Task getTodo(String line) throws MissingParameterException {
        String todo = line.replace("todo", "");
        String todoName = todo.trim();
        if(todoName.isEmpty()) {
            throw new MissingParameterException("todo");
        }
        return new Todo(todoName);
    }

    public static Task getEvent(String line) throws MissingParameterException {
        String event = line.replace("event", "");
        String[] segments = event.split("/from");
        if(segments.length < 2) {
            throw new MissingParameterException("event");
        }
        String eventName = segments[0].trim();
        if(eventName.isEmpty()) {
            throw new MissingParameterException("event");
        }
        segments = segments[1].split("/to");
        if(segments.length < 2) {
            throw new MissingParameterException("event");
        }
        String from = segments[0].trim();
        String to = segments[1].trim();
        if(from.isEmpty() || to.isEmpty()) {
            throw new MissingParameterException("event");
        }
        return new Event(eventName, from, to);
    }

    public static Task getDeadline(String line) throws MissingParameterException {
        String deadline = line.replace("deadline", "");
        String[] segments = deadline.split("/by");
        if(segments.length < 2) {
            throw new MissingParameterException("deadline");
        }
        String deadlineName = segments[0].trim();
        String by = segments[1].trim();
        if(deadlineName.isEmpty() || by.isEmpty()) {
            throw new MissingParameterException("deadline");
        }
        return new Deadline(deadlineName, by);
    }

    public static String getStringToFind(String line) {
        return line.split(" ")[1];
    }
}
