import java.util.Scanner;
public class PeeKay {
    static String indent = "     ";
    static String line = "    ____________________________________________________________";
    public static void echo(String input){
        if(input.equals("bye")){
            System.out.println(indent + "Bye. Hope to see you again soon!");
        }else {
            System.out.println(indent + "added: " +input);
        }
    }

    public static int mark(String input){
        int idx = Integer.parseInt(input.substring(input.indexOf(" ")+1));
        return idx -1;
    }

    public static void chat(){
        String input;
        Task[] list = new Task[100];
        int count = 0;
        do {
            input = new Scanner(System.in).nextLine();
            System.out.println(line);
            if (input.equals("list")){
                System.out.println(indent + "Here are the tasks in your list:");
                for(int x = 0; x < count;x++){
                    System.out.println(indent + " " + (x+ 1)+".[" + (list[x].getStatusIcon())+ "] "+list[x].getDescription());
                }
            } else if (input.contains("unmark")){
                int idx = mark(input);
                list[idx].setDone(false);
                System.out.println(indent + "OK, I've marked this task as not done yet:");
                System.out.println(indent +"["+ list[idx].getStatusIcon() + "] " + list[idx].getDescription());
            } else if (input.contains("mark")) {
                int idx = mark(input);
                list[idx].setDone(true);
                System.out.println(indent + "Nice! I've marked this task as done:");
                System.out.println(indent +"["+ list[idx].getStatusIcon() + "] " + list[idx].getDescription());
            } else {
                Task t = new Task(input);
                list[count] = t;
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




