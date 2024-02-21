package mona.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

import mona.input.InputParser;
import mona.manager.TaskManager;
import mona.task.Deadline;
import mona.task.Event;
import mona.task.Task;
import mona.task.Todo;
import mona.util.Constants;

public class TaskStorage {
    protected String filePath;

    public TaskStorage(String filePath) {
        this.filePath = filePath;
    }

    public static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public void saveToStorage(ArrayList<Task> tasks) {
        String textToAdd = generateTextToAdd(tasks);

        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToAdd);
            fw.write("\n"); // adding newline to EOF
            fw.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String generateTextToAdd(ArrayList<Task> tasks) {
        String textToAdd = "";
        for (Task task: tasks) {
            if (task != null) {
                textToAdd += generateSavedTaskFormat(task) + System.lineSeparator();
            }
        }
        return textToAdd;
    }

    public static String generateSavedTaskFormat(Task task) {
        String output = "";
        String isDone = "0";

        if (task.isDone()) {
            isDone = "1";
        }

        if (task instanceof Deadline){
            output += "D | ";

        } else if (task instanceof Event) {
            output += "E | ";

        } else if (task instanceof Todo) {
            output += "T | ";
        }
        output += isDone + " | " + task.getDescription();

        return output;
    }

    public String[] parseSavedInput(String line) {
        String[] savedInputElements = line.split("\\|");

        String taskType = savedInputElements[0].trim();
        String[] outputString = new String[2];
        
        switch(taskType) {
        case("T"):
            outputString[0] = "todo " + savedInputElements[2].trim();
            break;
        case("D"):
            outputString[0] = "deadline " + savedInputElements[2].trim();
            break;
        case("E"):
            outputString[0] = "event " + savedInputElements[2].trim();
            break;
        }

        if (savedInputElements[1].trim().equals("1")) {
            outputString[1] = "DONE";
        } else {
            outputString[1] = "NOT DONE";
        }

        return outputString;
    }
}
