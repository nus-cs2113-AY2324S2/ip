package jake;
import java.util.Scanner;
import java.io.File; 
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

// import jake.storage.Storage;
import jake.task.TaskList;
import jake.ui.Ui;



public class Jake {

    static final String savedTaskFilePath = "./ip/src/main/java/jake/data/tasks.txt";
    
    private static Ui ui = new Ui();
    private static TaskList tasks = new TaskList();
    // private static Storage storage = new Storage(savedTaskFilePath);

    // static final String home = System.getProperty("user.home");
    // static final java.nio.file.Path savedTaskFilePath = java.nio.file.Paths.get(home, "ip", "src", "main", "java", "jake", "data");

    // private static void saveTasks() {
    //     try {
    //         FileWriter writer = new FileWriter(savedTaskFilePath);
    //         for (int i = 0; i < commands.size(); i++){
    //             writer.write(commands.get(i) + System.lineSeparator());
    //         }
    //         writer.close();
    //     } catch (IOException e) {
    //         System.out.printf(MESSAGE_TASK_ERROR_ENCOUNTERED, e.getMessage());
    //     }
    // }

    // private static void loadTasks() {
    //     try {
    //         File savedFile = new File(savedTaskFilePath);
    //         Scanner savedFileScanner = new Scanner(savedFile);
    //         while (savedFileScanner.hasNext()) {
    //             String userInput = savedFileScanner.nextLine();
    //             char taskType = userInput.charAt(1);
    //             // addSavedTask(userInput, taskType);                
    //         }
    //         savedFileScanner.close();
    //     } catch (FileNotFoundException e) {
    //         System.out.println(MESSAGE_INVALID_FILEPATH);
    //     }
    // }

    public static void main(String[] args) throws JakeException {
        // loadTasks();
        ui.showGreeting();
        Scanner myScanner = new Scanner(System.in);
        String userInput= "";

        do {
            userInput = myScanner.nextLine();
            String taskType; 
            // Check if the userInput contains a space, to handle inputs like "list" and "bye";
            if (userInput.contains(" ")) {
                taskType = userInput.substring(0, userInput.indexOf(" "));
            } else {
                taskType = userInput;
            }

            switch (taskType) {
            case "bye":
                ui.showGoodbye();
                break;
            case "list":
                tasks.listTasks();
                break;
            case "mark":
            case "unmark":
                tasks.toggleTask(userInput, taskType);
                // tasks.saveTasks();
                break;
            case "delete":
                tasks.deleteTask(userInput);
            case "todo":
            case "deadline":
            case "event":
                try {
                    tasks.addInputtedTask(userInput, taskType);
                    // tasks.saveTasks();
                    break;
                } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                    ui.showInvalidCommand();
                    break;
                } 
            default:
                ui.showInvalidCommand();
            }
        } while (!userInput.equals("bye"));

        myScanner.close();
    }
}