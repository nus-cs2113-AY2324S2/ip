import java.io.*;

public class Storage {
    public String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
    }

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
