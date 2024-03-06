import java.io.IOException;
import java.util.Scanner;
public class Jane {
public static final String FILE_PATH = "./data/jane.txt";
    public static Storage storage;

    public static void main(String[] args) {
        String LOGO = " _____    _____    ____ _    _____ \n"
                + "|____ |  |     |  |    | |  | ____|\n"
                + "    | |  |  |  |  | |  | |  | |___ \n"
                + " _  | |  |  _  |  | |  | |  |  ___|\n"
                + "| |_| |  | | | |  | |  | |  | |___ \n"
                + "|_____|  |_| |_|  |_| ___|  |_____|\n";
        String SEPARATOR = "____________________________________________________________\n";
        String GREET_MESSAGE = "Hello! I am Jane.\nWhat can I do for you?\n";
        String EXIT_MESSAGE = "Bye. Hope to see you again soon!\n";

        System.out.print(LOGO + SEPARATOR);
        System.out.print(GREET_MESSAGE + SEPARATOR);


        try {
            storage = new Storage(FILE_PATH);
            storage.ensureDataFileExists();
            TaskList taskList = storage.loadTasksToFile();
            Ui ui = new Ui();

            while (!ui.input.equals("bye")) {
                try {
                    System.out.print(SEPARATOR);
                    ui.processInput(ui.input, taskList);
                    System.out.print(SEPARATOR);
                    storage.saveTasksToFile(taskList);
                } catch (JaneException e) {
                    System.out.println(e.getMessage());
                    System.out.print(SEPARATOR);
                }
                ui.nextInput();
            }

            try {
                storage.saveTasksToFile(taskList);
                System.out.print(SEPARATOR + EXIT_MESSAGE + SEPARATOR);
            } catch (IOException e) {
                System.out.println("File does not exist");
            }
        } catch (IOException e) {
            System.out.println("File does not exist");
        } catch (JaneDataCorruptedException e) {
            System.out.println("File does not exist");
        }
    }
}