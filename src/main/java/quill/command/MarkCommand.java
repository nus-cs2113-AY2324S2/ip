package quill.command;

import quill.storage.Save;
import quill.task.TaskList;
import quill.ui.TextUi;

/**
 * The MarkCommand Class represents a command to mark task as done or not done.
 * It extends the Command class and includes specific behavior marking tasks.
 */
public class MarkCommand extends Command{

    /**
     * Constructs the MarkCommand object with the specified commandWord and parameter.
     *
     * @param commandWord The command word.
     * @param parameter The user input excluding the command word.
     */
    public MarkCommand(String commandWord, String parameter) {
        super(commandWord, parameter);
    }

    /**
     * Executes the MarkCommand to mark tasks as done or not done.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface for displaying messages.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui) {
        try {
            int taskNumber = Integer.parseInt(parameter) - 1;
            if (commandWord.equals("mark")) {
                tasks.getTask(taskNumber).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
            } else {
                tasks.getTask(taskNumber).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println(tasks.getTask(taskNumber).toString());
            Save.writeToFile(tasks);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Hey, wake up! That task? Non-existent. Try something real.");
        } catch (NumberFormatException e) {
            System.out.println("Listen up! Numbers only, got it? Don't bother with anything else");
        }
    }
}
