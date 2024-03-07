package anonbot.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import anonbot.exception.ExportDataException;
import anonbot.misc.ExportParser;
import anonbot.task.Task;
import anonbot.task.TaskManager;

/**
 * A static class that save the tasks to a file in the default save location.
 */
public class AnonBotFileWriter extends AnonBotFile {
    /**
     * Writes individual tasks to the file in the default save location.
     *
     * @param writer A valid FileWriter to write the tasks to the file.
     */
    private static void writeIndividualTasks(FileWriter writer) {
        for (Task task : TaskManager.getTaskList()) {
            try {
                String data = ExportParser.convertTaskToCommandlineFormat(task);
                writer.write(data);
                writer.write(System.lineSeparator());
            } catch (IOException e) {
                System.out.println("Error: Unable to write to file");
                e.printStackTrace();
            } catch (ExportDataException e) {
                e.printErrorMessage();
            }
        }
    }

    /**
     * Writes the total number of tasks created to date to the file in the default save location,
     * and then writes the tasks from the tasklist to the file.
     */
    private static void writeData() {
        FileWriter writer;
        try {
            writer = new FileWriter(getDefaultFileName());
            String taskNumber = String.valueOf(TaskManager.getNumberOfActiveTasks());
            writer.write(taskNumber);
            writer.write(System.lineSeparator());
            writeIndividualTasks(writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: Unable to write to file");
            e.printStackTrace();
        }
    }

    /**
     * Creates a default save file and save all data related to the tasks in the task list.
     */
    public static void saveAnonBotData() {
        File f = new File(getDefaultFileName());
        try {
            f.delete();
            f.createNewFile();
            writeData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Error: Unable to create file");
        }
    }
}
