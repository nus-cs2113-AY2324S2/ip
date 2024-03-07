package interactions.commands;

import interactions.Storage;
import tasks.TaskList;

public class EchoCommand extends Command {
    public EchoCommand() {

    }
    public void execute(TaskList taskList, Storage storage) {
        System.out.println(taskDescription);
    }

}