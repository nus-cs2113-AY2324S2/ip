public class EventCommand {
    public static void execute(String command, TaskList taskList) throws GeneException {
        String[] parts = command.replaceFirst("\\S+", "").split("/");
        if (parts.length < 3) {
            throw new GeneException("Invalid event format." + System.lineSeparator() + "Use Format: event (Task Description) /from (Date) /to (Date)");
        }
        Event newEvent = new Event(parts[0].trim(), parts[1].replace("from", "").trim()
                , parts[2].replace("to", "").trim());
        taskList.addTask(newEvent);
    }
}
