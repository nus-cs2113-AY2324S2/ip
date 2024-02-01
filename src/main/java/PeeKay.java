import java.util.Scanner;
public class PeeKay {
    static String line = "____________________________________________________________";
    public static void echo(String input){
        if(input.equals("bye")){
            System.out.println("Bye. Hope to see you again soon!");
        }else {
            System.out.println("  " + input);

        }
    }

    public static void chat(){
        String input;
        do {
            input = new Scanner(System.in).nextLine();
            System.out.println(line);
            echo(input);
            System.out.println(line);
        } while(!input.equals("bye"));
    }

    public static void main(String[] args) {

        System.out.println(line);
        System.out.println("Hello! I'm PeeKay");
        System.out.println("What can I do for you?");
        System.out.println(line);
        chat();
    }
}
