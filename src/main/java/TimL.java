
import java.util.Scanner;
import java.lang.String;

public class TimL {
<<<<<<< HEAD
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
=======
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
        } else {
            addToList(text, list);
        }
    }

    public static void printToDoList(List<Task> list){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");

        int i = 1;
        for (Task task : list) {
            System.out.printf("    %d: [%s] %s%n", i, task.getStatus() ? "X" : " ", task.getDescription());
            i++;
        }

        System.out.println("    ____________________________________________________________\n");
    }

    public static void addToList(String task, List<Task> list){
        Task newTask = new Task(task);
        list.add(newTask);
        System.out.println("    ____________________________________________________________\n" +
                "    added: " + task + " to the list\n" +
                "    ____________________________________________________________\n");
    }

    public static void main(String[] args) {
        List<Task> todolist = new ArrayList<>(  100);
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm TimL\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
>>>>>>> 5a419c558feda1f16aac0891899331edea92bc9f
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
