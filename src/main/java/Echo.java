import java.util.Scanner;
public class Echo {
    public static void main(String[] arg){
        String line;
        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.println(" ");
            line = in.nextLine();
            if (line.equalsIgnoreCase("bye")) {
                Binks.createLineSpacing();
                System.out.println("Bye. Hope to see you again soon!");
                Binks.createLineSpacing();
                break;
            }
            Binks.createLineSpacing();
            System.out.println(line);
            Binks.createLineSpacing();

        }
    }
}
