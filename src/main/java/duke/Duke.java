package duke;

import java.util.Scanner;
public class Duke {
    private static Parser parser = new Parser();

    public static void mainLoop(String input)  {
        try {
            parser.parseInput(input);
        } catch (PythiaException pe) {
            System.out.println("Not a valid command\n" + MoodSprite.getLineBreak());
        }
    }

    public static void main(String[] args) {
        String input = "Start";
        Scanner in = new Scanner(System.in);
        //System.out.println("Working Directory = " + System.getProperty("user.dir") + "\n");
        System.out.println(MoodSprite.getIdle() + "Hello, Im Pythia, how may I help you today?\n"+ MoodSprite.getLineBreak());
        parser.tryRetrieveList();
        while (!input.equals("bye")) {
            input  = in.nextLine();
            mainLoop(input);
        }
        System.out.println(MoodSprite.getHappy()+"Happy to help, have a great day.\n"+ MoodSprite.getLineBreak());
    }
}
