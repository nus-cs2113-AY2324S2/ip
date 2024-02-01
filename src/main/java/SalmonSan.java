import java.util.Scanner;

public class SalmonSan {
    public static void main(String[] args) {
        System.out.println("Salmon-San desu! Yoroshikuonegaishimasu (^.^)/");

        Query query = new Query();
        QueryManager queryManager = new QueryManager();

        Scanner in = new Scanner(System.in);
        int status = -1;

        do {
            System.out.println("---");
            System.out.print("How can I assist you today? ");
            query.changeInput(in.nextLine());

            status = queryManager.executeQuery(query);
        } while (status == -1);
    }
}
