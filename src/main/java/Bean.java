import java.util.Scanner;

public class Bean {
    public static void main(String[] args) {
        String separator = "--------------------------------------";
        System.out.println("Hello! I'm Bean.\nWhat can I do for you?");
        System.out.println(separator);
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(!line.equals("bye")) {
            System.out.println("    " + line);
            System.out.println(separator);
            line = in.nextLine();
        }
        System.out.println("Bean will take a nap now. Bye!");
    }
}
