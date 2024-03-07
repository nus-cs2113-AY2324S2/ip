package anonbot.data;

import anonbot.exception.ExportDataException;
import anonbot.misc.ExportParser;
import anonbot.task.Task;
import anonbot.task.TaskManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AnonBotFileWriter extends AnonBotFile {
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
