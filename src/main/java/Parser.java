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
        String[] words = userInput.split(" ");
        return words[1];
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
