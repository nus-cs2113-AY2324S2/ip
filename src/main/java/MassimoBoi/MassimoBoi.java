package MassimoBoi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import MassimoBoiException.MassimoBoiException;

/**
 * Represents the MassimoBoi chatbot that users can interact with.
 * Users can interact using specific commands.
 */
public class MassimoBoi {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Creates an instance of the Massimo Boi chatbot.
     *
     * @param filename String consisting the name of the file containing users past task list.
     */
    public MassimoBoi(String filename) {
        this.ui = new Ui();
        //List<Task> taskList = new ArrayList<>();

        this.storage = new Storage(filename);
        try {
            this.tasks = new TaskList(storage.loadList());
        } catch (FileNotFoundException e) {
            System.out.println("The file does not exist. Please create it.");
            this.tasks = new TaskList();
        }

        ui.printGreetingMessage();
        ui.printUserGuideMessage();
        ui.makeHorizontalRow();
    }

    /**
     * The driver function for the chatbot.
     *
     * @throws IOException when the file cannot be written to or does not exist and cannot be created.
     */
    private void run() throws IOException {
        String userInput = "";
        Scanner myObj = new Scanner(System.in);
        while (true) {
            userInput = myObj.nextLine();
            if (userInput.equals("bye")) {
                storage.addToFile(tasks);
                ui.printGoodbyeMessage();
                ui.makeHorizontalRow();
                break;
            } else if (userInput.equals("list")) {
                tasks.printList();
            }
            try {
                Parser parser = new Parser(userInput, tasks, ui);
                tasks = parser.handleInput();
            } catch (MassimoBoiException e) {
                e.errorMessage();
            }
        }
    }

    /**
     * The function that runs the CLI.
     */
    public static void main(String[] args) throws IOException {
        new MassimoBoi("./src/main/java/list.txt").run();
    }
}



