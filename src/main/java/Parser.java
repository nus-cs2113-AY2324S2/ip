import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    private final String input;
    private final TaskList taskList;
    private final Storage storage;
    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "dd/MM/yyyy hh:mm:ss");
    private static final String todo = "todo";
    private static final String deadline = "deadline";
    private static final String event = "event";

    /**
     * Constructor for Parser class
     *
     * @param input input from user
     * @param taskList list of tasks added by the user
     * @param storage file handling class to save user modifications
     */
    Parser(String input, TaskList taskList, Storage storage) {
        this.input = input;
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Creates new tasklist and storage based on user input
     * Returns new Parser with the newly created tasklist and storage
     *
     * @return Parser with newly created tasklist and storage
     * @throws JxExceptions If user input undefined input
     */
    Parser parseParams() throws JxExceptions {
        String inputString = this.input;
        TaskList newList = this.taskList;
        Storage newStorage = this.storage;
        String rightParam;
        String[] words = inputString.split(" ", 2);
        try {
            rightParam = words[1];
        } catch (IndexOutOfBoundsException e) {
            throw new JxExceptions("Gibberish");
        }
        if (inputString.startsWith(todo)) {
            try {
                if (rightParam.trim().isEmpty()) {
                    throw new JxExceptions("no description");
                }
                newList = newList.add(new ToDo(rightParam));
                newStorage = newStorage.updateStorage(newList);
            } catch (IOException e) {
                System.out.println("unable to write to file");
                throw new RuntimeException(e);
            }
            return new Parser(inputString, newList, newStorage);
        }
        if (inputString.startsWith(deadline)) {
            try {
                String[] deadlineDescription =
                        rightParam.split("/by", 2);
                Date timestamp = formatter.parse(deadlineDescription[1]);
                newList = newList.add(new Deadline(deadlineDescription[0],
                        timestamp));
                newStorage = newStorage.updateStorage(newList);
            } catch(IndexOutOfBoundsException e) {
                System.out.println(
                        "clown! add something after deadline!");
            } catch (ParseException e) {
                System.out.println(
                        "clown! Be precise! write time proper!"
                );
            } catch (IOException e) {
                System.out.println("unable to write to file");
                throw new RuntimeException(e);
            }
            return new Parser(inputString, newList, newStorage);
        }
        if (inputString.startsWith(event)) {
            try {
                String[] eventDescription = rightParam.split("/from", 2);
                String[] timeWords = eventDescription[1].split(
                        "/to", 2);
                String description = eventDescription[0];
                Date from = formatter.parse(timeWords[0]);
                Date to = formatter.parse(timeWords[1]);
                newList = newList.add(new Event(description, from, to));
                newStorage = newStorage.updateStorage(newList);
            } catch(IndexOutOfBoundsException e) {
                System.out.println(
                        "clown! add something after event!");
            } catch (ParseException e) {
                System.out.println(
                        "clown! Be precise! write time proper!"
                );
            } catch (IOException e) {
                System.out.println("unable to write to file");
                throw new RuntimeException(e);
            }
            return new Parser(inputString, newList, newStorage);
        }
        if (inputString.startsWith("mark")) {
            try {
                int listIndex = Integer.parseInt(rightParam);
                Task markedTask = newList.get(listIndex - 1).markTask();
                newList = newList.set(listIndex - 1, markedTask);
                newStorage = newStorage.updateStorage(newList);
                newList.printTasks();
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println(
                        "clown! mark the indexes properly!");
            }
            catch (NumberFormatException e) {
                System.out.println("You are not marking an index!!!");
            } catch (IOException e) {
                System.out.println("unable to write to file");
                throw new RuntimeException(e);
            }
            return new Parser(inputString, newList, newStorage);
        }
        if (inputString.startsWith("unmark")) {
            try {
                int listIndex = Integer.parseInt(rightParam);
                Task unmarkedTask = newList.get(listIndex - 1).unmarkTask();
                newList = newList.set(listIndex - 1, unmarkedTask);
                newStorage = newStorage.updateStorage(newList);
                newList.printTasks();
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println(
                        "clown! mark the indexes properly!");
            }
            catch (NumberFormatException e) {
                System.out.println("You are not unmarking an index!!!");
            } catch (IOException e) {
                System.out.println("unable to write to file");
                throw new RuntimeException(e);
            }
            return new Parser(inputString, newList, newStorage);
        }
        if (inputString.startsWith("delete")) {
            try {
                int listIndex = Integer.parseInt(rightParam);
                newList = newList.remove(listIndex - 1);
                newStorage = newStorage.updateStorage(newList);
                newList.printTasks();
            } catch (NumberFormatException e) {
                System.out.println("You are not deleting an index!!!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e + " clownnnnn");
            } catch (IOException e) {
                System.out.println("unable to write to file");
                throw new RuntimeException(e);
            }
            return new Parser(inputString, newList, newStorage);
        }
        if (inputString.startsWith("find")) {
            try {
                newList.findTasks(rightParam);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e + " clownnnnn");
            }
            return new Parser(inputString, newList, newStorage);
        }
        try {
            throw new JxExceptions("gibberish");
        }
        catch(JxExceptions e) {
            System.out.println(e.getMessage());
        }
        return new Parser(inputString, newList, newStorage);
    }

    /**
     * Getter for input
     * @return input attribute
     */
    String getInput() {
        return this.input;
    }

    /**
     * Getter for taskList
     * @return taskList attribute
     */
    TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Getter for storage
     * @return storage attribute
     */
    Storage getStorage() {
        return this.storage;
    }


}
