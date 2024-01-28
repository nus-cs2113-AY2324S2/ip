import java.util.Scanner;
import java.util.Arrays;
public class Lotes {
    public static void main(String[] args) {
        String underscore = "    ____________________________________________________________\n";
        System.out.println(underscore + "    Hello! I'm Lotes\n" + "    What can I do for you?\n" + underscore);

        String userInput;
        while(true){
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            if(userInput.equals("bye")){
                System.out.println(underscore + "    Bye. Hope to see you again soon!\n" + underscore);
                break;
            }
            System.out.print(underscore + "     " + userInput + "\n" + underscore);
        }
    }
}
