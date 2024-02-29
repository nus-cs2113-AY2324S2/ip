package Tony;

import Tony.FileManager.FileLoader;
import Tony.FileManager.FileSaver;
import Tony.command.Command;
import Tony.utility.Parser;
import Tony.utility.Ui;
import Tony.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tony {
    private final ArrayList<Task> tasks;
    private final FileLoader fileLoader;
    private final FileSaver fileSaver;
    public static final String LINE_BREAKER = "__________________________________________________";
    /**
     * Instantiate a new {@code Tony} object.
     */
    public Tony() {
        this.tasks = new ArrayList<>();
        this.fileLoader = new FileLoader(this.tasks);
        this.fileSaver = new FileSaver(this.tasks);
    }
    /**
     * This method runs the Tony chatbot
     * @throws IOException if there is an error creating a File object
     */
    public void run() throws IOException {
        Ui ui = new Ui(tasks);
        ui.printWelcomeMessage();
        System.out.println(LINE_BREAKER);
        fileLoader.checkFileExists();
        Scanner userInput = new Scanner(System.in);
        Parser parser = new Parser(tasks);
        boolean isExit = false;
        while (!isExit) {
            String line = userInput.nextLine();
            System.out.println(LINE_BREAKER);
            Command command = parser.parse(line);
            command.execute(tasks, ui, fileSaver);
            System.out.println(LINE_BREAKER);
            isExit = command.isExit();
        }
        userInput.close();
    }
    /**
     * This is the start of Tony the chatbot.
     * @throws IOException if there is an error creating a File object in the run().
     */
    public static void main(String[] args) throws IOException {
        new Tony().run();
    }
}