package dukeRobot.Commands;
import dukeRobot.Tasks.*;
import dukeRobot.Commands.Command;
import dukeRobot.Tools.*;
import dukeRobot.Tasks.*;


import java.io.IOException;
import java.util.ArrayList;

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
