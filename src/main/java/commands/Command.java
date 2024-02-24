package commands;

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
