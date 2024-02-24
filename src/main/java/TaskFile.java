import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.Scanner;

public class TaskFile {

    public static void save(ArrayList<Task> tasks) {
        Greet greet = new Greet();
        File folder = new File("./data");
        if(!folder.exists()) {
            boolean ret = folder.mkdir();
            if(!ret) {
                return;
            }
        }
        try(FileWriter fw = new FileWriter("./data/duke.txt")) {
            for(Task task : tasks) {
                fw.write(task.toFileString() + "\n");
            }
        } catch(IOException e) {
            greet.printFormat();
            System.out.println("task cannot be saved");
            greet.printFormat();
        }
    }

    public static ArrayList<Task> load() {
        Greet greet = new Greet();
        File f = new File("./data/duke.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        if(!f.exists()) {
            return tasks;
        }
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            greet.printFormat();
            System.out.println("task cannot be saved");
            greet.printFormat();
        }
        while (true) {
            assert s != null;
            if (!s.hasNext()) break;
            String line = s.nextLine();
            if(line.startsWith("T")) {
                tasks.add(Todo.fromFileString(line));
            } else if(line.startsWith("D")) {
                tasks.add(Deadline.fromFileString(line));
            } else {
                tasks.add(Event.fromFileString(line));
            }
        }
        return tasks;
    }
}
