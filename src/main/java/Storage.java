import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
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

    protected static void saveToFile(ArrayList<Task> tasks, int index) throws IOException{
        new FileWriter("TaskList.txt", false).close();
        for (int TaskIndex = 0; TaskIndex < index; TaskIndex ++) {
            writeToFile("TaskList.txt", tasks.get(TaskIndex));
        }
    }

    static void writeToFile(String filePath, Task textToAdd) throws IOException{
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd + "\n");
        fw.close();
    }
}
