package Commands;


import Exceptions.HikoExceptions;
import Events.Task;
import Events.TaskList;
import Storage.Store;
import HikoUi.Ui;
public class ListCommand extends Command{

    /**
     * Constructs a ListCommand with the specified command word and parameter.
     * The parameter is not used in this command but is included to match the constructor
     * signature of the superclass.
     *
     * @param commandWord The command word associated with this command, typically "list".
     * @param parameter Any parameters that come with the command, not used in ListCommand.
     */
    public ListCommand(String commandWord, String parameter){super(commandWord,parameter);};

    /**
     * Executes the list command. This method retrieves all tasks from the task list
     * and displays them to the user via the UI. The method leverages the Ui class's
     * static method for displaying the list of tasks.
     *
     * @param tasks The TaskList containing the tasks to be displayed.
     * @param ui The Ui instance used for interacting with the user.
     */
    public void execute (TaskList tasks, Ui ui) {
        Ui.showList(tasks);
    }

    /**
     * Indicates whether this command should cause the application to exit.
     * Since ListCommand only displays tasks, it does not terminate the application.
     *
     * @return false always, as ListCommand does not terminate the application.
     */
    public boolean isExit() {
        return false;
    }


}
