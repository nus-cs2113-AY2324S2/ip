package timl;

import timl.exceptions.EmptyException;
import timl.exceptions.TimException;
import timl.task.TaskManager;
import timl.utility.Printer;
import timl.utility.TextParser;
import timl.utility.FileManager;
import java.io.IOException;
import java.util.Scanner;
import java.lang.String;

public class TimL {
    public static void respondToCommand(String response){
        String command = TextParser.extractCommand(response);
        String message = TextParser.extractMessage(response);
        int taskIndex;
        switch (command) {
            case "list":
                TaskManager.printList();
                break;
            case "mark":
                try {
                    taskIndex = Integer.parseInt(message) - 1;
                    TaskManager.mark(taskIndex);
                }catch (NumberFormatException | TimException e){
                    Printer.printInvalidNumber();
                }
                break;
            case "unmark":
                try {
                    taskIndex = Integer.parseInt(message) - 1;
                    TaskManager.unMark(taskIndex);
                }catch (NumberFormatException | TimException e){
                    Printer.printInvalidNumber();
                }
                break;
            case "todo":
                try {
                    TaskManager.addToDo(message);
                }catch (ArrayIndexOutOfBoundsException e){
                    Printer.printTaskOverflow();
                }catch (EmptyException e){
                    Printer.printEmptyTodoCommand();
                }
                break;
            case "deadline":
                try{
                    TaskManager.addDeadline(message);
                }catch (ArrayIndexOutOfBoundsException e){
                    Printer.printTaskOverflow();
                }catch (TimException | IndexOutOfBoundsException e) {
                    Printer.printInvalidDeadline();
                }
                break;
            case "event":
                try {
                    TaskManager.addEvent(message);
                }catch (ArrayIndexOutOfBoundsException e) {
                    Printer.printTaskOverflow();
                } catch (TimException | IndexOutOfBoundsException e) {
                    Printer.printInvalidEvent();
                }
                break;
            case "delete":
                try{
                    taskIndex = Integer.parseInt(message) - 1;
                    TaskManager.delete(taskIndex);
                }catch (NumberFormatException | TimException e){
                    Printer.printInvalidDelete();
                }
                break;
            default:
                Printer.printDefaultError();
                break;
        }
    }



    public static void main(String[] args) {
        Printer.printGreeting();
        String response;
        Scanner in = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        FileManager.checkAndReadFile(taskManager);
        response = in.nextLine();
        while (!response.equals("bye")){
            respondToCommand(response);
            in = new Scanner(System.in);
            response = in.nextLine();
        }
        try {
            FileManager.exportData();
        } catch (IOException e){
            Printer.printIOException();
        }
        Printer.printGoodbye();
    }
}
