import java.io.*;
import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class FileHandler {
    private static File file = fileWithDirectoryAssurance("data", "tickles.txt");
    private static String filePath = "./data/tickles.txt";

    public static void loadTickles() {

        try {
            Scanner in = new Scanner(new FileReader(filePath));
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] lineData = line.split("\\|");
                Task task = null;
                switch (lineData[0]) {
                case "[T]":
                    task = new Todo(lineData[2]);
                    break;
                case "[D]":
                    task = new Deadline(lineData[2], lineData[3]);
                    break;
                case "[E]":
                    task = new Event(lineData[2], lineData[3], lineData[4]);
                    break;
                default:
                    System.out.println("Error; corrupted file format");
                }
                if (lineData[1].equals("X")) {
                    task.markAsDone();
                }
                Tickles.list.add(task);
                Tickles.totalTasks += 1;
                }
        } catch (FileNotFoundException e) {
            file = new File("./data/tickles.txt");
        }
    }
    private static File fileWithDirectoryAssurance(String directory, String filename) {
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return new File("./" + directory + "/" + filename);
    }

    public static void save() {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(filePath, false);
            for (Task t : Tickles.list) {
                switch (t.type()) {
                case "[D]":
                    fileWriter.write(t.type() + "|" + t.getStatusIcon() + "|" + t.getDescription() + "|" + ((Deadline)t).by);
                    break;
                case "[T]":
                    fileWriter.write(t.type() + "|" + t.getStatusIcon() + "|" + t.getDescription());
                    break;
                case "[E]":
                    fileWriter.write(t.type() + "|" + t.getStatusIcon() + "|" + t.getDescription() + "|" +
                            ((Event)t).start + "|" + ((Event)t).end);
                    break;
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}

