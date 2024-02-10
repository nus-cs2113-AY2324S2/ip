package geepee.system;

import geepee.exceptions.*;

public class InputHandler {

    public static void checkTodoInputValidity(String line) throws EmptyDescriptionException {
        if (line.equals("todo")) {
            throw new EmptyDescriptionException();
        }
    }

    public static void checkDeadlineInputValidity(String line) throws EmptyDescriptionException {
        if (line.equals("deadline")) {
            throw new EmptyDescriptionException();
        }
    }

    public static void checkEventInputValidity(String line) throws EmptyDescriptionException {
        if (line.equals("event")) {
            throw new EmptyDescriptionException();
        }
    }
}
