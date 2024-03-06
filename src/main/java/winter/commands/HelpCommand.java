package winter.commands;

/**
 * Displays a help message showing all the possible commands
 */
public class HelpCommand extends Command {
    public HelpCommand() {
        System.out.println("Valid commands are <todo>, <deadline>, <event>, <list>,<delete>");
        System.out.println("For <todo>, the correct format should be 'todo (task)'");
        System.out.println("For <deadline>, the correct format should be 'deadline (task) by/ (deadline)'" +
                    "The deadline must be in the format yyyy-mm-dd HH:mm");
        System.out.println("For <event>, the correct format should be 'event (task) /from (start time) /to (end time)'");
        System.out.println("For <delete>, the correct format should be 'delete (task number)'");
        System.out.println("For <find>, the correct format should be 'find (keyword)'");

    }
}
