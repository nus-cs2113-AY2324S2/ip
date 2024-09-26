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

    /**
     * Represents {@code EventCommand} object to handle event command.
     * @param line is the String input by user
     * @param parser to help with processing the String input.
     */
    public EventCommand(String line, Parser parser) {
        this.USER_INPUT = line;
        this.parser = parser;
    }

    /**
     * Executes the <code>addEventCommand</code> method to add Event task into list,
     * checks if there are no words after event keyword
     * @param tasks is the current list of <code>tasks</code> to save to the file.
     * @param ui is the user interface of that prints texts on program.
     * @param fileSaver is object used to save data into the file.
     * @throws IOException If there is error in saving data into file.
     */
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
                    + "' cannot be empty.");
        }
    }

    /**
     * Processes the <code>deadlineTask</code> String by splitting the String at '/from' and '/to'
     * and saves into current <code>tasks</code> list.
     * @param eventTask array String after event keyword.
     * @throws IOException If there is error in saving data into file.
     */

    private void addEventCommand(String[] eventTask) throws IOException, TonyException, ArrayIndexOutOfBoundsException {
        try {
            parser.checkContainsFromTo(eventTask);
            String[] description = eventTask[1].split("/from | /to");
            Event event = new Event(description[0], description[1], description[2]);
            tasks.add(event);
            ui.printAddOrDeleteTask(description[0], tasks.indexOf(event));
            String eventLine = fileSaver.saveEvent(event);
            fileSaver.saveData(eventLine, true);
        } catch (TonyException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Your event command should have '/from' and '/to after task description."
                    + System.lineSeparator()
                    + "\tFormat: event <description> /from <DATE> /to <DATE>");
        }
    }

    /**
     * Returns <code>false</code> if command not entered <code>bye</code>
     * @return <code>false</code> and does not exit program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
