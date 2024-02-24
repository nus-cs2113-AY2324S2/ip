import java.util.Scanner;
public class Avocado {
    public static void main(String[] args) {
        System.out.println("Hello from avocado");
        System.out.println("How can I help you ?");
        System.out.println("See you!");
        while (true) {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
            if (line.equals("bye")) {
                return;
            } else {
                System.out.println(line);
            }
        }
    }
}