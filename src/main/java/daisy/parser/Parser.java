package daisy.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Parser {
    private static String action;
    private static String additionalNotes;

    public Parser(String command) {
        String[] separate_commands = command.split(" ",2);
        action = separate_commands[0];
        if (separate_commands.length < 2) {
            additionalNotes = null;
        }
        else {
            additionalNotes = separate_commands[1];
        }
    }

    public String getAction() {
        return action;
    }

    public int getIndexFromCommand() {
        if (additionalNotes == null) {
            return -1;
        }
        return Integer.parseInt(additionalNotes)-1;
    }

    public String getTodoInfo() {
        return additionalNotes;
    }

    public String[] getDeadlineInfo() {
        if (additionalNotes == null) {
            return new String[] {additionalNotes};
        }
        return additionalNotes.split(" /by ");
    }

    public String[] getEventInfo() {
        if (additionalNotes == null) {
            return new String[] {additionalNotes};
        }
        int from = additionalNotes.indexOf(" /from ");
        int to = additionalNotes.indexOf(" /to ");
        if (from == -1 || to == -1 ) {
            return new String[] {additionalNotes};
        }
        return new String[] {additionalNotes.substring(0, from),additionalNotes.substring(from + " /from ".length(), to), additionalNotes.substring(to+" /to ".length())};
    }
}



