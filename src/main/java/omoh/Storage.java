package omoh;

import omoh.customexceptions.CorruptedFileException;
import omoh.customexceptions.EmptyTodoException;
import omoh.tasktypes.Deadline;
import omoh.tasktypes.Event;
import omoh.tasktypes.Task;
import omoh.tasktypes.Todo;

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
            processFileText(parts, iteration);
            iteration++;
        }
        s.close();
    }

    //processes the output.txt file. According to the text it adds tasks and marks tasks
    public static void processFileText (String[] parts, int iteration) throws EmptyTodoException {
        String command;
        switch (parts[0].trim().charAt(0)) {
        case 'T':
            command = "todo " + parts[2].trim();
            Todo.addTodo(command);
            break;
        case 'D':
            command = "deadline " + parts[2].trim() + " /by " + parts[3].trim();
            Deadline.addDeadline(command);
            break;
        case 'E':
            command = "event " + parts[2].trim() + " /from " + parts[3].trim() + " /to " + parts[4].trim();
            Event.addEvent(command);
            break;
        }

        if (parts[1].trim().equals("1")) {
            command = "mark " + iteration + 1;
            Task.modifyDoneStateOrDelete(iteration + 1, command);
        }
    }
}
