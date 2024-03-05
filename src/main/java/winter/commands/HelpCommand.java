package winter.commands;

public class HelpCommand extends Command {
    public HelpCommand() {
        System.out.println("Valid commands are <todo>, <deadline>, <event>, <list>,<delete>");
        System.out.println("For <todo>, he correct format should be 'todo (task)'");
        System.out.println("For <deadline>, the correct format should be 'deadline (task) by/ (deadline)'");
        System.out.println("For <event>, the correct format should be 'event (task) /from (start time) /to (end time)'");
        System.out.println("For <delete> the correct format should be 'delete (task number)'");

    }
}
