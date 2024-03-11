package mona.manager;

import mona.output.ConsolePrint;
import mona.task.Deadline;
import mona.task.Event;
import mona.task.Task;
import mona.task.Todo;
import mona.util.Constants;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Manages the list of tasks. Contains methods to update the values in the list, based on the
 * user commands.
 */
public class TaskList {

    public static int noOfTasks;
    protected ArrayList<Task> tasks;

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        noOfTasks = tasks.size();
    }

    /**
     * Executes the given command to update the task list accordingly.
     *
     * @param commandTypeAndParams A String array containing the command type and its parameters.
     */
    public void executeCommand(String[] commandTypeAndParams) {
        String commandType = commandTypeAndParams[Constants.INDEX_COMMAND_TYPE];

        switch (commandType) {
        case ("mark"):
            int markIndex = Integer.parseInt(commandTypeAndParams[Constants.INDEX_DESCRIPTION]) - 1;
            tasks.get(markIndex).markAsDone();
            ConsolePrint.printMarkStatement(tasks.get(markIndex));
            break;
        case ("unmark"):
            int unmarkIndex = Integer.parseInt(commandTypeAndParams[Constants.INDEX_DESCRIPTION]) - 1;
            tasks.get(unmarkIndex).markAsNotDone();
            ConsolePrint.printUnmarkStatement(tasks.get(unmarkIndex));
            break;
        case ("list"):
            ConsolePrint.printList(tasks);
            break;
        case ("todo"):
            Task newTodo = new Todo(commandTypeAndParams[Constants.INDEX_DESCRIPTION]);
            tasks.add(newTodo);
            ConsolePrint.printAddTaskStatement(newTodo, noOfTasks + 1);
            noOfTasks += 1;
            break;
        case ("deadline"):
            Task newDeadline = new Deadline(commandTypeAndParams[Constants.INDEX_DESCRIPTION],
                    commandTypeAndParams[Constants.INDEX_DEADLINE]);
            tasks.add(newDeadline);
            ConsolePrint.printAddTaskStatement(newDeadline, noOfTasks + 1);
            noOfTasks += 1;
            break;
        case ("event"):
            Task newEvent = new Event(commandTypeAndParams[Constants.INDEX_DESCRIPTION],
                    commandTypeAndParams[Constants.INDEX_FROM_DATE],
                    commandTypeAndParams[Constants.INDEX_TO_DATE]);
            tasks.add(newEvent);
            ConsolePrint.printAddTaskStatement(newEvent, noOfTasks + 1);
            noOfTasks += 1;
            break;
        case("delete"):
            int deleteIndex = Integer.parseInt(commandTypeAndParams[Constants.INDEX_DESCRIPTION]) - 1;
            Task deletedTask = tasks.get(deleteIndex);
            tasks.remove(deleteIndex);
            ConsolePrint.printDeleteTaskStatement(deletedTask, noOfTasks - 1);
            noOfTasks -= 1;
            break;
        case("find"):
            String keyword = commandTypeAndParams[Constants.INDEX_DESCRIPTION];
            ArrayList<Task> filteredTasks = (ArrayList<Task>)tasks.stream()
                    .filter(t -> t.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                    .collect(Collectors.toList());
            ConsolePrint.printFilteredList(filteredTasks, tasks, keyword);
            break;
        case("help"):
            ConsolePrint.printHelpMessage();
            break;
        default:
            //doNothing?
        }
    }
}
