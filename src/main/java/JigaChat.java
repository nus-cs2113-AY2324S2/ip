import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

public class JigaChat {
    static protected DataFile previousData = new DataFile();
    static protected Parser Parser = new Parser();

    /**
     * Initialises the user's previous data from the previousData text file
     */
    private void initialisePreviousData() {
        try {
            String input;
            Scanner readPreviousData = new Scanner(previousData.data);
            input = readPreviousData.nextLine();
            TaskHandler.taskCounter = Integer.parseInt(input);
            while (readPreviousData.hasNext()) {
                input = readPreviousData.nextLine();
                Parser.readCommandWithoutPrints(input);
            }
        }
        catch(FileNotFoundException e) {
        }
    }

    /**
     * Starts the chatbot, initialises the previous data as well as handles certain prints that are only used on
     * startup
     * @param args not expected
     */
    public static void main(String[] args) {
        System.out.println("Hello! I'm JigaChat");
        System.out.println("What can I do for you? Type [help] to learn how to use JigaChat!");
        JigaChat chat = new JigaChat();
        if (!chat.previousData.data.exists()) {
            System.out.println("No existing data was found!");
            try {
                if (chat.previousData.data.createNewFile()) {
                    System.out.println("New data file created");
                    chat.previousData.writeToFile("0");
                }
                else {
                    System.out.println("File creation failed");
                }
            }
            catch(IOException e) {
                System.out.println("File writing/reading failed!");
            }
        }
        else {
            try {
                chat.initialisePreviousData();
                System.out.println("Data from your previous session was loaded");
            }
            catch(NoSuchElementException e) {
                try {
                System.out.println("Previous Data corrupted and will be deleted!");
                    chat.previousData.writeToFile("0");
                }
                catch(IOException ex) {
                    System.out.println("File writing/reading failed!");
                }
            }
        }
        while (1 == 1) {
            String input;
            Scanner in = new Scanner(System.in, StandardCharsets.UTF_8);
            input = in.nextLine();
            Parser.readCommand(input);
        }
    }
}

