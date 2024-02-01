import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = 
        "   J     A    SSSS    OOO   N   N \n"
        + "   J    A A   S      O   O  NN  N \n"
        + "   J   AAAAA   SSS   O   O  N N N \n"
        + "J  J  A     A     S  O   O  N  NN \n"
        + " JJJ  A     A  SSSS   OOO   N   N \n";
        System.out.println("Hello from Shaivan\r\n"
        + " Eyy wassup I'm Jason\r\n" 
        + " What can I do for you?\r\n"
        + logo);
      

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine(); 

            if (input.equalsIgnoreCase("bye")) { // User wants to exit
                System.out.println("Bye. See ya laterr!\r\n"
                        + "____________________________________________________________\r\n"
                        + "\n" + logo);
                break;
            }


            System.out.println(input);
            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }
}