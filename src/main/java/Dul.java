import java.util.Scanner;
public class Dul {
    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| |\n"
                + "| | | | | | | |\n"
                + "| |_| | |_| | |\n"
                + "|____/ \\__,_|_|\n";
        System.out.println("Hello I'm\n" + logo);
        System.out.println("What can I do for you?");
        String guyInput = "";
        Scanner in = new Scanner(System.in);
        while (!guyInput.equals("bye")) {
            guyInput = in.nextLine();
            System.out.println(guyInput);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
