package Chat.tasks;

import Chat.exceptions.RepeatMark;
import Chat.exceptions.RepeatUnmark;
import Chat.exceptions.invalidIndex;

import java.util.Scanner;

public class Evelyn {

    public static Task[] tasks;
    public static int indexOfTask = 0;

    public static void markTask(int index, boolean done) throws invalidIndex, RepeatMark, RepeatUnmark {
        if(index < 0 || index >= indexOfTask){
            throw new invalidIndex();
        }

        if (done) {
            try {
                tasks[index].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
            } catch(RepeatMark e){
                System.out.println("This task is already marked as done.");
            }
        } else {
            try {
                tasks[index].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
            } catch(RepeatUnmark e){
                System.out.println("This task is already unmarked as not done.");
            }
        }
        System.out.println("  " + tasks[index].getStatusIcon() + " "
                               + tasks[index].description);
        printLine();

    }

    public static void printList(Task[] tasks){
        int index = 1;
        for (Task task : tasks) {
            if (task == null) {
                printLine();
                break;
            } else {
                System.out.println(index + ". " + tasks[index - 1].toString());
                index++;
            }
        }
    }

    public static void echo() {
        String line;
        System.out.println("type your command: ");
        Scanner in = new Scanner(System.in);
        line = in.nextLine().trim();
        printLine();
        if (line.equals("bye")) {
            return;
        } else if (line.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                printList(tasks);

        } else if (line.startsWith("mark")) {
            try {
                int index = Integer.parseInt(line.substring(5).trim()) - 1;
                try {
                    markTask(index, true);
                } catch (invalidIndex e){
                    System.out.println("Invalid task index, please try again!");
                } catch (RepeatMark | RepeatUnmark e){
                    System.out.println("This task is already marked.");
                }
            } catch (NumberFormatException e){
                System.out.println("Please key in a number after 'mark'");
            }

        } else if (line.startsWith("unmark")) {
            try {
                int index = Integer.parseInt(line.substring(7).trim()) - 1;
                try {
                    markTask(index, false);
                }
                catch (RepeatMark | RepeatUnmark e){
                    System.out.println("This task is already marked.");
                }
                catch(invalidIndex e){
                    System.out.println("Invalid task index, please try again!");
                }
            } catch(NumberFormatException e){
                System.out.println("Please key in a number after 'unmark'");
            }

        } else if (line.startsWith("todo")) {
            try {
                tasks[indexOfTask] = new Todos(line.substring(5).trim());
                indexOfTask++;
                printAddingWords();
                printLine();
            } catch(StringIndexOutOfBoundsException e){
                System.out.println("Please enter a description for the todo task!");
            }
        } else if (line.startsWith("deadline")) {
                boolean haveBy = line.contains("/by");
                if (haveBy) {
                    try {
                        String[] parts = line.substring(9).split("/by");
                        String description = parts[0].trim();
                        String by = parts[1].trim();
                        tasks[indexOfTask] = new Deadlines(description, by);
                        indexOfTask++;
                        printAddingWords();
                        printLine();
                    } catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("Please enter a description for the deadline!");
                    }
                } else {
                    System.out.println("Please enter a time in the format of 'by/ (time)'");
                }

        } else if (line.startsWith("event")) {
                boolean haveFrom = line.contains("/from");
                boolean haveTo = line.contains("/to");
                if (haveFrom && haveTo) {
                    try {
                        String[] parts = line.substring(6).split("/from");
                        String description = parts[0].trim();
                        String[] date = parts[1].trim().split("/to");
                        String from = date[0].trim();
                        String to = date[1].trim();
                        tasks[indexOfTask] = new Events(description, from, to);
                        indexOfTask++;
                        printAddingWords();
                        printLine();
                    } catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("Please enter a description for the event!");
                    }
                } else {
                    System.out.println("Please enter a time in the format of '/from (time) /to (time)'");
                }
        } else {
            System.out.println("Please enter the correct command");
        }
        echo();
    }

    private static void printAddingWords() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks[indexOfTask - 1]);
        System.out.println("Now you have " + indexOfTask + " tasks in the list.");
    }

    public static void printLine() {
        System.out.print("____________________________________________________________\n");
    }

    public static void greeting() {
        printLine();
        System.out.println("Hello! I'm Evelyn");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void end() {
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void main(String[] args) {
        greeting();
        tasks = new Task[100];
        echo();
        end();
    }

}
