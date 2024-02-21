import java.io.File;
import java.io.IOException;

public class FileLoader {
    protected static final String DATA_PATH = "./data/tonytask.txt";
    protected static final String SEPARATOR = " | ";
    public static void checkFileExists() throws IOException {
        File directory = new File("./data/");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File("tonytask.txt");
        if (!file.exists()) {
            System.out.println("File not found. File will be created!");
            file.createNewFile();
        }
    }
}
