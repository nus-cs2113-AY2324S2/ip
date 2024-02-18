import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Saver {
    private static String FILE_PATH = "Data/Jeff.txt";
    private static ArrayList<Task> savedList;



    public Saver(ArrayList<Task> toSave){
        savedList = toSave;
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
            System.out.println("TASK UPLOADED");
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


    public static void deserializeTasks() {
        List<String> lines;
        savedList = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(FILE_PATH));
        } catch (IOException e) {
            System.out.println("File not found, creating a new one.");
            return;
        }
        for (String line : lines) {
            char taskType = line.charAt(1);
            boolean isDone = line.charAt(3) == 'X';
            String description, timeInfo;
            Task task = null;

            switch (taskType) {
                case 'T':
                    description = line.substring(6).trim();
                    task = new Todo(description, isDone);
                    break;

                case 'D':
                    int byIndex = line.indexOf("(by:");
                    if (byIndex != -1) {
                        description = line.substring(6, byIndex).trim();
                        String by = line.substring(byIndex + 4, line.length() - 1).trim();
                        task = new Deadline(description, by, isDone);
                    }
                    break;

                case 'E':
                    int atIndex = line.indexOf("(from: ");
                    if (atIndex != -1) {
                        description = line.substring(6, atIndex).trim();
                        timeInfo = line.substring(atIndex + 6, line.length() - 1);
                        String[] times = timeInfo.split(" to: ");
                        if (times.length == 2) {
                            String start = times[0].trim();
                            String end = times[1].trim();
                            task = new Event(description, start, end, isDone);
                        }
                    }
                    break;
            }
            if (task != null) {
                savedList.add(task);
            }
        }
    }
}



