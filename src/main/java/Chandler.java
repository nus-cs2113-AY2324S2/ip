import java.util.Scanner;

public class Chandler {

    public static final String LINE_DIVIDER = "------------------------------------------";
    public static final String OUTPUT_INDENTATION = "    ";
    private TaskList taskList;

    public void run() {

        taskList = new TaskList();
        ChandlerFileManager storage = new ChandlerFileManager();
        try {
            storage.loadTaskListFromFile(taskList);
        } catch (ChandlerException e) {
            System.out.println("ChandlerException: " + e.getMessage());
            taskList = new TaskList();
        }

        System.out.println(LINE_DIVIDER);
        System.out.println("Hello! I'm Chandler. Your personal sarcastic task manager.");
        Scanner myObj = new Scanner(System.in);
        System.out.println("So, what can you do for me? :)");
        System.out.println(LINE_DIVIDER);
        String input = "";

        while(!input.equals("bye")) {
            input = myObj.nextLine();
            try{
                System.out.println(LINE_DIVIDER);
                String[] words = input.split("\\s+");
                String inputCommand = words[0].toUpperCase();

                if (words.length <= 1 && !inputCommand.equals("LIST") && !inputCommand.equals("BYE")) {
                    throw new ChandlerException("You need to specify a task.");
                }
                switch (inputCommand) {
                    case "LIST":
                        taskList.listTasks();
                        break;
                    case "MARK":
                        taskList.markTaskAsDone(Integer.parseInt(input.replace("mark ", "")) - 1);
                        break;
                    case "UNMARK":
                        taskList.markTaskAsUndone(Integer.parseInt(input.replace("unmark ", "")) - 1);
                        break;
                    case "DELETE":
                        taskList.deleteTask(Integer.parseInt(input.replace("delete ", "")) - 1);
                        break;
                    case "TODO":
                        taskList.addTask("T", input.replace("todo ", ""));
                        break;
                    case "DEADLINE":
                        taskList.addDeadline(input.replace("deadline ", ""));
                        break;
                    case "EVENT":
                        taskList.addEvent(input.replace("event ", ""));
                        break;
                    case "FIND":
                        taskList.findMatchingTasks(input.replace("find ", ""));
                        break;
                    case "BYE":
                        break;
                    default:
                        throw new ChandlerException("You need to specify if it's a todo, deadline or event.");
                }
            } catch (ChandlerException e) {
                System.out.println("ChandlerException: " + e.getMessage());
            }
            System.out.println(LINE_DIVIDER);
        }
        System.out.println("Bye. Meeting you was okay..");
        System.out.println(LINE_DIVIDER);
        myObj.close();

        storage.saveTaskListToFile(taskList);
    }
}






