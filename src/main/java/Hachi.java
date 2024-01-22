/**
 * This program currently starts the chatbot with a greeting,
 * and ends off the program with a goodbye message.
 *
 * @author clarencepohh
 * @version 23/01/2024
 */
public class Hachi {
    /**
     * Prints a greeting to the user in the console
     * with the bot's name, Hachi.
     */
    public static void greet() {
        String logo = "._. ._.  ._____.  ._____.  ._. ._.  ._.\n"
                + "| | | |  | ._. |  |  ___|  | | | |  | |\n"
                + "| |_| |  | |_| |  | |      | |_| |  | |\n"
                + "| ._. |  | ._. |  | |___   |  _  |  | |\n"
                + "|_| |_|  |_| |_|  |_____|  |_| |_|  |_|\n";

        System.out.println("Hey, Hachi Here!\n" + logo + "How can I assist you today?\n");
        spacerInsert("medium");
    }

    /**
     * Prints to the console a spacer line made of tildes.
     *
     * @param length The desired length of the spacer line. Medium is chosen by default.
     */
    public static void spacerInsert(String length) {
        String spacer;
        switch (length) {
        case "small": // 20 tildes
            spacer = "~~~~~~~~~~~~~~~~~~~~";
            break;
        case "medium": // 40 tildes
        default:
            spacer = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
            break;
        case "large": // 60 tildes
            spacer = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
            break;
        }
        System.out.println(spacer);
    }

    /**
     * Prints to the console a goodbye message for the user.
     */
    public static void goodbye() {
        System.out.println("Goodbye! Hope you have a marvelous day.");
        spacerInsert("medium");
    }

    /**
     * The main program that starts and ends the chatbot.
     * Prints to the console for the user to read its messages.
     *
     * @param args Command line arguments - not used.
     */
    public static void main(String[] args) {
        spacerInsert("medium");
        greet();
        goodbye();
    }
}
