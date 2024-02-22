package baron;

import task.TaskList;
import parser.Parser;

import java.io.IOException;
import java.util.Scanner;

public class Baron {
    public static void main(String[] args) throws IllegalArgumentException {
        greetUser();
        TaskList tasks = new TaskList();
        FileStorage.loadFile();
        try {
            getUserInput(tasks);
        } catch (IllegalArgumentException e) {
            System.out.println("Command cannot be empty. Please enter a valid command.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    private static void greetUser() {
        System.out.println("Hello! I'm Baron");
        System.out.println("What can I do for you?\n");
    }

    public static void getUserInput(TaskList tasks) throws IOException {
        Scanner userInput = new Scanner(System.in);
        //noinspection InfiniteLoopStatement
        while (true) {
            String input = userInput.nextLine();
            input = input.toLowerCase();
            Parser.parseInput(input, tasks);
        }
    }

    public static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}