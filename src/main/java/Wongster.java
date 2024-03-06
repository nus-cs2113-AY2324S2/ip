import java.io.IOException;

public class Wongster {

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Ui ui = new Ui(taskList);
        ui.start();
    }
}