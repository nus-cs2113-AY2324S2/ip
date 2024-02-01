import java.util.Scanner;

public class Lovie {
    public static void main(String[] args) {
        Scanner inputGetter = new Scanner(System.in);
        Boolean isBye = true;   
        String input = new String(); 

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

        while (isBye) {
            input = inputGetter.nextLine(); 
            if (input.equals("bye")) {
                isBye = false; 
            }
            else {
                System.out.println("\n\n");
                System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
                System.out.println("        " + input + "\n\n"); 
                System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n"); 
            }
        } 
        System.out.println("\n\n");
        System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
        System.out.println("        Thanks for using me! See you next time <3\n\n");
        System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
    }
}
