import java.util.Scanner;

public class Incy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        String logo = " ____  _  _  ___  _  _ \n"
                    + "(_  _)( \\( )/ __)( \\/ )\n"
                    + " _)(_  )  (( (__  \\  / \n"
                    + "(____)(_)\\_)\\___) (__) \n";
        
        System.out.println("____________________________________________________________\n"
                            + "Hello! I'm\n" + logo
                            + "What can I do for you?\n"
                            + "____________________________________________________________"
        );
        
        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println("____________________________________________________________\n"
                                + input
                                + "\n____________________________________________________________"
            );
        }
        
        System.out.println("____________________________________________________________\n"
                            + "Bye. Hope to see you again soon!"
                            + "\n____________________________________________________________"
        );
        scanner.close();
    }
}
