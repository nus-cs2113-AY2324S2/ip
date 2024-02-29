package Casper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all functionalities that relate to saving and loading data from/to a text file.
 */
public class Storage {
    private final String pathToSaveDirectory;
    private ArrayList<Task> savedTaskList = new ArrayList<>();
    private final String saveFilename;
    private final String filePath;
    private Ui ui;

    public Storage(String pathToSaveDirectory, String saveFilename, Ui ui){
        this.pathToSaveDirectory = pathToSaveDirectory;
        this.saveFilename = saveFilename;
        this.filePath = pathToSaveDirectory + saveFilename;
        this.ui = ui;
    }

    private void writeToFile(ArrayList<Task> taskList) {
        try{
            FileWriter fw = new FileWriter(filePath);
            for(Task task : taskList){
                fw.write(task.saveFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e){
            ui.wrapEchoMessage("Something went wrong: "+ e.getMessage());
        }
    }

    private void handleTaskStringToTaskObject(String[] formatInputList){
        String taskType = formatInputList[0];
        Task newTask;
        if(taskType.equals("D")){
            newTask = new Deadline(formatInputList[2], formatInputList[3]);
        }else if (taskType.equals("E")){
            newTask = new Event(formatInputList[2], formatInputList[3], formatInputList[4]);
        }else{
            newTask = new Todo(formatInputList[2]);
        }
        if(formatInputList[1].equals("1")){
            newTask.isDone = true;
        }
        savedTaskList.add(newTask);
    }

    /**
     * Returns a list of tasks generated from the save file, includes
     * handling the event of an undetected/missing directory.
     *
     * @return <code>ArrayList</code> of <code>Task</code>
     */
    public ArrayList<Task> loadSaveFile(){
        try{
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()){
                String[] formatInputList = s.nextLine().split(" # ");
                handleTaskStringToTaskObject(formatInputList);
            }
        } catch (FileNotFoundException e){
            handleMissingDirectory();
        }
        return savedTaskList;
    }

    private void handleMissingDirectory(){
        Path absolutePath = Paths.get(pathToSaveDirectory).toAbsolutePath();
        try{
            Files.createDirectory(absolutePath);
            Path filePath = absolutePath.resolve(saveFilename);
            Files.createFile(filePath);
        } catch (IOException e){
            ui.wrapEchoMessage(e.getMessage());
        }
    }

    /**
     * Saves tasks and its respective status of the current session into a text file.
     *
     * @param tasklist An <code>ArrayList</code> of <code>Task</code> containing tasks of the current session.
     */
    public void handleSaveFile(TaskList tasklist){
        this.savedTaskList = tasklist.getTaskList();
        try{
            File saveFile = new File(filePath);
            PrintWriter writer = new PrintWriter(saveFile);
            writer.close();
            writeToFile(tasklist.getTaskList());
        } catch (FileNotFoundException e){
            handleMissingDirectory();
            writeToFile(tasklist.getTaskList());
        }
    }

}
