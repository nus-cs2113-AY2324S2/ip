import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.String;

public class TimL {
    public static void respondToCommand(String text, List<Task> list){
        if (text.equals("list")){
            printToDoList(list);
        } else if (text.startsWith("unmark") || text.startsWith("mark")) {
            boolean isMarkingComplete = text.startsWith("mark");
            int position = Integer.parseInt(text.replaceAll("\\D", "")) - 1;

            if (position >= 0 && position < list.size()) {
                Task task = list.get(position);
                if (isMarkingComplete) {
                    task.markAsComplete();
                } else {
                    task.markAsIncomplete();
                }
            } else {
                System.out.println("Invalid position.");
            }
        }else{
            addToList(text, list);
        }
    }

    public static void printToDoList(List<Task> list){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");

        int i = 1;
        for (Task task : list) {
            System.out.printf("    %d: [%s] [%s] %s%n", i, task.getType(), task.getStatus() ? "X" : " ", task.getDescription());
            i++;
        }

        System.out.println("    ____________________________________________________________\n");
    }

    public static void addToList(String text, List<Task> list){
        String type;
        switch (text.words[0]) {
            case "deadline":
                // code block to be executed if variable == value1
                break;
            case "event":
                // code block to be executed if variable == value2
                break;
            default:
                // code block to be executed if variable doesn't match any case
        }

        Task newTask = new Task(text, type);
        list.add(newTask);
        System.out.println("    ____________________________________________________________\n" +
                "    added: " + text + " to the list\n" +
                "    ____________________________________________________________\n");
    }

    public static void main(String[] args) {
        List<Task> todolist = new ArrayList<>(  100);
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm TimL\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
        String response;
        Scanner in = new Scanner(System.in);
        response = in.nextLine();
        while (!response.equals("bye")){
            respondToCommand(response, todolist);
            in = new Scanner(System.in);
            response = in.nextLine();
        }
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n");
    }
}
