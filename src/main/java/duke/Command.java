package duke;

import java.util.ArrayList;

public class Command {

    public static void list(ArrayList<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i+1)+"."+list.get(i));
        }
    }

    public static void tryAddTask(String input, ArrayList<Task> list, ArrayList<String> stringList) {
        try {
            list.add(addTask(input));
            stringList.add(input);
            System.out.println("added: " + list.get(list.size() - 1));
        } catch (PythiaException pe) {
            System.out.println("Please provide a proper input");
        }
    }

    public static void directAddTask(String input, ArrayList<Task> list, ArrayList<String> stringList) {
        try {
            list.add(addTask(input));
            stringList.add(input);
        } catch (PythiaException pe) {
            System.out.println("Please provide a proper input");
        }
    }

    public static Task addTask(String task) throws PythiaException{
        Parser taskParser = new Parser();
        String[] parsedTask;
        if (task.contains("todo ")) {
            task = task.replaceFirst("todo", "");
            if (task.isBlank()) {
                throw new PythiaException();
            }
            return new Todo(task);
        } else if (task.contains("deadline ")) {
            parsedTask = taskParser.parseDeadline(task);
            if (parsedTask.length < 2) {
                throw new PythiaException();
            }
            return new Deadline(parsedTask[0], parsedTask[1]);
        } else if (task.contains("event ")){
            parsedTask = taskParser.parseEvent(task);
            if (parsedTask.length < 3) {
                throw new PythiaException();
            }
            return  new Event(parsedTask[0], parsedTask[1], parsedTask[2]);
        } else {
            throw new PythiaException();
        }
    }

    public static void unmark(String input, ArrayList<Task> list) {
        String[] splitInput = input.split(" ");
        list.get(Integer.parseInt(splitInput[1])-1).doneIsFalse();
        System.out.println("Unmarked "+ Integer.parseInt(splitInput[1]));
    }

    public static void mark(String input, ArrayList<Task> list) {
        String[] splitInput = input.split(" ");
        list.get(Integer.parseInt(splitInput[1])-1).doneIsTrue();
        System.out.println("Marked "+ Integer.parseInt(splitInput[1]));
    }

    public static void delete(String input, ArrayList<Task> list, ArrayList<String> stringList) {
        String[] splitInput = input.split(" ");
        int indexToRemove = Integer.parseInt(splitInput[1])-1;
        System.out.println("Deleted " + list.get(Integer.parseInt(splitInput[1])-1));
        list.remove(list.get(indexToRemove));
        stringList.remove(stringList.get(indexToRemove));
    }
}
