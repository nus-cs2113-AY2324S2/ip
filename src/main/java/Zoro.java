import java.util.Scanner;

public class Zoro {
    private static final String TAB_SPACE = "    ";
    private static final String LINE = TAB_SPACE + "_____________________________________________________________";

    public static void main(String[] args) {
        String name = "Zoro";
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Hello I'm "+ name);
        System.out.println(TAB_SPACE + "What can I do for you?");
        System.out.println(LINE);
        Scanner in = new Scanner(System.in);

        while(true){
            String input;
            input = in.nextLine().trim();

            if(input.equalsIgnoreCase("bye")){
                break;
            }

            System.out.println(LINE);
            System.out.println(TAB_SPACE+input);
            System.out.println(LINE);
        }
        in.close();

        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}
