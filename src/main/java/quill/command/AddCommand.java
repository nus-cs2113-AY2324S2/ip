package quill.command;

import quill.exception.EmptyDateException;
import quill.exception.QuillException;
import quill.storage.Save;
import quill.task.*;
import quill.ui.TextUi;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class AddCommand extends Command{
    public AddCommand(String commandWord, String parameter) {
        super(commandWord, parameter);
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Save save) {
        int taskNumber = TaskList.getTotalTasks();
        switch (commandWord) {
        case "todo":
            if (parameter.isEmpty()) {
                System.out.println("No empty descriptions allowed for todos. Fill it in!");
            } else {
                tasks.addTask(new Todo(parameter));
                TextUi.showAddTask(tasks.getTask(taskNumber));
            }
            break;
        case "deadline":
            try {
                tasks.addTask(new Deadline(parameter));
                TextUi.showAddTask(tasks.getTask(taskNumber));
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Seriously? You call that a deadline?");
                System.out.println("It's 'deadline [task] /by yyyy-MM-dd HH:mm'. Get it right!");
                break;
            } catch (QuillException e) {
                System.out.println("No empty descriptions allowed for deadline. Fill it in!");
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Listen, it's simple: /by yyyy-MM-dd HH:mm.");
                break;
            }
            break;
        case "event":
            try {
                tasks.addTask(new Event(parameter));
                TextUi.showAddTask(tasks.getTask(taskNumber));
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Seriously? You call that an event?");
                System.out.println("It's 'event [task] /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm'. Get it right!");
            } catch (DateTimeParseException e) {
                System.out.println("Listen, it's simple: /by yyyy-MM-dd HH:mm.");
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
