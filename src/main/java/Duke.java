import java.util.Scanner;

public class Duke {
    public static void respond(String query) {
        // Echo query for now
        System.out.println(query);
    }
    public static void main(String[] args) {
        System.out.println("Greetings! I am Blue, your personal chatbot assistant. How may I help you?");
        Scanner in = new Scanner(System.in);
        for (String line = in.nextLine(); !line.equals("bye"); line = in.nextLine()) {
            respond(line);
        }
        System.out.println("Glad to be of service. Bye!");
    }
}
