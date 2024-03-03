import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class DukeFile {
    private static final String FILENAME = "./dukeData.txt";
    private static File dukeData;

    private static void writeToFile(String filePath, String textToAdd, boolean ifAppend) throws IOException {
        FileWriter writer = new FileWriter(filePath, ifAppend);
        writer.write(textToAdd);
        writer.close();
    }
    public static void updateFile(String inputText, boolean ifAppend) {
        try {
            writeToFile(FILENAME, inputText, ifAppend);
        } catch (IOException e) {
            PrintText.printWithLinebreak("IOExceptions occurred");
        }
    }
    public static int latestIndex() {
        try {
            File fileToLook = new File(FILENAME);
            Scanner s = new Scanner(fileToLook);
            String lastLine = "";
            while (s.hasNext()) {
                lastLine = s.nextLine();
                PrintText.print(lastLine);
            }
            int index = Integer.parseInt(lastLine.split("\\.")[0]);
            return index;
        } catch (Exception e) {
            PrintText.printWithLinebreak("return index 0");
            return 0;
        }
    }

    public static File getFileData() {
        return dukeData;
    }

    public static void readFromFile(File fileToRead, ArrayList<Task> existTasks) {
        try {
            Scanner scanner = new Scanner(fileToRead);
            while (scanner.hasNext()) {
                String lineSkipped = scanner.nextLine();
                String typeAsString = lineSkipped.substring(2, 5);
                String isMarkedAsString = lineSkipped.substring(5, 8);
                char taskType;
                boolean isDone;
                String description = lineSkipped.substring(9);
                switch (typeAsString) {
                case "[E]":
                    taskType = 'E';
                    break;
                case "[D]":
                    taskType = 'D';
                    break;
                case "[T]":
                    taskType = 'T';
                    break;
                default:
                    taskType = ' ';
                    break;
                }
                isDone = isMarkedAsString.equals("[X]");
            existTasks.add(new Task(description, taskType, isDone));
            }
        } catch (FileNotFoundException e) {
            PrintText.printWithLinebreak("File does not exist.");
        }
    }

    public static void main(String[] args) {
        dukeData = new File(FILENAME);
        try {
            writeToFile(dukeData.getPath(), "", true);
        } catch (IOException e) {
            PrintText.printWithLinebreak("File does not exist.");
        }
    }
}
