package Tony.command;

import Tony.FileManager.FileSaver;
import Tony.TonyException;
import Tony.task.Event;
import Tony.task.Task;
import Tony.utility.Parser;
import Tony.utility.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class EventCommand implements Command {
    private final String USER_INPUT;
    private ArrayList<Task> tasks;
    private FileSaver fileSaver;
    private Ui ui;
    private final Parser parser;
    public EventCommand(String line, Parser parser) {
        this.USER_INPUT = line;
        this.parser = parser;
    }
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, FileSaver fileSaver) throws IOException {
        this.tasks = tasks;
        this.ui = ui;
        this.fileSaver = fileSaver;
        String[] eventTask = USER_INPUT.split("event");

        try {
            parser.checkArrayLength(eventTask);
            addEventCommand(eventTask);
        } catch (TonyException e) {
            System.out.println("OOPS!! The description of '" + USER_INPUT
                    + "' cannot be empty." + System.lineSeparator());
        }
    }

    private void addEventCommand(String[] eventTask) throws IOException {
        String[] description = eventTask[1].split("/from | /to");
        Event event = new Event(description[0], description[1], description[2]);
        tasks.add(event);
        ui.printAddOrDeleteTask(description[0], tasks.indexOf(event));
        String eventLine = fileSaver.saveEvent(event);
        fileSaver.saveData(eventLine, true);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
