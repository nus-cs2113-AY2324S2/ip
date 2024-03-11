import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.ArrayList;

public class Storage {
    public static String filepath = "./data/chat.txt";
    public Storage() {
        File direcName = new File("./data");
        if(!direcName.exists()) {
            direcName.mkdir();
        }
        File f = new File(filepath);
        boolean res;
        try {
            f.createNewFile();
        } catch (IOException e) {
            Ui.printError(e.getMessage());
        }
    }
    public void loadFromDisk() throws FileNotFoundException {
        File f = new File(filepath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String taskLine = s.nextLine();
            String[] taskArr = taskLine.split("\\s\\|\\s");
            Task t;
            if (taskArr[0].equals("T")) { //todotask
                t = new Todo(taskArr[2]);
            } else if (taskArr[0].equals("D")) { //deadline
                t = new Deadline(taskArr[2], taskArr[3]);
            } else { //event
                t = new Event(taskArr[2], taskArr[3], taskArr[4]);
            }
            if (taskArr[1].equals("1")) {
                t.markTask(false);
            }
            TaskList.addTask(t, false);
        }
    }

    public void saveToDisk(ArrayList<Task> myTasks) throws IOException {
        FileWriter fw = new FileWriter(filepath, false);
        fw.close();
        for (Task t: myTasks) {
            writeToFile(t);
        }
    }

    public static void writeToFile(Task t) throws IOException {
        FileWriter fw = new FileWriter(filepath, true);
        fw.write(t.saveTaskFormat());
        fw.close();
    }
}
