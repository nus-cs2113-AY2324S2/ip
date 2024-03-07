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

    /**
     * Stores the reformatted entries read from the file into main Array list.
     *
     * @throws IOException When I/O error occurs.
     */
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

            DisplayList.listCommand(Aragorn.getList());
            System.out.println(Constants.LINE);
         } catch (IOException e) {
            System.out.println(Constants.NOFILE);
        }
    }

    /**
     * Splits the unformatted entry at the "|" characters to obtain
     * formatted task details and stores them in a String Array.
     *
     * @param entry Unformatted entry read in from the file.
     * @return Array containing the formatted task entry.
     */
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
