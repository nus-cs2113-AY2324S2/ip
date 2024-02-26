package geepee;

import java.io.File;
import geepee.system.SystemMessage;
import geepee.system.UI;
import geepee.task.list.List;

public class GeePee {

    private static List list = new List("data/data.txt");

    public static void main(String[] args) {
        SystemMessage.printWelcomeMessage();
        new File("data/").mkdir();
        UI.initialiseLoop(list);
        SystemMessage.printExitMessage();
    }
}
