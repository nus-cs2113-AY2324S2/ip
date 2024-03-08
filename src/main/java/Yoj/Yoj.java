package Yoj;

import Yoj.exception.InvalidCommandException;
import Yoj.exception.YojException;
import Yoj.parser.Parser;
import Yoj.storage.Storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import Yoj.taskList.List;
import Yoj.ui.*;


public class Yoj {

    public static void main(String[] args) throws YojException, InvalidCommandException, FileNotFoundException {
        Ui.printHello();
        // get user input
        Scanner in = new Scanner(System.in);
        List.tasks = Storage.loadTasks();
        String userInput;
        do {
            userInput = in.nextLine();
            try {
                Parser.manageUserInput(userInput);
            } catch (YojException e){
                System.out.println(e.getMessage());
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (!userInput.equals("bye"));
        in.close();

    }
}
