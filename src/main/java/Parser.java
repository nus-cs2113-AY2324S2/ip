
import jason.errorhandling.JasonException;
public class Parser {


    public static void executeCommand(String input, TaskList taskList) {
        String[] parts = input.split(" ", 2);

        try {
            if (parts[0].equalsIgnoreCase("mark")) {
                taskList.markTask(parts);
            } else if (parts[0].equalsIgnoreCase("unmark")) {
                taskList.unmarkTask(parts);
            } else if (parts[0].equalsIgnoreCase("list")) {
                Ui.showList(taskList.getTasks());
            } else if (parts[0].equalsIgnoreCase("delete")) {
                taskList.deleteTask(parts);
            } else {
                // For "todo", "deadline", "event" commands
                taskList.addTasks(input);
            }


            Storage.save(taskList.getTasks());
        } catch (JasonException e) {
            Ui.showError(e.getMessage());
        }
        Ui.showLine();
    }
}
