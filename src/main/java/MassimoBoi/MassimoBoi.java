package MassimoBoi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import MassimoBoiException.MassimoBoiException;


public class MassimoBoi {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    public MassimoBoi() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            this.tasks = new TaskList(storage.loadList());
        } catch (FileNotFoundException e) {
            System.out.println("The file does not exist. Please create it in the java directory and name it list.txt");
            this.tasks = new TaskList();
        }

        ui.initialiseUi();
        ui.printGreetingMessage();
        ui.printHorizontalRow();
    }

    /**
     *
     * @throws IOException
     */
    public void run() throws IOException {
        String userInput = "";
        Scanner myObj = new Scanner(System.in);
        while (true) {
            userInput = myObj.nextLine();
            if (userInput.equals("bye")) {
                storage.addToFile(tasks);
                ui.printGoodbyeMessage();
                ui.printHorizontalRow();
                break;
            }
            try {
                Parser parser = new Parser(userInput, tasks, ui);
                tasks = parser.handleInput();
            } catch (MassimoBoiException e) {
                e.errorMessage();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        new MassimoBoi().run();
    }
}



