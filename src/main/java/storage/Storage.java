package storage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import task.*;

import static constant.NormalConstant.TASK_SAVE_PATH;
import static constant.NormalConstant.TEMP_TASK_SAVE_PATH;

public class Storage {

    public void readFile(TaskList taskList) throws FileNotFoundException {
        try {
            Files.createDirectories(Paths.get("./data"));
        } catch (IOException e) {
            System.out.println("Fail to create directory!!!\n" + e.getMessage());
        }

        File file = new File(TASK_SAVE_PATH);
        if (!file.exists()) {
            try {
                Files.createFile(Paths.get(TASK_SAVE_PATH));
            } catch (IOException e) {
                System.out.println("Fail to create File!!!\n" + e.getMessage());
            }
            return;
        }

        Scanner fileContent = new Scanner(file);
        while (fileContent.hasNext()) {
            String content = fileContent.nextLine();
            handleFileContent(taskList, content);
        }
        fileContent.close();
    }

    public void handleFileContent(TaskList taskList, String content) {
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
                newTask.changeStatus(Integer.parseInt(detail[1]) == 1);
                break;
            case "D":
                newTask = new Deadline(detail[2], detail[3]);
                newTask.changeStatus(Integer.parseInt(detail[1]) == 1);
                break;
            case "E":
                newTask = new Event(detail[2], detail[3], detail[4]);
                newTask.changeStatus(Integer.parseInt(detail[1]) == 1);
                break;
            default:
                newTask = new ToDo("Corrupted File");
                break;
            }
            taskList.addTask(newTask);
        }
    }

    public String changeTaskToTextFormat(Task currentTask) {
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
        return taskFileFormat;
    }

    public void markTask(Task currentTask) throws IOException {
        File oldFile = new File(TASK_SAVE_PATH);
        File tempFile = new File(TEMP_TASK_SAVE_PATH);

        BufferedReader reader = new BufferedReader(new FileReader(oldFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String lineToChange = changeTaskToTextFormat(currentTask);
        currentTask.changeStatus(true);
        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            if (currentLine.trim().equals(lineToChange)) {
                writer.write(changeTaskToTextFormat(currentTask) + System.lineSeparator());
            }else {
                writer.write(currentLine + System.lineSeparator());
            }
        }

        writer.close();
        reader.close();

        Path taskPath = Paths.get(TASK_SAVE_PATH);
        Path tempTaskPath = Paths.get(TEMP_TASK_SAVE_PATH);

        Files.delete(taskPath);
        Files.copy(tempTaskPath, taskPath);
        Files.delete(tempTaskPath);
    }

    public void unmarkTask(Task currentTask) throws IOException {
        File oldFile = new File(TASK_SAVE_PATH);
        File tempFile = new File(TEMP_TASK_SAVE_PATH);

        BufferedReader reader = new BufferedReader(new FileReader(oldFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String lineToChange = changeTaskToTextFormat(currentTask);
        currentTask.changeStatus(false);
        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            if (currentLine.trim().equals(lineToChange)) {
                currentTask.changeStatus(false);
                writer.write(changeTaskToTextFormat(currentTask) + System.lineSeparator());
            } else {
                writer.write(currentLine + System.lineSeparator());
            }
        }

        writer.close();
        reader.close();

        Path taskPath = Paths.get(TASK_SAVE_PATH);
        Path tempTaskPath = Paths.get(TEMP_TASK_SAVE_PATH);

        Files.delete(taskPath);
        Files.copy(tempTaskPath, taskPath);
        Files.delete(tempTaskPath);
    }

    public void deleteTask(Task currentTask) throws IOException {
        File oldFile = new File(TASK_SAVE_PATH);
        File tempFile = new File(TEMP_TASK_SAVE_PATH);

        BufferedReader reader = new BufferedReader(new FileReader(oldFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String lineToRemove = changeTaskToTextFormat(currentTask);
        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            if (currentLine.trim().equals(lineToRemove)) {
                continue;
            }
            writer.write(currentLine + System.lineSeparator());
        }

        writer.close();
        reader.close();

        Path taskPath = Paths.get(TASK_SAVE_PATH);
        Path tempTaskPath = Paths.get(TEMP_TASK_SAVE_PATH);

        Files.delete(taskPath);
        Files.copy(tempTaskPath, taskPath);
        Files.delete(tempTaskPath);
    }

    public void saveTask(Task currentTask) throws IOException {
        FileWriter fileWriter = new FileWriter(TASK_SAVE_PATH, true);
        String taskFileFormat = changeTaskToTextFormat(currentTask);
        fileWriter.write(taskFileFormat + System.lineSeparator());
        fileWriter.close();
    }
}
