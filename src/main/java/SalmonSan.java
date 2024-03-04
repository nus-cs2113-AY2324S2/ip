import seedu.salmonsan.data.TasksList;
import seedu.salmonsan.filing.FileManager;
import seedu.salmonsan.filing.TasksListWriter;
import seedu.salmonsan.ui.Query;
import seedu.salmonsan.ui.QueryManager;

import java.io.IOException;
import java.util.Scanner;

public class SalmonSan {
    public static void main(String[] args) {
        // file management
        FileManager fileManager = new FileManager();
        TasksList tasksList = null;
        try {
            tasksList = fileManager.getTasksList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Salmon-San desu! \nYoroshikuonegaishimasu (^.^)/");
        // command execution
        Query query = new Query();
        QueryManager queryManager = new QueryManager();
        Scanner in = new Scanner(System.in);
        int status = -1;
        do {
            System.out.println("---");
            System.out.print("How can I assist you today? ");
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
