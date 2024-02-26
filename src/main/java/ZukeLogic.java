import java.util.Scanner;

import command.Command;
import command.CommandFactory;
import task.TaskList;
import tool.DataManager;
import tool.ResponseManager;
import exception.InputException;

public class ZukeLogic {
    private TaskList taskList;

    ZukeLogic() {
        taskList = new TaskList();
    }

    public static ZukeLogic initZuke() {
        ResponseManager.greet();
        return new ZukeLogic();
    }

    public void chattingStart() {
        Scanner userInput = new Scanner(System.in);
        DataManager.createFolder();
        DataManager.createTextFile();
        loadData();
        boolean exitFlag = false;

        while (!exitFlag) {
            DataManager.saveData(taskList);
            try {
                Command command = CommandFactory.generate(userInput.nextLine());
                command.run(taskList);
                exitFlag = command.isExit();
            } catch (InputException error) {
                ResponseManager.indentPrint(error.getMessage());
            }
        }
    }

    private void loadData() {
        try {
            this.taskList = DataManager.readSavedData();
        } catch (InputException error) {
            ResponseManager.indentPrint(error.getMessage());
        }
    }
}
