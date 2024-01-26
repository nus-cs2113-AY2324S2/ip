import java.util.Scanner;
import java.util.ArrayList;

public class Gab {
    private static ArrayList<String> taskList = new ArrayList<>();

    public static String getTask() {
        String task;
        Scanner in = new Scanner(System.in);
        System.out.println("\tEnter your new task: ");
        task = in.nextLine();
        taskList.add(task);
        return task;
    }

    public static void listTask() {
        for (int i = 0; i < taskList.size(); i++) {
            String displayList = taskList.get(i);
            System.out.println((i + 1) + ". " + displayList);
        }
        displayTask(getTask());
    }

    public static void checkKeywords (String keywords) {
        final String exitCode = "bye";
        final String displayCode = "list";
        keywords = keywords.toLowerCase();
        switch (keywords) {
            case exitCode:
                System.out.println("I hope you complete them!");
                break;
            case displayCode:
                Gab.listTask();
                break;
            default:
                Gab.displayTask(getTask());
                break;
        }
    }
    public static void displayTask(String task) {
        String exitCode = "Bye";
        System.out.println("_____________");
        System.out.println("\tadded: " + task);
        System.out.println("_____________");
        checkKeywords(task);
    }


    public static void main(String[] args) {
        String logo =
                          "  _____           __ \n"
                        + "/  ____|         |  |\n"
                        + "|  |  __   ____  |  |__\n"
                        + "|  | |_  |/  _   |  -   \\ \n"
                        + "|  |__|  |  (_|  | |_)   |\n"
                        + "\\_______ |__ ,_ |_.___ /\n";

        System.out.println(logo);
        System.out.println("\tI am Gab the Bot! Nice to meet you!");
        System.out.println("\tAnything I can help you with?");

        String task = Gab.getTask();
        Gab.displayTask(task);

    }






}


