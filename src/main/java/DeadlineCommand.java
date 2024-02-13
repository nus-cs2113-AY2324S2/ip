public class DeadlineCommand {
    public static void execute(String command, TaskList taskList) {
        String[] deadlineParts = command.replaceFirst("\\S+", "").split("/");
        Deadline newDeadline = new Deadline(deadlineParts[0].trim(), deadlineParts[1]
                .replace("by", "").trim());
        taskList.addTask(newDeadline);
    }
}
