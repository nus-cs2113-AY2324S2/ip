package chatman.commands;

import chatman.ChatMan;
import chatman.tasks.Task;

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
     * @param userCommand Receives and stores user-entered command (from CommandParser object) to use in perform()
     * method where implemented.
     * */
    public TaskCommand(String userCommand) {
        super(userCommand);
    }

    /**
     * Prints String representation of task object just added to task arraylist; inherited by TaskCommand subclasses
     * and called from their respective perform() methods during execution.
     * */
    public void replyAddedTask() {
        Task addedTask = ChatMan.storedTasks.get(ChatMan.storedTasks.size() - 1);
        int size = ChatMan.storedTasks.size();
        System.out.printf("Got it. I've added this task:%n%s%nNow you have %d tasks in the list.%n",
                addedTask.toString(), size);
    }

}
