package chatman.commands;

import chatman.tasks.Task;
import chatman.utility.Tasklist;

/**
 * Implements TaskCommand abstract class as template for all subclasses implementing task-related command functionality.
 *
 * @author LWachtel1
 * */
public abstract class TaskCommand extends Command {

    /**
     * Constructor for abstract TaskCommand class that it inherited from abstract Command superclass; invoked by its
     * concrete subclasses using super() method call.
     *
     * @param userCommand Receives and stores user-entered command (from Parser object) to use in perform()
     * method where implemented.
     * */
    public TaskCommand(String userCommand) {
        super(userCommand);
    }

    /**
     * Prints String representation of task object just added to task arraylist; inherited by TaskCommand subclasses
     * and called from their respective perform() methods during execution.
     * */
    public static void replyAddedTask() {
        Task addedTask = Tasklist.getTask(Tasklist.getSize() - 1);
        int size = Tasklist.getSize();
        System.out.printf("Got it. I've added this task:%n%s%nNow you have %d tasks in the list.%n",
                addedTask.toString(), size);
    }

}
