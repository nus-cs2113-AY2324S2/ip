package seedu.salmonsan.ui;

public class UI {
    final String WELCOME = "Salmon-San desu! \nYoroshikuonegaishimasu (^.^)/";
    final String QUERY = "How can I assist you today?";
    final String DIVIDER = "--------------------------------";
    public UI() {}

    public void welcome() {
        System.out.println(WELCOME);
    }

    public void askQuery() {
        System.out.println(QUERY);
    }

    public void printDivider() {
        System.out.println(DIVIDER);
    }

    public void printHelp()
}
