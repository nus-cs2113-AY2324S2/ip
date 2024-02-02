import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String logo = "  TTTTT  AAAAA  TTTTT  EEEEE\n" +
                      "    T    A   A    T    E\n" +
                      "    T    AAAAA    T    EEEE\n" +
                      "    T    A   A    T    E\n" +
                      "    T    A   A    T    EEEEE\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("------------------------------------------------------------");
        System.out.println("                Hello! I'm Tate!");
        System.out.println("                What do you need?\n");
        System.out.println("------------------------------------------------------------");

        String line;
        Scanner in = new Scanner(System.in);

        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }
            System.out.println("    ------------------------------------------------------------");
            System.out.println("    " + line);
            System.out.println("------------------------------------------------------------");


        }
        System.out.println("------------------------------------------------------------");
        System.out.println("            Goodbye and Take care, later then!");
        System.out.println("------------------------------------------------------------");


    }
}
