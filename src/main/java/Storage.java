import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.util.Scanner;
import java.util.ArrayList;
public class Storage {
    private String path;

    private ArrayList<Task> tasksList;

    public Storage(String path) throws FileNotFoundException {
        tasksList = new ArrayList<Task>();
        this.path = path;
    }

    public Storage() {
        tasksList = new ArrayList<Task>();
        try {
            File file = new File("./data.txt");
            if (file.createNewFile()) {
                this.path = "./data.txt";
            } else {
                this.path = "./data.txt";
                UI.printMessage("File already exists");
            }
        } catch (IOException ioe) {
            UI.printMessage("Something went wrong in the storage");
        }
    }

    protected void saveRaw(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(path);
        for (int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.get(i).getCSV() + "\n");
        }
        fw.close();
    }

    public void save(ArrayList<Task> tasks){
        try {
            saveRaw(tasks);
        } catch (IOException ioe){
            UI.printMessage("Something wrong with the data path");
        }
    }

    private void loadFromDiskWithExceptionHandle() {
        File f;
        Scanner s;
        try {
            f = new File(path);
            s = new Scanner(f);
        } catch (FileNotFoundException fnfe) {
            UI.printMessage("Something wrong with the data path!");
            return;
        }
        while (s.hasNext()) {
            String temp = s.nextLine();
            String[] words = temp.split(",");
            if (words[0].equals("T")){
                tasksList.add(new Todo(words[2], !words[1].equals("0")));
            } else if (words[0].equals("D")){
                tasksList.add(new Deadline(words[2], words[3], !words[1].equals("0")));
            } else {
                tasksList.add(new Event(words[2], words[3], words[4], !words[1].equals("0")));
            }
        }
    }

    public ArrayList<Task> loadFromDisk(){
        loadFromDiskWithExceptionHandle();
        return tasksList;
    }


}
