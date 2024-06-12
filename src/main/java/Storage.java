import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    private static final String filename = "./saved.txt";
    private static final Path filePath = Paths.get(filename);

    /**
     * Loads all the information from the save file
     * Creates a new save file if there is none found.
     *
     * @throws IOException when there is an error in restoring the save.
     */
    public static void loadFileContents() throws IOException {
        try {
            File file = filePath.toFile();
            if (!file.exists()) {
                System.out.println("Save file not found, creating new file");
                file = new File(filename);
                System.out.println("File created at: " + file.getAbsolutePath());
            } else {
                System.out.println("Save file found at: " + file.getAbsolutePath());
                Scanner s = new Scanner(filePath);
                boolean done = false;
                while (s.hasNext()) {
                    String savedTask = s.nextLine();
                    System.out.println("Restored: ");
                    done = Parser.parseCommand(savedTask, true);
                }
            }
        } catch (IOException e) {
            System.out.println("Exception occurred while restoring save.");
        }
    }

    /**
     * Writes the user command into the file
     * @param text the user command
     * @throws IOException when there is an error writing to the save file.
     */
    public static void writeToFile(String text) throws IOException {
        try {
            FileWriter fw = new FileWriter(filename, true);
            fw.write(text + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println("Exception occurred while writing to file.");
        }
    }

    /**
     * Deletes and creates a new save file with all existing tasks
     *
     * @throws IOException when there is an error in writing to new file
     */
    public static void overwriteFile() throws IOException {
        try {
            File file = filePath.toFile() ;
            if (file.delete()) {
                File newFile = new File(filename);
                System.out.println("File created at: " + file.getAbsolutePath());
                for (Task t : TaskList.tasks) {
                    convertToString(t);
                }
            } else {
                throw new IOException();
            }
        } catch (IOException e) {
            System.out.println("IOException encountered");
        } catch (SecurityException e) {
            System.out.println("SecurityException encountered");
        }
    }

    private static void convertToString(Task t) {
        String response = "";
        try {
            FileWriter fw = new FileWriter(filename, true);
            if (t instanceof ToDo) {
                response += "todo " + t.description;
            } else if (t instanceof Deadline) {
                response += "deadline " + t.description
                        + " /by " + ((Deadline) t).by;
            } else {
                response += "event " + t.description
                        + " /from " + ((Event) t).from + " /to " + ((Event) t).to;
            }
            response += System.lineSeparator();
            fw.write(response);
            if (t.isDone) {
                int index = TaskList.tasks.indexOf(t);
                String markDone = "mark " + index + System.lineSeparator();
                fw.write(markDone);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("IOException encountered");
        }
    }
}
