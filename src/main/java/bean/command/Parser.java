package bean.command;

public class Parser {
    private final String[] fieldValuePairs;
    private final String command;

    public String getValue(String field) {
        for (String item : fieldValuePairs) {
            if (item.startsWith(field) && item.contains(" ")) {
                int indexOfFirstSpace = item.indexOf(" ");
                return item.substring(indexOfFirstSpace + 1).trim();
            }
        }
        return "";
    }

    public String getArgument() {
        int indexOfFirstSpace = command.indexOf(" ");
        if (indexOfFirstSpace == -1) {
            return "";
        } else {
            return command.substring(indexOfFirstSpace + 1).trim();
        }

    }


    public String getCommand() {
        int indexOfFirstSpace = command.indexOf(" ");
        if (indexOfFirstSpace == -1) {
            return command;
        } else {
            return command.substring(0, indexOfFirstSpace).trim();
        }
    }

    public Parser(String line) {
        int indexOfFirstSlash = line.indexOf('/');
        if (indexOfFirstSlash != -1) {
            command = line.substring(0, indexOfFirstSlash);
            fieldValuePairs = line.substring(indexOfFirstSlash + 1).split("/");
            for (String item : fieldValuePairs) {
                item = item.trim();
            }
        } else {
            command = line.trim();
            fieldValuePairs = new String[0];
        }
    }
}
