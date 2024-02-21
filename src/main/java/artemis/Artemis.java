package artemis;
import artemis.errors.Errors;
import artemis.ui.UserInterface;
import artemis.processing.Save;

import java.io.FileNotFoundException;

public class Artemis {
    public static void main(String[] args) {
        UserInterface.printBanner();

        try {
            Save.loadSave();

        } catch (Errors.CorruptedSaveException e) {
            System.out.println("[artemis]: there seems to be an error when loading saved data."
                    + " not all data may be available");
        } catch (FileNotFoundException e) {
            UserInterface.requestUsername();
        }

        UserInterface.printHelp();
        UserInterface.commandLine();
    }
}
