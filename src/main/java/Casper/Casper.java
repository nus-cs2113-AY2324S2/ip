package Casper;

import java.util.Scanner;

public class Casper {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;
    private static final String[] keywordList = {"bye", "list", "mark", "unmark",
            "deadline", "event", "todo", "delete", "find"};
    private static final String pathToSaveDirectory = "./save/";
    private static final String saveFilename= "savedCasper.txt";

    public Casper(String pathToSaveDirectory, String saveFilename){
        ui = new Ui();
        storage = new Storage(pathToSaveDirectory, saveFilename, ui);
        tasks = new TaskList(storage.loadSaveFile());
    }

    private void run(){
        ui.echoGreetings();
        Scanner inputScanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            String userInput = inputScanner.nextLine();
            try{
                Command command = Parser.parse(userInput);
                command.execute(ui, tasks, storage);
                isRunning = command.isRunning;
            } catch (CasperUnrecognizedKeywordException e){
                ui.unrecognizedKeywordException(keywordList, e);
            }
        }
    }

    public static void main(String[] args) {
        new Casper(pathToSaveDirectory, saveFilename).run();
    }
}
