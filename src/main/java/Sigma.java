import java.io.FileNotFoundException;

import storage.Storage;
import ui.UI;

public class Sigma {

    public static void main(String[] args) {
        //Load file at the start
        try {
            Storage.loadFile();
            System.out.println("Done :D");
        } catch (FileNotFoundException e) {
            System.out.println("No file found to load!");
        }

        UI.greetUser();
        //Takes user inputs
        boolean terminate = false;
        while (!terminate) {
            terminate = UI.getUserInput();
        }
        UI.sayGoodBye();
    }
}

