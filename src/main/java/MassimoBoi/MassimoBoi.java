package MassimoBoi;


import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import MassimoBoiException.*;

public class MassimoBoi {
    public static void main(String[] args) throws IOException {

        String logo = " __  __               _                   _           _ \n" +
                "|  \\/  | __ _ ___ ___(_)_ __ ___   ___   | |__   ___ (_)\n" +
                "| |\\/| |/ _` / __/ __| | '_ ` _ \\ / _ \\  | '_ \\ / _ \\| |\n" +
                "| |  | | (_| \\__ \\__ \\ | | | | | | (_) | | |_) | (_) | |\n" +
                "|_|  |_|\\__,_|___/___/_|_| |_| |_|\\___/  |_.__/ \\___/|_|";

        System.out.println("Hello from\n" + logo);
        Scanner myObj = new Scanner(System.in);
        String userInput = "";
        List<Task> taskList = new ArrayList<>();
        try{
            FileClass.loadList(taskList);
        } catch(FileNotFoundException e){

        };


        greet();
        makeHorizontalRow();

        /*
         * The while loop takes in user input and processes them according to different preset commands
         * List of user inputs: task type, mark and unmark task, list, bye (to end chat)
         */
        while (true) {
            userInput = myObj.nextLine();
            if (userInput.equals("bye")) {
                goodbye(taskList);
                break;
            }
            try{
                handleInput(userInput, taskList);
            } catch(UnknownCommandType e){
                e.errorMessage();
                greet();
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
            } catch(IOException e){
                System.out.println("The task number you have tried to mark/unmark does not exist");


                System.out.println("The task number you have tried to mark/unmark does not exist");

            }
            finally {
                makeHorizontalRow();
            }



        }

    }

    public static void handleInput(String userInput, List<Task> taskList) throws MassimoBoiException, IOException {

        if (userInput.equals("list")){
            printList(taskList);
            return;
        }

        if (userInput.startsWith("unmark")){
            String[] handleInput = userInput.split(" ");
            int taskToUnmark = Integer.parseInt(handleInput[1]) - 1;
            taskList.get(taskToUnmark).unmark();
            System.out.printf("%s unmarked. Type 'list' to see updated list\n Type mark [list index] to mark this task\n"
                    , taskList.get(taskToUnmark).getDescription());
            return;
        } else if (userInput.startsWith("mark")){
            String[] handleInput = userInput.split(" ");
            int taskToMark = Integer.parseInt(handleInput[1]) - 1;
            taskList.get(taskToMark).markAsDone();
            System.out.printf("%s marked as done. Type 'list' to see updated list\n Type unmark [list index] to unmark this task\n"
                    , taskList.get(taskToMark).getDescription());

            return;
        } else if (userInput.startsWith("delete")){
            String[] handleInput = userInput.split(" ");
            int taskIndex = Integer.parseInt(handleInput[1]);
            printDeleteTaskMessage(taskIndex-1, taskList);
            deleteTask(taskIndex-1,taskList);
            return;
        } else if (userInput.startsWith("delete")){
        String[] handleInput = userInput.split(" ");
        int taskIndex = Integer.parseInt(handleInput[1]);
        printDeleteTaskMessage(taskIndex-1, taskList);
        deleteTask(taskIndex-1,taskList);
        return;
    }

        if (userInput.startsWith("todo")){
            Task newTask;
            String[] getDescription = userInput.split(" ", 2);
            if(getDescription.length == 1){
                throw new EmptyToDo();
            }
            newTask = new ToDo(getDescription[1]);
            taskList.add(newTask);
            printNewTaskMessage(newTask, taskList);
        } else if (userInput.startsWith("deadline")){
            Task newTask;
            String[] getDescription = userInput.split(" ", 2);
            if(getDescription.length == 1){
                throw new EmptyDeadline();
            } else if(!userInput.contains("/by")){
                throw new NoDueDate();
            }
            String[] getDeadline = getDescription[1].split("/by");
            newTask = new Deadline(getDeadline[0], getDeadline[1]);
            taskList.add(newTask);
            printNewTaskMessage(newTask, taskList);
        } else if (userInput.startsWith("event")){
            Task newTask;
            String[] getDescription = userInput.split(" ", 2);
            if(getDescription.length == 1){
                throw new EmptyEvent();
            } else if(!userInput.contains("/from")){
                throw new NoEventStart();
            } else if(!userInput.contains("/to")){
                throw new NoEventEnd();
            }
            String[] getEvent = getDescription[1].split("/from");
            String[] getFromAndBy = getEvent[1].split("/to");
            newTask = new Event(getEvent[0], getFromAndBy[0], getFromAndBy[1]);
            taskList.add(newTask);
            printNewTaskMessage(newTask, taskList);
        } else {
            throw new UnknownCommandType();
        }
    }

    public static void greet(){

        System.out.println("""

                Hello I am Massimo boi! What can I do for you?
                To add task, type the task type (todo,deadline,event) followed by description with following rules:
                    1. For deadline add /by at end of description followed by due date
                    2. For events add /from followed by start date and /to followed by end date
                To check list of tasks, type list.
                To mark as done, type mark [list index]
                """);
    }

    public static void goodbye(List<Task> taskList) throws IOException {
        FileClass.addToFile(taskList);
        System.out.println("This is Massimo boi signing out!");
        makeHorizontalRow();
    }

    public static void makeHorizontalRow() {
        for (int i = 0; i < 50; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printTask(Task task) {
        System.out.printf("""
                %s%s %s
                """, task.taskType(), task.getStatus(), task.getDescription());
    }

    public static void printNewTaskMessage(Task newTask, List<Task> taskList){
        System.out.println("Got it! Ya boi has added: ");
        printTask(newTask);
        System.out.printf("You now have %d %s in the list\n", taskList.size(), taskList.size() == 1 ? "task" : "tasks");
    }

<<<<<<< HEAD

=======
>>>>>>> branch-Level-6
    public static void deleteTask(int task, List<Task> taskList){
        taskList.remove(task);
    }

    public static void printDeleteTaskMessage(int taskIndex, List<Task> taskList) {
        System.out.println("Got it! I have deleted: ");
        printTask(taskList.get(taskIndex));
        System.out.printf("You now have %d %s in the list\n", taskList.size()-1, taskList.size() == 1 ? "task" : "tasks");
    }

    public static void printList(List<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print(i + 1 + ". ");
            printTask(taskList.get(i));
        }
    }

}
