package command;

import exception.InputException;
import task.TaskList;
import tool.ResponseManager;

public class MarkUnmarkCommand implements Command {
    private final CommandType taskType;
    private final int taskIndex;

    public MarkUnmarkCommand(CommandType taskType, int taskIndex) {
        this.taskType = taskType;
        this.taskIndex = taskIndex;
    }

    /**
     * {@inheritDoc}
     * 
     * Marks or unmarks a task with specific index in the task list.
     * If task type is mark, it will mark the task.
     * If task type is unmark, it will unmark the task.
     *
     * @param tasks the task list.
     * @throws InputException if the index is out of range.
     */
    @Override
    public void run(TaskList tasks) throws InputException {
        switch (taskType) {
        case MARK:
            if (tasks.getPosAt(taskIndex).isMarked()) {
                throw new InputException(ResponseManager.MARKED_TASK_MSG);
            }
            tasks.markTask(taskIndex);
            break;

        case UNMARK:
            if (tasks.getPosAt(taskIndex).isMarked()) {
                throw new InputException(ResponseManager.UNMARKED_TASK_MSG);
            }
            tasks.unmarkTask(taskIndex);
            break;

        default:
            break;
        }
        ResponseManager.printActionOnTasks(taskType,
                tasks.getPosAt(taskIndex).toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
