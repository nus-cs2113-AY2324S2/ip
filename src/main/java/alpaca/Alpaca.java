package alpaca;

import alpaca.file.FileReader;
import alpaca.logic.LogicManager;
import alpaca.UI.ResponseManager;
import alpaca.UI.InputManager;

public class Alpaca {
    public static void startConversation() {
        ResponseManager responseManager = new ResponseManager();
        LogicManager logicManager = new LogicManager(responseManager);
        InputManager inputManager = new InputManager();

        responseManager.printGreeting();
        FileReader.startFileReader();
        inputManager.listenForInput(logicManager);
    }

    public static void main(String[] args) {
        startConversation();
    }
}
