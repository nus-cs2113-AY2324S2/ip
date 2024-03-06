import java.util.List;

public class SearchCommand implements Command {
    private final String keyword;

    public SearchCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList) {
        List<Task> foundTasks = taskList.searchTasks(keyword);
        if (foundTasks.isEmpty()) {
            System.out.println("No tasks found for the keyword \"" + keyword + "\".");
        } else {
            System.out.println("Found tasks matching \"" + keyword + "\":");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println((i + 1) + ". " + foundTasks.get(i));
            }
        }
    }
}

