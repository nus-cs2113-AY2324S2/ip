import java.io.*;
import java.util.Scanner;
import java.io.File;

/**
 * Loads the save file into the list of tasks. If no save file exists yet, Storage will create one.
 */
public class Storage {
    private static File file;
    private static String filePath = "./data/tickles.txt";

    public Storage(String fp) {
        fileWithDirectoryAssurance("data", "tickles.txt");
        String filePath = fp;
    }

    /**
     * Loads the data from the save file into the Task List.
     */
    public void loadTickles() {
        TaskList taskList = new TaskList();
        try {
            Scanner in = new Scanner(new FileReader(filePath));
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] lineData = line.split("\\|");
                Task task = null;
                switch (lineData[0]) {
                case "[T]":
                    task = new Todo(lineData[2]);
                    break;
                case "[D]":
                    task = new Deadline(lineData[2], lineData[3]);
                    break;
                case "[E]":
                    task = new Event(lineData[2], lineData[3], lineData[4]);
                    break;
                default:
                    throw new StorageException();
                }
                if (lineData[1].equals("X")) {
                    task.markAsDone();
                }
                TaskList.list.add(task);
                TaskList.totalTasks += 1;
                }
        } catch (FileNotFoundException | StorageException e) {
            file = new File("./data/tickles.txt");
        }
    }

    /**
     * Ensures the directory exists to store the save data in.
     * @param directory name of the directory
     * @param fileName name of the file
     * @return the file that was just created, in case it needs to be referenced later.
     */
    private static File fileWithDirectoryAssurance(String directory, String fileName) {
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return new File("./" + directory + "/" + fileName);
    }

    /**
     * Stores the data currently in Task List into the save file, to be used if Tickles is started up again later.
     */
    public static void save() {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(filePath, false);
            for (Task t : TaskList.list) {
                switch (t.type()) {
                case "[D]":
                    fileWriter.write(t.type() + "|" + t.getStatusIcon() + "|" + t.getDescription() + "|" +
                            ((Deadline)t).by);
                    break;
                case "[T]":
                    fileWriter.write(t.type() + "|" + t.getStatusIcon() + "|" + t.getDescription());
                    break;
                case "[E]":
                    fileWriter.write(t.type() + "|" + t.getStatusIcon() + "|" + t.getDescription() + "|" +
                            ((Event)t).start + "|" + ((Event)t).end);
                    break;
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}

