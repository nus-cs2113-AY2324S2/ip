package Command;
import Templates.TaskList;

public abstract class BaseCommand {
    String taskString = null;
    public boolean isExit;

    public BaseCommand(Boolean isExit, String taskString){
        this.isExit = isExit;
        this.taskString = taskString;
    }
    public abstract String execute(TaskList taskList) throws Exception;

    public boolean isExit() {
        return this.isExit;
    }
}
