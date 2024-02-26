package natsu.command;

import static natsu.util.Ui.printList;

/**
 * Represents a command to display all tasks in the task list to the user.
 * When executed, it invokes a utility method to print details of each task
 * currently stored in the task list.
 */
public class ListCommand {

    /**
     * Constructs a {@code ListCommand} instance which, when instantiated,
     * immediately triggers the printing of the entire task list. This provides
     * users with a view of all tasks they have added.
     */
    public ListCommand() {
        printList();
    }
}
