import java.io.File;
import java.io.IOException;

/**
 * creates local TaskList.txt file on the local machine
 */
public class CreateFile {
    public static void CreateFile(){
        File f = new File("TaskList.txt");
        if (!f.exists()) {
            try {
                if (f.createNewFile()) {
                    System.out.println("File created: " + f.getAbsolutePath());
                } else {
                    System.out.println("File creation failed.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else {
            System.out.println("File already exists: " + f.getAbsolutePath());
        }
    }
}
