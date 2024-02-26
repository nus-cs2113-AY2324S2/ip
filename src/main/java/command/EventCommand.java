package command;

import errorhandle.UserInputErrorOutputHandler;
import storage.Storage;
import task.Event;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public class EventCommand extends Command {
    public static final String identity = "event";
    Storage storage;
    protected UserInputErrorOutputHandler userInputError;
    protected Ui ui;
    protected String from;
    protected String to;

    public EventCommand(String userCommandText) {
        storage = new Storage();
        userInputError = new UserInputErrorOutputHandler();
        ui = new Ui();
        int spaceIndex = userCommandText.indexOf(" ");
        prepareEventCommand(userCommandText.substring(spaceIndex + 1));
    }

    @Override
    public void execute(TaskList taskList) {
        Task t = new Event(content.trim(), from.trim(), to.trim());
        taskList.addTask(t);
        ui.printNewTaskAddedMessage(t, taskList);
        try {
            storage.saveTask(taskList.getTask(taskList.getSize() - 1));
        } catch (IOException e) {
            System.out.println("Can not save your Task!!!\n" + e.getMessage());
        }
    }

    public void prepareEventCommand(String unpreparedUserCommand) {
        if (unpreparedUserCommand.toLowerCase().startsWith(identity)) {
            userInputError.printNoTaskContentError(identity);
            setIfNoError(false);
            return;
        }
        setIfNoError(true);
        int fromIndex = unpreparedUserCommand.indexOf("/from");
        content = unpreparedUserCommand.substring(0, fromIndex);
        int toIndex = unpreparedUserCommand.indexOf("/to");
        from = unpreparedUserCommand.substring(fromIndex + 5, toIndex).trim();
        to = unpreparedUserCommand.substring(toIndex + 3).trim();
    }
}
