package exceptions;

import utils.constants;

// Custom exception for handling invalid task input
public class TaskIndexOutOfBoundsException extends Exception {
    public TaskIndexOutOfBoundsException(int taskCount) {
        super("The task index is out of bounds!!!\n You have " + taskCount + " task(s) in your list.");
    }
}