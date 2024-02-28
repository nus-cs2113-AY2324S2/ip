package omoh;

import java.io.File;

public class Storage {


    //function creates directory for output.txt file if it is not present
    public static void createFileDirectory () {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdirs(); // This will create the necessary directories
        }
    }
}
