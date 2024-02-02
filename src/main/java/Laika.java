import java.util.Scanner;

public class Laika {
    public static void main(String[] args) {
        String line = "";
        Scanner in = new Scanner(System.in);
        String logo = " ^..^      /\n"
                + " /_/\\_____/\n"
                + "    /\\   /\\\n"
                + "   /  \\ /  \\\n";
        System.out.println("Laika: Hi! My name is Laika!\n\n" + logo + "Laika: How can I help you?");
        while(!line.equalsIgnoreCase("bye")){
            System.out.print("User: ");
            line = in.nextLine();
            System.out.println("Laika: " + line);
        }

        System.out.println("Laika: Bye! Have a nice day!");
    }
}
