import java.util.Scanner;
import java.util.ArrayList;

public class Sam {
    private static final String FILE_PATH = "data/sam.txt";

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Sam() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            taskList = new TaskList(storage.load());
        } catch (SamException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.printGreeting();
        ui.printTasks(taskList);

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (!line.equals("bye")) {
            try {
                ui.printLine();
                Parser.parseCommand(line, taskList);
                storage.saveTasks(taskList.getTasks());
                ui.printLine();
                line = in.nextLine();
            } catch (SamException e) {
                ui.printLine();
                line = in.nextLine();
            }
        }
        ui.printFarewell();
    }

    public static void main(String[] args) {
        Sam sam = new Sam();
        sam.run();
    }
}
