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
        storage.tryRetrieveList();
        System.out.println(MoodSprite.getIdle() + "Hello, Im Pythia, input 'help' if you would like to know what I can do!\n"+ MoodSprite.getLineBreak());
        input.mainLoop();
        System.out.println(MoodSprite.getHappy()+"Happy to help, have a great day.\n"+ MoodSprite.getLineBreak());
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
