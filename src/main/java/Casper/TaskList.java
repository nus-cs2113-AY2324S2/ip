package Casper;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;
    private int noOfTasks;

    public TaskList(){
        this.taskList = new ArrayList<Task>();
        this.noOfTasks = 0;
    }

    public TaskList(ArrayList<Task> taskList){
        this.taskList = taskList;
        this.noOfTasks = taskList.size();
    }

    public ArrayList<Task> getTaskList(){
        return this.taskList;
    }

    public void markTask(int index){
        taskList.get(index-1).markTask();
    }

    public void unMarkTask(int index){
        taskList.get(index-1).unMarkTask();
    }

    public int getTaskNumber(){
        return noOfTasks;
    }

    public Task getTask(int index){
        return taskList.get(index);
    }

    public void addTask(Task newTask){
        taskList.add(newTask);
        noOfTasks++;
    }

    public void removeTask(int index){
        taskList.remove(index);
        noOfTasks--;
    }

    private static boolean checkNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void echoTaskList(){
        if (noOfTasks==0) {
            System.out.println("     You have no tasks this time around.");
        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i=1; i<=noOfTasks; i++) {
                System.out.println("     "+i+". "+taskList.get(i-1));
            }
        }
    }

    public void echoFoundTasks(){
        if (noOfTasks==0) {
            System.out.println("     That does not sound like a task you have.");
        } else {
            System.out.println("     Here are the matching tasks in your list:");
            for (int i=1; i<=noOfTasks; i++) {
                System.out.println("     "+i+". "+taskList.get(i-1));
            }
        }
    }

    public int validateTargetedInput(String userInput) throws CasperInvalidInputException{
        String[] userInputSplit = userInput.split(" ");
        if (userInputSplit.length==2 && checkNumeric(userInputSplit[1])) {
            int targetTaskNumber = Integer.parseInt(userInputSplit[1]);
            if (targetTaskNumber<=noOfTasks&&targetTaskNumber>0) {
                return targetTaskNumber;
            } else {
                throw new CasperInvalidInputException("That task does not exist!");
            }
        } else {
            throw new CasperInvalidInputException("Invalid input!");
        }
    }

    public void handleMarkTask(String userInput) throws CasperInvalidInputException{
        boolean toMark = userInput.split(" ")[0].equals("mark");
        int targetTaskNumber = validateTargetedInput(userInput);
        if(toMark){
            markTask(targetTaskNumber);
        }else{
            unMarkTask(targetTaskNumber);
        }
    }

}
