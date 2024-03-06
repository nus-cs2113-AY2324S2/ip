package GermaBot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import DataHandling.*;
import Exceptions.FileReadException;
import Tasks.*;

public class Storage {
    private File dataFile;

    public Storage() {
    }

    public int getNumOfTask() {
        return LoadData.getCounter();
    }

    public ArrayList<Task> loadFile() throws FileNotFoundException, FileReadException {
        return LoadData.loadFile();
    }

    public static void saveTodo(ToDo toDoTask, char taskType) throws IOException {
        SaveData.addTodoToFile(toDoTask, taskType);
    }

    public static void saveDeadline(Deadline deadlineTask, char taskType) throws IOException {
        SaveData.addDeadlineToFile(deadlineTask, taskType);
    }

    public static void saveEvent(Event eventTask, char taskType) throws IOException {
        SaveData.addEventToFile(eventTask, taskType);
    }

}
