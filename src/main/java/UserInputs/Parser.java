package UserInputs;

import Exceptions.ThawException;
import FileManagerPackage.Storage;
import PrintMessages.UI;
import Tasks.Task;
import commands.AddTask;
import commands.DeleteTask;
import commands.MarkTask;
import commands.UnmarkTask;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
            } else {
                throw new ThawException("Invalid command");
            }
        } catch (ThawException e) {
            ui.handleError(e);
        }
        Storage.saveData(task);
    }


    public static LocalDate processDate(String inputDateTimeString) {
        String inputDateString = inputDateTimeString.strip();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return LocalDate.parse(inputDateString, inputFormatter);
    }

    public static LocalTime processTime(String inputDateTimeString) {
        String inputTimeString = inputDateTimeString.strip();
        DateTimeFormatter inputTimeFormatter = DateTimeFormatter.ofPattern("HHmm");
        return LocalTime.parse(inputTimeString, inputTimeFormatter);
    }
}
