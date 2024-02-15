
import java.util.Scanner;
import java.lang.String;

public class TimL {
    public static void respondToCommand(String response, Task[] list) {
        String command = TextParser.getCommand(response);
        String message = TextParser.getMessage(response);
        int emptyIndex = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                emptyIndex = i;
                break;
            }
        }
        int taskIndex;
        switch (command) {
            case "list":
                printList(list, emptyIndex);
                break;
            case "mark":
                taskIndex = Integer.parseInt(message) - 1;
                TaskManager.mark(taskIndex, list);
                break;
            case "unmark":
                taskIndex = Integer.parseInt(message) - 1;
                TaskManager.unMark(taskIndex, list);
                break;
            case "todo":
                TaskManager.addToDo(message, list, emptyIndex);
                break;
            case "deadline":
                TaskManager.addDeadline(message, list, emptyIndex);
                break;
            case "event":
                TaskManager.addEvent(message, list, emptyIndex);
                break;
            default:
                Printer.printDefaultError();
                break;
        }
    }



    public static void printList(Task[] list, int index){
        Printer.printList();
        int i = 1;
        for (int j = 0; j < index; j++) {
            System.out.println("    " + i + ": " + list[j].getStatus());
            i++;
        }
        Printer.printLine();
    }


    public static void main(String[] args) {
        Task[] todolist = new Task[100];
        Printer.printGreeting();
        String response;
        Scanner in = new Scanner(System.in);
        response = in.nextLine();
        while (!response.equals("bye")){
            respondToCommand(response, todolist);
            in = new Scanner(System.in);
            response = in.nextLine();
        }
        Printer.printGoodbye();
    }
}
