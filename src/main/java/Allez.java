import java.util.Scanner;


public class Allez {

    public static void printList(Task[] list){
        int count = 0;
        while(list[count] != null){
            System.out.println((count+1) + ". " + list[count].getStatusIcon() +
                    " " + list[count].getDescription());
            count+=1;
        }
    }

//    public static void storeInList(String[] list, int count, String line) {
//        list[count] = line;
//    }
    public static void main(String[] args) {

        printGreeting();
        executeCommands();
        printExit();
    }

    private static void executeCommands() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        Task[] list = new Task[100];
        int toMark = 0;

        int itemsInList = 0;


        while (!line.equals("bye")){

            //mark activity as done
            if(line.startsWith("mark")) {
                toMark = Integer.parseInt(line.substring(4).trim()) -1;
                list[toMark].markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("\t" + list[toMark].getStatusIcon() + " " + list[toMark].getDescription());

            } else if (line.equals("list")) { //print list
                printList(list);
            } else{ //store line as new task
                list[itemsInList] = new Task(line);
                System.out.println("added: " + list[itemsInList].getDescription());
                itemsInList+=1;
            }



            line = in.nextLine();
        }
    }

    private static void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printGreeting() {
        System.out.println("_________________________");
        System.out.println("Hello! I'm Allez");
        System.out.println("What can I do for you?");
        System.out.println("_________________________");
    }
}
