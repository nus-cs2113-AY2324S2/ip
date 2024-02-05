import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        List list = new List();
        list.generateSizeOfList(100);
        System.out.println("Hello! My name is Jeff.");
        System.out.println("What can I do for you?");


        boolean isChatting = true;
        while (isChatting){
            String userInput = Parser.getUserInput();
            String userFirstWord = Parser.getFirstWord(userInput);
            int taskIndex;


            switch (userFirstWord){
            case "list":
                list.listTasks();
                break;

            case "bye":
                isChatting = false;
                break;

            case "mark":
                taskIndex = Parser.getFirstInt(userInput);
                list.markIndex(taskIndex);
                break;

            case "unmark":
                taskIndex = Parser.getFirstInt(userInput);
                list.unmarkIndex(taskIndex);
                break;

            case "deadline":
                String description = "PLACEHOLDER";
                list.insertTask(new Deadline(description));
                break;


            case "event":
                String otherDescription = "PLACEHOLDER";
                String start = "PLACEHOLDER START";
                String end = "PLACEHOLDER END";
                list.insertTask(new Event(otherDescription, start, end));
                break;


            case "todo":
                list.insertTask(new Todo(Parser.extractTodoDescription(userInput)));
                break;

            default:
                System.out.println("Hi, please use a correct keyword (todo, event, deadline) to add an item to the " +
                        "list");
                break;
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
