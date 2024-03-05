package MassimoBoi;

import java.util.Scanner;

public class Ui {

    private String logo;

    Ui(){
        this.logo = " __  __               _                   _           _ \n" +
                "|  \\/  | __ _ ___ ___(_)_ __ ___   ___   | |__   ___ (_)\n" +
                "| |\\/| |/ _` / __/ __| | '_ ` _ \\ / _ \\  | '_ \\ / _ \\| |\n" +
                "| |  | | (_| \\__ \\__ \\ | | | | | | (_) | | |_) | (_) | |\n" +
                "|_|  |_|\\__,_|___/___/_|_| |_| |_|\\___/  |_.__/ \\___/|_|";
    }

    public void initialiseUi(){
        System.out.println("Hello from\n" + logo);
    }
    public void printGreetingMessage(){

        System.out.println("""
                To add task, type the task type (todo,deadline,event) followed by description with following rules:
                    1. For deadline add /by at end of description followed by due date
                    2. For events add /from followed by start date and /to followed by end date
                To check list of tasks, type list.
                To mark as done, type mark [list index]
                """);
    }

    public void printHorizontalRow() {
        for (int i = 0; i < 50; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public void printGoodbyeMessage() {
        System.out.println("This is Massimo boi signing out!");
    }
}
