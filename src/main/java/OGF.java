import java.util.Scanner;
public class OGF {
    public static void main(String[] args) {
        String logo = "\n" +
                " _____  _    _                   _____ ______ \n" +
                "|  _  || |  | |                 |  __ \\|  ___|\n" +
                "| | | || |_ | |__    ___  _ __  | |  \\/| |_   \n" +
                "| | | || __|| '_ \\  / _ \\| '__| | | __ |  _|  \n" +
                "\\ \\_/ /| |_ | | | ||  __/| |    | |_\\ \\| |    \n" +
                " \\___/  \\__||_| |_| \\___||_|     \\____/\\_|    \n" +
                "                                              \n" +
                "                                              \n";
        System.out.println("Welcome! I'm your \n" + logo + "Nice to meet you!");
        System.out.println("What can I do for you?");


        Task[] tasks = new Task[100];
        int numItem = 0;
        Scanner scanner = new Scanner(System.in);
        int taskNo;
        while (true) {
            String input = scanner.nextLine();
            switch (input.split(" ")[0]) {
                case ("bye"):
                    System.out.println("Bye bye now!");
                    return;
                case ("list"):
                    System.out.println("Here are your tasks for today: ");
                    for (int i = 0; i < numItem; i++){
                        System.out.print(i+1 + ". ");
                        tasks[i].printTask();
                    }
                    System.out.println(("____________________________________________________________"));
                    break;
                case ("mark"):
                //Fallthrough
                case ("unmark"):
                    taskNo = Integer.parseInt(input.split(" ")[1])-1;

                    if (input.split(" ")[0].equals("mark")){
                        System.out.println("Good Job! I'm setting this task as done: ");
                        tasks[taskNo].setDone(true);
                    } else{
                        System.out.println("Oop! I'm setting this task as undone: ");
                        tasks[taskNo].setDone(false);
                    }
                    tasks[taskNo].printTask();
                    System.out.println(("____________________________________________________________"));
                    break;
                default:
                    tasks[numItem] = new Task(input);
                    numItem++;
                    System.out.println("Added: " + input);
                    System.out.println(("____________________________________________________________"));
                    break;
            }
        }

    }
}
