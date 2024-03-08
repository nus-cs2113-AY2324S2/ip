import seedu.salmonsan.data.TasksList;
import seedu.salmonsan.filing.FileManager;
import seedu.salmonsan.filing.TasksListWriter;
import seedu.salmonsan.parser.Query;
import seedu.salmonsan.parser.QueryManager;
import seedu.salmonsan.ui.UI;

import java.io.IOException;
import java.util.Scanner;

public class SalmonSan {
    private UI ui;
    private TasksList tasksList;
    private FileManager fileManager;

    public static void main(String[] args) {
        new SalmonSan().run();
    }

    public SalmonSan() {
        ui = new UI();
        fileManager = new FileManager();
        try {
            tasksList = fileManager.getTasksList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        ui.welcome();

        // command execution
        Query query = new Query();
        QueryManager queryManager = new QueryManager();
        Scanner in = new Scanner(System.in);
        int status = -1;
        do {
            ui.printDivider();
            ui.askQuery();
            query.changeInput(in.nextLine());

            status = queryManager.executeQuery(query, tasksList);

            // write
            try {
                TasksListWriter.writeTasksListToFile(tasksList);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (status == -1);
    }
}
