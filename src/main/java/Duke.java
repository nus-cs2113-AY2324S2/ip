import java.util.Scanner;
import java.util.Arrays;
public class Duke {
    public static void dividingLine(){
        System.out.println("\t________________________________");
    }
    public static void communicate(){
        String[] list = new String[100];
        int index = 0;
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while(!line.equals("bye")){
            dividingLine();
            if(!line.equals("list")) {
                list[index] = line;
                index++;
                System.out.println("\tadded: " + line);
            }
            else{
                for(int i = 0; i < index; i++){
                    System.out.println((i + 1) + ". " + list[i]);
                }
            }
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
