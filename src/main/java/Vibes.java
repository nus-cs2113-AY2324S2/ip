import java.util.Scanner;

public class Vibes {
    public static void main(String[] args) {
        String name = "Vibes";
        String line = "\t---------------------------------------------------------------------------------------";
        String userInput;
        Scanner in = new Scanner(System.in);

        System.out.println(line);
        System.out.println("\t Hello! I'm " + name + "\n\t What can I do for you?");
        System.out.println(line);

        while(true){
            userInput = in.nextLine().trim();
            if(userInput.equalsIgnoreCase("bye")){
                break;
            }
            System.out.println(line);
            System.out.println("\t " + userInput);
            System.out.println(line);
        }

        System.out.println(line);
        System.out.println("\t Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}