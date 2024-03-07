/**
 * This is the chatbot.
 */

public class Duke {
    private TaskList tasks;
    private Storage storage;

    public Duke(){
        UI.welcome();
        storage = new Storage();
        tasks = new TaskList(storage.loadFromDisk());
    }

    public void run(){
        Parser.takeResponse(tasks);
        UI.bye();
    }

    public static void main (String[] args) {
        Duke bot = new Duke();
        bot.run();
    }
}
