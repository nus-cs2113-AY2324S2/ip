import Exceptions.*;
import FileManagerPackage.FileManager;
import PrintMessages.Messages;
import Tasks.*;
import commands.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) {
        Messages.printsGreeting();
        startProgramme();
    }

    private static void startProgramme() {
        try {
            ArrayList<Task> list = new ArrayList<>();
            Scanner input = new Scanner(System.in);

            File f = FileManager.getFile();
            Scanner s = new Scanner(f);
            FileManager.readFile(s, list);

            startListening(input, list);
        } catch (ThawException e) {
            handleError(e);
        } catch (IOException e) {
            System.out.println("     An error occurred while reading file: " + e.getMessage());
        }
    }

    private static void startListening(Scanner input, ArrayList<Task> list) throws ThawException {
        boolean canExit = false;
        while (!canExit) {
            String usersInput = input.nextLine();

            if (usersInput.strip().equals("bye")) {
                canExit = true;
                Messages.printGoodByeMessage();
            } else if (usersInput.strip().equals("list")) {
                Messages.printList(list);
            } else {
               editTask(usersInput.strip(), list);
            }
        }
    }

    private static void editTask(String usersInput, ArrayList<Task> task) {
        try {
            if (usersInput.startsWith("mark")) {
                MarkTask.markTask(task, usersInput);
            } else if (usersInput.startsWith("unmark")) {
                UnmarkTask.unmarkTask(task, usersInput);
            } else if (usersInput.startsWith("delete")) {
                DeleteTask.deleteTask(task, usersInput);
            } else if (usersInput.startsWith("todo") || usersInput.startsWith("deadline") || usersInput.startsWith("event")) {
                AddTask.addTask(usersInput, task);
                Messages.printAcknowledgementMessage(task);
            } else {
                throw new ThawException("Invalid command");
            }
        } catch (ThawException e) {
            handleError(e);
        }
        FileManager.saveData(task);
    }

    private static void handleError(ThawException e) {
        if (e.getMessage().startsWith("Empty command")) {
            System.out.println("OOPS!!! The description of a " + e.getMessage().substring(14) +" cannot be empty.");
        } else if (e.getMessage().equals("Invalid command")) {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
