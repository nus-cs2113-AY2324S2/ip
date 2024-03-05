package vibes.storage;

import vibes.common.ErrorMessages;
import vibes.common.TaskTypes;
import vibes.task.TaskList;
import vibes.task.type.Deadline;
import vibes.task.type.Event;
import vibes.task.type.Task;
import vibes.task.type.Todo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String DATA_FOLDER = "./data/";
    private static final String DATA_FILE = "tasks.txt";
    private static final String PARAM_SEPARATOR = " , ";
    public static final String EMPTY_STRING = "";
    public static final String LINE_BREAK = "\n";
    public static final String MARKED = "1";
    public static final int MARKED_INT = 1;
    public static final int UNMARKED_INT = 0;
    public static final int TASK_TYPE_INDEX = 0;
    public static final int DESCRIPTION_INDEX = 2;
    public static final int BY_INDEX = 3;
    public static final int FROM_INDEX = 3;
    public static final int TO_INDEX = 4;
    public static final int STATUS_INDEX = 1;
    public static final int TASKS_SIZE_OFFSET = 1;

    private final File file;

    public Storage(){
        File folder = new File(DATA_FOLDER);
        file = new File(folder, DATA_FILE);

        try {
            if (!file.exists()) {
                if (!folder.exists() && !folder.mkdirs()) {
                    throw new IOException(ErrorMessages.FAILED_TO_CREATE_FOLDER);
                }

                if (!file.createNewFile()) {
                    throw new IOException(ErrorMessages.FAILED_TO_CREATE_FILE);
                }
            }
        } catch (IOException e) {
            System.out.println(ErrorMessages.ERROR_OCCURRED + e.getMessage());
        }
    }

    public void writeToFile(TaskList taskList) throws IOException {
        clearFileContent();
        FileWriter fileWriter = new FileWriter(file, true);

        for (Task task : taskList.tasks) {
            if (task == null) {
                break;
            }

            String textToWrite = EMPTY_STRING;
            switch (task.getTaskType()) {
            case TaskTypes.TODO_TYPE:
                assert task instanceof Todo;
                Todo todoTask = (Todo) task;
                textToWrite = todoTask.getTaskType() + PARAM_SEPARATOR + (todoTask.isDone() ? MARKED_INT : UNMARKED_INT)
                        + PARAM_SEPARATOR + todoTask.getDescription();
                break;
            case TaskTypes.DEADLINE_TYPE:
                assert task instanceof Deadline;
                Deadline deadlineTask = (Deadline) task;
                textToWrite = deadlineTask.getTaskType() + PARAM_SEPARATOR + (deadlineTask.isDone() ? MARKED_INT :
                        UNMARKED_INT) + PARAM_SEPARATOR + deadlineTask.getDescription() + PARAM_SEPARATOR +
                        deadlineTask.getBy();
                break;
            case TaskTypes.EVENT_TYPE:
                assert task instanceof Event;
                Event eventTask = (Event) task;
                textToWrite = eventTask.getTaskType() + PARAM_SEPARATOR + (eventTask.isDone() ? MARKED_INT :
                        UNMARKED_INT) + PARAM_SEPARATOR + eventTask.getDescription() + PARAM_SEPARATOR +
                        eventTask.getFrom() + PARAM_SEPARATOR + eventTask.getTo();
                break;
            }
            fileWriter.write(textToWrite + LINE_BREAK);
        }
        fileWriter.close();
    }

    private void clearFileContent() throws IOException {
        FileWriter fileClearer = new FileWriter(file);
        fileClearer.write(EMPTY_STRING);
        fileClearer.close();
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        Scanner s = new Scanner(file);
        TaskList taskList = new TaskList();
        while (s.hasNext()) {
            readTask(taskList, s.nextLine());
        }
        return taskList.tasks;
    }

    private void readTask(TaskList taskList, String textLine) {
        String[] parsedInput = textLine.trim().split(PARAM_SEPARATOR);
        switch (parsedInput[TASK_TYPE_INDEX]){
        case TaskTypes.TODO_TYPE:
            taskList.addTodo(parsedInput[DESCRIPTION_INDEX]);
            break;
        case TaskTypes.DEADLINE_TYPE:
            String[] deadlineTask = {parsedInput[DESCRIPTION_INDEX], parsedInput[BY_INDEX]};
            taskList.addDeadline(deadlineTask);
            break;
        case TaskTypes.EVENT_TYPE:
            String[] eventTask = {parsedInput[DESCRIPTION_INDEX], parsedInput[FROM_INDEX], parsedInput[TO_INDEX]};
            taskList.addEvent(eventTask);
        }

        if (parsedInput[STATUS_INDEX].equals(MARKED)) {
            taskList.tasks.get(taskList.tasks.size() - TASKS_SIZE_OFFSET).setDone(true);
        }
    }

    public void saveTask(TaskList taskList) {
        try {
            writeToFile(taskList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
