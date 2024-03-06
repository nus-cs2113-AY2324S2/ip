package command;

import task.TaskList;
import ui.Message;

public class FindCommand implements Command {
    private String keyword;

    public FindCommand(String[] keywords) {
        this.keyword = keywords[0];
    }

    @Override
    public boolean execute(TaskList tasks) {
        String tasksFound = tasks.findTasks(keyword);
        if (tasksFound.isEmpty()) {
            System.out.println(Message.FIND_FAIL_MESSAGE);
        } else {
            System.out.print(Message.FIND_MESSAGE + tasksFound);
        }
        return false;
    }
    
}
