import java.util.Scanner;

public class SalmonSan {
    public static void main(String[] args) {
        System.out.println("Salmon-San desu! Yoroshikuonegaishimasu (^.^)/");

        Query query = new Query();
        Scanner in = new Scanner(System.in);

        System.out.print("How can I assist you today? ");
        query.changeInput(in.nextLine());

        System.out.println("Can I double check that you said: " + query.getInput());
    }
}
