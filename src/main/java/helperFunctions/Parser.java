package helperFunctions;

public class Parser {
    public static boolean processUserInput(TaskList tasks, String line, String FILE_PATH, boolean isReadMode) throws InvalidParamsException {
        String[] req = line.split(" ");

        if (req[0].equalsIgnoreCase("BYE")) {
            Ui.sayBye();
            return false; // EXITS loop
        }
        if (req[0].equalsIgnoreCase("LIST")) {
            System.out.print(tasks.displayList());
        } else if (req[0].toUpperCase().contains("MARK")) { // both unmark & mark contains 'mark'
            boolean isMark = !line.toUpperCase().contains("UNMARK");
            tasks.markOperation(req, isMark, FILE_PATH, isReadMode);
        } else if (req[0].equalsIgnoreCase("DELETE")) {
            tasks.deleteOperation(req, FILE_PATH);
        } else if (req[0].equalsIgnoreCase("TODO")) {
            tasks.addTodoTask(req, line, FILE_PATH, isReadMode); // change tasks to ArrayList<TAsk>, f void now
        } else if (req[0].equalsIgnoreCase("DEADLINE")) {
            tasks.addDeadlineTask(req, line, FILE_PATH, isReadMode);
        } else if (req[0].equalsIgnoreCase("EVENT")) {
            tasks.addEventTask(req, line, FILE_PATH, isReadMode);
        } else {
            throw new InvalidParamsException("No such command." + System.lineSeparator() + Ui.printCommandsList());
        }
        return true;
    }
}
