import java.util.Scanner;
import java.util.ArrayList;

public class Alice {
    public static void main(String[] args) throws AliceException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String line = "____________________________________________________________";

        System.out.println(line);
        System.out.println("yoo i'm alice! ur virtual bestie here to keep track of ur vibes");
        System.out.println("what's poppin? u can tell me stuff to remember or type 'bye' to dip");
        System.out.println(line);

        while (true) {
            String input = scanner.nextLine();

            try {
                if (input.equals("bye")) {
                    System.out.println(line);
                    System.out.println("bye bestie! catch ya later, and remember to stay hydrated <3");
                    System.out.println(line);
                    break;
                } else if (input.equals("help")) {
                    // Respond to the help command by listing available commands
                    System.out.println(line);
                    System.out.println("okay bestie, here's what u can throw at me to help ya <3 :");
                    System.out.println("  - 'list': Peek at all that you gotta get done");
                    System.out.println("  - 'todo [description]': Got something new to remember? I gotchu girlie!");
                    System.out.println("  - 'deadline [description] /by [date]': Racing against the clock? I'll keep ya accountable");
                    System.out.println("  - 'event [description] /from [start time] /to [end time]': Got new plans? Lemme know so u don't miss out any of them");
                    System.out.println("  - 'mark [task number]': Finshed a task? YAY good job bestie ill help mark it off for ya");
                    System.out.println("  - 'unmark [task number]': Took a lil step back? No worries, we can get back to it later u got this!");
                    System.out.println("  - 'bye': Need to head off somewhere? I'll catch ya later! Rememeber that ur amazing bestie :)");
                    System.out.println("Feelin' lost? Just type 'help' and I'll be right here with the deets again!");
                    System.out.println(line);
                } else if (input.startsWith("list")) {
                    System.out.println(line);
                    if (tasks.isEmpty()) {
                        System.out.println("this list is emptier than my motivation on a monday morning lol");
                    } else {
                        System.out.println("aight here's what's up:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i).toString());
                        }
                    }
                    System.out.println(line);
                } else if (input.startsWith("todo ")) {
                    handleTodo(input, tasks, line);
                } else if (input.startsWith("mark") || input.startsWith("unmark")) {
                    handleMarkUnmark(input, tasks, line);
                } else if (input.startsWith("deadline ")) {
                    handleDeadline(input, tasks, line);
                } else if (input.startsWith("event ")) {
                    handleEvent(input, tasks, line);
                } else {
                    throw new AliceException("ayo my bad i can't seem to understand ya, try saying smt valid, or type 'help'");
                }
            } catch (AliceException e){
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println(line);
            }
        }
        scanner.close();
    }

    private static void handleTodo(String input, ArrayList<Task> tasks, String line) throws AliceException {
        String description = input.substring(5).trim();
        Todo newTask = new Todo(description);
        if (description.isEmpty()){
            throw new AliceException("hey u cant leave the description empty :(");
        }
        tasks.add(newTask);
        System.out.println(line);
        System.out.println("aight i gotchu bestie, added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        System.out.println(line);
    }

    private static void handleMarkUnmark(String input, ArrayList<Task> tasks, String line) throws AliceException {
        int taskIndex = Integer.parseInt(input.replaceAll("\\D", "")) - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task task = tasks.get(taskIndex);
            if (input.startsWith("mark")) {
                task.markAsDone();
                System.out.println(line);
                System.out.println("yas queen, marked it off! we are crushing it frfr:");
            } else {
                task.markAsUndone();
                System.out.println(line);
                System.out.println("aight, took a lil step back, unmarked this one:");
            }
            System.out.println(task);
        } else {
            throw new AliceException("aw man that task doesn't seem to exist :( try using a valid task number yea");
        }
        System.out.println(line);
    }

    private static void handleDeadline(String input, ArrayList<Task> tasks, String line) throws AliceException {

        if(!input.contains("/by")){
            throw new AliceException("ayo deadline inputs must include '/by' so i'll know the deadline yea :)");
        }

        String[] parts = input.substring(9).split(" /by ", 2);
        Task newTask = new Deadline(parts[0], parts[1]);

        if (parts[1].isEmpty()){
            throw new AliceException("hey do tell me when ur deadline is yea?");
        }

        tasks.add(newTask);
        System.out.println(line);
        System.out.println("ooh we're on a deadline kinda vibe? aight added it in:");
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        System.out.println(line);
    }

    private static void handleEvent(String input, ArrayList<Task> tasks, String line) throws AliceException {

        if (!input.contains("/to") || !input.contains("/from")){
            throw new AliceException("ayo event commands must include '/from' and '/to' so that i'll know when your event starts and ends yea?");
        }

        String[] parts = input.substring(6).split(" /from ", 2);

        if (!parts[1].contains(" /to ")){
            throw new AliceException("ayo the event time must be specified with '/from' followed by '/to' yea?");
        }

        String[] timeParts = parts[1].split(" /to ", 2);

        if(timeParts.length < 2){
            throw new AliceException("ayo do specify both the start and end times for your event yea?");
        }

        Task newTask = new Event(parts[0], timeParts[0], timeParts[1]);
        tasks.add(newTask);
        System.out.println(line);
        System.out.println("ayy noted down an event, don't forget girlie: ");
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        System.out.println(line);
    }
}