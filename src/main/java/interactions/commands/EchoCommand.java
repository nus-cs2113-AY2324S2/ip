package interactions.commands;

import interactions.Storage;
import tasks.TaskList;

/** Repeats what is said by the user in input prompt line */
public class EchoCommand extends Command {
    public EchoCommand() {

    }
    public void execute(TaskList taskList, Storage storage) {
        System.out.println(taskDescription);
    }

}