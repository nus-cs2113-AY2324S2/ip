import java.util.Scanner;

public class ListKeeper {
    private Task[] list;
    private int numTaskAdded = 0;

    public ListKeeper(int size) {
        this.list = new Task[size];
    }

    private void addToList(String task) {
        if (this.numTaskAdded == list.length) {
            return;
        }
        list[this.numTaskAdded] = new Task(task);
        this.numTaskAdded++;
        System.out.println("added: " + task);
    }

    private void printList() {
        for (int i = 0; i < this.numTaskAdded; i++) {
            System.out.print(i + 1 + ". ");
            this.list[i].printTask();
        }
    }

    /**
     * User's command must be of the following forms
     * 1. "list"
     * 2. "mark x" where x must be an integer
     * 3. "unmark x" where x must be an integer
     * 4. any string ( least priority )
     */
    public void manageList() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {

            while (line.isEmpty()) {
                line = in.nextLine();
            }

             HorizontalGenerator.printHorizontal();
             String[] words = line.split(" ");

             if (words.length == 1 && words[0].equals("list")) {
                 this.printList();
             } else if (words.length == 2 && (words[0].equals("mark") || words[0].equals("unmark"))) {
                 try {
                     int task = Integer.parseInt( words[1] );
                     if ( task > this.numTaskAdded || task <= 0 ) {
                         System.out.println("Please specify a valid task");
                     } else if (words[0].equals("mark")) {
                         this.list[task - 1].mark();
                     } else {
                         this.list[task - 1].unmark();
                     }
                 } catch ( NumberFormatException error ) {
                     this.addToList(line);
                 }
             } else {
                 this.addToList(line);
             }

             HorizontalGenerator.printHorizontal();
             line = in.nextLine();
        }
    }
}
