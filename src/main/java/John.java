import Backend.Command;
import Backend.DataManager;
import Backend.Parser;
import Backend.Ui;
import tasks.Task;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;

public class John {

    private List<Task> tasks;

    public John(String filePath) {
        try {
            tasks = DataManager.readData(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new John("data.txt").run();
    }

    public void run() {
        Ui.WelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        boolean isDone = false;
        while (!isDone) {
            try {
                Ui.ReadCommand();
                Command c = Parser.parse(scanner);
                c.execute(tasks);
                isDone = c.isDone();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                Ui.showLine();
            }
        }
    }
}
