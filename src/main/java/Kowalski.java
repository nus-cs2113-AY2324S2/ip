import java.util.Scanner;

public class Kowalski {
    public static void main(String[] args){
        String dividingline = "____________________________________________________________";
        System.out.println(dividingline);
        System.out.println("Hello, I'm Kowalski!" +
                "What can I do for you?" );
        System.out.println(dividingline);

        String userInput;
        Scanner in = new Scanner(System.in);

        while (true){
            userInput = in.nextLine();
            System.out.println(dividingline);
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(dividingline);
                break;
            }
            System.out.println(userInput);
            System.out.println(dividingline);

        }
    }
}
