import java.util.Scanner;

public class Echo {
    static String break_line = "----------------------------------------";
    public void startEchoing() {
        Scanner scanner = new Scanner(System.in);
        String input = " ";

        while (!input.equalsIgnoreCase("bye")) {
            System.out.println("What can I do for you?");
            input = scanner.nextLine();

            if (!input.equalsIgnoreCase("bye")) {
                System.out.println(break_line);
                System.out.println(repeat(input));
                System.out.println(break_line);
            }
        }

        scanner.close();
        System.out.println("Kkkkkk thanks bye ");
    }

    public String repeat(String input) {
        return input;
    }
}
