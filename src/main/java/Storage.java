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

    /**
     * Constructor for Storage
     *
     * @param filename path of the file for data to be saved
     */
    Storage(String fileName) {
        this.fileName = fileName;
        this.taskFile = new File(fileName);
    }

    /**
     * Constructor for Storage
     *
     * @param filename path of the file for data to be saved
     * @param taskList list of tasks to be written in the file
     */
    Storage(String fileName, TaskList taskList) {
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

    /**
     * Decodes strings in data file and returns a list of tasks
     *
     * @return list of tasks
     */
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

    /**
     * Writes tp data file with encoded strings from list of tasks
     *
     * @param file file to be written to
     * @param updatedList list of tasks to be written
     */
    void writeFile(File file, TaskList updatedList) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(this.encodeTasks(updatedList));
        fw.close();
    }
    /**
     * Iterates through list of tasks, encodes them
     * and concatenates all of them into a result string
     *
     * @param updatedList list of tasks to be written
     * @return result string of all encoded tasks
     */
    String encodeTasks(TaskList updatedList) {
        return updatedList.getTasks().stream().map(
                Task::encodeString).collect(
                        Collectors.joining("\n")) + "\n";
    }

    /**
     * Getter for filename
     *
     * @return filename path of the file for data to be saved
     */
    String getFileName() {
        return this.fileName;
    }

    /**
     * Returns a new Storage based on new list of tasks
     *
     * @return storage with new list of tasks
     */
    Storage updateStorage(TaskList newList) throws IOException {
        return new Storage(this.fileName, newList);
    }







}
