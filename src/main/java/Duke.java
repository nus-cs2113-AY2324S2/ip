import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Task[] line = new Task[100];
        Scanner in = new Scanner(System.in);
        System.out.println("Ay yo mans name is Bobby :D\n" + "Faster put ur tasks in yea?");

        for (int i = 1; i < 101; i += 1) {
            line[i] = new Task(in.nextLine());
            String description = line[i].getDescription();
            if (description.startsWith("mark")) {
                int entry = Integer.parseInt(description.substring(5));
                if (entry > 0 && entry < i) {
                    line[entry].setDone(true);
                    i -= 1;
                    System.out. println("Solid I help u mark done alr");
                    System.out.print(entry + ".[" + line[entry].getStatusIcon() + "] ");
                    line[entry].printDescription();
                }
            } else {
                if (description.startsWith("unmark")) {
                    int entry = Integer.parseInt(description.substring(7));
                    if (entry > 0 && entry < i) {
                        line[entry].setDone(false);
                        i -= 1;
                        System.out. println("Ehh scam me, ok I help u remove");
                        System.out.print(entry + ".[" + line[entry].getStatusIcon() + "] ");
                        line[entry].printDescription();
                    }
                } else {
                    if (!description.equals("bye") && !description.equals("list")) {
                        System.out.println("added: " + line[i].description);
                    } else {
                        if (description.equals("list")) {
                            for (int j = 1; j < i; j += 1) {
                                System.out.print(j + ".[" + line[j].getStatusIcon() + "] ");
                                line[j].printDescription();
                            }
                            i -= 1;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("BYE BYE BOO BOO!");
    }
}
