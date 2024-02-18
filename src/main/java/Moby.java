import interactions.*;
import java.util.Scanner;
public class Moby {
    public static void main(String[] args) {
        Chatbot bot = new Chatbot();
        ToDoList list = new ToDoList();
        bot.greet();
        String line;
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("YOU: ");
            line = in.nextLine();
            System.out.print(bot.getName() + ": ");
            if (line.equals("bye")) {
                break;
            }
            if (line.equals("list")) {
                list.printList();
            }
            else if (line.startsWith("mark")){
                list.mark(line, true);
            }
            else if (line.startsWith("unmark")) {
                list.mark(line, false);
            }
            else if (line.startsWith("todo")) {
                list.addNewTask(line, "todo");
            }
            else if (line.startsWith("deadline")) {
                list.addNewTask(line, "deadline");
            }
            else if (line.startsWith("event")) {
                list.addNewTask(line, "event");
            } else {
                bot.didYouMean(line);
            }
        }
        bot.exit();
    }
}
