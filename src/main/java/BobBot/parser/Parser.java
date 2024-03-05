package BobBot.parser;

import java.util.Scanner;

import BobBot.BobBot;
import BobBot.storage.Storage;
import taskList.TaskList;

public class Parser {

    public static void runTaskManager() {
        String line;
        Scanner in = new Scanner(System.in);

        line = in.nextLine();

        while (!line.equalsIgnoreCase("bye")) {

            try {
                if (line.equalsIgnoreCase("help")) {
                    BobBot.printHelpMessage();
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
                BobBot.printStandardExceptionMessage(e);
            }
            Storage.saveFile();
            line = in.nextLine();
        }
    }
}
