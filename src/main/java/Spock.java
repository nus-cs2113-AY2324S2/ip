import java.util.Scanner;

public class Spock {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Spock");
        Scanner myObj = new Scanner(System.in);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        String input = myObj.nextLine();

        for(String reply = input; !reply.equals("bye"); reply = myObj.nextLine()) {
            System.out.println("____________________________________________________________");
            System.out.println(reply);
            System.out.println("____________________________________________________________");
        }


        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}   