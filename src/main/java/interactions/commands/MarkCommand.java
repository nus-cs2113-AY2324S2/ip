package interactions.commands;

import interactions.Storage;
import interactions.Ui;
import tasks.Task;
import tasks.TaskList;

import java.io.IOException;
import java.util.ArrayList;

/** Sets whether a task is marked or unmarked, indicating whether it is done or not. */
public class MarkCommand extends Command {
    public MarkCommand() {

    }

    /**
     * Marks or unmarks a task from the task list given the index given in the input.
     *
     * @param tasklist List of tasks containing ToDo's, Events and Deadlines.
     */
    public void mark(TaskList tasklist) {
        boolean isMark = firstWord.equals("mark");
        int index = Integer.parseInt(line.substring(isMark ? 5 : 7));
        ArrayList<Task> list = tasklist.getList();
        Task markedTask = list.get(index - 1);
        if (markedTask.isMarked() == isMark) {
            System.out.println("This task is already set as " + (isMark ? "marked." : "unmarked."));
            return;
        }
        if (isMark) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.print(INDENT);
        markedTask.setMarked(isMark);
        markedTask.print();
    }

    /**
     * Marks or unmarks a task in the task list to show if it is done or not.
     * Updates and saves any changes to file.
     *
     * @param taskList List of tasks containing ToDo's, Events and Deadlines.
     * @param storage Storage handler that saves to file.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        mark(taskList);
        try {
            storage.saveToFile("data/list.txt", taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}