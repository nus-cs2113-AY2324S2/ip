import java.util.Scanner;
public class Gab {

    public static String getTask() {
        String task;
        Scanner in = new Scanner(System.in);
        System.out.println("\tEnter your new task: ");
        task = in.nextLine();
        return task;
    }

    public static void displayTask(String task) {
        String exitCode = "Bye";
        System.out.println("_____________");
        System.out.println("\t" + task);
        System.out.println("_____________");
        if (task.equalsIgnoreCase(exitCode)) {
            System.out.println("I hope you complete them!");
        } else {
            displayTask(getTask());
        }
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


