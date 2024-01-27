import java.util.Scanner;
public class Echo {
    public static void main(String[] arg){
        String line;
        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.println(" ");
            line = in.nextLine();
            if (line.equalsIgnoreCase("bye")) {
                Binks.lineSpacing();
                System.out.println("Bye. Hope to see you again soon!");
                Binks.lineSpacing();
                break;
            }
            Binks.lineSpacing();
            System.out.println(line);
            Binks.lineSpacing();

        }
    }
}
