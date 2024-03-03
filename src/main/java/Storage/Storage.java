package Storage;

import TaskList.TaskList;
import tasks.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {

    private static String filepath;
    public Storage(String filepath){
        Storage.filepath = filepath;

    }

    public static ArrayList<Task> load(String filepath) throws FileNotFoundException {
        return LoadData.loadData(filepath);
    }

    public static void upload(String filepath, TaskList tasks) throws IOException {
         UploadData.updateFile(filepath, tasks);
    }
}
