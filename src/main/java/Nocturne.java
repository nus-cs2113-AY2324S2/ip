import exceptions.NocturneException;
import util.Parser;
import util.TaskList;

public class Nocturne {
    public static void main(String[] args) throws NocturneException {
        TaskList list = new TaskList();
        System.out.println("Good evening. I'm Nocturne.");
        System.out.println("What ails you on this fine day?");
        Parser.getInput(list);
        System.out.println("Farewell, and may the fortunes be ever in your favour.");
    }
}
