import java.util.Scanner;

public class Vibes {
    public static void main(String[] args) {
        String name = "Vibes";
        String line = "\t---------------------------------------------------------------------------------------";
        String userInput;
        String vibesOutput = "";
        boolean isExit = true;
        Scanner in = new Scanner(System.in);
        List taskList = new List();

        System.out.println(line);
        System.out.println("\t Hello! I'm " + name + "\n\t What can I do for you?");
        System.out.println(line);

        while(isExit){
            userInput = in.nextLine().trim();

            switch (userInput){
            case "bye":
                vibesOutput = "Bye. Hope to see you again soon!";
                isExit = false;
                break;
            default:
                taskList.addTask(userInput);
                vibesOutput = "added: " + userInput;
                break;
            }
            System.out.println(line);
            System.out.println("\t " + vibesOutput);
            System.out.println(line);
        }
    }
}