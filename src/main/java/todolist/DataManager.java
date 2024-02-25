package todolist;

import todolist.task.DeadLinesTask;
import todolist.task.EventsTask;
import todolist.task.ToDoTask;

import java.io.*;
import java.util.Objects;

public class DataManager {
    private final File dataFile;
    private static final String FILE_END_STRING = "#end";
    private static final char TASK_EVENT = 'E';
    private static final char TASK_TODOTASK = 'T';
    private static final char TASK_DEADLINE = 'D';
    private static final char INFORMATION_SEPARATOR = '|';
    private static final int  MARK_STATUS_POSITION = 2;
    private static final int TASK_NAME_POSITION = 4;


    public DataManager(String filePath) {
        this.dataFile = new File(filePath);
    }

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

    public void loadDataTodoList(ToDoList toDoList) {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.dataFile.getName()))) {
            String line;
            while (!Objects.equals(line = reader.readLine(), FILE_END_STRING)) {
                char isDone = line.charAt(MARK_STATUS_POSITION);
                switch (line.charAt(0)) {
                case TASK_EVENT:
                    //get name
                    int splitIndexEvent1 = line.indexOf(INFORMATION_SEPARATOR, TASK_NAME_POSITION);
                    //get start and end time
                    int splitIndexEvent2 = line.indexOf(INFORMATION_SEPARATOR, splitIndexEvent1 + 1);
                    toDoList.getToDoListArray().add(new EventsTask(line.substring(TASK_NAME_POSITION, splitIndexEvent1 - 1),
                            isDone != '0',
                            line.substring(splitIndexEvent1 + 1, splitIndexEvent2 - 1),
                            line.substring(splitIndexEvent2 + 1)));
                    break;
                case TASK_TODOTASK:
                    toDoList.getToDoListArray().add(new ToDoTask(line.substring(TASK_NAME_POSITION), isDone != '0'));
                    break;
                case TASK_DEADLINE:
                    int splitIndexDeadline = line.indexOf('|', TASK_NAME_POSITION);
                    toDoList.getToDoListArray().add(new DeadLinesTask(line.substring(TASK_NAME_POSITION, splitIndexDeadline - 1),
                            isDone != '0',
                            line.substring(splitIndexDeadline + 1)));
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
}
