import roleypoley.command.HandleCommand;
import roleypoley.data.ReadFile;
import roleypoley.exception.RoleyPoleyFileException;
import roleypoley.exception.RoleyPoleyParseException;
import roleypoley.ui.TextUi;

import roleypoley.task.Task;

import java.util.Scanner;
import java.util.ArrayList;

public class RoleyPoley {

    public static void main(String[] args) throws RoleyPoleyFileException {
        ReadFile.readFileToArrayList();
        run();
    }

    public static void run() {
        TextUi.welComeMessage();
        runCommandLoopUntilExitCommand();
    }

    private static void runCommandLoopUntilExitCommand() {
        boolean isExit;
        do {
            String command = TextUi.getUserInput();
            isExit = HandleCommand.executeCommand(command);
        } while (!isExit);
    }
}




