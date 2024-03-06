package duke;
public class Duke {
    private UI input;
    private Storage storage;

    public Duke() {
        input = new UI();
        storage = new Storage();
    }

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
