import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public TaskList loadTasks() throws PhoebeException {
        TaskList tasks = new TaskList();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                Task task = Parser.parseTaskFromString(data);
                if (task != null) {
                    TaskList.addTasktotasks(task);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            Ui.printFileNotFound();
        } catch (Exception e) {
            Ui.printCorrupted();
        }
        return tasks;
    }


    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter myWriter = new FileWriter("phoebe.txt");
        for (Task task : tasks) {
            myWriter.write(task.toFileFormat() + System.lineSeparator());
        }
        myWriter.close();
    }
}
