import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.List;

public class Duke {

    public static void  printTasks(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.printf("%s. %s%n",(i + 1), tasks.get(i));
        }

    }
    public static void main(String[] args) {
        String chatbot = "Jing Xiang";
        String todo = "todo";
        String deadline = "deadline";
        String event = "event";
        String filename = "data.txt";
        SimpleDateFormat formatter = new SimpleDateFormat(
                "dd/MM/yyyy hh:mm:ss");
        List<Task> tasks;
        TaskListFile file;
        Scanner input = new Scanner(System.in);
        System.out.println("Hello clown I am " + chatbot);
        try {
            file = new TaskListFile(filename);
            tasks = file.decodeTasks();
            while (true) {
                System.out.println("------------stop clowning and type sth------------");
                System.out.println(
                        "Any dates must be in dd/mm/yyyy hh:mm:ss. " +
                                "Be precise!");
                System.out.println(
                        """
                                sample task inputs
                                todo <description>
                                deadline <description> /by <dd/mm/yyyy hh:mm:ss>
                                event <description> /from <dd/mm/yyyy hh:mm:ss> /to <dd/mm/yyyy hh:mm:ss>
                                """
                );
                System.out.println("--------------------------------------------------");
                String line = input.nextLine();
                if (line.equals("bye")) {
                    break;
                }
                if (line.isEmpty()) {
                    continue;
                }
                if (line.equals("list")) {
                    printTasks(tasks);
                    continue;
                }
                String[] words = line.split(" ", 2);
                if (line.startsWith(todo)) {
                    try {
                        tasks.add(new ToDo(words[1]));
                        file = file.updateTaskListFile(tasks);
                    } catch(IndexOutOfBoundsException e) {
                        System.out.println("clown! add something after todo!");
                    }
                    continue;
                }

                if (line.startsWith(deadline)) {
                    try {
                        String[] deadlineDescription =
                                words[1].split("/by", 2);
                        Date timestamp = formatter.parse(deadlineDescription[1]);
                        tasks.add(new Deadline(deadlineDescription[0],
                                timestamp));
                        file = file.updateTaskListFile(tasks);
                    } catch(IndexOutOfBoundsException e) {
                        System.out.println(
                                "clown! add something after deadline!");
                    } catch (ParseException e) {
                        System.out.println(
                                "clown! Be precise! write time proper!"
                        );
                    }
                    continue;
                }

                if (line.startsWith(event)) {
                    try {
                        String[] eventDescription = words[1].split("/from", 2);
                        String[] timeWords = eventDescription[1].split(
                                "/to", 2);
                        String description = eventDescription[0];
                        Date from = formatter.parse(timeWords[0]);
                        Date to = formatter.parse(timeWords[1]);
                        tasks.add(new Event(description, from, to));
                        file = file.updateTaskListFile(tasks);
                    } catch(IndexOutOfBoundsException e) {
                        System.out.println(
                                "clown! add something after event!");
                    } catch (ParseException e) {
                        System.out.println(
                                "clown! Be precise! write time proper!"
                        );
                    }
                    continue;
                }
                if (line.startsWith("mark")) {
                    try {
                        int listIndex = Integer.parseInt(words[1]);
                        Task markedTask = tasks.get(listIndex - 1).markTask();
                        tasks.set(listIndex - 1, markedTask);
                        file = file.updateTaskListFile(tasks);
                        printTasks(tasks);
                    }
                    catch (NumberFormatException e) {
                        System.out.println("You are not marking an index!!!");
                    }
                    continue;
                }

                if (line.startsWith("unmark")) {
                    try {
                        int listIndex = Integer.parseInt(words[1]);
                        Task unmarkedTask = tasks.get(
                                listIndex - 1).unmarkTask();
                        tasks.set(listIndex - 1, unmarkedTask);
                        file = file.updateTaskListFile(tasks);
                        printTasks(tasks);
                    }
                    catch (NumberFormatException e) {
                        System.out.println(
                                "You are not unmarking an index!!!");
                    }
                    catch(IndexOutOfBoundsException e) {
                        System.out.println(e + " clownnnnn");
                    }
                    continue;
                }

                if (line.startsWith("delete")) {
                    try {
                        int listIndex = Integer.parseInt(words[1]);
                        tasks.remove(listIndex - 1);
                        file = file.updateTaskListFile(tasks);
                        printTasks(tasks);
                    } catch (NumberFormatException e) {
                        System.out.println("You are not deleting an index!!!");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(e + " clownnnnn");
                    }
                    continue;
                }
                try {
                    throw new JxExceptions("gibberish");
                } catch(JxExceptions e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("Hope to see you soon");
        } catch (IOException ex) {
            System.out.println("Error when handling files " + ex.getMessage());
            throw new RuntimeException(ex);
        }
    }




}
