package helperFunctions;

import tasks.Task;

import java.util.ArrayList;

public class Parser {
    public static boolean processUserInput(ArrayList<Task> tasks, String line, String FILE_PATH, boolean isReadMode) throws InvalidParamsException {
        String[] req = line.split(" ");

        if (req[0].equalsIgnoreCase("BYE")) {
            Ui.sayBye();
            return false; // EXITS loop
        }
        if (req[0].equalsIgnoreCase("LIST")) {
            System.out.print(TaskList.displayList(tasks));
        } else if (req[0].toUpperCase().contains("MARK")) { // both unmark & mark contains 'mark'
            boolean isMark = !line.toUpperCase().contains("UNMARK");
            TaskList.markOperation(tasks, req, isMark, FILE_PATH, isReadMode);
        } else if (req[0].equalsIgnoreCase("DELETE")) {
            TaskList.deleteOperation(tasks, req, FILE_PATH);
        } else if (req[0].equalsIgnoreCase("TODO")) {
            TaskList.addTodoTask(req, line, tasks, FILE_PATH, isReadMode); // change tasks to ArrayList<TAsk>, f void now
        } else if (req[0].equalsIgnoreCase("DEADLINE")) {
            TaskList.addDeadlineTask(req, line, tasks, FILE_PATH, isReadMode);
        } else if (req[0].equalsIgnoreCase("EVENT")) {
            TaskList.addEventTask(req, line, tasks, FILE_PATH, isReadMode);
        } else {
            throw new InvalidParamsException("No such command." + System.lineSeparator() + Ui.printCommandsList());
        }
        return true;
    }
}
