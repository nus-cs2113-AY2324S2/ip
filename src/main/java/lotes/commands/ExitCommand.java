package lotes.commands;

import lotes.ui.UserInterface;

public class ExitCommand extends Command {
    public static final String EXIT_COMMAND_WORD = "exit";
    public static final String BYE_COMMAND_WORD = "bye";

    public void run() {
        UserInterface.showGoodbyeMessage();
    }

    @Override
    public void execute() {
        System.out.println("BYEEEE");
    }
}
