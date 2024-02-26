package jeff;

import jeff.exceptions.CorruptFileException;
import jeff.tasks.Deadline;
import jeff.tasks.Event;
import jeff.tasks.Todo;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Storage {
    private static final String EXPECTED_FILE_PATH = "data/jeff.txt";
    private static final int MARK_INDEX = 4;

    public static void loadTasks() {
        try {
            File f = new File(EXPECTED_FILE_PATH);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                if (!isExpectedFormat(line)) {
                    throw new CorruptFileException();
                }
                Task task = getTask(line);
                TaskList.add(task);
            }
        } catch (FileNotFoundException e) {
            ExceptionHandler.handleFileNotFoundException();
        } catch (CorruptFileException e) {
            ExceptionHandler.handleCorruptFileException();
        }
    }

    public static void appendTask(Task task) {
        try {
            FileWriter fw = new FileWriter(EXPECTED_FILE_PATH, true); // create a FileWriter in append mode
            String textToAppend = task.toFileString() + System.lineSeparator();
            fw.write(textToAppend);
            fw.close();
        } catch (IOException e) {
            ExceptionHandler.handleIOException(e);
        }
    }

    public static void deleteTask(int index) {
        try {
            File f = new File(EXPECTED_FILE_PATH);
            Scanner s = new Scanner(f);
            StringBuilder content = new StringBuilder();
            int currentLine = 0;
            while (s.hasNext()) {
                String line = s.nextLine();
                if (currentLine == index) {
                    continue;
                }
                content.append(line).append(System.lineSeparator());
                currentLine++;
            }
            FileWriter fw = new FileWriter(EXPECTED_FILE_PATH);
            fw.write(content.toString());
            fw.close();
        } catch (FileNotFoundException e) {
            ExceptionHandler.handleFileNotFoundException();
        } catch (IOException e) {
            ExceptionHandler.handleIOException(e);
        }
    }

    public static void updateMarkStatus(int index, boolean mark) {
        char newMarkStatus = mark ? '1' : '0';

        try {
            File f = new File(EXPECTED_FILE_PATH);
            Scanner s = new Scanner(f);
            StringBuilder content = new StringBuilder();
            int currentLine = 0;
            while (s.hasNext()) {
                String line = s.nextLine();
                if (currentLine == index) {
                    StringBuilder modifiedLine = new StringBuilder(line);
                    modifiedLine.setCharAt(MARK_INDEX, newMarkStatus);
                    line = modifiedLine.toString();
                }
                content.append(line).append(System.lineSeparator());
                currentLine++;
            }
            FileWriter fw = new FileWriter(EXPECTED_FILE_PATH);
            fw.write(content.toString());
            fw.close();
        } catch (FileNotFoundException e) {
            ExceptionHandler.handleFileNotFoundException();
        } catch (IOException e) {
            ExceptionHandler.handleIOException(e);
        }
    }

    public static void createNewFile() {
        String directoryPath = "data";
        String fileName = "jeff.txt";

        try {
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File file = new File(directory, fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

        } catch (IOException e) {
            ExceptionHandler.handleIOException(e);
        }
    }

    private static boolean isExpectedFormat(String line) {
        String[] params = line.split(" \\| ");
        if (params.length < 3 || params.length > 4) {
            return false;
        }
        String type = params[0];
        String mark = params[1];
        String description = params[2];
        if (!mark.equals("0") && !mark.equals("1")) {
            return false;
        }
        if (description.isEmpty()) {
            return false;
        }
        if (params.length == 3) {
            return type.equals("T");
        }
        String[] lastParam = params[3].split("-");
        if (lastParam.length == 3) {
            if (!type.equals("D")) {
                return false;
            }
            String by = lastParam[0];
            return !by.isEmpty();
        }
        if (!type.equals("E")) {
            return false;
        }
        String from = lastParam[0];
        String to = lastParam[1];
        return !from.isEmpty() && !to.isEmpty();
    }

    private static Task getTask(String line) throws CorruptFileException {
        Task task;
        String[] params = line.split(" \\| ");
        String description = params[2] + " ";
        if (line.charAt(0) == 'T') {
            task = new Todo(description);
        } else if (line.charAt(0) == 'D') {
            String byString = params[3];
            LocalDate by = LocalDate.parse(byString);
            task = new Deadline(description, by);
        } else if (line.charAt(0) == 'E') {
            String[] lastParam = params[3].split("-");
            String from = lastParam[0] + " ";
            String to = lastParam[1];
            task = new Event(description, from, to);
        } else {
            throw new CorruptFileException();
        }
        if (params[1].equals("1")) {
            task.mark();
        }
        return task;
    }
}
