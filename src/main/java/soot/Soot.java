package soot;

import soot.manager.CommandManager;
import soot.GreetingManager;
import java.util.Scanner;

public class Soot {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        CommandManager commandManager = new CommandManager();

        boolean isBye = false;

        GreetingManager.drawLine(); //initial greeting
        GreetingManager.greetUser();

        while (!isBye) {
            line = in.nextLine(); //user input
            GreetingManager.drawLine();
            isBye = commandManager.isInputBye(line);
        }
        GreetingManager.greetGoodbye();
    }
}
