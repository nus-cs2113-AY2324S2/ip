import java.util.Scanner;
public class PeeKay {
    static String indent = "  ";
    static String line = "  ____________________________________________________________";
    public static void echo(String input){
        if(input.equals("bye")){
            System.out.println(indent + "Bye. Hope to see you again soon!");
        }else {
            System.out.println(indent + "added: " +input);

        }
    }

    public static void chat(){
        String input;
        String[] list = new String[100];
        int count = 0;
        do {
            input = new Scanner(System.in).nextLine();
            System.out.println(line);
            if (input.equals("list")){
                for(int x = 0; x < count;x++){
                    System.out.println(indent + (x+ 1)+"."+" "+list[x]);
                }
            }else {
                list[count] = input;
                count++;
                echo(input);
            }
            System.out.println(line);
        } while(!input.equals("bye"));
    }

    public static void main(String[] args) {

        System.out.println(line);
        System.out.println(indent + "Hello! I'm PeeKay");
        System.out.println(indent + "What can I do for you?");
        System.out.println(line);
        chat();
    }
}
