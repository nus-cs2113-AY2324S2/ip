package utilities.commands;

import main.Aragorn;
import ui.Constants;

public class DisplayList {
    public static void listCommand() {
        if (Aragorn.getList().isEmpty()) {
            System.out.println(Constants.EMPTYLIST);
            return;
        }
        System.out.println(Constants.LINE);
        System.out.println(Constants.CURRENTLIST);
        for (int i = 0; i < Aragorn.getList().size(); i += 1) {
            System.out.println(Constants.TAB + (i + 1) + Constants.DOT + Aragorn.getList().get(i).taskString());
        }
        System.out.println(Constants.NEWLINE);
    }
}
