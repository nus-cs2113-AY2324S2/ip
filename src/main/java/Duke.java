public class Duke {
    private TaskList tasks;
    private Storage storage;

    private Parser parser;
    public Duke(){
        UI.welcome();
        storage = new Storage();
        parser = new Parser();
        tasks = new TaskList(storage.loadFromDisk());
    }

    /**
     * execute Stella
     */
    public void run(){
        parser.takeResponse(tasks);
        UI.bye();
    }

    public static void main (String[] args) {
        Duke bot = new Duke();
        bot.run();
    }
}
