
import java.util.Scanner;


public class Eln {
    static String LINE = "____________________________________________________________";
    private static void echoUser() {
        Scanner scan = new Scanner(System.in);
        String input = "";
        String[] list = new String[20];
        int index = 0;

        while(!input.equals("bye")) {
            input = scan.nextLine();

            if(input.equals("list")) {
                System.out.println(LINE);
                if(index == 0) {
                    System.out.println("list is empty");
                }
                for(int i = 0; i < index; i++) {
                    System.out.println((i+1) + ". " + list[i]);
                }
                System.out.println(LINE);
            } else {
                list[index] = input;
                System.out.println(LINE);
                System.out.println("added: " + input);
                System.out.println(LINE);
                index++;
            }
        }
    }
    public static void main(String[] args) {

        System.out.println(LINE);
        System.out.println("Hello! I'm Eln");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        echoUser();

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);

    }
}
