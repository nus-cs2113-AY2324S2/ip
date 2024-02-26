import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

public class JigaChat {
    static protected DataFile previousData = new DataFile();
    static protected Parser Parser = new Parser();

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
                System.out.println("the hell?");
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
                }
            }
        }
        while (1 == 1) {
            String input;
            Scanner in = new Scanner(System.in, StandardCharsets.UTF_8);
            input = in.nextLine();
            Parser.readCommand(input, chat);
        }
    }
}

