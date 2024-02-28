package omoh;

import omoh.customexceptions.CorruptedFileException;
import omoh.customexceptions.EmptyTodoException;
import omoh.tasktypes.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
            readFile();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (EmptyTodoException e) {
            System.out.println("Todo task empty");
        } catch (CorruptedFileException e) {
            System.out.println("Output.txt file format is wrong. File corrupted.");
        }
    }


    //Method to read and process the output.txt file
    public static void readFile() throws FileNotFoundException, CorruptedFileException, EmptyTodoException {
        //open file for reading
        File f = new File("data/output.txt");
        Scanner s = new Scanner(f);
        Task.initArray();
        //iteration required so that programme knows which task to mark as done
        int iteration = 0;
        while (s.hasNextLine()) {
            String line = s.nextLine();
            // Process each line (splitting by "|", for example)
            String[] parts = line.split("\\|"); // Split the line by "|"
            if (parts.length < 3 || parts.length > 5 ) {
                throw new CorruptedFileException();
            }
            Task.processFileText(parts, iteration);
            iteration++;
        }
        s.close();
    }
}
