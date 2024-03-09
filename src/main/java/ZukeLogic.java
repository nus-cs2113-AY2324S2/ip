import java.util.Scanner;

import command.Command;
import command.CommandFactory;
import task.TaskList;
import storage.DataManager;
import ui.ResponseManager;
import exception.ZukeException;

/**
 *  The ZukeLogic class represents the main class that runs the initialization and chat iteration for the Zuke chatbot.
 */
public class ZukeLogic {
    private TaskList taskList;

    ZukeLogic() {
        taskList = new TaskList();
    }

    private void loadData() {
        DataManager.createFolder();
        DataManager.createFile();
        try {
            this.taskList = DataManager.readSavedData();
        } catch (ZukeException error) {
            ResponseManager.indentPrint(error.getMessage());
        }
    }

    public static ZukeLogic initZuke() {
        ZukeLogic zukeBot = new ZukeLogic();
        ResponseManager.greet();
        zukeBot.loadData();
        return zukeBot;
    }

    public void chattingStart() {
        Scanner userInput = new Scanner(System.in);
        boolean exitFlag = false;

        while (!exitFlag) {
            DataManager.saveData(taskList);
            try {
                Command command = CommandFactory.generate(userInput.nextLine());
                command.run(taskList);
                exitFlag = command.isExit();
            } catch (ZukeException error) {
                ResponseManager.indentPrint(error.getMessage());
            }
        }
        userInput.close();
    }
}
