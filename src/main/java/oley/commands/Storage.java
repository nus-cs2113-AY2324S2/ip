package oley.commands;

import oley.tasks.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    public Storage() {
    }

    public static void appendToFile (String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }

    public static void changeFile (String filePath, TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(tasks.get(0).format() + System.lineSeparator());
        fw.close();
        for (int i = 1; i < tasks.size(); i++) {
            appendToFile(filePath, tasks.get(i).format());
        }
    }

    public static void clearFile (String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.close();
    }

    public static void load(TaskList tasks, String file) {
        Scanner s;
        try {
            File f = new File(file);
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            Ui.printFileNotFound();
            Ui.lineBreaker();
            Path folder = Paths.get("./data/");
            Path filePath = Paths.get(file);
            try {
                Files.createDirectory(folder);
                Files.createFile(filePath);
            } catch (IOException ex) {
                Ui.printFileNotCreated();
            }
            return;
        }

        while (s.hasNext()) {
            String line = s.nextLine();
            String isDone = line.substring(0,1);
            String task = line.substring(1);
            try {
                Command.addTask(tasks, task, true);
            } catch (InputNotRecognizedException e) {
                Ui.printFileCorrupted();
            }
            if (isDone.equals("1")) {
                Command.mark(tasks, "mark " + tasks.size(), true);
            }
        }
    }
}
