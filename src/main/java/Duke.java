import java.util.Scanner;

public class Duke {

    public static void inputChecker() {
        String line; //declared a string object to take in user input
        Scanner in = new Scanner(System.in); //declared a scanner object
        line = "";

        while (true) {
            line = in.nextLine(); //takes in user input
            if (line.equals("bye")) {
                System.out.println("Bye human. Come back soon !");
                break;
            }
            System.out.println("You entered: " + line);
        }
    }

    public static void main(String[] args) {

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        inputChecker();
    }
}


