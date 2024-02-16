package uwunzhe.handler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import uwunzhe.exceptions.UwunzheException;

public class StorageHandler {
    private static String storageFolderPath = "./data";
    private static String storagePath = "./data/uwunzhe.txt";
    private static File storage;

    public StorageHandler() throws UwunzheException {
        // Create data folder if it does not exist
        if (!new File(storageFolderPath).exists()) {
            new File(storageFolderPath).mkdir();
        }

        // Create data file if it does not exist
        storage = new File(storagePath);
        try {
            storage.createNewFile();
        } catch (IOException | SecurityException e) {
            throw new UwunzheException("Storage file oopsies!");
        }
    }
}
