package commands;

/**
 *  Check if the given parameter (the userinput) is an empty command, meaning just the command word alone
 */
public class Command {
    public static boolean commandWithoutDescription(String usersInput) {
        return  usersInput.equals("unmark")   ||
                usersInput.equals("mark")     ||
                usersInput.equals("delete")   ||
                usersInput.equals("todo")     ||
                usersInput.equals("deadline") ||
                usersInput.equals("event");
    }
}
