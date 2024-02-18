public class CommandHandler {
    public static Command checkCommand(String task, TaskList taskList) throws GabException {
        String[] taskAction = task.split(" ");
        String action = taskAction[0];

        switch (action) {
        case "bye": {
            return new ByeCommand();
        }
        case "list": {
            return new ListCommand();
        }
        case "todo": {//need display [T][ ] name
            return Parser.parseToDo(task);
        }
        case "deadline": {
            return Parser.parseDeadline(task);
        }
        case "event": {
            return Parser.parseEvent(task);
        }
        case "mark": {
            return Parser.markTask(task, taskList);
        }
        case "unmark": {
            return Parser.UnmarkTask(task, taskList);
        }
        default:
            throw new GabException("Not a valid command");
        }
    }
}

