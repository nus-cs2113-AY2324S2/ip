import java.util.Scanner;
import java.util.ArrayList;
public class Lotes {
    public static String lineSeparator = System.lineSeparator();
    public static String underscore = "    ____________________________________________________________";
    public static void printUserList(ArrayList<Task> tasks){
        System.out.println(underscore);
        if(tasks.isEmpty()){
            System.out.println("    List is empty, please enter some text to add to list.");
        }else{
            System.out.println("     Here are the tasks in your list:");
            int index = 0;
            for(Task task : tasks){
                index++;
                System.out.print("     " + index + ".[" + task.getStatusIcon() +
                        "] " + task.getDescription() + lineSeparator);
            }
        }
        System.out.println(underscore);
    }
    public static void main(String[] args) {
        String logo = "  #        ####  ##### ######  ####\n" +
                "                #       #    #   #   #      #\n" +
                "                #       #    #   #   #####   ####\n" +
                "                #       #    #   #   #           #\n" +
                "                #######  ####    #   ######  ####";

        System.out.println(underscore + lineSeparator + "    Hello! I'm" + logo +
                lineSeparator + "    What can I do for you?" + lineSeparator + underscore);
        String userInput;
        ArrayList<Task> taskList = new ArrayList<Task>();

        while(true){
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            if(userInput.equals("bye")){
                System.out.println(underscore + lineSeparator
                        + "    Bye. Hope to see you again soon!\n" + underscore);
                break;
            }else if(userInput.equals("list")){
                printUserList(taskList);
            }else if (userInput.startsWith("mark ")){
                try {
                    int number = Integer.parseInt(userInput.substring(5)) - 1;
                    taskList.get(number).setDone(true);
                    System.out.print(underscore + lineSeparator + "     Nice! I've marked this task as done:" +
                            lineSeparator + "     [" + taskList.get(number).getStatusIcon() + "] " +
                            taskList.get(number).getDescription() + lineSeparator + underscore + lineSeparator);
                }
                catch (NumberFormatException e){
                    System.out.println("     Invalid integer input");
                }
            }else if (userInput.startsWith("unmark ")){
                try {
                    int number = Integer.parseInt(userInput.substring(7)) - 1;
                    taskList.get(number).setDone(false);
                    System.out.print(underscore + lineSeparator + "     OK, I've marked this task as not done yet:" +
                            lineSeparator + "     [" + taskList.get(number).getStatusIcon() + "] " +
                            taskList.get(number).getDescription() + lineSeparator + underscore + lineSeparator);
                }
                catch (NumberFormatException e){
                    System.out.println("     Invalid integer input");
                }
            }else{
                Task newTask = new Task(userInput);
                taskList.add(newTask);
                System.out.print(underscore + lineSeparator + "     added: " + userInput
                        + lineSeparator + underscore + lineSeparator);
            }
        }
    }
}
