
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Tickles {

    private Storage storage;
    private Ui ui;
    //protected static int totalTasks = 0;
    //protected static List<Task> list = new ArrayList<>();
    //protected TaskList list;

    private final String LOGO = "╱╭╮╱╱╱╱╭╮╱╭╮\n" +
            "╭╯╰╮╱╱╱┃┃╱┃┃\n" +
            "╰╮╭╋┳━━┫┃╭┫┃╭━━┳━━╮\n" +
            "╱┃┃┣┫╭━┫╰╯┫┃┃┃━┫━━┫\n" +
            "╱┃╰┫┃╰━┫╭╮┫╰┫┃━╋━━┃\n" +
            "╱╰━┻┻━━┻╯╰┻━┻━━┻━━╯\n";

    public Tickles(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui(storage);
        storage.loadTickles();
//        try {
//            //tasks = new TaskList(storage.loadTickles());
//            storage.loadTickles();
//        } catch (StorageException e) { //TODO: define TicklesException class
//            ui.showLoadingError();
//            //tasks = new TaskList();
//        }
    }

    public void run() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(" What can I do for you?");
        ui.promptUser();
    }

    public static void main(String[] args) {
        new Tickles("./data/tickles.txt").run();
    }
}
