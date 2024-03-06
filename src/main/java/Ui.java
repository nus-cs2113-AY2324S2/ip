import java.util.Scanner;
public class Ui {
    public String input;
    public Scanner in;

    public Ui() {
        in = new Scanner(System.in);
        input = in.nextLine();
    }

    public void nextInput() {
        input = in.nextLine();
    }
}
