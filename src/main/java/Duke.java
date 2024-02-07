import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm apple");
        System.out.println("What can I do for you?");

       

        String line = "";


        
        while (!line.equals("bye")){
            Scanner input = new Scanner(System.in);
            line = input.nextLine();
            

            if (line.equals("bye")){
                break;
            }

            System.out.println(line);
        }
        System.out.println("Bye. Hope to see you again soon!");
        
        
    }
}
