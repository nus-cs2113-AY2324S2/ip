package Tony;

import Tony.FileManager.FileLoader;
import Tony.FileManager.FileSaver;
import Tony.command.Command;
import Tony.utility.Parser;
import Tony.utility.Ui;
import Tony.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Tony {
    private final ArrayList<Task> tasks;
    private final FileLoader fileLoader;
    private final FileSaver fileSaver;
    public static final String LINE_BREAKER = "__________________________________________________"
            + System.lineSeparator();

    public Tony() {
        this.tasks = new ArrayList<>();
        this.fileLoader = new FileLoader(this.tasks);
        this.fileSaver = new FileSaver(this.tasks);
    }

    public void run() throws Exception {
        Ui ui = new Ui(tasks);
        ui.printWelcomeMessage();
        System.out.println(LINE_BREAKER);
        fileLoader.checkFileExists();
        Scanner userInput = new Scanner(System.in);
        Parser parser = new Parser(tasks);
        while (userInput.hasNextLine()) {
            System.out.println(LINE_BREAKER);
            String line = userInput.nextLine();
            Command c = parser.parse(line);
            c.execute(tasks, ui, fileSaver);
            System.out.println(LINE_BREAKER);
        }
        userInput.close();
    }

    public static void main(String[] args) throws Exception {
        new Tony().run();
    }
}