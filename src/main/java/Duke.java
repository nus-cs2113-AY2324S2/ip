import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private final String filename;
    private final Ui userInterface;
    private final Scanner scanner;

     Duke(String filename) {
        this.filename = filename;
        this.userInterface = new Ui();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        TaskList tasks;
        Storage file;
        try {
            file = new Storage(filename);
            tasks = file.decodeTasks();
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
                Parser help = new Parser(line, tasks, file);
                Parser newHelp = help.parseParams();
                tasks = newHelp.getTaskList();
                file = newHelp.getStorage();
            }
            userInterface.printLeaveMessage();
        } catch (IOException ex) {
            System.out.println("Error when handling files " + ex.getMessage());
            throw new RuntimeException(ex);
        } catch (JxExceptions e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        String filename = "data.txt";
        new Duke(filename).run();
    }
}
