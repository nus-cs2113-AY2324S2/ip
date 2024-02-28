package Tony;

import Tony.FileManager.FileLoader;
import Tony.FileManager.FileSaver;
import Tony.utility.Parser;
import Tony.utility.Ui;
import Tony.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static Tony.utility.Ui.LINE_BREAKER;

public class Tony {
    private final ArrayList<Task> tasks;
    private final FileSaver fileSaver;
    private final FileLoader fileLoader;

    public Tony() {
        this.tasks = new ArrayList<>();
        this.fileSaver = new FileSaver(this.tasks);
        this.fileLoader = new FileLoader(this.tasks);
    }

    public void run() throws Exception {
        Ui ui = new Ui(tasks);
        ui.printWelcomeMessage();
        fileLoader.checkFileExists();
        Scanner userInput = new Scanner(System.in);
        Parser parser = new Parser(tasks);
        while (userInput.hasNextLine()) {
            String line = userInput.nextLine();
            if (line.equals("bye")) {
                ui.printByeMessage();
                return;
            } else if (line.equals("list")) {
                ui.printTaskList();
            } else if (line.contains("mark")) {
                try {
                    String[] subCommand = line.split(" ");
                    int num = Integer.parseInt(subCommand[1]);
                    parser.checkNumberWithinRange(num);
                    parser.markTaskCommand(subCommand, num);
                } catch (NumberFormatException nfe) {
                    System.out.println("Suggest only number after 'mark'!");
                } catch (TonyException e) {
                    System.out.println("To mark a task, suggest a number available in the list!");
                }
            } else if (line.startsWith("todo") || line.startsWith("deadline")
                    || line.startsWith("event")) {
                parser.checkFirstWordOfTaskCommand(line);
            } else if (line.startsWith("delete")) {
                parser.deleteTaskCommand(line);
            } else {
                parser.checkFirstCommandWord();
            }
        }
    }
    public static void main(String[] args) throws Exception {
        new Tony().run();
    }


}