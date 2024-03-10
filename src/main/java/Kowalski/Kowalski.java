package Kowalski;

import Kowalski.UI.Ui;
import Kowalski.commands.Parser;
import Kowalski.commands.Storage;
import Kowalski.commands.TaskList;

import java.io.IOException;
import java.util.Scanner;

/**
 * Kowalski is a bot for users to keep track of your everyday tasks.
 * It is able to store and retrieve tasks which you need to do, tasks with deadlines and events important to users.
 * It allows you to easily add, remove tasks, or even mark and unmark tasks.
 * It also allows users to find previous tasks based on their inputs.
 */
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