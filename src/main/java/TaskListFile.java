import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.stream.Collectors;

public class TaskListFile {
    private final List<Task> taskList;
    private final String fileName;
    private final File taskFile;
    private static final String delimiter = "\\|";

    TaskListFile(String fileName) {
        List<Task> taskList1;
        this.fileName = fileName;
        this.taskFile = new File(fileName);
        try {
            taskList1 = this.decodeTasks();
        } catch (FileNotFoundException e) {
            taskList1 = new ArrayList<>();
        }
        this.taskList = taskList1;
    }
    TaskListFile(String fileName, List<Task> taskList) throws IOException {
        this.fileName = fileName;
        this.taskFile = new File(fileName);
        this.taskList = taskList;

        try {
            if (this.taskFile.createNewFile()) {
                System.out.printf("File created %s%n",this.fileName);
            } else {
                System.out.println("File exists, adding tasks to file");
            }
            this.writeFile(this.taskFile);
        } catch (IOException e) {
            System.out.println("Error creating files " + e.getMessage());
        }
    }

    List<Task> decodeTasks() throws FileNotFoundException {
        List<Task> loadList = new ArrayList<>();
        Scanner s = new Scanner(this.taskFile);
        while (s.hasNext()) {
            String inputLine = s.nextLine();
            String[] words = inputLine.split(delimiter);
            try {
                if (inputLine.startsWith("T")) {
                    boolean status = words[1].equals("X");
                    String description = words[2];
                    loadList.add(new ToDo(description, status));
                    continue;
                }
                if (inputLine.startsWith("D")) {
                    boolean status = words[1].equals("X");
                    String description = words[2];
                    String timestamp = words[3];
                    loadList.add(new Deadline(
                            description, status, timestamp));
                    continue;
                }
                if (inputLine.startsWith("E")) {
                    boolean status = words[1].equals("X");
                    String description = words[2];
                    String from = words[3];
                    String to = words[4];
                    loadList.add(new Event(description, status, from, to));
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("File corrupted");
                throw new RuntimeException(e);
            }
        }
        return loadList;
    }

    void writeFile(File file) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(this.encodeTasks());
        fw.close();
    }
    String encodeTasks() {
        return this.taskList.stream().map(
                Task::encodeString).collect(
                        Collectors.joining("\n")) + "\n";
    }

    String getFileName() {
        return this.fileName;
    }
    void printFile() throws FileNotFoundException {
        Scanner s = new Scanner(this.taskFile);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    TaskListFile updateTaskListFile(List<Task> newList) throws IOException {
        return new TaskListFile(this.fileName, newList);
    }







}
