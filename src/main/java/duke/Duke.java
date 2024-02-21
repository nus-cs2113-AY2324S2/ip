package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;
public class Duke {
    private static MoodSprite mood = new MoodSprite();
    private static List<Task> list = new ArrayList<>(2);
    private static Parser parser = new Parser();
    private static final String lineBreak = "----------------------------------------------------------";
    public static void printList(List<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i+1)+"."+list.get(i));
        }
    }

    public static void saveList() throws IOException {
        FileWriter fileInput = new FileWriter("./ip/src/main/java/savedList.txt");
        for (int i = 0; i < list.size(); i++) {
            fileInput.write(list.get(i).toString() + "\n");
        }
        fileInput.close();
    }

    public static void mainLoop(String input) throws PythiaException {
        if (input.equalsIgnoreCase("list")) {
            printList(list);
        }
        else if (parser.isTaskCommand(input)) {
            try {
                list.add(parser.addTask(input));
                System.out.println("added: " + list.get(list.size() - 1));
            } catch (PythiaException pe) {
                System.out.println("Please provide a proper input");
            }
        }
        else if (input.contains("unmark ")) {
            String[] splitInput = input.split(" ");
            list.get(Integer.parseInt(splitInput[1])-1).doneIsFalse();
            System.out.println("Unmarked "+ Integer.parseInt(splitInput[1]));
        }
        else if (input.contains("mark ")) {
            String[] splitInput = input.split(" ");
            list.get(Integer.parseInt(splitInput[1])-1).doneIsTrue();
            System.out.println("Marked "+ Integer.parseInt(splitInput[1]));
        } else if (input.contains("save")) {
            try {
                saveList();
            } catch (IOException e) {
                System.out.println("File not found");
            }
        }
        else if (input.contains("delete ")) {
            String[] splitInput = input.split(" ");
            System.out.println("Deleted " + list.get(Integer.parseInt(splitInput[1])-1));
            list.remove(list.get(Integer.parseInt(splitInput[1])-1));
        }
        else {
            throw new PythiaException();
        }
        System.out.println(lineBreak);
    }
    public static void main(String[] args) {
        String input = "Start";
        Scanner in = new Scanner(System.in);
        System.out.println(mood.getIdle() + "Hello, Im Pythia, how may I help you today?\n"+lineBreak);
        while (!input.equals("bye")) {
            input  = in.nextLine();
            try {
                mainLoop(input);
            } catch (PythiaException pe) {
                System.out.println("Not a valid command");
            }
        }
        System.out.println(mood.getHappy()+"Happy to help, have a great day.\n"+lineBreak);
    }
}
