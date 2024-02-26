package natsu;

import natsu.util.Parser;
import natsu.util.Ui;
import natsu.util.TaskList;

import java.util.Scanner;

import static natsu.util.Storage.readFile;

public class Natsu {

    public void run() {
        Ui.printWelcomeMessage();
        new TaskList();
        try (Scanner input = new Scanner(System.in)) {
            boolean isActive = true;
            readFile();
            while (isActive) {
                String userInput = input.nextLine();
                isActive = Parser.executeCommand(userInput);
            }
        }
    }

    public static void main(String[] args) {
        new Natsu().run();
    }
}
