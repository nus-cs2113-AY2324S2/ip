import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner myObj = new Scanner(System.in);
        String userInput = "";
        List<Task>taskList = new ArrayList<>();
        greet();
        while(true){
            userInput = myObj.nextLine();
            if(userInput.equals("bye")){
                goodbye();
                break;
            } else if(userInput.equals("list")){
                printList(taskList);
                makeHorizontalRow();
                continue;
            } else if(userInput.contains("unmark")){
                String[] handleInput = userInput.split(" ");
                int taskToUnmark = Integer.parseInt(handleInput[1]) - 1;
                taskList.get(taskToUnmark).unmark();
                System.out.printf("%s unmarked. Type 'list' to see updated list\n Type mark [list index] to mark this task\n"
                        ,taskList.get(taskToUnmark).getDescription());
                makeHorizontalRow();
                continue;
            } else if(userInput.contains("mark")){
                String[] handleInput = userInput.split(" ");
                int taskToMark = Integer.parseInt(handleInput[1]) - 1;
                taskList.get(taskToMark).markAsDone();
                System.out.printf("%s marked as done. Type 'list' to see updated list\n Type unmark [list index] to unmark this task\n"
                        ,taskList.get(taskToMark).getDescription());
                makeHorizontalRow();
                continue;
            }
            taskList.add(new Task(userInput));
            System.out.printf("added %s\n",userInput);
            makeHorizontalRow();

        }

    }

    public static void greet(){
        System.out.println("Hello I am Massimo boi!\n What can I do for you?\n " +
                        "To add task, type the task name. " +
                        "To check list of tasks, type list. To mark as done, type mark [list index]");

        makeHorizontalRow();
    }

    public static void goodbye(){
        System.out.println("This is Massimo boi signing out!");
        makeHorizontalRow();
    }

    public static void makeHorizontalRow(){
        for(int i = 0; i < 50; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printList(List<Task> taskList){
        for(int i = 0; i < taskList.size(); i++){
            System.out.printf("%d. %s %s \n",i+1, taskList.get(i).getStatus(), taskList.get(i).getDescription());
        }
    }



}
