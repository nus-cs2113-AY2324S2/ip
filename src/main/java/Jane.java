import java.io.IOException;
import java.util.Scanner;
public class Jane {
    public static final String FILE_PATH = "./data/jane.txt";
    public static Storage storage;

    public static void main(String[] args) {
        String Logo = " _____    _____    ____ _    _____ \n"
                + "|____ |  |     |  |    | |  | ____|\n"
                + "    | |  |  |  |  | |  | |  | |___ \n"
                + " _  | |  |  _  |  | |  | |  |  ___|\n"
                + "| |_| |  | | | |  | |  | |  | |___ \n"
                + "|_____|  |_| |_|  |_| ___|  |_____|\n";
        String Separator = "____________________________________________________________\n";
        String Greet_Message = "Hello! I am Jane.\nWhat can I do for you?\n";
        String Exit_Message = "Bye. Hope to see you again soon!\n";

        System.out.print(Logo + Separator);
        System.out.print(Greet_Message + Separator);

        try {
            storage = new Storage(FILE_PATH);
            storage.ensureDataFileExists();
            TaskList taskList = storage.loadTasksToFile();
            Ui ui = new Ui();

            while (!ui.input.equals("bye")) {
                try {
                    System.out.print(Separator);
                    ui.processInput(ui.input, taskList);
                    System.out.print(Separator);
                    storage.saveTasksToFile(taskList);
                } catch (JaneException e) {
                    System.out.println(e.getMessage());
                    System.out.print(Separator);
                }
                ui.nextInput();
            }

            try {
                storage.saveTasksToFile(taskList);
                System.out.print(Separator + Exit_Message + Separator);
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