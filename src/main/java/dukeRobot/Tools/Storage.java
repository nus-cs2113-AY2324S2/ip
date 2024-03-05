package dukeRobot.Tools;
import dukeRobot.Tasks.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import dukeRobot.Tools.*;

public class Storage {
    private TaskList tasks;
    private File f;
    private String filePath;
    private Ui ui;

    public Storage(String filePath, Ui ui) {
        this.tasks = new TaskList();
        this.f = new File(filePath);
        this.filePath = filePath;
        this.ui = ui;
    }
    public void FileInitializer (String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public void FileUpdater (String textToAdd) throws IOException {
        if (!f.exists()) {
            FileInitializer(textToAdd);
        } else {
            FileAppender(textToAdd);
        }
    }

    public void FileAppender (String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public void FileClearer () throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw.flush();
        fw.close();
    }

    public void FileLoader() throws FileNotFoundException{
        try (Scanner s = new Scanner(f)) {
            while (s.hasNext()) {
                String sentence = s.nextLine();
                Task task = fileToTask(sentence);
//                System.out.println(task);
                tasks.add(task);
                if (sentence.contains("X")) {
                    task.markAsDone();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file is not found.");
        }
    }

    public Task fileToTask(String sentence) {
        String[] words = sentence.split(" ",2);
        String tag = words[0];
        if(tag.contains("D")) {
            String descriptionBy = words[1].split("] ")[1].trim();
            String description = descriptionBy.split("\\(by:|\\)")[0].trim();
            String by = "by: "+ descriptionBy.split("\\(by:|\\)")[1].trim();
            return new Deadline(description,by);
        } else if (tag.contains("E")) {
            String descriptionFromTo = words[1].split("]")[1];
            String[] sentences = descriptionFromTo.split("\\(|\\)");

            String description = sentences[0].trim();
            String[] secondSentenceParts = sentences[1].split("to:");
            String from = (secondSentenceParts[0].trim()).trim();
            String to = ("to: " + secondSentenceParts[1].trim()).trim();
//            System.out.println(from);
            return new Event(description, from, to);
        } else if (tag.contains("T")) {
            String description = words[1].split("] ")[1].trim();
            return new ToDo(description);
        } else {
            ui.notUnderstandError();
            return null;
        }
    }

    public TaskList getTasks() {
        return tasks;
    }
}
