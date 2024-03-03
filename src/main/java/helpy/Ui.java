package helpy;

public class Ui {
    public static final String HORIZONTAL_LINE = "______________________\n";
    public static final String name =
            "░▒█░▒█░▒█▀▀▀░▒█░░░░▒█▀▀█░▒█░░▒█\n" +
            "░▒█▀▀█░▒█▀▀▀░▒█░░░░▒█▄▄█░▒▀▄▄▄▀\n" +
            "░▒█░▒█░▒█▄▄▄░▒█▄▄█░▒█░░░░░░▒█░░\n";
    public Ui() {}

    public void printMessage(String message) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }
    public void greetUser() {
        printMessage("Greetings, I am\n" + name + "\nHow can I help you?\n");
    }

    public void sayGoodbye() {
        printMessage("Goodbye, see you next time!");
    }

    public void showInvalidTaskNumErr(String command) {
        printMessage("Task number provided is invalid! " +
                "Did you enter wrongly? You typed: " + command);
    }

    public void showAbsentTaskNumErr(String command) {
        printMessage("Task number doesn't exist! " +
                "Did you enter wrongly? You typed: " + command);
    }
}
