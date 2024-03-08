package interactions.commands;

import interactions.Storage;
import interactions.Ui;
import tasks.TaskList;

/** Repeats what is said by the user in input prompt line */
public class EchoCommand extends Command {
    public EchoCommand() {

    }
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println(taskDescription);
    }

}