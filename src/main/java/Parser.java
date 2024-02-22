public class Parser {
    private static void checkValidDeadline (String command) throws WrongInputFormat, MissingEntries, MissingTaskName {
        String[] commandWords = command.split("/");

        if (commandWords.length != 2) {
            throw new WrongInputFormat();
        } else {
            if (commandWords[0].length() < 10) {
                throw new MissingTaskName();
            }

            if (commandWords[1].length() < 4) {
                throw new MissingEntries();
            }
        }

    }

    private static void checkValidEvent (String command) throws WrongInputFormat, MissingEntries, MissingTaskName {
        String[] commandWords = command.split("/");
        if (commandWords.length != 3) {
            throw new WrongInputFormat();
        } else {

            String eventName = commandWords[0].substring(6);
            if (eventName.trim().isEmpty()) {
                throw new MissingTaskName();
            }

            if (commandWords[1].length() < 6) {
                throw new MissingEntries();
            } else {
                String from = commandWords[1].substring(5);
            }

            if (commandWords[2].length() < 4) {
                throw new MissingEntries();
            } else {
                String to = commandWords[2].substring(3);
            }

        }
    }

    private static void checkValidTodo (String command) throws RedundantEntries, MissingTaskName {
        String[] commandWords = command.split(" ");
        if (commandWords.length < 2) {
            throw new MissingTaskName();
        } else if (commandWords.length > 2) {
            throw new RedundantEntries();
        }
    }

    private static boolean isValidDeadline (String command) {
        try {
            checkValidDeadline(command);
        } catch (WrongInputFormat wif) {
            UI.printMessage("OOPS! Wrong command format");
            return false;
        } catch (MissingEntries me) {
            UI.printMessage("Missing deadline");
            return false;
        } catch (MissingTaskName mtn) {
            UI.printMessage("Missing task name");
            return false;
        }
        return true;
    }

    private static boolean isValidTodo (String command) {
        try {
            checkValidTodo(command);
        } catch (RedundantEntries re) {
            UI.printMessage("The command has too many entries");
            return false;
        } catch (MissingTaskName me) {
            UI.printMessage("Missing task name");
            return false;
        }
        return true;
    }

    private static boolean isValidEvent (String command) {
        try {
            checkValidEvent(command);
        } catch (WrongInputFormat wif) {
            UI.printMessage("OOPS! Wrong command format");
            return false;
        } catch (MissingEntries me) {
            UI.printMessage("Missing from/to dat");
            return false;
        } catch (MissingTaskName mtn) {
            UI.printMessage("Missing task name");
            return false;
        }
        return true;
    }

    public static boolean isValidTaskCommand (String command, String[] commandWords) {
        if (commandWords[0].equals("deadline")) {
            return isValidDeadline(command);
        } else if (commandWords[0].equals("event")) {
            return isValidEvent(command);
        }
        return isValidTodo(command);
    }

    private static Deadline parseDeadline (String command) {
        String[] commandWords = command.split("/");
        String taskName = commandWords[0].substring(9);
        String by = commandWords[1].substring(3);
        return new Deadline(taskName.trim(), by.trim());
    }

    private static Event parseEvent (String command) {
        String[] commandWords = command.split("/");

        String eventName = commandWords[0].substring(6);

        String from = commandWords[1].substring(5);
        String to = commandWords[2].substring(3);

        return new Event(eventName.trim(), from.trim(), to.trim());
    }

    private static Todo parseTodo (String command) {
        String[] commandWords = command.split(" ");
        return new Todo(commandWords[1].trim());
    }

    public static Task parseCommand (String command) {
        String[] commandWords = command.split(" ");
        if (commandWords[0].equals("deadline")) {
            return parseDeadline(command);
        } else if (commandWords[0].equals("event")) {
            return parseEvent(command);
        }
        return parseTodo(command);
    }
}
