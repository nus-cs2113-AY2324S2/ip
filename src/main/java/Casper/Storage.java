package Casper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

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

    public void writeToFile(ArrayList<Task> taskList) {
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

    public void handleTaskStringToTaskObject(String[] formatInputList){
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

    public void handleMissingDirectory(){
        Path absolutePath = Paths.get(pathToSaveDirectory).toAbsolutePath();
        try{
            Files.createDirectory(absolutePath);
            Path filePath = absolutePath.resolve(saveFilename);
            Files.createFile(filePath);
        } catch (IOException e){
            ui.wrapEchoMessage(e.getMessage());
        }
    }

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
