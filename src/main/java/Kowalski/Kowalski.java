package Kowalski;

import Kowalski.UI.Ui;
import java.io.IOException;
import java.util.Scanner;

public class Kowalski {
    public static Scanner in = new Scanner (System.in);

    public static void main(String[] args) throws IOException {
        Ui.printIntro();
        Storage.readTextFile(TaskList.currentTask);
        String userCommand = TaskList.processInput(in.next());
        while (!(userCommand.equals("bye"))){
            Parser.parseUserCommand(TaskList.processInput(userCommand), TaskList.currentTask, in);
            userCommand = in.next();
        }
        Ui.printEndConversation();
    }
}