package timl;

import timl.task.TaskManager;
import timl.utility.CommandHandler;
import timl.utility.Printer;
import timl.utility.FileManager;
import java.io.IOException;
import java.util.Scanner;
import java.lang.String;

/**
 * The main class of chatbot TimL
 */
public class TimL {
    public static void main(String[] args) {
        Printer.printGreeting();
        String response;
        Scanner in = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        FileManager.checkAndReadFile(taskManager);
        response = in.nextLine();
        while (!response.equals("bye")){
            CommandHandler.respondToCommand(response);
            in = new Scanner(System.in);
            try {
                FileManager.exportData();
            } catch (IOException e){
                Printer.printIOException();
            }
            response = in.nextLine();
        }
        Printer.printGoodbye();
    }
}
