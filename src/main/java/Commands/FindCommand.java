package Commands;

import Exceptions.HikoExceptions;
import Events.Task;
import Events.TaskList;
import Storage.Store;
import HikoUi.Ui;

import java.util.ArrayList;

public class FindCommand extends Command{


    /**
     * Constructs a FindCommand with the specified command word and search parameter.
     *
     * @param commandWord The command word associated with this command, typically "find".
     * @param parameter The search keyword used to find matching tasks in the task list.
     */
    public FindCommand(String commandWord, String parameter){super(commandWord,parameter);};



    /**
     * Executes the find command. It searches the task list for tasks whose descriptions
     * contain the search keyword specified in the parameter. Matching tasks are then
     * displayed to the user via the UI.
     *
     * @param tasks The TaskList containing the tasks to search through.
     * @param ui The Ui instance used for interacting with the user.
     */
    public void execute(TaskList tasks, Ui ui) {
        ArrayList<Task> matchingTask;
        try {
            matchingTask = iterateTask(tasks);
            Ui.showFindList(matchingTask);
        } catch (NullPointerException e) {
            System.out.println("No such Task found !");
        }
    }



    /**
     * Searches through the provided task list and returns a list of tasks whose descriptions
     * contain the search keyword specified in the parameter.
     *
     * @param tasks The TaskList to search through.
     * @return An ArrayList containing the matching tasks.
     */

    public ArrayList<Task> iterateTask(TaskList tasks) {
        if (parameter.isEmpty()) {
            parameter = null;
        }
        ArrayList<Task> matchingTask = new ArrayList<>();
        for (int i = 0; i < TaskList.getTotalTasks(); i++) {
            if (tasks.getTask(i).getDescription().contains(parameter)) {
                matchingTask.add(tasks.getTask(i));
            }
        }
        return matchingTask;
    }


    /**
     * Indicates whether this command should cause the application to exit.
     *
     * @return false always, as FindCommand does not terminate the application.
     */
    public boolean isExit() {
        return false;
    }


}
