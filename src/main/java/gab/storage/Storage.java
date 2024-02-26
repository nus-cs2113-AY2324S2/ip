package gab.storage;

import gab.exception.GabException;
import gab.task.Task;
import gab.task.TaskList;

import java.io.*;

/**
 * Class storage to save and load data to and from external data file
 */
public class Storage {
    public String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads data from external data file
     * If no data file exists, create a new file according to file path
     *
     * @return taskList that stores the tasks
     * @throws IOException thrown when there is an input/output error
     * @throws GabException thrown when there is a file format error
     */
    public TaskList loadTaskList() throws IOException, GabException {
        TaskList taskList = new TaskList();
        String readLine;

        try {
            BufferedReader bufferReader = new BufferedReader(new FileReader(filePath));
            while ((readLine = bufferReader.readLine()) != null) {
                Task task = Task.loadFromFile(readLine);
                System.out.println(task);
                taskList.addToList(task);
            }
            bufferReader.close();
        } catch (FileNotFoundException e) {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return taskList;
    }

    /**
     * Save task into the external data file in the filepath
     *
     * @param taskList arraylist of tasks
     * @throws GabException thrown when there is an error in saving tasks
     */
    public void saveTask(TaskList taskList) throws GabException {
        try {
            BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(filePath));

            for (Task task : taskList.taskList) {
                bufferWriter.write(task.toFileFormat());
                bufferWriter.newLine();
            }
            bufferWriter.close();
        } catch (IOException e) {
            throw new GabException("Error saving task!");
        }
    }
}
