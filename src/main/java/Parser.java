public class Parser {

    public int parseTaskNumber(String input) throws AliceException {
        try {
            return Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new AliceException("Please enter a valid task number.");
        }
    }

    public String parseDescription(String input) throws AliceException {
        String description = input.substring(input.indexOf(" ") + 1);
        if (description.isBlank()) {
            throw new AliceException("Description cannot be empty.");
        }
        return description;
    }

    public static class DeadlineData {
        String description;
        String by;
    }

    public DeadlineData parseDeadline(String input) throws AliceException {
        String[] parts = input.split(" /by ");
        if (parts.length < 2 || parts[1].isBlank()) {
            throw new AliceException("Invalid deadline format. Please include '/by'.");
        }
        DeadlineData deadlineData = new DeadlineData();
        deadlineData.description = parts[0].substring(parts[0].indexOf(" ") + 1);
        deadlineData.by = parts[1];
        return deadlineData;
    }

    public static class EventData {
        String description;
        String start;
        String end;

        EventData(String description, String start, String end) {
            this.description = description;
            this.start = start;
            this.end = end;
        }
    }
    public EventData parseEvent(String input) throws AliceException {
        if (!input.contains("/to") || !input.contains("/from")) {
            throw new AliceException("ayo event commands must include '/from' and '/to' so that i'll know when your event starts and ends yea?");
        }

        String[] parts = input.split(" /from ", 2);
        if (!parts[1].contains(" /to ")) {
            throw new AliceException("ayo the event time must be specified with '/from' followed by '/to' yea?");
        }

        String[] timeParts = parts[1].split(" /to ", 2);
        if (timeParts.length < 2) {
            throw new AliceException("ayo do specify both the start and end times for your event yea?");
        }

        String description = parts[0].substring(parts[0].indexOf(" ") + 1);
        String start = timeParts[0];
        String end = timeParts[1];

        return new EventData(description, start, end);
    }

    public static Task parseTask(String line) throws AliceException {
        String[] parts = line.split(" \\| ");
        boolean isDone = parts[1].equals("1");
        Task task;

        switch (parts[0]) {
            case "T":
                task = new Todo(parts[2]);
                break;
            case "D":
                task = new Deadline(parts[2], parts[3]);
                break;
            case "E":
                task = new Event(parts[2], parts[3], parts[4]);
                break;
            default:
                throw new AliceException("Unknown task type in the save file");
        }
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}
