package Commands;
import Task.Task;
import Utils.Parser;

import java.io.IOException;
import java.util.ArrayList;

public abstract  class Command {
    protected String commandType;

    public Command(Parser parser){
        this.commandType = parser.getType();
    }
    public abstract void execute(ArrayList<Task> tasks_list) throws IOException;
}
