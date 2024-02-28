package omoh;

import omoh.customexceptions.CorruptedFileException;
import omoh.customexceptions.EmptyTodoException;
import omoh.tasktypes.Task;

import java.io.File;
import java.io.FileNotFoundException;

public class Storage {


    //function creates directory for output.txt file if it is not present
    public static void createFileDirectory () {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdirs(); // This will create the necessary directories
        }
    }

    //creates output.txt file to store data
    //also reads output file
    public static void createOrReadOutputFile () {
        //code to create new file in data directory
        File f = new File("data/output.txt");
        try {
            Task.readFile();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (EmptyTodoException e) {
            System.out.println("Todo task empty");
        } catch (CorruptedFileException e) {
            System.out.println("Output.txt file format is wrong. File corrupted.");
        }
    }
}
