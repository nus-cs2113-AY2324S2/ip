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

    public static String extractTodoDescription(String userInput){
        String[] words = userInput.split(" ");
        return words[1];
    }
}
