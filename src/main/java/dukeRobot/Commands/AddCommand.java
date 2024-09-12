package dukeRobot.Commands;
import dukeRobot.Tasks.*;
import dukeRobot.Commands.Command;
import dukeRobot.Tools.*;
import dukeRobot.Tasks.*;


import java.io.IOException;
import java.util.ArrayList;
/**
 * Represent the Add commands, Add commands include deadline, event and todo,
 * A <code>AddCommand</code> object represents create a new task to the tasklist
 */
public class AddCommand extends Command {
    private String description;
    private String by;
    private String from;
    private String to;

    public AddCommand (Parser parser) {
        super(parser);
        this.description = parser.getDescription();
        this.by = parser.getBy();
        this.from = parser.getFrom();
        this.to = parser.getTo();
    }

    /**
     *
     * @param tasks stores in the Duck, represent the list of tasks
     * @param ui manipulates the user interactions
     * @param storage manipulates the file loading, updating and saving.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (keyWord.equals("deadline") || keyWord.equals("event") || keyWord.equals("todo")) {
                if (description.isEmpty()) {
                    throw new NoSuchMethodException();
                }
            switch (keyWord) {
                case "todo":
                    tasks.add(new ToDo(this.description));
                    break;
                case "deadline":
                    tasks.add(new Deadline(this.description,this.by));
                    break;
                case "event":
                    tasks.add(new Event(this.description, this.from, this.to));
                    break;
            }
            ui.showAddedMessage(tasks);
            //add storage
            try {
                storage.FileUpdater(tasks.get(Task.numOfTask - 1).toString() + "\n");
            } catch (IOException e) {
                ui.showIOException();
            }
            } else {
                ui.notUnderstandError();
            }
        } catch (NoSuchMethodException e) {
            ui.emptyDescriptionError();
        }
    }
}
