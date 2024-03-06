import java.util.Scanner;
public class Ui {
    public String input;
    public Scanner in;
    public final int EXPECTED_LENGTH = 2;

    public Ui() {
        this.in = new Scanner(System.in);
        this.input = in.nextLine();
    }

    public void nextInput() {
        this.input = in.nextLine();
    }

    public void processInput(String input, TaskList taskList) throws JaneException {
        try {
            String[] inputPart = input.split(" ", 2);
            if (inputPart.length < EXPECTED_LENGTH || inputPart[1] == null) {
                switch (inputPart[0]) {
                case "todo" :
                case "deadline" :
                case "event" :
                    throw new JaneException("Description for a " + inputPart[0] + " cannot be empty");
                }
            }

            Parser parser = new Parser(taskList);
            switch (inputPart[0]) {
            case "todo":
                parser.processTodo(inputPart[1]);
                break;
            case "deadline":
                parser.processDeadline(inputPart[1]);
                break;
            case "event":
                parser.processEvent(inputPart[1]);
                break;
            case "list":
                taskList.printList();
                break;
            case "mark":
                parser.markAsDone(Integer.parseInt(inputPart[1]) - 1);
                break;
            case "unmark":
                parser.markAsUndone(Integer.parseInt(inputPart[1]) - 1);
                break;
            case "delete":
                taskList.removeTask(Integer.parseInt(inputPart[1]) - 1);
                break;
            default:
                throw new JaneException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (JaneException e) {
            throw new JaneException(e.getMessage());
        }
    }
}