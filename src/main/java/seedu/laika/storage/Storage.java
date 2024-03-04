package seedu.laika.storage;

import seedu.laika.LaikaException;
import seedu.laika.tasklist.TaskList;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected File f;
    protected ArrayList<String> lines;
    protected int count = 0;

    public Storage(String filename){
        this.f = new File(filename);
    }

    public TaskList load() {
        TaskList tasks = new TaskList();
        try {
            Scanner reader = new Scanner(f);
            while (reader.hasNext()){
                String command = reader.nextLine();
                tasks.addTask(command);
                tasks.addLines(command);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Laika: Error while reading save data.");
            tasks = new TaskList();
        } catch (LaikaException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }

    public void save(TaskList tasks) throws IOException {
        FileWriter myWriter = new FileWriter("laika.txt");
        for (int i = 0; i< tasks.getSize();i++){
            myWriter.write(tasks.getLine(i) + System.lineSeparator());

        }
        myWriter.close();
    }
}
