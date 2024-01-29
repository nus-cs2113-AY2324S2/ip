import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! My name is Jeff.");
        System.out.println("What can I do for you?");
        boolean stillUsing = true;
        while (stillUsing){
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.equals("bye") || line.equals("Bye")){
                stillUsing = false;
            }
            else{
                System.out.println(line);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");


    }
}
