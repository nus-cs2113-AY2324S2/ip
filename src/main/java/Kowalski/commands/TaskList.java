package Kowalski.commands;

import Kowalski.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    public static List<Task> currentTask = new ArrayList<>();
    public static final String DOT = ".";

    /**
     * Used to process the different variations of the users inputs
     * @param userInput : String which the user inputs
     * @return String which is in lowercase and clear of any unnecessary whitespace
     */
    public static String processInput(String userInput){
        return (userInput.trim()).toLowerCase();
    }

    /**
     * Used in the "list" command to print all the Current Tasks in the proper format
     */
    public static void printCurrentTaskItems(){
        for (int i = 1; i <= currentTask.size(); i++){
            System.out.println(i + DOT + currentTask.get(i-1));
        }
    }

    /**
     * Used in the "find" command to print all the matching Tasks in the proper format
     */
    public static void printMatchingTasks(List<Task> matchList){
        for (int i = 1; i <= matchList.size(); i++){
            System.out.println(i + DOT + matchList.get(i-1));
        }
    }

    public static void addTask(Task task) {
        currentTask.add(task);;
    }

    public static void removeTask(int index){
        currentTask.remove(index);
    }

    public static void findMatch(String input){
        List<Task>matchList = currentTask.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(input))
                .collect(Collectors.toList());

        if (matchList.isEmpty()){
            System.out.println("My analysis shows no matches Skipper!");
        } else {
            System.out.println("Skipper here are the matching tasks:");
            printMatchingTasks(matchList);
        }
    }
}
