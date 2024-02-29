public class Parser {
    public static void parseCommand(String line) throws InvalidInputException {
        String[] parts = line.split(" ", 2);
        String command = parts[0].trim();

        final String COMMAND_BYE = "bye";
        final String COMMAND_LIST = "list";
        final String COMMAND_MARK = "mark";
        final String COMMAND_UNMARK = "unmark";
        final String COMMAND_DEADLINE = "deadline";
        final String COMMAND_TODO = "todo";
        final String COMMAND_EVENT = "event";
        final String COMMAND_DELETE = "delete";
        final String COMMAND_FIND = "find";

        switch (command) {
            case COMMAND_BYE:
                TaskList.handleByeTask();
                TaskList.setActive(false);
                break;
            case COMMAND_LIST:
                TaskList.handleListTask();
                break;
            case COMMAND_MARK:
                TaskList.handleMarkTask(line);
                Storage.saveToFile();
                break;
            case COMMAND_UNMARK:
                TaskList.handleUnmarkTask(line);
                Storage.saveToFile();
                break;
            case COMMAND_DEADLINE:
                TaskList.handleDeadlineTask(line);
                Storage.saveToFile();
                break;
            case COMMAND_TODO:
                TaskList.handleTodoTask(line);
                Storage.saveToFile();
                break;
            case COMMAND_EVENT:
                TaskList.handleEventTask(line);
                Storage.saveToFile();
                break;
            case COMMAND_DELETE:
                TaskList.handleDeleteTask(line);
                Storage.saveToFile();
                break;
            case COMMAND_FIND:
                TaskList.handleFindTask(line);
                break;
            default:
                throw new InvalidInputException("Invalid command: " + command);
        }
    }

    public static void parseFileLine(String line) throws InvalidInputException {
        String[] parts = line.split("\\|");

        if (parts.length < 3) {
            throw new InvalidInputException("Invalid input format in file");
        }

        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        switch (type) {
            case "T":
                TaskList.list.add(new ToDo(description, isDone));
                break;
            case "D":
                if (parts.length < 4) {
                    throw new InvalidInputException("Invalid input format for deadline in file");
                }
                String by = parts[3].trim();
                TaskList.list.add(new Deadline(description, by, isDone));
                break;
            case "E":
                if (parts.length < 5) {
                    throw new InvalidInputException("Invalid input format for event in file");
                }
                String from = parts[3].trim();
                String to = parts[4].trim();
                TaskList.list.add(new Events(description, from, to, isDone));
                break;
            default:
                throw new InvalidInputException("Unknown task type in file");
        }
    }
}
