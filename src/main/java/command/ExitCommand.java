package command;

import task.TaskList;
import tool.ResponseManager;

public class ExitCommand implements Command {
    public void run(TaskList tasks) {
        ResponseManager.sayGoodbye();
    }
    public boolean isExit() {
        return true;
    }
}
