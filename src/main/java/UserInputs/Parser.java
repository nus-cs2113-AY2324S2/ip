package UserInputs;

import Exceptions.ThawException;
import FileManagerPackage.Storage;
import PrintMessages.UI;
import Tasks.Task;
import commands.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    public static void startListening(Scanner input, ArrayList<Task> list, UI ui) throws ThawException {
        boolean canExit = false;
        while (!canExit) {
            String usersInput = input.nextLine();

            if (usersInput.strip().equals("bye")) {
                canExit = true;
                ui.printGoodByeMessage();
            } else if (usersInput.strip().equals("list")) {
                ui.printList(list);
            } else {
               editTask(usersInput.strip(), list, ui);
            }
        }
    }

    private static void editTask(String usersInput, ArrayList<Task> task, UI ui) {
        try {
            if (usersInput.startsWith("mark")) {
                MarkTask.markTask(task, usersInput);
            } else if (usersInput.startsWith("unmark")) {
                UnmarkTask.unmarkTask(task, usersInput);
            } else if (usersInput.startsWith("delete")) {
                DeleteTask.deleteTask(task, usersInput);
            } else if (usersInput.startsWith("todo") || usersInput.startsWith("deadline") || usersInput.startsWith("event")) {
                AddTask.addTask(usersInput, task);
                ui.printAcknowledgementMessage(task);
            } else if (usersInput.startsWith("find")) {
                Find.find(usersInput, task);
            } else {
                throw new ThawException("Invalid command");
            }
        } catch (ThawException e) {
            ui.handleError(e);
        }
        Storage.saveData(task);
    }


}
