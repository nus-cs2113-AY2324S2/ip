package artemis;
import artemis.ui.Messages;
import artemis.ui.UserInterface;

public class Artemis {
    private final UserInterface ui;
    public Artemis(String[] args) {
        this.ui = new UserInterface();
        ui.initialize(args);
        run();
    }
    public void run() {
        this.ui.commandLine();
        exit();
    }

    public void exit() {
        Messages.printGoodbye();
        System.exit(0);
    }

    public static void main(String[] args) {
        new Artemis(args).run();
    }
}
