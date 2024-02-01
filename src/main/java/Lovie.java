import java.util.ArrayList;
import java.util.Scanner;

public class Lovie {
    public static void main(String[] args) {
        Scanner inputGetter = new Scanner(System.in);   
        String input = new String(); 
        ArrayList<String> taskList = new ArrayList<String>();   

        String logo = 
        "       ██╗░░░░░░█████╗░██╗░░░██╗██╗███████╗ \n" +
        "       ██║░░░░░██╔══██╗██║░░░██║██║██╔════╝ \n" +
        "       ██║░░░░░██║░░██║╚██╗░██╔╝██║█████╗░░ \n" +
        "       ██║░░░░░██║░░██║░╚████╔╝░██║██╔══╝░░ \n" +
        "       ███████╗╚█████╔╝░░╚██╔╝░░██║███████╗ \n" +
        "       ╚══════╝░╚════╝░░░░╚═╝░░░╚═╝╚══════╝ \n\n"; 
        System.out.println("        Hey hey! My name is \n\n" + logo);
        System.out.println("        How can I help you today?\n");
        System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");

        while (true) {
            input = inputGetter.nextLine(); 
            if (input.equals("bye")) {
                break; 
            } 
            else {
                if (input.equals("list")) {
                    System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
                    int counter = 0; 
                    while (counter < taskList.size()) {
                        String listNumber = Integer.toString(counter+1); 
                        System.out.println("        " + listNumber + ". " + taskList.get(counter) + "\n\n");
                        counter += 1; 
                    }
                    System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
                }
                else {
                    taskList.add(input);
                    System.out.println("\n\n");
                    System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
                    System.out.println("        added: " + input + "\n\n"); 
                    System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n"); 
                }
            }
        } 
        System.out.println("\n\n");
        System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
        System.out.println("        Thanks for using me! See you next time <3\n\n");
        System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
    }
}
