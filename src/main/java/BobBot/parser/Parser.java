package BobBot.parser;

import java.util.Scanner;

import BobBot.storage.Storage;
import BobBot.ui.Ui;
import taskList.TaskList;

public class Parser {

    public static void runTaskManager() {
        String line;
        Scanner in = new Scanner(System.in);

        line = in.nextLine();

        while (!line.equalsIgnoreCase("bye")) {

            try {
                if (line.equalsIgnoreCase("help")) {
                    Ui.printHelpMessage();
                } else if (line.equalsIgnoreCase("list")) {
                    TaskList.displayList();
                } else if (line.startsWith("mark")) {
                    TaskList.performTaskOperation(line, TaskList.TaskStatus.MARK);
                } else if (line.startsWith("unmark")) {
                    TaskList.performTaskOperation(line, TaskList.TaskStatus.UNMARK);
                } else if (line.startsWith("delete")) {
                    TaskList.performTaskOperation(line, TaskList.TaskStatus.DELETE);
                } else {
                    boolean isLoad = false;
                    TaskList.addTask(line, isLoad);
                }
            } catch (NullPointerException | NumberFormatException e) {
                Ui.printStandardExceptionMessage(e);
            }
            Storage.saveFile();
            line = in.nextLine();
        }
    }
}
