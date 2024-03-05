package kapwa;

import java.util.Scanner;
public class Ui {
    private Scanner scanner;

    public Ui(){
        this.scanner = new Scanner(System.in);
    }

    public String readCommand(){
        return scanner.nextLine().trim();
    }

    public void showLine(){
        System.out.println("____________________________________________________________");
    }

    public void showWelcome(){
        System.out.println("Hello From Kapwa!\n" +
                "What can I do for you?");
        showLine();
    }

    public void showError(String message){
        System.out.println("an error occurred: " + message);
    }

    public void showGoodByeMessage(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void closeScanner(){
        scanner.close();
    }
}
