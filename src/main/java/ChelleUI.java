import Common.Messages;
import java.util.Scanner;

public class ChelleUI {

    private Scanner scanner;

    public ChelleUI() {
        this.scanner = new Scanner(System.in);
        System.out.println(Messages.MESSAGE_GREETING);
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void closeScanner(){
        scanner.close();
    }
}
