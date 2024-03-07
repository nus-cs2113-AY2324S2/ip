import java.util.Scanner;

/**
 * Parse user's input and
 * respond to it.
 */
public class Parser {
    private static void checkValidDeadline (String command) throws WrongInputFormat, MissingEntries, MissingTaskName {
        String[] commandWords = command.split("-");

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
        String[] commandWords = command.split("-");
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

    private static boolean isValidTaskCommand (String command, String[] commandWords) {
        if (commandWords[0].equals("deadline")) {
            return isValidDeadline(command);
        } else if (commandWords[0].equals("event")) {
            return isValidEvent(command);
        }
        return isValidTodo(command);
    }

    private static Deadline parseDeadline (String command) {
        String[] commandWords = command.split("-");
        String taskName = commandWords[0].substring(9);
        String by = commandWords[1].substring(3);
        return new Deadline(taskName.trim(), by.trim());
    }

    private static Event parseEvent (String command) {
        String[] commandWords = command.split("-");

        String eventName = commandWords[0].substring(6);

        String from = commandWords[1].substring(5);
        String to = commandWords[2].substring(3);

        return new Event(eventName.trim(), from.trim(), to.trim());
    }

    private static Todo parseTodo (String command) {
        String[] commandWords = command.split(" ");
        return new Todo(commandWords[1].trim());
    }

    private static Task parseCommand (String command) {
        String[] commandWords = command.split(" ");
        if (commandWords[0].equals("deadline")) {
            return parseDeadline(command);
        } else if (commandWords[0].equals("event")) {
            return parseEvent(command);
        }
        return parseTodo(command);
    }

    private static boolean isValidTask (String command) {
        return command.equals("todo") || command.equals("deadline") || command.equals("event");
    }

    private static boolean isValidCommand (String command) {
        return command.equals("list") || command.equals("mark") || command.equals("unmark") || command.equals("delete")
                || command.equals("save") || isValidTask(command);
    }


    private static void responseToCommand (String command, TaskList tasks) {
        String[] commandWords = command.split(" ");
        if (!isValidCommand(commandWords[0])) {
            UI.printMessage("Command not recognized");
            return;
        }
        if (commandWords[0].equals("list")) {
            tasks.listTasks();
        } else if (commandWords[0].equals("mark")) {
            tasks.mark(Integer.parseInt(commandWords[1]));
        } else if (commandWords[0].equals("unmark")) {
            tasks.unmark(Integer.parseInt(commandWords[1]));
        } else if (commandWords[0].equals("delete")) {
            tasks.delete(Integer.parseInt(commandWords[1]));
        } else {
            if (Parser.isValidTaskCommand(command, commandWords)) {
                Task newTask;
                newTask = Parser.parseCommand(command);
                tasks.addTask(newTask);
            }
        }
    }

    /**
     * Take user's input from the terminal and
     * decide how Stella will react to user's commands
     * (list, create a task, mark, unmark, delete, or
     * throw an error message).
     * @param tasks the current user's task list
     */
    public void takeResponse (TaskList tasks) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equals("bye")) {
            responseToCommand(line, tasks);
            line = in.nextLine();
        }
    }
}
