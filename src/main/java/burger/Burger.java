package burger;

import burger.Storage.BurgerFileClass;
import burger.TaskList.TaskList;
import burger.UI.Utilities;

import static burger.Parser.Parser.parseUserInput;
import static burger.Storage.BurgerFileClass.DEFAULT_PATHNAME;


public class Burger {

    public static final String CHATBOT_NAME = "Burger";

    private BurgerFileClass storage;
    private TaskList tasks;
    private Utilities ui;

    public Burger(String filePath) {
        ui = new Utilities();
        tasks = new TaskList();
        storage = new BurgerFileClass(filePath);
    }

    public void run() {
        ui.welcomeMessage(CHATBOT_NAME);
        parseUserInput(tasks, ui);
        storage.setSaveFile(tasks);
        ui.goodbye();
    }

    public static void main(String[] args) {
        new Burger(DEFAULT_PATHNAME).run();
    }
}