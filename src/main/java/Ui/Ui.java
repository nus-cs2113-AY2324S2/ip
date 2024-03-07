package Ui;

/**
 * A class that deals with interactions with the user.
 */
public class Ui {
    /**
     * Greet the user when the program starts.
     *
     * @param botName The name of the chatbot.
     */
    public void greet(String botName) {
        PrintText.printWithLinebreak("Hello! I'm " + botName + "\n" +
                "What can I do for you?");
    }

    /**
     * Say goodbye to the user when exit the program.
     */
    public void bye() {
        PrintText.printWithLinebreak("Bye. Hope to see you again soon!");
    }
}
