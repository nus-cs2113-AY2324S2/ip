/**
 * Represents an ui that interacts and respond to the user on the CLI through its user interface methods.
 */
public class UI {
    protected boolean ifExit; //exits program if true

    /**
     * Sets ifExit to "false" to maintain while loop until "bye" command is given.
     */
    public UI (){
        this.ifExit = false;
    }

    /**
     * Prints out the ui logo when file is run
     */
    public void logo () {
        String logo
                = " _______    ___           _____   _____ ___    ___\n"
                + "|   ____\\___\\  \\___    __/   __| /   __|\\  \\__/  /\n"
                + " \\   \\   \\___  ____\\__|__    ___|    ___|\\_   __/\n"
                + "  \\   \\     |  | /  _  \\|   |    |  |     /  / \n"
                + " __\\   \\    |  ||    __/|   |    |  |  __/  / \n"
                + "/_______|   |__| \\_____||___|    |__| |____/ \n";
        System.out.println("--------------------------------------");
        System.out.println(logo);
        System.out.println("--------------------------------------");
    }

    /**
     * Gives users a greeting after logo before receiving commands from users
     */
    public void greeting () {
        System.out.println("Hello! I'm Steffy");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------------");
    }

    /**
     * Bids goodbye before exiting program to the user when "bye" command is given to exit program.
     */
    public void printBye () {
        System.out.println("--------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------------");
        ifExit = true;
    }

}
