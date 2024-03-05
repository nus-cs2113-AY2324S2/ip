package soot.ui;

import soot.parser.Parser;
import soot.task.Task;
import soot.task.TaskList;

import java.util.ArrayList;

public class UserUi {
    public static final String INDENT = "  ";

    public UserUi() {
        
    }
    public static void showUserGreeting() {
        System.out.println("Hello! I'm Soot, your personal chatbot companion :)");
        System.out.println("What can I help you with?");
        displayDividerLine();
    }

    public static void showGoodbyeMessage() {
        System.out.println("Bye! Till the next time we meet... \n " +
                "i've saved your list for future use <3");
        displayDividerLine();
    }

    public static void displayDividerLine() {
        int LINE_LENGTH = 60;
        for (int i = 0; i < LINE_LENGTH; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }

    public static void displayIndent() {
        System.out.print(INDENT);
    }

    public static void showTaskListCount() {
        int taskCount = TaskList.getSize();
        System.out.println("you currently have " + taskCount + " tasks on your list :)");
    }

    public static void printKeywordList(ArrayList<Task> foundKeywordList) {
        if (foundKeywordList.isEmpty()) {
            System.out.println("i couldn't find any tasks with this word.");
        } else {
            System.out.println("i found your word in these tasks:");
            for (int i = 0; i < foundKeywordList.size(); i++) {
                UserUi.displayIndent();
                foundKeywordList.get(i).printTask(i + 1);
            }
        }
        UserUi.displayDividerLine();
    }
}
