package memory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import static constant.NormalConstant.TASK_SAVE_PATH;

public class FileAccess {

    public void readFile(ArrayList<Task> list) throws FileNotFoundException {
        try {
            Files.createDirectories(Paths.get("./data"));
        } catch (IOException e) {
            System.out.println("Fail to create directory!!!" + e.getMessage());
        }

        File file = new File(TASK_SAVE_PATH);
        if (!file.exists()) {
            try {
                Files.createFile(Paths.get(TASK_SAVE_PATH));
            } catch (IOException e) {
                System.out.println("Fail to create File!!!" + e.getMessage());
            }
            return;
        }
        Scanner fileContent = new Scanner(file);
        while (fileContent.hasNext()) {
            String content = fileContent.nextLine();
            handleFileContent(list, content);
        }
    }

    public void handleFileContent(ArrayList<Task> list, String content) {
        String[] detail = content.split(" [|] ");
        int index = 0;
        if (!(detail.length >= 3 && detail.length <= 5)) {
            index++;
            System.out.println(index + " line of data is corrupted and cannot be read");
        } else {
            Task newTask;
            switch (detail[0]) {
            case "T":
                newTask = new ToDo(detail[2]);
                newTask.changStatus(Integer.parseInt(detail[1]) == 1);
                break;
            case "D":
                newTask = new Deadline(detail[2], detail[3]);
                newTask.changStatus(Integer.parseInt(detail[1]) == 1);
                break;
            case "E":
                newTask = new Event(detail[2], detail[3], detail[4]);
                newTask.changStatus(Integer.parseInt(detail[1]) == 1);
                break;
            default:
                newTask = new ToDo("Corrupted File");
                break;
            }
            list.add(newTask);
        }
    }

    public void saveFile(Task currentTask) throws IOException {
        FileWriter fileWriter = new FileWriter(TASK_SAVE_PATH, true);
        String separator = " | ";
        String taskFileFormat = "";

        taskFileFormat += currentTask.getIdentity().charAt(1);
        taskFileFormat += separator;
        taskFileFormat += (currentTask.getIsDone() ? "1" : "0");
        taskFileFormat += separator;
        taskFileFormat += currentTask.getDescription();

        switch (currentTask.getIdentity().charAt(1)) {
        case 'D':
            taskFileFormat += separator;
            taskFileFormat += currentTask.getBy();
            break;
        case 'E':
            taskFileFormat += separator;
            taskFileFormat += currentTask.getFrom();
            taskFileFormat += separator;
            taskFileFormat += currentTask.getTo();
            break;
        }
        fileWriter.write(taskFileFormat + System.lineSeparator());
        fileWriter.close();
    }
}
