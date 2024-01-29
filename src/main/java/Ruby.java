import java.util.Scanner;

public class Ruby {
    public static void main(String[] args) {
        greet();

        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();

        while (!userInput.toLowerCase().equals("bye")){
            print(userInput);
            userInput = in.nextLine();
        }
        exit();
    }

    private static void greet(){
        print(new String[]{"Hi, I am here. Greets from Ruby.", "What can I do for you?"});
    }

    private static void exit(){
        print("Bye. Always feels good work with you.");
    }

    private static void print(String thingToPrint){
        System.out.println("    " + "--------------");
        System.out.println("    " + thingToPrint);
        System.out.println("    " + "--------------");
    }

    private static void print(String[] thingsToPrint){
        System.out.println("    " + "--------------");
        for (String thing: thingsToPrint){
            System.out.println("    " + thing);
        }
        System.out.println("    " + "--------------");
    }
}
