package gab.command;

import gab.task.TaskList;

/**
 * Abstract class for other commands
 */

public abstract class Command {
    /**
     * Abstract method to be modified by each specific command classes
     *
     * @param taskList arraylist of task
     */

     public abstract void execute (TaskList taskList);
}
