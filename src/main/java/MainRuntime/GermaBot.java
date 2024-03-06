package MainRuntime;

import Exceptions.*;
import Tasks.*;
import DataHandling.*;
import GermaBot.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GermaBot {
    static ArrayList<Task> toDoList = new ArrayList<>();
    private static UI ui;
    private static Storage storage;

    public GermaBot() {
        storage = new Storage();
        ui = new UI();
        ui.printWelcomeMessage();
        try {
            toDoList = storage.loadFile();
            Task.setNoOfTask(LoadData.getCounter());
            ui.printLoadComplete();
        } catch (FileNotFoundException e) {
            ui.printFileNotFoundException();
        } catch (FileReadException e) {
            ui.printFileReadException();
        }
        ui.printPostLoadingMessage();
    }

    public void run() {
        while (true) {
            String input = ui.getInput();
            if (input.equals("bye")) {
                break;
            }
            try {
                Logic.readCommand(toDoList, input);
            } catch (UnknownInputException e) {
                ui.printUnknownInputException(input);
            }
        }
        ui.printByeMessage();
    }

    public static void main(String[] args) {
        new GermaBot().run();
    }
}
