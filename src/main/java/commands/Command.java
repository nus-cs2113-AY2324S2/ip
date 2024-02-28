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

    public static boolean isACommandWithDateTime(String usersInput) {
        return usersInput.startsWith("deadline") || usersInput.startsWith("event");
    }
    public static boolean correctDateTimeFormat(String userInput) {
        if (userInput.startsWith("deadline")) {
            String lengthOfDateTimeString = userInput.substring(userInput.indexOf("/by") + 3).strip();
            return countDashes(userInput) >= 2 && lengthOfDateTimeString.length() == 15 ;
        } else if (userInput.startsWith("event")) {
            String lengthOfFirstDateTime = userInput.substring(userInput.indexOf("/from") + 5, userInput.indexOf("/to")).strip();
            String lengthOfSecondDateTime = userInput.substring(userInput.indexOf("/to") + 4).strip();
            return countDashes(userInput) >= 4 && lengthOfFirstDateTime.length() == 15 && lengthOfSecondDateTime.length() == 15;
        } else {
            return false;
        }
    }

    private static int countDashes(String input) {
        int count = 0;

        // Loop through each character in the input string
        for (int i = 0; i < input.length(); i++) {
            // Check if the character is "/"
            if (input.charAt(i) == '-') {
                count++;
            }
        }

        return count;
    }
}
