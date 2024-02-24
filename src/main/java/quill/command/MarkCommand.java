package quill.command;

import quill.storage.Save;
import quill.task.Task;
import quill.task.TaskList;
import quill.ui.TextUi;

import java.util.ArrayList;

public class MarkCommand extends Command{
    public MarkCommand(String commandWord, String parameter) {
        super(commandWord, parameter);
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Save save) {
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
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Hey, wake up! That task? Non-existent. Try something real.");
        } catch (NumberFormatException e) {
            System.out.println("Listen up! Numbers only, got it? Don't bother with anything else");
        }
    }
}
