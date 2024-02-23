import java.util.Scanner;
import java.io.File;
public class SalmonSan {
    public static void main(String[] args) {
        System.out.println("Salmon-San desu! \nYoroshikuonegaishimasu (^.^)/");

        Query query = new Query();
        QueryManager queryManager = new QueryManager();
        TasksList tasksList = new TasksList();
        Scanner in = new Scanner(System.in);
        TasksListWriter writer = new TasksListWriter();
        int status = -1;

        do {
            System.out.println("---");
            System.out.print("How can I assist you today? ");
            query.changeInput(in.nextLine());

            status = queryManager.executeQuery(query, tasksList);
        } while (status == -1);
    }
}
