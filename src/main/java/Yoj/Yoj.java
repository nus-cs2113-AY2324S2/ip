package Yoj;

import Yoj.exception.InvalidCommandException;
import Yoj.exception.YojException;
import Yoj.storage.Storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import Yoj.ui.*;

import static Yoj.List.List.*;
import Yoj.List.List.*;

public class Yoj {

    public static void main(String[] args) throws YojException, InvalidCommandException, FileNotFoundException {
        Ui.printHello();
        // get user input
        Scanner in = new Scanner(System.in);
        tasks = Storage.loadTasks();
        String userInput;
        do {
            userInput = in.nextLine();
            try {
                manageUserInput(userInput);
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
