import java.util.Scanner;

public class PeeKay {

    static String line = "\t____________________________________________________________";
    static Task[] tasks = new Task[100];
    static int count = 0;

    public static void echo(String input) {
        System.out.println("\t Got it. I've added this task: ");
        System.out.println(("\t   " + tasks[count - 1].toString()));
        String task = (count == 1) ? "task " : "tasks ";
        System.out.println("\t Now you have " + count + " " + task + "in the tasks.");
    }

    public static void sayBye() {
        System.out.println("\t Bye. Hope to see you again soon!");
    }

    public static void createTodo(String input) throws EmptyTaskException {
        String todo = input.substring(input.indexOf("todo ") + 5);
        if (todo.isBlank()) {
            throw new EmptyTaskException();
        }
        tasks[count] = new ToDo(todo);
        count++;
        echo(input);
    }


    public static void createDeadline(String input) throws EmptyTaskException, MissingDeadlineException {
        String description = input.replaceFirst("deadline ", "");
        int by = description.indexOf("/");
        if (by == -1){
            throw new MissingDeadlineException();
        }
        String deadline = description.substring(0, by - 1);
        if (deadline.isBlank()){
            throw new EmptyTaskException();
        }
        String date = description.substring(by + 4);
        tasks[count] = new Deadline(deadline, date);
        count++;
        echo(input);
    }

    public static void createEvent(String input) throws EmptyTaskException, MissingDeadlineException, MissingStartException {
        String description = input.replaceFirst("event ", "");
        int from = description.indexOf("/from");
        if (from == -1) {
            throw new MissingStartException();
        }
        int by = description.indexOf("/to");
        if (by == -1) {
            throw new MissingDeadlineException();
        }
        String startDate = description.substring(from + 6, by - 1);
        String endDate = description.substring(by + 4);
        String event = description.substring(0, from - 1);
        if (event.isBlank()){
            throw new EmptyTaskException();
        }
        tasks[count] = new Event(event, startDate, endDate);
        count++;
        echo(input);
    }

    public static void handleInput(String input) throws UnknownInputException {
        if (input.contains("todo")) {
            try {
                createTodo(input);
            } catch (EmptyTaskException e) {
                System.out.println("Todo should not be empty!");
            }
        } else if (input.contains("deadline")) {
            try {
                createDeadline(input);
            } catch (MissingDeadlineException e) {
                System.out.println("Include when this deadline is due as such: /by {deadline}");
            } catch (EmptyTaskException e){
                System.out.println("Deadline should not be empty!");
            }
        } else if (input.contains("event")) {
            try {
                createEvent(input);
            } catch (MissingDeadlineException e) {
                System.out.println("Include when this event ends as such: /to {end date}");
            } catch (MissingStartException e) {
                System.out.println("Include when this event starts as such: /from {start date}");
            } catch (EmptyTaskException e) {
                System.out.println("Event should not be empty!");
            }
        } else if (input.contains("bye")) {
            sayBye();
        } else {
            throw new UnknownInputException();
        }
    }

    public static int getIndexToMark(String input) {
        int idx = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
        return idx - 1;
    }

    public static void listItems() {
        System.out.println("\t Here are the tasks in your list:");
        for (int x = 0; x < count; x++) {
            System.out.println("\t  " + (x + 1) + "." + tasks[x].toString());
        }
    }

    public static void unmarkItem(String input) {
        int idx = getIndexToMark(input);
        tasks[idx].setDone(false);
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.println("\t " + tasks[idx].toString());
    }

    public static void markItem(String input) {
        int idx = getIndexToMark(input);
        tasks[idx].setDone(true);
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t " + tasks[idx].toString());
    }

    public static void chat() {
        String input;
        do {
            input = new Scanner(System.in).nextLine();
            System.out.println(line);
            if (input.equals("list")) {
                listItems();
            } else if (input.contains("unmark")) {
                unmarkItem(input);
            } else if (input.contains("mark")) {
                markItem(input);
            } else {
                try {
                    handleInput(input);
                } catch (UnknownInputException e){
                    System.out.println("I've not seen this input before. Please tell me something else I can help you with.");
                }
            }
            System.out.println(line);
        } while (!input.equals("bye"));
    }

    public static void main(String[] args) {

        System.out.println(line);
        System.out.println("\t Hello! I'm PeeKay");
        System.out.println("\t What can I do for you?");
        System.out.println(line);
        chat();
    }
}




