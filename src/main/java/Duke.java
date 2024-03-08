import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private final String filename;
    private final Ui userInterface;
    private final Scanner scanner;

    /**
     * Constructor for Duke.
     *
     * @param filename filename of stored tasks.
     */
     Duke(String filename) {
        this.filename = filename;
        this.userInterface = new Ui();
        this.scanner = new Scanner(System.in);
    }

    /**
     * main driver loop that runs the command line interface
     *
     */
    public void run() {
        TaskList tasks;
        Storage file;
        file = new Storage(filename);
        try{
            tasks = file.decodeTasks();
        } catch (FileNotFoundException ex) {
            tasks = new TaskList();
        }

        while (true) {
            this.userInterface.printInputMessage();
            String line = this.scanner.nextLine();
            if (line.equals("bye")) {
                break;
            }
            if (line.isEmpty()) {
                continue;
            }
            if (line.equals("list")) {
                tasks.printTasks();
                continue;
            }
            try {
                Parser help = new Parser(line, tasks, file);
                Parser newHelp = help.parseParams();
                tasks = newHelp.getTaskList();
                file = newHelp.getStorage();
            } catch (JxExceptions e) {
                System.out.println(e.getMessage());
            }
        }
        userInterface.printLeaveMessage();
    }

    /**
     * main to run the command line interface
     *
     */
    public static void main(String[] args) {
        String filename = "data.txt";
        new Duke(filename).run();
    }
}
