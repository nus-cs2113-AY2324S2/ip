public class Duke {
    public static void main(String[] args) {
        Ui userInterface = new Ui();
        userInterface.printLogo();
        userInterface.printWelcome();

        TaskManager manager = new TaskManager();
        Parser inputParser = new Parser();

        while (true) {
            String userCommand = inputParser.parseCommand();

            if (userCommand.equals("bye")) {
                break;
            }

            // Initialize variables
            String result;
            String[] arguments;
            String taskName;

            switch (userCommand) {
            case "list":
                result = manager.listTasks();
                break;
            case "mark":
            case "unmark":
                arguments = inputParser.parseArguments(1);
                int taskId = Integer.parseInt(arguments[0]);
                result = manager.updateTaskProgress(taskId, userCommand);
                break;
            case "todo":
                arguments = inputParser.parseArguments(1);
                taskName = arguments[0];
                result = manager.addTodo(taskName);
                break;
            case "deadline":
                arguments = inputParser.parseArguments(2);
                taskName = arguments[0];
                String dueDate = arguments[1];
                result = manager.addDeadline(taskName, dueDate);
                break;
            case "event":
                arguments = inputParser.parseArguments(3);
                taskName = arguments[0];
                String startTime = arguments[1];
                String endTime = arguments[2];
                result = manager.addEvent(taskName, startTime, endTime);
                break;
            default:
                inputParser.clearInput();
                result = "ERROR";
                break;
            }

            userInterface.print(result);
        }

        userInterface.printExit();
    }
}
