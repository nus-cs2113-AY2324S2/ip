public class parser {

    // parses the user input to get the command to perform.

    public static String parseInputCommand(String userInput) {
        String[] words = userInput.split(" ", 2);
        String inputCommand = words[0];

        return inputCommand;
    }

    // Reads and parse the user input to perform its respective actions.

    public static boolean performUserInput(String userInput, TaskList taskList) {
        boolean isExit = false;
        String command = parseInputCommand(userInput);

        switch (command) {
        case "bye":
            System.out.println(taskList.goodbyeMessage);
            isExit = true;
            break;
        case "list":
            taskList.printTasksList();
            break;
        case "mark":
            taskList.markTask(userInput);
            break;
        case "unmark":
            taskList.unMarkTask(userInput);
            break;
        case "todo":
            taskList.addToDo(userInput);
            break;
        case "deadline":
            taskList.addDeadline(userInput);
            break;
        case "event":
            taskList.addEvent(userInput);
            break;
        case "add":
            taskList.addNewTask(userInput);
            break;
        default:
            System.out.println("Enter something to add to your task list");
        }
        return isExit;
    }
}
