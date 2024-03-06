package bobby;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Bobby {
    public static final String FILE_PATH = "data/tasks.txt";

    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage(FILE_PATH);
        Parser parser = new Parser();
        boolean isExit = false;
        ArrayList<Task> tasks = new ArrayList<>(); // Using ArrayList instead of array
        Scanner in = new Scanner(System.in);
        ui.showWelcomeMessage();
        storage.createFile();
        try {
           storage.loadFile(tasks);
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
        }

        while (!isExit) {
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
                isExit = true;
                break;
            case "mark":
                entry = Integer.parseInt(input.substring(5));
                if (entry > 0 && entry <= tasks.size()) {
                    tasks.get(entry - 1).setDone(true);
                    ui.showMarkMessage();
                    System.out.println(entry + "." + tasks.get(entry - 1));
                    try {
                        storage.writeToFile(tasks);
                    } catch (IOException e) {
                        ui.showSavingError();
                    }
                }
                break;
            case "unmark":
                entry = Integer.parseInt(input.substring(7));
                if (entry > 0 && entry <= tasks.size()) {
                    tasks.get(entry - 1).setDone(false);
                    ui.showUnmarkMessage();
                    System.out.println(entry + "." + tasks.get(entry - 1));
                    try {
                        storage.writeToFile(tasks);
                    } catch (IOException e) {
                        ui.showSavingError();
                    }
                }
                break;
            case "list":
                ui.showList(tasks);
                break;
            case "todo":
                try {
                    description = parser.parseTodoDescription(input);
                } catch (BobbyException e) {
                    ui.showInvalidTodoMessage();
                    break;
                }
                tasks.add(new Todo(description, false));
                ui.showValidTodoMessage(tasks);
                try {
                    storage.writeToFile(tasks);
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
                tasks.add(new Deadline(description, false, by));
                ui.showValidDeadlineMessage(tasks);
                try {
                    storage.writeToFile(tasks);
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
                tasks.add(new Event(description, false, by, from));
                ui.showValidEventMessage(tasks);
                try {
                    storage.writeToFile(tasks);
                } catch (IOException e) {
                   ui.showSavingError();
                }
                break;
            case "delete":
                entry = Integer.parseInt(input.substring(7));
                if (entry > 0 && entry <= tasks.size()) {
                    ui.showDeleteMessage(tasks, entry);
                    try {
                        storage.writeToFile(tasks);
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
}
