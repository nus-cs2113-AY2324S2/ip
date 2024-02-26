import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class handles file input/output operations for task data.
 * It provides methods to read from, write to, and manipulate data in text files.
 */
public class Storage {

    /**
     * Prints the contents of the file specified by the given file path.
     *
     * @param filePath The path of the file to print.
     * @throws FileNotFoundException If the file specified by the file path is not found.
     */
    public static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    /**
     * Writes the given text to the file specified by the file path.
     *
     * @param filePath  The path of the file to write to.
     * @param textToAdd The text to add to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Appends the given text to the end of the file specified by the file path.
     *
     * @param filePath     The path of the file to append to.
     * @param textToAppend The text to append to the file.
     * @throws IOException If an I/O error occurs while appending to the file.
     */
    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write("\n");
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Performs a startup check for the existence of the data file.
     * If the file exists, loads its contents into the task manager.
     * If the file does not exist, creates an empty file.
     *
     * @param filePath The path of the data file.
     * @throws FileNotFoundException If the data file is not found.
     */
    public static void startUpCheck(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        if (f.exists()) {
            ArrayList<String> dataArrayList = new ArrayList<>();
            System.out.println("Data file exists.");
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                dataArrayList.add(s.nextLine());
            }
            TaskManager.loadTasks(dataArrayList);
        } else {
            try {
                writeToFile(filePath, "");
            } catch (IOException e) {
                System.out.println("failed to create file");
            }
        }
    }

    /**
     * Clears the contents of the file specified by the file path.
     *
     * @param filePath The path of the file to clear.
     */
    public static void clearFile(String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.flush();
            pw.close();
            fw.close();
        } catch (Exception exception) {
            System.out.println("failed to clear file");
        }
    }

    /**
     * Removes the line of data at the specified line number from the file.
     *
     * @param filePath   The path of the file to remove data from.
     * @param deleteLine The line number of the data to be removed.
     * @throws FileNotFoundException If the file specified by the file path is not found.
     */
    public static void removeLineData(String filePath, int deleteLine) throws FileNotFoundException {
        ArrayList<String> dataContents = new ArrayList<>();
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        int count = 1;
        while (s.hasNext()) {
            if (count != deleteLine) {
                dataContents.add(s.nextLine());
            } else {
                s.nextLine();
            }
            count += 1;
        }
        try {
            int counter = 0;
            for (String line : dataContents) {
                if (counter == 0) {
                    writeToFile(filePath, line);
                } else {
                    appendToFile(filePath, line);
                }
                counter += 1;
            }
        } catch (IOException e) {
            System.out.println("error in removing line");
        }
    }

    /**
     * Changes the status of a task in the file specified by the file path.
     *
     * @param filePath        The path of the file containing task data.
     * @param changeStatusLine The line number of the task whose status is to be changed.
     * @param status           The new status to set for the task (0 for not done, 1 for done).
     * @throws FileNotFoundException If the file specified by the file path is not found.
     */
    public static void changeTaskStatusData(String filePath, int changeStatusLine, int status) throws FileNotFoundException {
        ArrayList<String> dataContents = new ArrayList<>();
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        int count = 1;
        while (s.hasNext()) {
            if (count != changeStatusLine) {
                dataContents.add(s.nextLine());
            } else {
                String nextLine = s.nextLine();
                String[] lineArray = nextLine.split(",");
                String remainingContent = "";
                for (int i = 2; i < lineArray.length; i += 1) {
                    remainingContent = remainingContent + "," + lineArray[i];
                }
                dataContents.add(lineArray[0] + "," + status + remainingContent);
            }
            count += 1;
        }
        try {
            int counter = 0;
            for (String line : dataContents) {
                if (counter == 0) {
                    writeToFile(filePath, line);
                } else {
                    appendToFile(filePath, line);
                }
                counter += 1;
            }
        } catch (IOException e) {
            System.out.println("error in removing line");
        }
    }
}
