package huan.main;
import huan.task.*;

import java.util.ArrayList;
import java.util.List;
public class Huan {

    public static void main(String[] args) {
        String botName = "Huan";
        UI.displayWelcomeMessage();

        Storage.readFile();

        Parser.parseCommands();
    }

}
