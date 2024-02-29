package Storage;
import Task.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
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
                taskList.add(new Deadline(taskName, hasDone, result[3]));
                break;
            case "E":
                String[] duration = result[3].split(" - ");
                taskList.add(new Event(taskName, hasDone, duration[0],duration[1]));
                break;
            default:
                break;
            }
            nextLine = br.readLine();
        }
        return taskList;
    }

    private void listWrite(ArrayList<Task> taskList) throws IOException {
        Files.createDirectories(Paths.get("./data"));
        FileWriter out = new FileWriter("./data/Ruby.txt", false);
        for (int i=0; i < taskList.size(); i++){
            out.write(taskList.get(i).toString());
            out.write(System.lineSeparator());
        }
        out.close() ;
    }

    public void saveToFile(ArrayList<Task> taskList){
        try{
            listWrite(taskList);
        } catch (IOException e) {
            System.out.println("Sorry, something wrong with my recording function.");
        }
    }

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
