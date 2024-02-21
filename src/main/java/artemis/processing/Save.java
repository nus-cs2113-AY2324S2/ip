package artemis.processing;

import artemis.errors.Errors;
import artemis.tasks.Deadline;
import artemis.tasks.Event;
import artemis.tasks.Task;
import artemis.tasks.ToDo;
import artemis.ui.UserInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
    save format:
    first line: username
    following lines: task data
    taskData format: [T/D/E] | [M/U] | TaskName | Other Info (due, from/to)
 */

public class Save {
    private static final String SAVE_FILEPATH = "./save.txt";

    public static void loadSave() throws Errors.CorruptedSaveException, FileNotFoundException {
        File saveFile = new File(SAVE_FILEPATH);

        Scanner fileScan = new Scanner(saveFile);

        boolean usernameFound = false;
        while (fileScan.hasNext()) {
            if (!usernameFound) {
                UserInterface.username = fileScan.nextLine();
                usernameFound = true;
            }

            try {
                String[] currentTaskArray = fileScan.nextLine().split(" \\| ");
                String taskName = currentTaskArray[2];
                boolean isDone = currentTaskArray[1].equals("M");
                Task currentTask = Parser.parseSaveData(currentTaskArray, taskName, isDone);
                TaskHandler.taskList[TaskHandler.listCount] = currentTask;
                TaskHandler.listCount++;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new Errors.CorruptedSaveException();
            }


        }

        System.out.println("[artemis]: saved data successfully retrieved!");
    }

    public static void saveData() {
        try (FileWriter fw = new FileWriter(SAVE_FILEPATH, false)) {
            fw.write(UserInterface.username + System.lineSeparator());

            for (int index = 0; index < TaskHandler.listCount; index++) {
                Task currentTask = TaskHandler.taskList[index];

                Class<? extends Task> currentClass = currentTask.getClass();
                String currentOutput = formatSaveData(currentTask, currentClass);

                fw.write(currentOutput);
            }

            System.out.println("[artemis]: successfully saved data!");

        } catch (IOException e) {
            System.out.println("IO ERROR" + e);
        }

    }

    private static String formatSaveData(Task currentTask, Class<? extends Task> currentClass) {
        String currentOutput = "";
        String currentTaskName = currentTask.getTaskName();
        String isDoneString = currentTask.getIsDone() ? "M" : "U";

        if (currentClass.equals(ToDo.class)) {
            currentOutput = String.format("T | %s | %s\n", isDoneString, currentTaskName);
        } else if (currentClass.equals(Deadline.class)) {
            currentOutput = String.format("D | %s | %s | %s\n", isDoneString, currentTaskName,
                    ((Deadline) currentTask).getDueDate());
        } else if (currentClass.equals(Event.class)) {
            currentOutput = String.format("E | %s | %s | %s | %s\n", isDoneString, currentTaskName,
                    ((Event) currentTask).getStartDateTime(), ((Event) currentTask).getEndDateTime());
        }
        return currentOutput;
    }
}
