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
        try {
            startProgramme();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private static void startProgramme() throws FileNotFoundException {
        try {
            ArrayList<Task> list = new ArrayList<>();
            Scanner input = new Scanner(System.in);

            File f = FileManager.getFile();
            Scanner s = new Scanner(f);
            FileManager.readFile(s, list);

            startListening(input, list);
        } catch (IOException e) {
            System.out.println("     An error occurred while reading file: " + e.getMessage());
        }
    }

    private static void startListening(Scanner input, ArrayList<Task> list) {
        boolean canExit = false;
        while (!canExit) {
            String usersInput = input.nextLine();

            if (usersInput.equals("bye")) {
                canExit = true;
                Messages.printGoodByeMessage();
            } else if (usersInput.equals("list")) {
                Messages.printList(list);
            } else if (commandsWithDesc(usersInput)) {
                editTask(usersInput, list);
            }
        }
    }

    private static void editTask(String usersInput, ArrayList<Task> task) {
        int taskIndex;
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
            }
            else {
                System.out.println("Error with editing Task");
            }
            FileManager.saveData(task);
        }
        catch (ThawException e) {
            handleError(e);
        }
        catch (IOException e) {
            System.out.println("Error with saving");
        }

    }

    private static void handleError(ThawException e) {
        if (e.getMessage().startsWith("Empty command")) {
            System.out.println("OOPS!!! The description of a " + e.getMessage().substring(14) +" cannot be empty.");
        } else if (e.getMessage().equals("Invalid command")) {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static boolean commandsWithDesc(String usersInput) {
        return (usersInput.startsWith("unmark") && !usersInput.strip().endsWith("unmark"))      ||
                (usersInput.startsWith("mark") && !usersInput.strip().endsWith("unmark"))       ||
                (usersInput.startsWith("delete") && !usersInput.strip().endsWith("delete"))     ||
                (usersInput.startsWith("todo") && !usersInput.strip().endsWith("todo"))         ||
                (usersInput.startsWith("deadline") && !usersInput.strip().endsWith("deadline")) ||
                (usersInput.startsWith("event") && !usersInput.strip().endsWith("event"));
    }
}
