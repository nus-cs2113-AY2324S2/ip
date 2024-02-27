package main;

import java.io.IOException;
import java.util.ArrayList;
import exceptions.AragornException;
import utilities.commands.CommandExecuter;
import utilities.commands.CommandIdentifier;
import ui.Constants;
import utilities.file.FileReader;
import utilities.file.FileWriter;
import tasks.Task;
import java.util.Scanner;

public class Aragorn {

    private static final ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) throws AragornException {
        System.out.println(Constants.HELLOMESSAGE);
        try {
            FileReader.readFile();
        } catch (IOException e) {
            System.out.println(Constants.FILEREADERROR);
        }
        Scanner in = new Scanner(System.in);
        while(true) {
            String userInput;
            userInput = in.nextLine();
            String commandType = CommandIdentifier.commandIdentifier(userInput);
            if (commandType.equals(Constants.BYE)) {
                System.out.println(Constants.BYEMESSAGE);
                FileWriter.writeFile();
                return;
            }
            CommandExecuter.ExecuteCommand(userInput, commandType);
            FileWriter.writeFile();
        }
    }

    public static ArrayList<Task> getList() {
        return list;
    }
}

