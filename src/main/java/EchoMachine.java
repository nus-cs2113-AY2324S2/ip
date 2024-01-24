import java.util.Scanner;

public class EchoMachine {
    public static void echo() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            HorizontalGenerator.printHorizontal();
            System.out.println(line);
            HorizontalGenerator.printHorizontal();
            line = in.nextLine();
        }
    }
}
