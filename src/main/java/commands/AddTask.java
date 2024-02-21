package commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ArrayList;
import Tasks.*;
import Exceptions.*;

public class AddTask {

    public static void addTask(String usersInput, ArrayList<Task> list) throws ThawException, IOException  {
        if (usersInput.strip().equals("todo") || usersInput.strip().equals("deadline") ||
                usersInput.strip().equals("event") || usersInput.strip().equals("delete")) {
            throw new ThawException("Empty command " + usersInput.strip());
        } else if (usersInput.startsWith("todo")) {
            list.add(new Todo(usersInput.substring(5), usersInput));
        } else if (usersInput.startsWith("deadline")) {
            int startingIndex = usersInput.indexOf("/by");
            list.add(new Deadline(usersInput.substring(9, startingIndex - 1),
                    usersInput.substring(startingIndex + 4)));
        } else if (usersInput.startsWith("event")) {
            int startIndex = usersInput.indexOf("from");
            int endIndex = usersInput.indexOf("to");
            list.add(new Event(usersInput.substring(6, startIndex - 2),
                    "from: " + usersInput.substring(startIndex+ 5, endIndex - 2) + " to: " + usersInput.substring(endIndex + 3)));
        } else {
            throw new ThawException("Invalid command");
        }

    }


}
