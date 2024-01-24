import java.util.Scanner;

public class ListKeeper {
    private String[] list;
    private int numTaskAdded = 0;

    public ListKeeper(int size) {
        this.list = new String[size];
    }

    private void addToList(String task) {
        if (this.numTaskAdded == list.length) {
            return;
        }
        list[this.numTaskAdded] = task;
        this.numTaskAdded++;
        System.out.println("added: " + task);
    }

    private void printList() {
        for (int i = 0; i < this.numTaskAdded; i++) {
            System.out.println(i + 1 + ". " + this.list[i]);
        }
    }

    public void manageList() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
             HorizontalGenerator.printHorizontal();
             if (line.equals("list")) {
                 this.printList();
             } else {
                 this.addToList(line);
             }
             HorizontalGenerator.printHorizontal();
             line = in.nextLine();
        }
    }
}
