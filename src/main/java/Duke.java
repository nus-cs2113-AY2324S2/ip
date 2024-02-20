import java.io.FileWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    static final int MAX_SIZE = 100;
    public static void main(String[] args) {
        printsGreeting();
        try {
            mimicMessage();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private static void handleError(ThawException e) {
        if (e.getMessage().equals("Empty command")) {
            System.out.println("OOPS!!! The description of a todo cannot be empty.");
        } else if (e.getMessage().equals("Invalid command")) {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void markOrUnmarkTask (String usersInput, Task[] task) {
        int taskIndex;
        boolean isDone = false;
        if (usersInput.startsWith("mark")) {
            taskIndex = Integer.parseInt(usersInput.substring(5)) - 1 ;
            System.out.println("Nice! I've marked this task as done:");
            isDone = true;
        } else {
            taskIndex = Integer.parseInt(usersInput.substring(7)) - 1;
            System.out.println("OK, I've marked this task as not done yet:");
        }
        task[taskIndex].isDone = isDone;
        System.out.println(task[taskIndex].getStatusIcon());
    }

    private static void mimicMessage() throws FileNotFoundException {
        File f = new File("./data/ThawBot.txt");
        Scanner s = new Scanner(f);
        Scanner input = new Scanner(System.in);
        Task[] list = new Task[MAX_SIZE];
        int currentIteration = 0;

         while (s.hasNext()) {
            String[] currentLine = s.nextLine().split("\\s*\\|\\s*");
            if (currentLine[0].equals("D")) {
                list[currentIteration] = new Deadline(currentLine[2], currentLine[3]);
            }
            else if (currentLine[0].equals("T")) {
                 list[currentIteration] = new Todo(currentLine[2], currentLine[2]);
             }
            else if (currentLine[0].equals("E")) {
                String[] duration = currentLine[3].split("\\s*\\-\\s*");
                 list[currentIteration] = new Event(currentLine[2],
                         "from: " + duration[0] + " to: " + duration[1]);
            }

            if (currentLine[1].equals("1")) {
                list[currentIteration].isDone = true;
            }
            currentIteration++;
        }
        boolean canExit = false;

        while (!canExit) {
            String usersInput = input.nextLine();

            if (usersInput.equals("bye")) {
                canExit = true;
                printGoodByeMessage();
            } else if (usersInput.equals("list")) {
                printList(currentIteration, list);
            } else if ((usersInput.startsWith("unmark") && !usersInput.strip().endsWith("unmark")) ||
                    (usersInput.startsWith("mark") && !usersInput.strip().endsWith("unmark"))) {
                markOrUnmarkTask(usersInput, list);
                try {
                    saveData(list, currentIteration);
                } catch (IOException e) {
                    System.out.println("Error with saving");
                }
            } else {
                try {
                    addTask(usersInput, list, currentIteration);
                    saveData(list, currentIteration);
                    currentIteration++;
                } catch (ThawException e) {
                    handleError(e);
                } catch (IOException e) {
                    System.out.println("Error with saving");
                }
            }
        }
    }

    private static void addTask(String usersInput, Task[] list, int currentIteration) throws ThawException, IOException {
        if (usersInput.strip().equals("todo") || usersInput.strip().equals("deadline") || usersInput.strip().equals("event")) {
            throw new ThawException("Empty command");
        } else if (usersInput.startsWith("todo")) {
            list[currentIteration] = new Todo(usersInput.substring(5), usersInput);
        } else if (usersInput.startsWith("deadline")) {
            int startingIndex = usersInput.indexOf("/by");
            list[currentIteration] = new Deadline(usersInput.substring(9, startingIndex - 1),
                    usersInput.substring(startingIndex + 4));
        } else if (usersInput.startsWith("event")) {
            int startIndex = usersInput.indexOf("from");
            int endIndex = usersInput.indexOf("to");
            list[currentIteration] = new Event(usersInput.substring(6, startIndex - 2),
                    "from: " + usersInput.substring(startIndex+ 5, endIndex - 2) + " to: " + usersInput.substring(endIndex + 3));
        } else {
            throw new ThawException("Invalid command");
        }
        printAcknowledgementMessage(list, currentIteration);
    }


    public static void saveData(Task[] list, int currentIteration) throws IOException {
        final String filePath = "./data/ThawBot.txt";
        FileWriter fw = new FileWriter(filePath);
        String textToAdd = "";
        for (int i = 0; i < currentIteration + 1; i++) {
            textToAdd += list[i].printFileFormat() + System.lineSeparator();
        }
        fw.write(textToAdd);
        fw.close();
    }
    private static void printsGreeting() {
        String greetingMessage = "Hello! I'm ThawBot!\nWhat can I do for you?\n";
        System.out.println(greetingMessage);
    }

    private static void printGoodByeMessage() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);
    }

    private static void printList(int index, Task[] task) {
        for (int i = 0; i < index; i ++) {
            System.out.println((i + 1) + ". " + task[i].getStatusIcon());
        }
    }

    private static void printAcknowledgementMessage(Task[] task, int index) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task[index].getStatusIcon());
        System.out.print("Now you have " + (index + 1) + " task in the list.");
    }
}
