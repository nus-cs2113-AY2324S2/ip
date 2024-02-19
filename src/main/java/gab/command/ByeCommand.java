package gab.command;

import gab.Gab;
import gab.task.TaskList;

public class ByeCommand implements Command {

    @Override
    public void execute (String input, TaskList taskList) {
        Gab.isExit = true;
        System.out.println("All the best!");
    }
}
