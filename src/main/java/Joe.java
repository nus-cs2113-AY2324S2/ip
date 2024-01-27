import java.util.Scanner;

public class Joe {
    private static final String H_LINE = "____________________________________________________________\n";
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(H_LINE + "HI I'M JOE\n" + "WHAT CAN I DO FOR YOU\n" + H_LINE);
        
        boolean hasExitInput = false;
        while (!hasExitInput) {
            String input = in.nextLine();
            if (input.equals("bye")) {
                hasExitInput = true;
            } else {
                System.out.println(H_LINE + input + '\n' + H_LINE);
            }
        }
        
        System.out.println(H_LINE + "GOODBYE. PLEASE COME BACK AGAIN :)\n" + H_LINE);
    }
}
