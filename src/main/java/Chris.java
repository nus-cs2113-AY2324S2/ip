import java.util.Scanner;

import task.TaskLists;
import Storage.DataManager;
import exceptions.InputException;
import userInterface.Message;
import command.CommandHandler;

public class Chris {
    public static void main(String[] args) throws InputException {
        System.out.println("Hello from \n" + Message.LOGO);

        Scanner sc = new Scanner(System.in);
        String command = "";
        TaskLists listCommands = new TaskLists();

        DataManager.createFolder();
        DataManager.createText();

        try {
            listCommands = DataManager.readSavedData();
        } catch (InputException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(Message.GREETING);
        while (sc.hasNextLine()) {
            DataManager.saveText(listCommands);
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
