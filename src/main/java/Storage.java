import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final String FILE_PATH = "Data/Jeff.txt";
    private static ArrayList<Task> savedList;
    protected static Ui userInterface;



    public Storage(ArrayList<Task> toSave, Ui uiComponent){
        savedList = toSave;
        userInterface = uiComponent;

    }

    public void setSavedList(ArrayList<Task> toSave){
        savedList = toSave;
    }

    public ArrayList<Task> getSavedList(){
        return savedList;
    }

    public void uploadTasks() { //takes string form of list and puts it in file
        List<String> serialisedList = serialiseTasks();
        try {
            Path path = Paths.get(FILE_PATH);
            Files.createDirectories(path.getParent());
            Files.write(path, serialisedList);
            userInterface.successfulUploadMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private List<String> serialiseTasks(){ //turns the list of tasks into strings
        List<String> serialisedList = new ArrayList<>();
        for(Task task : savedList){
            serialisedList.add(task.getDescription());
        }
        return serialisedList;
    }

    private static Task deserializeTodo(String description, Task task, boolean isDone, String line){
        description = line.substring(6).trim();
        return new Todo(description, isDone);
    }

    private static Task deserializeDeadline(String description, Task task, boolean isDone, String line) {
        int byIndex = line.indexOf("(by:");
        if (byIndex != -1) {
            description = line.substring(6, byIndex).trim();
            String by = line.substring(byIndex + 4, line.length() - 1).trim();
            return new Deadline(description, by, isDone);
        }
        return null;
    }


    private static Task deserializeEvent(String description, Task task, boolean isDone, String line){
        int atIndex = line.indexOf("(from: ");
        if (atIndex != -1) {
            description = line.substring(6, atIndex).trim();
            String timeInfo = line.substring(atIndex + 6, line.length() - 1);
            String[] times = timeInfo.split(" to: ");
            if (times.length == 2) {
                String start = times[0].trim();
                String end = times[1].trim();
                return new Event(description, start, end, isDone);
            }
        }
        return null;
    }




    public static void deserializeTasks() {
        List<String> lines;
        savedList = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(FILE_PATH));
        } catch (IOException e) {
            userInterface.showFileNotFoundException();
            return;
        }
        for (String line : lines) {
            char taskType = line.charAt(1);
            boolean isDone = line.charAt(3) == 'X';
            String description = "";
            Task task = null;

            switch (taskType) {
                case 'T':
                    task = deserializeTodo(description, task, isDone, line);
                    break;

                case 'D':
                    task = deserializeDeadline(description, task, isDone, line);
                    break;

                case 'E':
                    task = deserializeEvent(description, task, isDone, line);
                    break;
            }
            if (task != null) {
                savedList.add(task);
            }
        }
    }
}



