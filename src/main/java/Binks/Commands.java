package Binks;

public class Commands {
    /**
     * Handles the case where the mark command is given as input
     *
     * @param command Command array of input string without spaces
     * @param list List of tasks
     * @throws BinksException if command.length != 2
     */
    public static void markCommand(String[] command, List list) throws BinksException {
        if (command.length == 2) {
            int taskNumber = Integer.parseInt(command[1]);
            list.markAsDone(taskNumber);
        } else {
            throw new BinksException("Please specify which task to mark.");
        }
    }

    /**
     * Handles the case where the unmark command is given as input
     *
     * @param command Command array of input string without spaces
     * @param list List of tasks
     * @throws BinksException if command.length != 2
     */
    public static void unmarkCommand(String[] command, List list) throws BinksException {
        if (command.length == 2) {
            int taskNumber = Integer.parseInt(command[1]);
            list.unmarkAsDone(taskNumber);
        } else {
            throw new BinksException("Please specify which task to unmark!");
        }
    }

    /**
     * Handles the case where the todo command is given as input
     *
     * @param line Line of the initial command string including spaces
     * @param command Command array of input string without spaces
     * @param list List of tasks
     * @throws BinksException when command.length < 2
     */
    public static void todoCommand(String line, String[] command, List list) throws BinksException {
        if (command.length >= 2) {
            list.addTodo(line.substring(5));
        } else {
            throw new BinksException("The description of todo cannot be empty!");
        }
    }

    /**
     * Handles the case where the deadline command is given as input
     *
     * @param line Line of the initial command string including spaces
     * @param command Command array of input string without spaces
     * @param list List of tasks
     * @throws BinksException when command.length < 2 or deadline is not given
     */
    public static void deadlineCommand(String line, String[] command, List list) throws BinksException {
        if (command.length >= 2) {
            int bySeparator = line.indexOf("/by ");
            if (bySeparator != -1) {
                String deadline = line.substring(bySeparator + 4);
                String task = line.substring(9, bySeparator);
                list.addDeadline(task + "(by: " + Date.convertDate(deadline) + ")");
            } else {
                throw new BinksException("Please indicate a deadline using /by!");
            }
        } else {
            throw new BinksException("The description of deadline cannot be empty!");

        }
    }

    /**
     * Handles the case where the event command is given as input
     *
     * @param line Line of the initial command string including spaces
     * @param command Command array of input string without spaces
     * @param list List of tasks
     * @throws BinksException when command.length < 2 or timeframe is not given
     */
    public static void eventCommand(String line, String[] command, List list) throws BinksException {
        if (command.length >= 2) {
            int fromSeparator = line.indexOf("/from ");
            int toSeparator = line.indexOf("/to ");
            if (fromSeparator != -1 && toSeparator != -1) {
                String startTime = line.substring(fromSeparator + 6, toSeparator);
                String endTime = line.substring(toSeparator + 4);
                String task = line.substring(6, fromSeparator);
                list.addEvent(task + "(from: " + startTime + "to: " + endTime + ")");
            } else {
                throw new BinksException("Please specify start and end times using /from and /to!");
            }
        } else {
            throw new BinksException("The description of event cannot be empty!");
        }
    }
    /**
     * Handles the case where the delete command is given as input
     *
     * @param command Command array of input string without spaces
     * @param list List of tasks
     * @throws BinksException when command.length != 2 ie. task to delete not given
     */
    public static void deleteCommand(String[] command, List list) throws BinksException {
        if (command.length == 2) {
            int taskNumber = Integer.parseInt(command[1]);
            list.deleteTask(taskNumber);
        } else {
            throw new BinksException("Please specify which task to delete.");
        }
    }
    /**
     * Handles the case where the event command is given as input
     *
     * @param command Command array of input string without spaces
     * @param list List of tasks
     * @throws BinksException when command.length != 2 ie. keyword to find not given
     */
    public static void findCommand(String[] command, List list) throws BinksException {
        if (command.length == 2) {
            String keyword = command[1];
            list.findTask(keyword);
        } else {
            throw new BinksException("Please specify the keyword you want to find.");
        }
    }
}
