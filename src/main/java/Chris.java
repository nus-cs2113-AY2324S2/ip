import java.util.Scanner;

import task.TaskLists;
import task.DataManage;
import exceptions.InputException;
import userInterface.Message;
import command.CommandHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Chris {
    public static void main(String[] args) throws InputException {
        System.out.println("Hello from \n" + Message.LOGO);

        Scanner sc = new Scanner(System.in);
        String command = "";
        TaskLists listCommands = new TaskLists();

        DataManage.createFolder();
        DataManage.createText();

        try {
            listCommands = DataManage.readSavedData();
        } catch (InputException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(Message.GREETING);
        while (sc.hasNextLine()) {
            DataManage.saveData(listCommands);
            try {
                command = sc.nextLine();
                boolean continueFlag = CommandHandler.handle(command, listCommands);
                if (!continueFlag) {
                    break;
                }
            } catch (InputException e) {
                System.out.print(Message.DASH);
                System.out.println(e.getMessage());
                System.out.println(Message.DASH);
            }
        }
        sc.close();
    }
}
