package bobby;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Bobby {
    private static final String FILE_PATH = "data/tasks.txt";
    private static Ui ui;
    private static Storage storage;
    private static Parser parser;
    private static TaskList tasks;

    public Bobby(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        tasks = new TaskList();
        storage.createFile();
        try {
            storage.loadFile(tasks.list);
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
        }
    }

    public void run() {
        boolean hasExit = false;
        Scanner in = new Scanner(System.in);
        ui.showWelcomeMessage();

        while (!hasExit) {
            String input = in.nextLine();
            String command;
            String description;
            String by;
            String from;
            int entry;
            command = parser.parseCommand(input);
            switch (command) {
            case "bye":
                ui.showByeMessage();
                hasExit = true;
                break;
            case "mark":
                entry = Integer.parseInt(input.substring(5));
                if (entry > 0 && entry <= tasks.list.size()) {
                    tasks.markTask(entry);
                    ui.showMarkMessage(tasks.list, entry);
                    try {
                        storage.writeToFile(tasks.list);
                    } catch (IOException e) {
                        ui.showSavingError();
                    }
                }
                break;
            case "unmark":
                entry = Integer.parseInt(input.substring(7));
                if (entry > 0 && entry <= tasks.list.size()) {
                    tasks.unmarkTask(entry);
                    ui.showUnmarkMessage(tasks.list, entry);
                    try {
                        storage.writeToFile(tasks.list);
                    } catch (IOException e) {
                        ui.showSavingError();
                    }
                }
                break;
            case "list":
                ui.showList(tasks.list);
                break;
            case "todo":
                try {
                    description = parser.parseTodoDescription(input);
                } catch (BobbyException e) {
                    ui.showInvalidTodoMessage();
                    break;
                }
                tasks.addTodo(description);
                ui.showValidTodoMessage(tasks.list);
                try {
                    storage.writeToFile(tasks.list);
                } catch (IOException e) {
                    ui.showSavingError();
                }
                break;
            case "deadline":
                try {
                    description = parser.parseDeadlineDescription(input);
                    by = parser.parseDeadlineBy(input);
                } catch (StringIndexOutOfBoundsException e) {
                    ui.showInvalidDeadlineMessage();
                    break;
                }
                tasks.addDeadline(description, by);
                ui.showValidDeadlineMessage(tasks.list);
                try {
                    storage.writeToFile(tasks.list);
                } catch (IOException e) {
                    ui.showSavingError();
                }
                break;
            case "event":
                try {
                    description = parser.parseEventDescription(input);
                    by = parser.parseEventBy(input);
                    from = parser.parseEventFrom(input);
                } catch (StringIndexOutOfBoundsException e) {
                    ui.showInvalidEventMessage();
                    break;
                }
                tasks.addEvent(description, by, from);
                ui.showValidEventMessage(tasks.list);
                try {
                    storage.writeToFile(tasks.list);
                } catch (IOException e) {
                    ui.showSavingError();
                }
                break;
            case "delete":
                entry = Integer.parseInt(input.substring(7));
                if (entry > 0 && entry <= tasks.list.size()) {
                    ui.showDeleteMessage(tasks.list, entry);
                    tasks.deleteTask(entry);
                    try {
                        storage.writeToFile(tasks.list);
                    } catch (IOException e) {
                        ui.showSavingError();
                    }
                }
                break;
            default:
                ui.showInvalidCommandError();
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Bobby(FILE_PATH).run();
    }
}