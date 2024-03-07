import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    /**
     * loads ArrayList<Task> tasks with tasks saved on TaskList.txt
     * @param tasks contains the list of tasks
     * @param filePath the path to TaskList.txt saved on the local machine
     * @param index the number of tasks saved in TaskList.txt
     * @return index the number of tasks saved in TaskList.txt
     * @throws IOException exception when local TaskList.txt file does not exist
     * @throws UnexpectedCommandException exception when the format or details of the tasks are not followed and provided respectively
     */
    public static int fillFileContents(ArrayList<Task> tasks, String filePath, int index) throws IOException, UnexpectedCommandException {//updates index
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        while (s.hasNext()) {

            String sLine = s.nextLine();

            if (sLine.contains("[E]")) {
                TaskList.dealWithEvent(tasks, index, sLine, true);
            } else if (sLine.contains("[D]")) {
                TaskList.dealWithDeadline(tasks, index, sLine, true);
            } else if (sLine.contains("[T]")) {
                TaskList.dealWithTodo(tasks, index, sLine, true);
            }

            index++;
        }
        boolean isInTxt = false;
        return index;
    }

    /**
     * saves the updated tasks list onto TaskList.txt in the local machine
     * @param tasks the array of tasks
     * @param index the nymber of tasks in array of tasks
     * @throws IOException exception when local TaskList.txt file does not exist
     */
    protected static void saveToFile(ArrayList<Task> tasks, int index) throws IOException{
        new FileWriter("TaskList.txt", false).close();
        for (int TaskIndex = 0; TaskIndex < index; TaskIndex ++) {
            writeToFile("TaskList.txt", tasks.get(TaskIndex));
        }
    }

    /**
     * adds additional tasks into TaskList.txt
     * @param filePath the path to TaskList.txt saved on the local machine
     * @param textToAdd the new task to add
     * @throws IOException exception when local TaskList.txt file does not exist
     */
    static void writeToFile(String filePath, Task textToAdd) throws IOException{
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd + "\n");
        fw.close();
    }
}
