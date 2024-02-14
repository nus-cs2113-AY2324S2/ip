package exceptions;

import utils.constants;

public class TaskIndexOutOfBoundsException extends Exception {
    public TaskIndexOutOfBoundsException(int taskCount) {
        System.out.println("Task index out of bound, the total number of task(s) you have now is: " + taskCount);
        System.out.println(constants.BREAKLINE);
    }
}