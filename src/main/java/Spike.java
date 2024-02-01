import java.util.Scanner;

public class Spike {
    public static void main(String[] args) {
        String chatbot = "Spike";
        System.out.println("Hello! I'm " + chatbot);
        System.out.println("What can I do for you?\n");

        while(true){
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            if(input.contentEquals("bye")){
                break;
            }

            System.out.println(input);

        }


        System.out.println("Bye. Hope to see you again soon!");
    }
}
