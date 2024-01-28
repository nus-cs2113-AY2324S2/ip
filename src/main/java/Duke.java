import java.util.Scanner;
import java.util.Arrays;
public class Duke {
    public static void dividingLine(){
        System.out.println("\t________________________________");
    }
    public static void communicate(){
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while(!line.equals("bye")){
            dividingLine();
            System.out.println("\t" + line);
            dividingLine();
            line = in.nextLine();
        }
    }
    public static void startConversation(){
        dividingLine();
        System.out.println("\tHello!, I'm 'Noooob'");
        System.out.println("\tWhat can I do for you?");
        dividingLine();

        communicate();
        dividingLine();
        System.out.println("\tBye. Hope to see you again soon!");
        dividingLine();
    }
    public static void main(String[] args) {
       startConversation();
    }
}
