import java.util.Scanner;

public class Parser {


    public static String getFirstWord(String userInput){
        return userInput = userInput.split(" ")[0];
    }

    public static String getUserInput(){
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }

    public static int getFirstInt(String userInput){
        String numberString = "0";
        for (int i = 0; i < userInput.length(); i++) {
            if (Character.isDigit(userInput.charAt(i))) {
                numberString = userInput.substring(i);
                break;
            }
        }
        return Integer.parseInt(numberString);
    }

    public static String extractDescription(String userInput){
        try {
            int start = userInput.indexOf(" ") + 1;
            int end = userInput.indexOf("/") - 1;
            if (start > 0 && (end > start)) {
                return userInput.substring(start, end);
            } else if (start > 0) {
                return userInput.substring(start);
            } else {
                throw new JeffException.InvalidInputException("YOU NEED A DESCRIPTION");
            }
        } catch (JeffException.InvalidInputException e) {
            throw new IllegalArgumentException("ERROR IN THE INPUT STRING");
        }
    }

    public static String extractStartTime(String userInput){
        String[] words = userInput.split(" ");
        String startTime = null;

        for (String word : words) {
            if (word.startsWith("/")) {
                startTime = word;
                break;
            }
        }
        return startTime.replace("/", "");
    }


    public static String extractEndTime(String userInput) {
        String[] words = userInput.split(" ");
        int importantPartsFound = 0;
        String endTime = null;


        for (String word : words) {
            if (word.startsWith("/")) {
                importantPartsFound++;
                if (importantPartsFound == 2) {
                    endTime = word;
                    break;
                }
            }
        }
        return endTime.replace("/", "");
    }
}
