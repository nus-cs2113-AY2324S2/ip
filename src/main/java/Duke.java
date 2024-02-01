import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = "         z$$$$P\n" +
                "        d$$$$\"\n" +
                "      .$$$$$\"\n" +
                "     z$$$$$\"\n" +
                "    z$$$$P\n" +
                "   d$$$$$$$$$$\"\n" +
                "  *******$$$\"\n" +
                "       .$$$\"\n" +
                "      .$$\"\n" +
                "     4$P\"\n" +
                "    z$\"\n" +
                "   zP\n" +
                "  z\"\n" +
                " /    M A V I S'\n" +
                "^";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Mavis!");
        System.out.println("What can I do for you?\n");

        String inputToEcho;
        Scanner in = new Scanner(System.in);

        while(true) {
            inputToEcho = in.nextLine();

            if (inputToEcho.equals("bye")) {
                break;
            }

            System.out.println(" ____________________________________________________________");
            System.out.println(inputToEcho);
            System.out.println(        "    ____________________________________________________________");
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

    }
}
