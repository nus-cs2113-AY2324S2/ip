package Events;

import Parsers.parsers;
import HikoUi.Ui;
import Commands.*;
import Storage.Store; // Import Store for persistence

public class Echo {

    private Ui ui;
    private Store store; // Add a Store reference for command persistence


    /**
     * Constructs an Echo object with a specified UI and Store.
     *
     * @param ui The Ui object used for interaction with the user.
     * @param store The Store object used for persisting task changes.
     */
    public Echo(Ui ui, Store store) { // Adjust constructor to receive Store
        this.ui = ui;  // Initialize it in the constructor
        this.store = store; // Initialize Store
    }


    /**
     * Starts the main interaction loop of the application. The loop prompts for user commands,
     * processes them, and executes the corresponding actions until an exit command is given.
     *
     * @param tasks The TaskList that contains all tasks and is manipulated by user commands.
     */
    public void HikoStart(TaskList tasks) {
        ui.showHiko();
        ui.sayhi(); // Call on instance `ui`

        Command input;
        do {
            String line = ui.UserInput(); // Use `ui` instance to get user input
            ui.divider(); // Call on instance `ui`
            input = parsers.parse(line, store); // Adjust `parsers` method to accept `Store` and correct method name if necessary
            input.execute(tasks, ui);
            ui.divider(); // Call on instance `ui`
        } while (!input.isExit()); // Check if the command is an exit command

    }
}
