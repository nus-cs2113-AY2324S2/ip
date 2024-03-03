package Storage;
import Task.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Manages the storage of tasks in a file.
 * Allows for reading from and writing to the file, enabling persistence of tasks between sessions.
 */
public class Storage {

    /**
     * Reads tasks from the storage file and returns them as a list.
     *
     * @return An ArrayList of Task objects read from the file.
     * @throws IOException If there's an error reading the file.
     * @throws ArrayIndexOutOfBoundsException If the data in the file is in an unexpected format.
     */
    private ArrayList<Task> listRead() throws IOException, ArrayIndexOutOfBoundsException {
        Files.createDirectories(Paths.get("./data"));
        String path = "./data/Ruby.txt";
        BufferedReader br = new BufferedReader(new FileReader(path));
        ArrayList<Task> taskList = new ArrayList<>();
        String nextLine = br.readLine();
        String[] result;

        while (nextLine != null) {
            result = nextLine.split(" \\| ");
            String taskType = result[0];
            boolean hasDone = Boolean.parseBoolean(result[1]);
            String taskName = result[2];

            switch (taskType){
            case "T":
                taskList.add(new Todo(taskName,hasDone));
                break;
            case "D":
                taskList.add(new Deadline(taskName, hasDone, LocalDate.parse(result[3])));
                break;
            case "E":
                String[] duration = result[3].split(" - ");
                taskList.add(new Event(taskName, hasDone, LocalDate.parse(duration[0]), LocalDate.parse(duration[1])));
                break;
            default:
                break;
            }
            nextLine = br.readLine();
        }
        return taskList;
    }

    /**
     * Writes a list of tasks to the storage file.
     *
     * @param taskList The list of tasks to write to the file.
     * @throws IOException If there's an error writing to the file.
     */
    private void listWrite(ArrayList<Task> taskList) throws IOException {
        Files.createDirectories(Paths.get("./data"));
        FileWriter out = new FileWriter("./data/Ruby.txt", false);
        for (int i=0; i < taskList.size(); i++){
            out.write(taskList.get(i).toString());
            out.write(System.lineSeparator());
        }
        out.close() ;
    }

    /**
     * Public method to save the current list of tasks to the file.
     *
     * @param taskList The list of tasks to be saved.
     */
    public void saveToFile(ArrayList<Task> taskList){
        try{
            listWrite(taskList);
        } catch (IOException e) {
            System.out.println("Sorry, something wrong with my recording function.");
        }
    }

    /**
     * Loads tasks from the file into a list.
     *
     * @return An ArrayList of Task objects loaded from the file.
     */
    public ArrayList<Task> loadFile(){
        try{
            return listRead();
        }catch (IOException e){
            System.out.println("There is no records found.");
            return new ArrayList<Task>();
        }catch (ArrayIndexOutOfBoundsException ee){
            System.out.println("Record read failed.");
            return new ArrayList<Task>();
        }
    }
}
