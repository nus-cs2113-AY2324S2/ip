import java.util.ArrayList;
public class FindCommand extends Command {
    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public ArrayList<Task> execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        if (keyword.isEmpty()) {
            throw new DukeException("ADD A KEYWORD!!!!!");
        }
        if (matchingTasks.isEmpty()) {
            ui.printError("Keywords not found: " + keyword);
        } else {
            ui.showLine();
            System.out.println("Here are the matching tasks in your list:");

            int i = 0;
            while (i < matchingTasks.size()) {
                System.out.println((i + 1) + "." + matchingTasks.get(i));
                i++;
            }
            ui.showLine();
        }
        return matchingTasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

