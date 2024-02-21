package commands;
import java.util.ArrayList;

import Exceptions.ThawException;
import Tasks.*;

public class AddTask extends EditTask {
    public static void addTask(String usersInput, ArrayList<Task> list) throws ThawException {
        if (!commandWithoutDescription(usersInput)) {
            if (usersInput.startsWith("todo")) {
                list.add(new Todo(usersInput.substring(5), usersInput));
            } else if (usersInput.startsWith("deadline")) {
                int startingIndex = usersInput.indexOf("/by");
                list.add(new Deadline(usersInput.substring(9, startingIndex - 1),
                        usersInput.substring(startingIndex + 4)));
            } else if (usersInput.startsWith("event")) {
                int startIndex = usersInput.indexOf("from");
                int endIndex = usersInput.indexOf("to");
                list.add(new Event(usersInput.substring(6, startIndex - 2),
                        "from: " + usersInput.substring(startIndex + 5, endIndex - 2) + " to: " + usersInput.substring(endIndex + 3)));
            }
        } else if (commandWithoutDescription(usersInput)){
            throw new ThawException("Empty command " + usersInput);
        }
    }
}
