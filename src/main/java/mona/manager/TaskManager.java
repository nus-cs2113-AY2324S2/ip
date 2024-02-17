package mona.manager;

import mona.output.ConsolePrint;
import mona.task.Deadline;
import mona.task.Event;
import mona.task.Task;
import mona.task.Todo;
import mona.util.Constants;

import java.util.ArrayList;

public class TaskManager {
    public static int noOfTasks;
    protected ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
        noOfTasks = 0;
    }
    public void executeCommand(String[] commandTypeAndParams) {
        String commandType = commandTypeAndParams[Constants.INDEX_COMMAND_TYPE];

        switch (commandType) {
        case ("mark"):
            int markIndex = Integer.parseInt(commandTypeAndParams[Constants.INDEX_DESCRIPTION]) - 1;
            //tasks[markIndex].markAsDone();
            tasks.get(markIndex).markAsDone();
            ConsolePrint.printMarkStatement(tasks.get(markIndex));
            break;
        case ("unmark"):
            int unmarkIndex = Integer.parseInt(commandTypeAndParams[Constants.INDEX_DESCRIPTION]) - 1;
            //tasks[unmarkIndex].markAsNotDone();
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
        }
    }
}
