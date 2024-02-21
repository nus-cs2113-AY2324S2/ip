package anonbot.data;

import anonbot.exception.InvalidTaskException;
import anonbot.misc.ExportParser;
import anonbot.task.Task;
import anonbot.task.TaskManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class AnonBotFileWriter {
    private static void writeIndividualTask(FileWriter writer) {
        for (Task task : TaskManager.getTaskList()){
            try{
                String data = ExportParser.convertTaskToCommandlineFormat(task);
                writer.write(data);
                writer.write(System.lineSeparator());
            } catch (IOException e){
                System.out.println("Unable to write to file");
                e.printStackTrace();
            } catch (InvalidTaskException e) {
                e.printErrorMessage();
            }

        }
    }

    private static void writeData() {
        FileWriter writer;
        try {
            writer = new FileWriter(AnonBotFile.FILE_NAME);
            String taskNumber = String.valueOf(TaskManager.getNumberOfActiveTasks());
            writer.write(taskNumber);
            writer.write(System.lineSeparator());
            writeIndividualTask(writer);
            writer.close();

        } catch (IOException e) {
            System.out.println("Unable to write to file");
            e.printStackTrace();
            return;
        }

    }

    public static void saveAnonBotData(){
        File f = new File(AnonBotFile.FILE_NAME);
        try {
            f.delete();
            f.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Unable to create file");
        }
        writeData();
    }


}
