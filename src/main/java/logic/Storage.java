package logic;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Storage {
    private File dataFile;

    private TaskManager taskManager;

    public Storage(String filePath) {
        if (new File("./data").mkdir()) {
            System.out.println("data folder created");
        } else {
            System.out.println("data folder found");
        }
        try {
            if (new File(filePath).createNewFile()) {
                System.out.println("dor.txt created");
            } else {
                System.out.println("dor.txt found");
            }
        } catch (IOException e) {
            System.out.println("ERROR: Could not create or find dor.txt!");
        }
        this.dataFile = new File(filePath);
        this.taskManager = new TaskManager();
    }

    public TaskManager loadDataFromTextFile() throws FileNotFoundException {
        Scanner s = null;
        try {
            s = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Could not load dor.txt!");
            throw new FileNotFoundException();
        }
        while (s.hasNext()) {
            String data = s.nextLine();
            String taskType = data.substring(0, 1);
            boolean taskDoneStatus = processTaskDoneStatus(data);
            switch (taskType) {
            case "T":
                loadToDo(data, taskDoneStatus);
                break;
            case "D":
                loadDeadline(data, taskDoneStatus);
                break;
            case "E":
                loadEvent(data, taskDoneStatus);
                break;
            }
        }
        System.out.println("Successfully loaded data!");
        System.out.println("Current number of tasks: " + taskManager.currIndex);
        return taskManager;
    }

    private boolean processTaskDoneStatus(String data) {
        boolean taskDoneStatus;
        if (data.substring(3, 4).equals("1")){
            taskDoneStatus = true;
        } else {
            taskDoneStatus = false;
        }
        return taskDoneStatus;
    }

    private void loadToDo(String data, boolean taskDoneStatus) {
        String taskName = data.substring(6);
        taskManager.tasks.add(new ToDo(taskName, taskDoneStatus));
        taskManager.currIndex++;
    }

    private void loadDeadline(String data, boolean taskDoneStatus) {
        int commaAfterTaskNameIndex = data.indexOf(',', 6);
        String taskName = data.substring(6, commaAfterTaskNameIndex);
        String byWhen = data.substring(commaAfterTaskNameIndex + 2);
        taskManager.tasks.add(new Deadline(taskName, taskDoneStatus, byWhen));
        taskManager.currIndex++;
    }

    private void loadEvent(String data, boolean taskDoneStatus) {
        int commaAfterTaskNameIndex = data.indexOf(',', 6);
        String taskName = data.substring(6, commaAfterTaskNameIndex);
        int commaAfterFromDateTimeIndex = data.indexOf(",", commaAfterTaskNameIndex + 1);
        String fromWhen = data.substring(commaAfterTaskNameIndex + 2, commaAfterFromDateTimeIndex);
        String toWhen = data.substring(commaAfterFromDateTimeIndex + 2);
        taskManager.tasks.add(new Event(taskName, taskDoneStatus, fromWhen, toWhen));
        taskManager.currIndex++;
    }

    public void saveDataToTextFile() throws IOException {
        FileWriter fw = new FileWriter("./data/dor.txt");
        for (int i = 0; i < taskManager.currIndex; i++) {
            Task currTask = taskManager.tasks.get(i);
            String data = processData(currTask);
            fw.write(data + System.lineSeparator());
        }
        fw.close();
    }

    private String processData(Task currTask) {
        String data;
        String doneStatusAlt;
        if (currTask.getDoneStatus().equals("X")) {
            doneStatusAlt = "1";
        } else {
            doneStatusAlt = "0";
        }
        data = currTask.getType() + ", " + doneStatusAlt + ", " + currTask.getName();
        if (currTask.getType().equals("D")) {
            data = data + ", " + appendByDateTimeToData((Deadline) currTask);
        } else if (currTask.getType().equals("E")) {
            data = data + ", " + appendFromToDateTimeToData((Event) currTask);
        }
        return data;
    }

    public String appendByDateTimeToData(Deadline data) {
        return data.getBy();
    }

    public String appendFromToDateTimeToData(Event data) {
        return (data.getFrom() + ", " + data.getTo());
    }
}
