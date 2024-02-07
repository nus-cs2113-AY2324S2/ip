import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String list[] = new String[100];

        System.out.println("Hello! I'm apple");
        System.out.println("What can I do for you?");

       int index = 0;

        String line = "";


        
        while (!line.equals("bye")){
            Scanner input = new Scanner(System.in);
            line = input.nextLine();

            if (line.equals("bye")){
                break;
            }

            if (line.equals("list")){
                for (int i = 0; i < index; i ++) {

                    System.out.println((i + 1) + ". " + list[i]);
                }
            }else {
                list[index] = line;
                System.out.println("added: " + line);
            }

            index ++;
            




        }
        System.out.println("Bye. Hope to see you again soon!");
        
        
    }
}
