public class TodoCommand {
    public static void execute(String command, TaskList taskList) {
        String[] parts = command.split(" ");
        String toDoParts = command.replaceFirst("\\S+", "");
        Todo newToDo = new Todo(toDoParts.trim());
        taskList.addTask(newToDo);
    }
}
