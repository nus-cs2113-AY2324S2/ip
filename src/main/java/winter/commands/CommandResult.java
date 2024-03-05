package winter.commands;

import winter.task.Task;

import java.util.ArrayList;

public class CommandResult {
    public final String messageForUser;
    private final ArrayList <Task> taskList;

    public CommandResult(String messageForUser) {
        this.messageForUser = messageForUser;
        taskList = null;
    }

    /*public CommandResult(String messageForUser, ArrayList<Task> taskList) {
        this.messageForUser = messageForUser;
        this.taskList = taskList;
    }*/
}
