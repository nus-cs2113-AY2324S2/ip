import Tasks.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;

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
                }
                else {
                    appendToFile(filePath, line);
                }
                counter += 1;
            }
        } catch (IOException e) {
            System.out.println("error in removing line");
        }
    }

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
                }
                else {
                    appendToFile(filePath, line);
                }
                counter += 1;
            }
        } catch (IOException e) {
            System.out.println("error in removing line");
        }
    }
}
