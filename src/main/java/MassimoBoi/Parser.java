package MassimoBoi;

import MassimoBoiException.*;

/**
 * Represents a Parser made to handle user commands.
 */
public class Parser {
    private String userInput;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new parser object.
     *
     * @param userInput the String entered by the user.
     * @param tasks an arrayList of tasks the user has entered.
     * @param ui the Ui object containing the printGreetingMessage and printHorizontalRow methods.
     */
    Parser(String userInput, TaskList tasks, Ui ui){
        this.userInput = userInput;
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Returns a task list that is modified according to user command.
     * Prints relevant information from tasks to console based on user command.
     *
     * @return the modified task list.
     * @throws MassimoBoiException which handles incorrect and incomplete user input.
     */
    public TaskList handleInput() throws MassimoBoiException{
        try {
            if (userInput.startsWith("unmark")) {
                String[] handleInput = userInput.split(" ");
                int taskToUnmark = Integer.parseInt(handleInput[1]) - 1;
                tasks.get(taskToUnmark).unmark();
                System.out.printf("%s unmarked. Type 'list' to see updated list\n Type mark [list index] to mark this task\n"
                        , tasks.get(taskToUnmark).getDescription());
            } else if (userInput.startsWith("mark")) {
                String[] handleInput = userInput.split(" ");
                int taskToMark = Integer.parseInt(handleInput[1]) - 1;
                tasks.get(taskToMark).markAsDone();
                System.out.printf("%s marked as done. Type 'list' to see updated list\n Type unmark [list index] to unmark this task\n"
                        , tasks.get(taskToMark).getDescription());
            } else if (userInput.startsWith("delete")) {
                String[] handleInput = userInput.split(" ");
                int taskIndex = Integer.parseInt(handleInput[1]);
                tasks.deleteTask(taskIndex - 1);

            } else if (userInput.startsWith("todo")) {
                Task newTask;
                String[] getDescription = userInput.split(" ", 2);
                if (getDescription.length == 1) {
                    throw new EmptyToDo();
                }
                newTask = new ToDo(getDescription[1]);
                tasks.addTask(newTask);
            } else if (userInput.startsWith("deadline")) {
                Task newTask;
                String[] getDescription = userInput.split(" ", 2);
                if (getDescription.length == 1) {
                    throw new EmptyDeadline();
                } else if (!userInput.contains("/by")) {
                    throw new NoDueDate();
                }
                String[] getDeadline = getDescription[1].split("/by");
                newTask = new Deadline(getDeadline[0], getDeadline[1]);
                tasks.addTask(newTask);
            } else if (userInput.startsWith("event")) {
                Task newTask;
                String[] getDescription = userInput.split(" ", 2);
                if (getDescription.length == 1) {
                    throw new EmptyEvent();
                } else if (!userInput.contains("/from")) {
                    throw new NoEventStart();
                } else if (!userInput.contains("/to")) {
                    throw new NoEventEnd();
                }
                String[] getEvent = getDescription[1].split("/from");
                String[] getFromAndBy = getEvent[1].split("/to");
                newTask = new Event(getEvent[0], getFromAndBy[0], getFromAndBy[1]);
                tasks.addTask(newTask);
            } else if(!userInput.contains("list")) {
                throw new UnknownCommandType();
            }
        }catch(UnknownCommandType e){
            e.errorMessage();
            ui.printUserGuideMessage();
        } catch(EmptyToDo e){
            e.errorMessage();
        } catch(NoDueDate e){
            e.errorMessage();
        } catch (EmptyDeadline e){
            e.errorMessage();
        } catch(MassimoBoiException e){
            e.errorMessage();
        } catch(IndexOutOfBoundsException e){
            System.out.println("The task number you have tried to mark, unmark, or delete does not exist");
        } finally {
            ui.makeHorizontalRow();
        }
        return tasks;
    }




}
