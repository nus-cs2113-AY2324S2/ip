import java.io.File;
import java.io.IOException;

public class CreateFile {
    public static void main(String[] args) {
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
