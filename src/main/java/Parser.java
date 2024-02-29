public class Parser {
    public static void parseCommand(String line) throws InvalidInputException {
        String[] parts = line.split(" ", 2);
        String command = parts[0].trim();

        switch (command) {
            case "bye":
                TaskList.handleByeTask();
                TaskList.setActive(false);
                break;
            case "list":
                TaskList.handleListTask();
                break;
            case "mark":
                TaskList.handleMarkTask(line);
                Storage.saveToFile();
                break;
            case "unmark":
                TaskList.handleUnmarkTask(line);
                Storage.saveToFile();
                break;
            case "deadline":
                TaskList.handleDeadlineTask(line);
                Storage.saveToFile();
                break;
            case "todo":
                TaskList.handleTodoTask(line);
                Storage.saveToFile();
                break;
            case "event":
                TaskList.handleEventTask(line);
                Storage.saveToFile();
                break;
            case "delete":
                TaskList.handleDeleteTask(line);
                Storage.saveToFile();
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
