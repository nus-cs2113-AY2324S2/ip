package utilities.file;

import main.Aragorn;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;
import ui.Constants;
import utilities.commands.DisplayList;
import java.io.IOException;
import java.util.List;

public class FileReader {
    public static void readFile() throws IOException {
        try {
            List<String> entries = FileHandler.readFile();
            Aragorn.getList().clear();

            for (String entry : entries) {
                Task task = formatEntry(entry);
                Aragorn.getList().add(task);
            }

            if (Aragorn.getList().isEmpty()) {
                return;
            }

            DisplayList.listCommand();
         } catch (IOException e) {
            System.out.println(Constants.NOFILE);
        }
    }

    private static Task formatEntry(String entry) {
        Task newTask;
        try {
            String[] formattedEntry = entry.split(Constants.BAR);
            String taskType = formattedEntry[0].toUpperCase();
            boolean done = formattedEntry[1].equals(Constants.ONE);
            switch (taskType) {
                case Constants.TODO:
                    newTask = new ToDo(formattedEntry[2], done);

                    break;

                case Constants.DEADLINE:
                    newTask = new Deadline(formattedEntry[2], done, formattedEntry[3]);
                    break;

                case Constants.EVENT:
                    newTask = new Event(formattedEntry[2], done, formattedEntry[3], formattedEntry[4]);
                    break;

                default:
                    return Constants.taskTypeError(taskType);
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(Constants.FILEERROR);
            return null;
        }
        return newTask;
    }
}
