package duke;

/**
 * Main class of the programme
 */
public class Duke {
    private UI input;
    private Storage storage;

    /**
     * Constructor class to instantiate variables
     */
    public Duke() {
        input = new UI();
        storage = new Storage();
    }

    /**
     * Main logic flow of the programme
     */
    public void run() {
        System.out.println(MoodSprite.getIdle() + "Hello, Im Pythia, how may I help you today?\n"+ MoodSprite.getLineBreak());
        storage.tryRetrieveList();
        input.mainLoop();
        System.out.println(MoodSprite.getHappy()+"Happy to help, have a great day.\n"+ MoodSprite.getLineBreak());
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
