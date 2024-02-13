public class TodoCommand {
    public static void execute(String command, TaskList taskList) throws GeneException {
        String[] parts = command.split(" ");
        if (parts.length <= 1) {
            throw new GeneException("Description cannot be empty for todo task.");
        }
        String toDoParts = command.replaceFirst("\\S+", "");
        Todo newToDo = new Todo(toDoParts.trim());
        taskList.addTask(newToDo);
    }
}
