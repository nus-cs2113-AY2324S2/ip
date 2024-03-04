package MassimoBoi;

import MassimoBoiException.*;

import java.io.IOException;
import java.util.List;
import MassimoBoi.Ui;
import MassimoBoi.TaskList;

import static MassimoBoi.MassimoBoi.*;

/*
 * Takes in user input and processes them according to different preset commands
 * List of user inputs: task type, mark and unmark task, list, bye (to end chat)
 */
public class Parser {
    private String userInput;
    private TaskList tasks;
    Parser(String userInput, TaskList tasks){
        this.userInput = userInput;
        this.tasks = tasks;
    }


    public TaskList handleInput() throws MassimoBoiException{
        Ui ui = new Ui();
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
            ui.printGreetingMessage();
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
