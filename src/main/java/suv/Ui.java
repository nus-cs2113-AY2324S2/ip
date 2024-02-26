package suv;

import java.util.ArrayList;
import static suv.TaskList.tasksList;

public class Ui {
    final static String LINE = "____________________________________________________________\n";
    public static void printAddTodoMessage(Task newTask, int size){
        System.out.println(LINE +
                " Got it. I've added this task:\n" + "  " + newTask +
                "\n Now you have " + Integer.toString(size) +" tasks " + "in the list.\n" + LINE
        );
    }

    public static void printAddEventMessage(Task newTask, int size){
        System.out.println(LINE +
                " Got it. I've added this task:\n" + "  " + newTask +
                "\n Now you have " + Integer.toString(size) +" tasks " + "in the list.\n" + LINE
        );
    }

    public static void printAddDeadlineMessage(Task newTask, int size){
        System.out.println(LINE +
                " Got it. I've added this task:\n" + "  " + newTask +
                "\n Now you have " + Integer.toString(size) +" tasks " + "in the list.\n" + LINE
        );
    }

    public static void printMarkMessage(String description){
        System.out.println(LINE + " Nice! I've marked this task as done:\n" + "   [X] " + description + "\n" + LINE);
    }

    public static void printUnmarkMessage(String description){
        System.out.println(LINE + " OK, I've marked this task as not done yet:\n" + "   [ ] " + description+ "\n" + LINE);
    }

    public static void printDeleteTodoMessage(Task currentTask, int size){
        System.out.println(LINE + " Noted. I've removed this task:\n" + " " + currentTask + "\n Now you have " + Integer.toString(size) +" tasks " + "in the list.\n" + LINE);

    }

    public static void printByeMessage(){
        System.out.println(LINE +" Bye. Hope to see you again soon!\n" + LINE);
    }

    public static void printList(){
        System.out.println(LINE + " Here are the tasks in your list:");
        for(int i = 0; i < tasksList.size(); i++){
            int index = i + 1;
            System.out.println(" " + index + "." + tasksList.get(i) );
        }
        System.out.println(LINE);
    }
}
