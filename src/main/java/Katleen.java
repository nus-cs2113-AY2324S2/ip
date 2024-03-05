import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Katleen {
    public static void main(String[] args) throws IncompleteTaskException, UnrecognizedCommandException, IOException {
        new Katleen().run();
    }
    public void run() throws IOException {
        Ui.GreetUser();
        generalLoop();
        Ui.endConversation();

    }
    private TaskList taskList;
    private final Ui ui;

    public Katleen() {
        ui = new Ui();
    }
    private void generalLoop() throws IOException {
        String userInput = "";
        boolean done = false;
        do {
            userInput = ui.getUserInput();
            if (!userInput.equals("bye")) { //Does not say "added" if closing app
            }
            done = Parser.parseCommand(userInput, false);

        } while (!done);
    }
}
