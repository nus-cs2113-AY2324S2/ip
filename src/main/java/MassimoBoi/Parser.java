package MassimoBoi;

import MassimoBoiException.MassimoBoiException;
import MassimoBoiException.NoDueDate;
import MassimoBoiException.EmptyDeadline;
import MassimoBoiException.EmptyEvent;
import MassimoBoiException.EmptyToDo;
import MassimoBoiException.NoEventStart;
import MassimoBoiException.NoEventEnd;
import MassimoBoiException.UnknownCommandType;

public class Parser {
    private String userInput;
    private TaskList tasks;
    private Ui ui;

    Parser(String userInput, TaskList tasks, Ui ui){
        this.userInput = userInput;
        this.tasks = tasks;
        this.ui = ui;
    }

    public TaskList handleInput() throws MassimoBoiException{
        try {
            if (userInput.startsWith("find")){
                String[] handleInput = userInput.split(" ",2);
                tasks.findAndPrint(handleInput[1]);
            } else if (userInput.startsWith("list")){
                tasks.printList();
            } else if (userInput.startsWith("unmark")) {
                String[] handleInput = userInput.split(" ");
                int indexToUnmark = Integer.parseInt(handleInput[1]) - 1;
                tasks.get(indexToUnmark).unmark();
                System.out.printf("%s unmarked. Type 'list' to see updated list\n Type mark [list index] to mark this task\n"
                        , tasks.get(indexToUnmark).getDescription());
            } else if (userInput.startsWith("mark")) {
                String[] handleInput = userInput.split(" ");
                int indexToMark = Integer.parseInt(handleInput[1]) - 1;
                tasks.get(indexToMark).markAsDone();
                System.out.printf("%s marked as done. Type 'list' to see updated list\n Type unmark [list index] to unmark this task\n"
                        , tasks.get(indexToMark).getDescription());
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
                String todoDescription = getDescription[1];
                newTask = new ToDo(todoDescription);
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
                String deadlineDescription = getDeadline[0];
                String deadlineDueDate = getDeadline[1];
                newTask = new Deadline(deadlineDescription, deadlineDueDate);
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
                String eventDescription = getEvent[0];
                String from = getFromAndBy[0];
                String to = getFromAndBy[1];
                newTask = new Event(eventDescription, from, to);
                tasks.addTask(newTask);
            } else {
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
        } catch (EmptyEvent e){
            e.errorMessage();
        } catch (NoEventEnd e){
            e.errorMessage();
        } catch (NoEventStart e){
            e.errorMessage();
        } catch(IndexOutOfBoundsException e){
            System.out.println("The task number you have tried to mark, unmark, or delete does not exist");
        } finally {
            ui.printHorizontalRow();
        }
        return tasks;
    }
}
