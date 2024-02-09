
import java.util.Scanner;
public class Avocado {
    public static void main(String[] args) {
        System.out.println("Hello from avocado");
        System.out.println("How can I help you ?");
        System.out.println("See you!");
        String[] list = new String[100];
        int i = 0;
        int n = 0;
        while (true) {
            String line;
            Scanner in = new Scanner(System.in);

            line = in.nextLine();
            if (line.equals("bye")) {
                return;
            }

            if (line.equals("list")) {
                for (i = 0; i < n; i++) {
                    System.out.println((i + 1) + "." + list[i]);
                }
            } else {
                list[i] = line;
                System.out.println("added: " + line);
                i++;
                n++;
            }
        }
    }
}