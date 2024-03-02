import java.util.Scanner;

public class Krot {
    private static final String FILE_PATH = "./data/taskList.txt";
    private UI ui;
    private Parser parser;
    private Storage storage;
    private TaskList taskList;

    public Krot() {
        this.ui = new UI();
        this.storage = new Storage(FILE_PATH);
        this.parser = new Parser();
        try {
            taskList = new TaskList(storage.readDb());
        } catch (Exception e) {
            ui.printError(e.getMessage());
        }

    }

    public void generateResponse(String key, String line) {
        Task task;
        switch (key) {
        case "bye":
            ui.hasEnded = true;
            break;
        case "list":
            ui.listTasks(taskList.getTasks());
            break;
        case "mark":
        case "unmark":
            try {
                int index = parser.parseIndexToMark(line);
                task = taskList.markTask(key, index);
                ui.printMarkMessage(task);
            } catch (Exception e) {
                ui.printError(e.getMessage());
            }
            break;
        case "todo":
            try {
                String title = parser.todoParser(line);
                task = taskList.createTodo(title);
                ui.printCreateTaskMessage(task, taskList.getTasks().size());
            } catch (Exception e) {
                ui.printError(e.getMessage());
            }
            break;
        case "deadline":
            try {
                String[] deadlineDetails = parser.deadlineParser(line);
                task = taskList.createDeadline(deadlineDetails[0], deadlineDetails[1]);
                ui.printCreateTaskMessage(task, taskList.getTasks().size());
            } catch (Exception e) { // Catches if wrong initializer
                ui.printError(e.getMessage());
            }
            break;
        case "event":
            try {
                String[] eventDetails = parser.eventParser(line);
                task = taskList.createEvent(eventDetails[0], eventDetails[1], eventDetails[2]);
                ui.printCreateTaskMessage(task, taskList.getTasks().size());
            } catch (Exception e) { // Catches if wrong initializer is used
                ui.printError(e.getMessage());
            }
            break;
        case "delete":
            try {
                int listIndex = parser.deleteParser(line);
                task = taskList.deleteTask(listIndex - 1);
                ui.printDeleteMessage(task, listIndex, taskList.getTasks().size());
            } catch (Exception e) {
                ui.printError(e.getMessage());
            }
            break;
        default:
            ui.printError(new UnknownCommandException("I'm sorry I didnt quite catch that").getMessage());
        }
    }

    public void run() {
        String line;
        ui.printSeparator();
        ui.greeting();
        Scanner in = new Scanner(System.in);
        while (!ui.hasEnded) {
            ui.printSeparator();
            ui.printUser();
            line = in.nextLine();
            ui.printSeparator();
            ui.printName();
            try {
                String key = parser.checkKey(line);
                generateResponse(key, line);
            } catch (Exception e) {
                ui.printError(e.getMessage());
            }
            storage.saveList(taskList.getTasks());
        }
        ui.endChat();
        in.close();
    }

    public static void main(String[] args) {
        new Krot().run();
    }
}

