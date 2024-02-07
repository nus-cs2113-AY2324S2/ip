import java.util.Scanner;

public class GermaBot {
    public static void main(String[] args) {
        String WelcomeMessage = "____________________ \n"
                + "Hello! GermaBot here! \n"
                + "What may I do for you this fine day? \n"
                + "____________________";
        System.out.println(WelcomeMessage);
        String[] toDoList = new String[100];
        int counter = 0;
        while (true) {
            String echo;
            Scanner in = new Scanner(System.in);
            echo = in.nextLine();
            if (echo.equals("bye")) {
                break;
            }
            if (echo.equals("list")) {
                int i = 1;
                for (String s : toDoList) {
                    if (s == null) {
                        break;
                    }
                    System.out.println(i + ". " + s);
                    i++;
                }
            }
            else {
                toDoList[counter] = echo;
                System.out.println("Added: " + echo + " to the list!");
                counter += 1;
            }
        }
        String GoodbyeMessage = "____________________ \n"
                + "Thanks for using me! Hope you again soon~! \n"
                + "____________________";
        System.out.println(GoodbyeMessage);
    }
    }
