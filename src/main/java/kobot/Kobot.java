package kobot;

import kobot.task.TaskList;

import java.util.Scanner;

public class Kobot {
    TaskList taskList;
    Ui ui;
    Scanner in;
    Command command;
    
    public Kobot() {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.in = new Scanner(System.in);
        this.command = new Command();
    }
    
    public void run() {
        ui.printHelloMessage();

        while (!command.getIsExit()) {
            String input = ui.receiveInput(in);
            command.parseCommand(input);
            command.executeCommand(taskList, ui);
        }

        ui.printGoodbyeMessage();
    }
}
