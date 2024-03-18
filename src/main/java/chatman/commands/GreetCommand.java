package chatman.commands;


/**
 * Implements initial ChatMan greeting when user runs program.
 *
 * @author LWachtel1
 * */
public class GreetCommand extends Command {

    /**
     * Constructor for GreetCommand; invokes superclass constructor.
     *
     * @param userCommand String parameter required as result of being extended from Command abstract superclass.
     *
     * */
    public GreetCommand(String userCommand) {
        super(userCommand);
    }

    /**
     * Prints greeting for ChatMan user upon initial program execution; cannot be invoked by user-entered command
     * after this point.
     *
     */
    @Override
    public void perform() {
        System.out.printf("%s%n", "____________________________________________________________");
        System.out.printf("%s%n", "Hello! I'm CHAT-MAN");
        System.out.printf("%s%n", "What can I do for you?");
    }
}
