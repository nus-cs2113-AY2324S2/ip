package todolist;

import todolist.task.DeadLinesTask;
import todolist.task.EventsTask;
import todolist.task.Task;
import todolist.task.ToDoTask;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Objects;

public class DataManager {
    private final File dataFile;
    private static final String FILE_END_STRING = "#end";
    private static final char TASK_EVENT = 'E';
    private static final char TASK_TODOTASK = 'T';
    private static final char TASK_DEADLINE = 'D';
    private static final char INFORMATION_SEPARATOR = '|';
    private static final char TASK_TYPE_POSITION = 0;
    private static final int  MARK_STATUS_POSITION = 2;
    private static final int TASK_NAME_POSITION = 4;
    private static final char MARKED = '1';
    private static final char UNMARKED = '0';


    /**
     * Constructor for DataManager
     * @param filePath the file path of the data file
     */
    public DataManager(String filePath) {
        this.dataFile = new File(filePath);
    }

    /**
     * Create a new data file if it does not exist
     */
    public void createLocalDataFile() {
        try {
            if (this.dataFile.createNewFile()) {
                System.out.println("Data file created: " + this.dataFile.getName());
                FileWriter fw = new FileWriter(this.dataFile.getName());
                fw.append(FILE_END_STRING);
                fw.close();
            } else {
                System.out.println("Data file already exists: " + this.dataFile.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    /**
     * Load data from the data file
     * @param toDoList the ToDoList to load the data to
     */
    public void loadDataTodoList(ToDoList toDoList) {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.dataFile.getName()))) {
            String line;
            while (!Objects.equals(line = reader.readLine(), FILE_END_STRING)) {
                char isMarked = line.charAt(MARK_STATUS_POSITION);
                switch (line.charAt(TASK_TYPE_POSITION)) {
                case TASK_EVENT:
                    //get name
                    int splitIndexEvent1 = line.indexOf(INFORMATION_SEPARATOR, TASK_NAME_POSITION);
                    //get start and end time
                    int splitIndexEvent2 = line.indexOf(INFORMATION_SEPARATOR, splitIndexEvent1 + 1);
                    toDoList.getToDoListArray().add(new EventsTask(line.substring(TASK_NAME_POSITION, splitIndexEvent1 - 1),
                            isMarked != UNMARKED,
                            LocalDateTime.parse(line.substring(splitIndexEvent1 + 1, splitIndexEvent2 - 1)),
                            LocalDateTime.parse(line.substring(splitIndexEvent2 + 1))));
                    break;
                case TASK_TODOTASK:
                    toDoList.getToDoListArray().add(new ToDoTask(line.substring(TASK_NAME_POSITION), isMarked != UNMARKED));
                    break;
                case TASK_DEADLINE:
                    int splitIndexDeadline = line.indexOf(INFORMATION_SEPARATOR, TASK_NAME_POSITION);
                    toDoList.getToDoListArray().add(new DeadLinesTask(line.substring(TASK_NAME_POSITION, splitIndexDeadline - 1),
                            isMarked != UNMARKED,
                            LocalDateTime.parse(line.substring(splitIndexDeadline + 1))));
                    break;
                default:
                    throw new IOException();
                }
            }
            System.out.println("Data loaded successfully");
        } catch (IOException | StringIndexOutOfBoundsException e) {
            System.err.println("Data file damaged, please continue");
        }
    }

    /**
     * Write data to the data file
     * @param toDoList the ToDoList to write the data from
     * @throws IOException if an error occurs
     */
    public void writeToFile(ToDoList toDoList) throws IOException {
        FileWriter fw = new FileWriter(this.dataFile.getName());
        fw.write(storeDataString(toDoList));
        fw.close();
    }

    /**
     * Store the data to a string
     * @param toDoList the ToDoList to store the data from
     * @return the string representation of the data
     */
    private String storeDataString(ToDoList toDoList) {
        StringBuilder dataString = new StringBuilder();
        for (Task task : toDoList.getToDoListArray()) {
            dataString.append(task.storeDataString()).append("\n");
        }
        dataString.append("#end");
        return dataString.toString();
    }
}
