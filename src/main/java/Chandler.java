import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Chandler {

    public static final String LINE_DIVIDER = "____________________________________________________________";
    public static final String OUTPUT_INDENTATION = "    ";

    public static void main(String[] args) {

        System.out.println(LINE_DIVIDER);
        System.out.println("Hello! I'm Chandler. Your personal sarcastic task manager.");
        Scanner myObj = new Scanner(System.in);
        System.out.println("So, what can you do for me? :)");
        System.out.println(LINE_DIVIDER);

        String input = myObj.nextLine();
        List<Task> list = new ArrayList<>();


        for(int listSize = 1; !input.equals("bye"); input = myObj.nextLine()) {
            try{
                System.out.println(LINE_DIVIDER);
                String[] words = input.split("\\s+");
                String inputCommand = words[0];

                if (words.length <= 1 && !inputCommand.equals("list") && !inputCommand.equals("bye") && !inputCommand.equals("mark") && !inputCommand.equals("unmark")) {
                    throw new ChandlerException("You need to specify a task.");
                }

                switch (inputCommand) {
                    case "list":
                        System.out.println(OUTPUT_INDENTATION + "Here are the tasks in your list:");
                        for (int index = 0; index < listSize-1; index++) {
                            System.out.println(OUTPUT_INDENTATION + (index+1) + "." + list.get(index));
                        }
                        break;
                    case "mark":
                        int task_number = Integer.parseInt(input.replace("mark ", "")) - 1;
                        if (task_number < 0 || task_number >= list.size()) {
                            throw new ChandlerException("Invalid task number");
                        }
                        System.out.println(OUTPUT_INDENTATION + "Nice! I've marked this task as done:");
                        list.get(task_number).markAsDone();
                        System.out.println(OUTPUT_INDENTATION + list.get(task_number));
                        break;
                    case "unmark":
                        int task_number2 = Integer.parseInt(input.replace("unmark ", "")) - 1;
                        if (task_number2 < 0 || task_number2 >= list.size()) {
                            throw new ChandlerException("Invalid task number");
                        }
                        System.out.println(OUTPUT_INDENTATION + "Ok, I've marked this task as not done yet:");
                        list.get(task_number2).markAsUndone();
                        System.out.println(OUTPUT_INDENTATION + list.get(task_number2));
                        break;
                    case "todo":
                        Todo taskTodo = new Todo(input.replace("todo ", ""), "T");
                        list.add(taskTodo);
                        System.out.println(
                                OUTPUT_INDENTATION + "Awesome! Something to do without deadline hehe\n" +
                                        OUTPUT_INDENTATION + "  " + taskTodo + "\n" +
                                        OUTPUT_INDENTATION + "You better not procrastinate... or maybe you should");
                        listSize++;
                        break;
                    case "deadline":
                        input = input.replace("deadline ", "");
                        int indexBy = input.indexOf("/by");
                        String description = input.substring(0, indexBy);
                        String by = input.substring(indexBy + 4);
                        Deadline taskDeadline = new Deadline(description, by, "D");
                        list.add(taskDeadline);
                        System.out.println(
                                OUTPUT_INDENTATION + "Oh wow... a deadline, how exciting :)\n" +
                                        OUTPUT_INDENTATION + "  " + taskDeadline+ "\n" +
                                        OUTPUT_INDENTATION + "A deadline a day keeps the sanity away.");
                        listSize++;
                        break;
                    case "event":
                        input = input.replace("event ", "");
                        int indexFrom = input.indexOf("/from");
                        int indexTo = input.indexOf("/to");
                        String description2 = input.substring(0, indexFrom);
                        String from = input.substring(indexFrom + 6, indexTo);
                        String to = input.substring(indexTo + 4);
                        Event taskEvent = new Event(description2, from, to, "E");
                        list.add(taskEvent);
                        System.out.println(
                                OUTPUT_INDENTATION + "Event... yeay.\n" +
                                        OUTPUT_INDENTATION + "  " + taskEvent + ")\n" +
                                        OUTPUT_INDENTATION + "Can it BE any more fun?");
                        listSize++;
                        break;
                    default:
                        throw new ChandlerException("You need to specify if it's a todo, deadline or event.");
                }
                System.out.println(LINE_DIVIDER);
            } catch (ChandlerException e) {
                System.out.println("ChandlerException: " + e.getMessage());
                System.out.println(LINE_DIVIDER);
            }
        }
        System.out.println(LINE_DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_DIVIDER);
    }
}






