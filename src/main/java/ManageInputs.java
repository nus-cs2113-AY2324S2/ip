import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ManageInputs {
    protected static String from;
    protected static String to;
    public static String description;
    protected static String by;

    private static void writeToFile(String filePath, Task textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd + "\n");
        fw.close();
    }



    private static int fillFileContents(ArrayList<Task> tasks, String filePath, int index, boolean isInTxt) throws IOException, UnexpectedCommandException {//updates index
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        while (s.hasNext()) {

            String sLine = s.nextLine();
            if (sLine.contains("[E]")) {
                dealWithEvent(tasks, index, sLine, true);
            } else if (sLine.contains("[D]")) {
                dealWithDeadline(tasks, index, sLine, true);
            } else if (sLine.contains("[T]")) {
                dealWithTodo(tasks, index, sLine, true);
            }
            index++;
        }
        isInTxt = false;
        return index;
    }



    public static void dealWithEvent(ArrayList<Task> tasks, int index, String line, boolean isInTxt) throws UnexpectedCommandException, IOException {
        int indexTo = line.lastIndexOf("to");
        int indexFrom = line.indexOf("from");
        String from;
        String to;
        String description;

        if ((indexTo == -1) || (indexFrom == -1)) { //invalid format
            System.out.println("Invalid format! Enter event in the format: event (description) from (start) to (end)");
            throw new UnexpectedCommandException();
        }
        try {//timeline not specified/ both not specified
            from = line.substring(indexFrom + 5, indexTo - 1);
            to = line.substring(indexTo + 2);
        } catch (IndexOutOfBoundsException e) {
            try {
                description = line.substring(5, indexFrom - 1);
            } catch (IndexOutOfBoundsException f) {
                System.out.println("event description and timeline not specified");
                throw new UnexpectedCommandException();
            }
            System.out.println("event timeline not specified");
            throw new UnexpectedCommandException();
        }
        try {//description not specified
            description = line.substring(5, indexFrom - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("event description not specified");
            throw new UnexpectedCommandException();
        }
        if(!isInTxt) {
            from = line.substring(indexFrom + 5, indexTo - 1);
            to = line.substring(indexTo + 3);
            description = line.substring(5, indexFrom - 1);
        } else {
            from = line.substring(indexFrom + 6, indexTo - 1);
            to = line.substring(indexTo + 4, line.length() - 1);
            description = line.substring(5, indexFrom - 1);
        } //when uncomment this, cannot add but reads file contents properly
        //when commented, can add but cannot read file contents properly
        //System.out.println(isInTxt);

        tasks.add(index, new Event(description, from, to));
    }

    public static void dealWithDeadline(ArrayList<Task> tasks, int index, String line, boolean isInTxt) throws UnexpectedCommandException, IOException {
        int indexBy = line.indexOf("by");
        int space = line.indexOf(" ");
        String by;
        String description;
        if (indexBy == -1) {//invalid format
            System.out.println("Invalid format! Enter deadline in the format: deadline (description) by (deadline)");
            throw new UnexpectedCommandException();
        }
        try {//deadline / both not specified
            by = line.substring(indexBy + 2);
        } catch (IndexOutOfBoundsException e) {
            try {
                description = line.substring(space, indexBy - 1);
            } catch (IndexOutOfBoundsException f) {
                System.out.println("deadline description and deadline not specified");
                throw new UnexpectedCommandException();
            }
            System.out.println("deadline not specified");
            throw new UnexpectedCommandException();
        }
        try {//deadline not specified
            description = line.substring(space, indexBy - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("deadline description not specified");
            throw new UnexpectedCommandException();
        }
        if (!isInTxt) {
            description = line.substring(space, indexBy - 1);
            by = line.substring(indexBy + 3);
        } else{
            description = line.substring(space, indexBy - 1);
            by = line.substring(indexBy + 4, line.length() - 1);
        }

        tasks.add(index, new Deadline(description, by));
    }

    public static void dealWithTodo(ArrayList<Task> tasks, int index, String line, boolean isInTxt) throws UnexpectedCommandException, IOException {
        int indexSpace = line.indexOf(" ");
        if (indexSpace == -1) {
            System.out.println("todo description not specified");
            throw new UnexpectedCommandException();
        }

        String description = line.substring(indexSpace);

        tasks.add(index, new Todo(description));

    }

    private void handleUnexpectedCommand(boolean isValidCommand) throws UnexpectedCommandException {
        if (!isValidCommand) {
            throw new UnexpectedCommandException();
        }
    }

    private void handleEmptyInput(String line) throws EmptyLineException {
        if (line.isEmpty()) {
            throw new EmptyLineException();
        }
    }

    public ManageInputs(ArrayList<Task> tasks, int index, String line) throws IOException, UnexpectedCommandException {
        boolean isInTxt = false;
        index = fillFileContents(tasks, "TaskList.txt", index, isInTxt);

        while (!line.equals("bye")) {
            Boolean isValidCommand = false;
            Scanner input = new Scanner(System.in);
            line = input.nextLine();

            Task t = new Task(line);
            String[] inputs = line.split(" ");
            
            if (inputs[0].equals("mark")) {//mark as done
                isValidCommand = true;
                int idx = Integer.parseInt(inputs[1]);
                tasks.get(idx - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(tasks.get(idx - 1));
            } else if (inputs[0].equals("unmark")) {//unmark done
                isValidCommand = true;
                int idx = Integer.parseInt(inputs[1]);
                tasks.get(idx - 1).unmarkDone();
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println(tasks.get(idx - 1));
            } else if (line.equals("list")) {//lists tasks
                isValidCommand = true;
                System.out.println("Here are the tasks in your list: ");

                for (int i = 0; i < index; i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else if (line.equals("bye")) {//exit chat
                isValidCommand = true;




                break;
            } else if (inputs[0].equals("event") || inputs[0].equals("todo") || inputs[0].equals("deadline")) {//add items
                try {
                    if (inputs[0].equals("event")) {
                        isValidCommand = true;
                        dealWithEvent(tasks, index, line, isInTxt);
                        writeToFile("TaskList.txt", tasks.get(index));
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(tasks.get(index));
                        index++;
                    } else if (inputs[0].equals("deadline")) {
                        isValidCommand = true;
                        dealWithDeadline(tasks, index, line, isInTxt);
                        writeToFile("TaskList.txt", tasks.get(index));
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(tasks.get(index));
                        index++;
                    } else {
                        isValidCommand = true;
                        dealWithTodo(tasks, index, line, isInTxt);
                        writeToFile("TaskList.txt", tasks.get(index));
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(tasks.get(index));
                        index++;
                    }
                } catch (UnexpectedCommandException e) {
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (inputs[0].equals("delete")) {
                isValidCommand = true;
                int idx = Integer.parseInt(inputs[1]);
                System.out.println("Noted. I've removed this task: ");
                System.out.println(tasks.get(idx - 1));
                tasks.remove(idx - 1);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                index--;
            } else {
                try {
                    handleEmptyInput(line);
                    handleUnexpectedCommand(isValidCommand);
                } catch (UnexpectedCommandException e) {
                    System.out.println("please enter a valid command");
                } catch (EmptyLineException e) {
                    System.out.println("enter a task");
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}