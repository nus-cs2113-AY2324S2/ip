import Tasks.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileProcessor {

    public static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write("\n");
        fw.write(textToAppend);
        fw.close();
    }

    public static void startUpCheck(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        if (f.exists()) {
            ArrayList<String> dataArrayList = new ArrayList<>();
            System.out.println("The file already exists.");
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                dataArrayList.add(s.nextLine());
            }
            TaskManager.loadTasks(dataArrayList);
        } else {
            createFile(f);
        }
    }

    public static void createFile(File f) {
        try {
            // Create the file
            if (f.createNewFile()) {
                System.out.println("File created successfully.");
            } else {
                System.out.println("Failed to create the file.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file");
        }
    }

    public static void removeLineData(String filePath, int deleteLine) {
        try {
            File inputFile = new File(filePath);
            File tempFile = new File("./src/main/temp.txt"); // Temporary file to write modified contents

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            int lineNumber = 1;

            while ((currentLine = reader.readLine()) != null) {
                // If the current line number is not the line to delete, write it to the temporary file
                if (lineNumber != deleteLine) {
                    writer.write(currentLine);
                    writer.newLine();
                }
                lineNumber++;
            }

            writer.close();
            reader.close();

            // Delete the original file and rename the temporary file to the original file name
            if (inputFile.delete()) {
                if (!tempFile.renameTo(inputFile)) {
                    System.out.println("Could not rename the file.");
                }
            } else {
                System.out.println("Could not delete the file.");
            }

            System.out.println("Line " + deleteLine + " deleted successfully.");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

}
