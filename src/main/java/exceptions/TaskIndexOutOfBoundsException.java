package exceptions;

import utils.constants;

public class TaskIndexOutOfBoundsException extends Exception {
    public TaskIndexOutOfBoundsException(int taskCount) {
        super("The task index is out of bounds!!!\n You have " + taskCount + " task(s) in your list.");
    }
}