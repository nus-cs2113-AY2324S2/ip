import java.util.Scanner;

public class Kowalski {
    public static void main(String[] args){
        Task[] currentTasks = new Task[100];
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
                    System.out.println(i + ".[" + currentTasks[i-1].getStatusIcon() +"] " + currentTasks[i-1].description);
                }
                System.out.println(dividingLine);
            } else if (userInput.contains("unmark")) {
                int unmarkPosition = userInput.indexOf("k");
                String taskNumberString = (userInput.substring(unmarkPosition+1)).trim();
                int taskNumber = Integer.parseInt(taskNumberString);

                //Checking for valid task number
                if (taskNumber-1 <= counter){
                    currentTasks[taskNumber-1].removeDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  [" + currentTasks[taskNumber-1].getStatusIcon() +"] " + currentTasks[taskNumber-1].description);
                    System.out.println(dividingLine);
                } else {
                    break;
                }

            } else if (userInput.contains("mark")){
                int markPosition = userInput.indexOf("k");
                String taskNumberString = (userInput.substring(markPosition+1)).trim();
                int taskNumber = Integer.parseInt(taskNumberString);

                //Checking for valid task number
                if (taskNumber-1 <= counter){
                    currentTasks[taskNumber-1].setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  [" + currentTasks[taskNumber-1].getStatusIcon() +"] " + currentTasks[taskNumber-1].description);
                    System.out.println(dividingLine);
                } else {
                    break;
                }

            } else {
                System.out.println("added: " + userInput);
                currentTasks[counter] = new Task(userInput);
                counter++;
                System.out.println(dividingLine);
            }
        }
    }
}
