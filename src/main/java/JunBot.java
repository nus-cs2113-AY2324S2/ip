import junbot.common.TaskList;
import junbot.error.InvalidInputException;
import junbot.error.JunBotException;
import junbot.parser.Parser;
import junbot.storage.Storage;
import junbot.tasks.Task;
import junbot.ui.Ui;

import java.io.IOException;

public class JunBot {
    private static String FILEPATH = "./data/tasks.txt";
    private static Ui ui;
    private static Storage storage;
    private static TaskList tasks;
    private static Parser parser;

    public JunBot(String filePath){
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            parser = new Parser();
            tasks = new TaskList(storage.load());
        } catch (JunBotException | IOException | InvalidInputException e) {
            tasks = new TaskList();
        }
    }

    public static void addEvent(String description) throws InvalidInputException, IOException {
        Task eventTask = tasks.addEvent(description);

        ui.printDivider();
        ui.printAddSuccessMessage(eventTask);
        ui.printNumTasks(tasks.getTasksList());
        ui.printDivider();
        storage.updateFile(tasks.getTasksList());


    }

    public static void addDeadline(String description) throws InvalidInputException, IOException {
        Task deadlineTask = tasks.addDeadline(description);

        ui.printDivider();
        ui.printAddSuccessMessage(deadlineTask);
        ui.printNumTasks(tasks.getTasksList());
        ui.printDivider();
        storage.updateFile(tasks.getTasksList());


    }

    public static void addTodo(String description) throws InvalidInputException, IOException {
        Task todoTask = tasks.addTodo(description);

        ui.printDivider();
        ui.printAddSuccessMessage(todoTask);
        ui.printNumTasks(tasks.getTasksList());
        ui.printDivider();
        storage.updateFile(tasks.getTasksList());

    }


    public static boolean isValidListPosition(String command) {
        if (command == null) {
            return false;
        }
        try {
            int listPosition = Integer.parseInt(command);
            return listPosition <= tasks.getSize() && listPosition > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void unmarkTaskInList(String command) throws IOException {
        String listNumber = parser.removeCommandIndicator(command,"unmark");
        int taskArrayIndex = parser.convertToArrayIndex(listNumber);

        if (!isValidListPosition(listNumber)){
            System.out.println("Invalid List Number");
            return;
        }

        Task taskToUnmark = tasks.unmarkTaskInList(taskArrayIndex);

        ui.printUnmarkSuccessMessage(taskToUnmark);
        storage.updateFile(tasks.getTasksList());

    }

    public static void markTaskInList(String command) throws IOException {
        String listNumber = parser.removeCommandIndicator(command, "mark");
        int taskArrayIndex = parser.convertToArrayIndex(listNumber);

        if (!isValidListPosition(listNumber)){
            System.out.println("Invalid List Number");
            return;
        }

        Task taskToMark = tasks.markTaskInList(taskArrayIndex);

        ui.printMarkSuccessMessage(taskToMark);
        storage.updateFile(tasks.getTasksList());

    }

    public static void printList() {
        int taskNumber = 1;

        ui.printDivider();
        System.out.println("Here are the tasks in your list: ");
        for(int i = 0; i < tasks.getSize(); i++){
            System.out.print( taskNumber + ". ");
            System.out.println(tasks.getTasksList().get(i));
            taskNumber += 1;
        }
        ui.printDivider();
    }

    public static void deleteTask(String command) throws IOException{
        String listNumber = parser.removeCommandIndicator(command, "delete");
        int taskArrayIndex = parser.convertToArrayIndex(listNumber);

        if (!isValidListPosition(listNumber)){
            System.out.println("Invalid List Number");
            return;
        }

        Task taskToDisplay = tasks.getTasksList().get(taskArrayIndex);
        tasks.getTasksList().remove(taskArrayIndex);

        ui.printDeleteSuccessMessage(taskToDisplay);
        storage.updateFile(tasks.getTasksList());

    }

    public static void handleCommands(String command, String userInput) throws IOException, InvalidInputException {
        switch (command) {
        case "list":
            printList();
            break;
        case "mark":
            markTaskInList(userInput);
            break;
        case "unmark":
            unmarkTaskInList(userInput);
            break;
        case "todo":
            addTodo(userInput);
            break;
        case "deadline":
            addDeadline(userInput);
            break;
        case "event":
            addEvent(userInput);
            break;
        case "delete":
            deleteTask(userInput);
            break;
        default:
            System.out.println("Enter a valid command");
            break;
        }
    }

    public static void handleUserInput() throws IOException {

        String userInput = ui.readCommand();

        while (!userInput.equals("bye")) {
            String command = parser.getCommandIndicator(userInput);
            try {
                handleCommands(command, userInput);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
            userInput = ui.readCommand();
        }

    }

    public static void main(String[] args) throws IOException, InvalidInputException{
        new JunBot(FILEPATH);
        ui.printWelcomeMessage();
        handleUserInput();
        ui.printGoodbyeMessage();
    }
}
