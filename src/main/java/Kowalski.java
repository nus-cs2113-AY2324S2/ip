import java.util.Scanner;

public class Kowalski {
    public static void main(String[] args){
        String[] currentTasks = new String[100];
        String dividingLine = "____________________________________________________________";
        String userInput;
        int counter = 0;

        // Introduction to User
        System.out.println(dividingLine);
        System.out.println("Hello, I'm Kowalski!\n" +
                "What can I do for you?" );
        System.out.println(dividingLine);

        // Turn on scanner
        Scanner in = new Scanner(System.in);

        //Continuous loop until break statement
        while (true){
            //User Inputs
            userInput = ((in.nextLine()).trim()).toLowerCase();
            System.out.println(dividingLine);

            //Cases for the userInput
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(dividingLine);
                break;
            } else if (userInput.equals("list")){
                for (int i = 1; i < counter+1;i++){
                    System.out.println(i + ". " + currentTasks[i-1]);
                }
                System.out.println(dividingLine);
            } else {
                System.out.println("added: " + userInput);
                currentTasks[counter] = userInput;
                counter++;
                System.out.println(dividingLine);
            }
        }
    }
}
