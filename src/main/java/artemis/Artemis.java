package artemis;
import artemis.ui.UserInterface;

public class Artemis {
    public static void main(String[] args) {
        UserInterface.printBanner();
        UserInterface.requestUsername();
        UserInterface.printHelp();
        UserInterface.commandLine();
    }
}
