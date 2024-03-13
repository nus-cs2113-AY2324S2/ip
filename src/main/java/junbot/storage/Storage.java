package junbot.storage;

import junbot.common.TaskList;
import junbot.error.InvalidInputException;
import junbot.error.JunBotException;
import junbot.tasks.Task;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class handles the saving and loading of tasks into and from the data file
 */
public class Storage {
    protected String filepath;
    protected TaskList tasks;


    public Storage(String filepath) {
        this.filepath = filepath;
        this.tasks = new TaskList();
    }

    /**
     * Loads all entries from tasks.txt and returns a TaskList containing all imported tasks
     *
     * @return TaskList containing all imported tasks
     * @throws InvalidInputException If any inputs are invalid when importing commands
     */
    public TaskList importCommands(TaskList tasks, File f) throws FileNotFoundException, InvalidInputException {
        Scanner s = new Scanner(f);
        int counter = 0;

        while (s.hasNext()) {
            String[] commands = s.nextLine().split("-");
            switch (commands[0]) {
            case "T":
                tasks.addTodo("todo " + commands[2]);
                break;
            case "D":
                tasks.addDeadline("deadline " + commands[2] +
                        " /by " + commands[3]);
                break;
            case "E":
                tasks.addEvent("event " + commands[2] +
                        " /from " + commands[3] +
                        " /to " + commands[4]);
                break;
            default :
            }

            if (commands[1].equals("X")) {
                tasks.markTaskInList(counter);
            }
            counter += 1;
        }

        return tasks;

    }

    /**
     * Loads tasks from the storage file. Creates a new tasks.txt file if file does not exist.
     *
     * @return The list of tasks loaded from the storage file.
     * @throws IOException If an I/O error occurs while reading the file.
     * @throws InvalidInputException If the input is invalid.
     * @throws JunBotException If there is an issue with the JunBot.
     */
    public ArrayList<Task> load() throws IOException, InvalidInputException, JunBotException {
        File f = new File(filepath);
        if(!f.exists()){
            Path path = Paths.get(filepath);
            Files.createDirectories(path.getParent());
            Files.createFile(path);
            throw new JunBotException();
        }

        this.tasks = importCommands(this.tasks, f);

        return tasks.getTasksList();
    }

    /**
     * Updates the storage file with the provided list of tasks.
     *
     * @param tasks The list of tasks to be written to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void updateFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filepath);
        for (Task task : tasks) {
            String taskType = task.getTag();

            switch (taskType) {
            case "T":
                fw.write(task.getTag() + "-" + task.getStatusIcon() +
                        "-" + task.getDescription() +
                        System.lineSeparator());
                break;
            case "D":
                fw.write(task.getTag() + "-" + task.getStatusIcon() +
                        "-" + task.getDescription() +
                        "-" + task.getEndDate() +
                        System.lineSeparator());
                break;
            case "E":
                fw.write(task.getTag() + "-" + task.getStatusIcon() +
                        "-" + task.getDescription() +
                        "-" + task.getStartDate() +
                        "-" + task.getEndDate() +
                        System.lineSeparator());
                break;

            default:
                break;
            }

        }

        fw.close();
    }
}
