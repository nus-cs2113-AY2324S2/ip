import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Storage {
    private final String fileName;
    private final File taskFile;
    private static final String delimiter = "\\|";
    private static final SimpleDateFormat formatter =
            new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    Storage(String fileName) {
        this.fileName = fileName;
        this.taskFile = new File(fileName);
    }
    Storage(String fileName, TaskList taskList) throws IOException {
        this.fileName = fileName;
        this.taskFile = new File(fileName);
        try {
            if (this.taskFile.createNewFile()) {
                System.out.printf("File created %s%n",this.fileName);
            } else {
                System.out.println("File exists, adding/deleting tasks to file");
            }
            this.writeFile(this.taskFile, taskList);
        } catch (IOException e) {
            System.out.println("Error creating files " + e.getMessage());
        }
    }

    TaskList decodeTasks() throws FileNotFoundException {
        TaskList loadList = new TaskList();
        Scanner s = new Scanner(this.taskFile);
        while (s.hasNext()) {
            String inputLine = s.nextLine();
            String[] words = inputLine.split(delimiter);
            try {
                if (inputLine.startsWith("T")) {
                    boolean status = words[1].equals("X");
                    String description = words[2];
                    loadList = loadList.add(new ToDo(description, status));
                    continue;
                }
                if (inputLine.startsWith("D")) {
                    boolean status = words[1].equals("X");
                    String description = words[2];
                    Date timestamp = formatter.parse(words[3]);
                    loadList = loadList.add(new Deadline(
                            description, status, timestamp));
                    continue;
                }
                if (inputLine.startsWith("E")) {
                    boolean status = words[1].equals("X");
                    String description = words[2];
                    Date from = formatter.parse(words[3]);
                    Date to = formatter.parse(words[4]);
                    loadList = loadList.add(
                            new Event(description, status, from, to));
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("File corrupted");
                throw new RuntimeException(e);
            } catch (ParseException e) {
                System.out.println("Date corrupted");
                throw new RuntimeException(e);
            }
        }
        return loadList;
    }

    void writeFile(File file, TaskList updatedList) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(this.encodeTasks(updatedList));
        fw.close();
    }
    String encodeTasks(TaskList updatedList) {
        return updatedList.getTasks().stream().map(
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

    Storage updateStorage(TaskList newList) throws IOException {
        return new Storage(this.fileName, newList);
    }







}
