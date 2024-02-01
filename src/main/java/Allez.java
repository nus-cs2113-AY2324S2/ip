import java.util.Scanner;


public class Allez {

    public static void printList(String[] list){
        int count = 0;
        while(list[count]!=null){
            System.out.println((count+1) + ". " + list[count]);
            count+=1;
        }
    }

//    public static void storeInList(String[] list, int count, String line) {
//        list[count] = line;
//    }
    public static void main(String[] args) {

        System.out.println("_________________________");
        System.out.println("Hello! I'm Allez");
        System.out.println("What can I do for you?");
        System.out.println("_________________________");
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] list = new String[100];
        int itemsInList = 0;


        while (!line.equals("bye")){
            switch(line){
                case "list":
                    //method to list out items
                    printList(list);
                    break;
                default:
                    //method to store items;
                    list[itemsInList] = line;
                    System.out.println("added: " + list[itemsInList]);
                    itemsInList+=1;
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
