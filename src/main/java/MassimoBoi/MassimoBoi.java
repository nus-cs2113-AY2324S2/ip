package MassimoBoi;


import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import MassimoBoiException.DeadlineException;
import MassimoBoiException.EmptyDeadline;
import MassimoBoiException.EmptyEvent;
import MassimoBoiException.EmptyToDo;
import MassimoBoiException.EventException;
import MassimoBoiException.MassimoBoiException;
import MassimoBoiException.NoDueDate;
import MassimoBoiException.NoEventEnd;
import MassimoBoiException.NoEventStart;
import MassimoBoiException.UnknownCommandType;

public class MassimoBoi {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    public MassimoBoi() {
        this.ui = new Ui();
        //List<Task> taskList = new ArrayList<>();

        this.storage = new Storage();
        try {
            this.tasks = new TaskList(storage.loadList());
        } catch (FileNotFoundException e) {
            System.out.println("The file does not exist. Please create it.");
            this.tasks = new TaskList();
        }

        ui.initialiseUi();
        ui.printGreetingMessage();
        ui.makeHorizontalRow();
    }

    public void run() throws IOException {
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
                Parser parser = new Parser(userInput, tasks);
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



