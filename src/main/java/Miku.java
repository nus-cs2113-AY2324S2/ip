import java.util.Scanner;

public class Miku {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("______________________");
        System.out.println("Hello! I'm Miku!\n" + "What can I do for you?");
        System.out.println("______________________");

        line = in.nextLine();
        String newItem = line;
        Task[] storedList = new Task[100];
        int numberOfListItems = 0;

        while (!line.equals("bye")) {
            System.out.println("______________________");

            if (line.equals("list")) {
                System.out.println("Here are ycxdfqwert1e123our list items!");
                for (int i = 0; i < numberOfListItems; i++) {
                    System.out.println(i + 1 + ". [" + storedList[i].getStatusIcon()
                            + "] " + storedList[i].description + "\n");
                }
            } else if (line.contains("mark")) {
                boolean isUnmarking = line.contains("unmark");
                String printedLine = (isUnmarking ? "Aww... I've marked it as undone."
                        : "Good job~! I've marked it as done");
                System.out.println(printedLine);
                String[] markList = line.split(" ");
                String listNumberString = markList[(markList.length - 1)];
                int listNumberInt = Integer.parseInt(listNumberString);
                storedList[listNumberInt-1].isDone = (!isUnmarking);
                System.out.println("[" + storedList[listNumberInt-1].getStatusIcon()
                        + "] " + storedList[listNumberInt-1].description + "\n");
            } else {
                    System.out.println("added: " + newItem);
                    storedList[numberOfListItems] = new Task(newItem);
                    storedList[numberOfListItems].description = newItem;
                    numberOfListItems++;
            }
            System.out.println("______________________");
            line = in.nextLine();
            newItem = line;
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("______________________");
    }
}
