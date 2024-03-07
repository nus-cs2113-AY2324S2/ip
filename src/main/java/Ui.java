/**
 * deals with interactions with the user
 */

public class Ui {
    public void sayHi(){
        System.out.println("Hello! I'm Apple");
        System.out.println("What can I do for you?");
    }

    public void readCommand(){
        System.out.print("Enter your command: ");
    }

    public static void errorMessage(String message){
        System.out.println(message);
    }

    public void sayBye(){
        System.out.println("Bye. Hope to see you again soon!");
    }
}
