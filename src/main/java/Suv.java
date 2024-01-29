import java.util.Scanner;

public class Suv {
    public static void main(String[] args) {
        String name = "Suv";
        Scanner in = new Scanner(System.in);

        while(true) {
            String input = in.nextLine();
            if(!input.equals("bye")){
                System.out.println("____________________________________________________________\n" +
                        input +
                        "\n____________________________________________________________\n");
            }else {
                System.out.println( "____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n");
                break;
            }
        }
    }
}