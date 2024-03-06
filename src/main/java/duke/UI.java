package duke;
import java.util.Scanner;

public class UI {
    private Parser parser = new Parser();

    public void tryParseInput(String input)  {
        try {
            parser.parseInput(input);
        } catch (PythiaException pe) {
            System.out.println("Not a valid command\n" + MoodSprite.getLineBreak());
        }
    }
    public void mainLoop() {
        String input = "Start";
        Scanner in = new Scanner(System.in);
        while (!input.equals("bye")) {
            input  = in.nextLine();
            tryParseInput(input);
        }
    }
}
