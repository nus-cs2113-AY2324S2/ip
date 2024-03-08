import java.io.File;
import java.io.IOException;

/**
 * creates local TaskList.txt file on the local machine
 */
public class CreateFile {
    protected static Ui ui;
    public static void CreateFile() {
        File file = new File("TaskList.txt");
        String errorDescription;
        String fileStatus;

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    fileStatus = "File created";
                    System.out.println(fileStatus + ": " + file.getAbsolutePath());
                } else {
                    errorDescription = "File creation failed.";
                    ui.errorMessage(errorDescription);
                }
            } catch (IOException e) {
                errorDescription = "An I/O error occurred.";
                ui.errorMessage(errorDescription);
                e.printStackTrace();
            }
        } else {
            fileStatus = "File already exists";
            System.out.println(fileStatus + ": " + file.getAbsolutePath());
        }
    }
}
