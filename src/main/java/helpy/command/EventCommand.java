package helpy.command;

import helpy.Storage;
import helpy.Ui;
import helpy.exceptions.EventDateSequenceException;
import helpy.task.Event;
import helpy.task.TaskList;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command{
    public EventCommand(String body) {
        super(body);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Event newEvent = new Event(commandBody);
            taskList.addTask(newEvent);
            ui.showAddMessage(newEvent, taskList.getListLength());
            storage.update(taskList);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            ui.showEventDescErr();
        } catch (EventDateSequenceException e) {
            ui.showEventSeqErr();
        } catch (IOException e) {
            ui.showIOExceptionErr();
        }
    }
}
