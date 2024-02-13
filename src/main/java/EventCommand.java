public class EventCommand {
    public static void execute(String command, TaskList taskList) {
        String[] eventParts = command.replaceFirst("\\S+", "").split("/");
        Event newEvent = new Event(eventParts[0].trim(), eventParts[1].replace("from", "").trim()
                , eventParts[2].replace("to", "").trim());
        taskList.addTask(newEvent);
    }
}
