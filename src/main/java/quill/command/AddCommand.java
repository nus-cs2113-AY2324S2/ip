package quill.command;

import quill.exception.EmptyDateException;
import quill.exception.QuillException;
import quill.storage.Save;
import quill.task.Deadline;
import quill.task.Event;
import quill.task.Task;
import quill.task.Todo;
import quill.ui.TextUi;

import java.util.ArrayList;

public class AddCommand extends Command{
    public AddCommand(String commandWord, String parameter) {
        super(commandWord, parameter);
    }

    @Override
    public void execute(ArrayList<Task> tasks, TextUi ui, Save save) {
        int taskNumber = Task.getTotalTasks();
        switch (commandWord) {
        case "todo":
            if (parameter.isEmpty()) {
                System.out.println("No empty descriptions allowed for todos. Fill it in!");
            } else {
                tasks.add(new Todo(parameter));
                TextUi.showAddTask(tasks.get(taskNumber));
            }
            break;
        case "deadline":
            try {
                tasks.add(new Deadline(parameter));
                TextUi.showAddTask(tasks.get(taskNumber));
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Seriously? You call that a deadline?");
                System.out.println("It's 'deadline [task] /by [date]'. Get it right!");
                Task.removeTask();
                break;
            } catch (EmptyDateException e) {
                break;
            } catch (QuillException e) {
                System.out.println("No empty descriptions allowed for deadline. Fill it in!");
                break;
            }
            break;
        case "event":
            try {
                tasks.add(new Event(parameter));
                TextUi.showAddTask(tasks.get(taskNumber));
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Seriously? You call that an event?");
                System.out.println("It's 'event [task] /from [date] /to [date]'. Get it right!");
                Task.removeTask();
            } catch (EmptyDateException e) {
                break;
            } catch (QuillException e) {
                System.out.println("No empty descriptions allowed for event. Fill it in!");
            }
            break;
        default:
            System.out.println("Error adding task: unknown task type");
        }
    }
}
