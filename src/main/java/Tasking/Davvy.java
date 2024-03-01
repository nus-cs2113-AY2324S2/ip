package Tasking;

import NewExceptions.EmptyArgumentException;
import NewExceptions.EmptyStatementException;
import Storage.Storage;
import Parser.Parser;
import Ui.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Davvy {

    public static void writeData(Task task) throws IOException {
        int status = task.isDone ? 1 : 0;
        String data = "";
        // Formatting data into different string to be saved in file
        if (task instanceof Todo) {
            data = "T | " + status + " | " + task.description;
        } else if (task instanceof Events) {
            data = "E | " + status + " | " + task.description + " | "
                    + ((Events) task).dueFrom + " | " + ((Events) task).dueAt;
        } else if (task instanceof Deadline) {
            data = "D | " + status + " | " + task.description + " | "
                    + ((Deadline) task).dueDate;
        }
        Storage.writeToFile(data);
    }

    public static void handleDeadline(String commandArg) throws IOException {
        String[] newCommandArg = commandArg.split("/by", 2);
        Deadline inputDeadline = new Deadline(newCommandArg[0], newCommandArg[1]);
        TaskList.addTask(inputDeadline,false);
    }

    public static void handleEvent(String commandArg) throws IOException {
        String[] newCommandArg = commandArg.split("/from", 2);
        String[] newCommandArg2 = newCommandArg[1].split("/to", 2);
        Events inputEvent = new Events(newCommandArg[0], newCommandArg2[0], newCommandArg2[1]);
        TaskList.addTask(inputEvent, false);
    }

    public void startChat() {
        Ui.printStatement("greetings");
        boolean isExitStatus = false;
        Scanner in = new Scanner(System.in);
        try {
            Storage.readFile();
            while (!isExitStatus) {
                try {
                    String[] parsedInput = Parser.processInput(in.nextLine());
                    isExitStatus = Parser.processCommand(parsedInput);
                } catch (EmptyStatementException e) {
                    Ui.printLine();
                    System.out.println("Please type something >:(");
                } catch (NumberFormatException e) {
                    System.out.println("Exception thrown: " + e.getMessage());
                    System.out.println("Please Enter A Valid Number");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Exception thrown: " + e.getMessage());
                    System.out.println("There is no such task!");
                } catch (EmptyArgumentException e) {
                    System.out.println("Please enter a proper argument >:(");
                } finally {
                    Ui.printLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            Ui.printLine();
        }
    }

    public void endChat() {
        Ui.printStatement("goodbye");
    }

}
