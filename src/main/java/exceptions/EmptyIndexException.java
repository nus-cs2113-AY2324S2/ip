package exceptions;

import utils.constants;

public class EmptyIndexException extends Exception {
//    public void printError() {
//        System.out.println(constants.BREAKLINE);
//        System.out.println("You have not entered the index!!!");
//        System.out.println(constants.BREAKLINE);
//    }
    public EmptyIndexException() {
        super("You have not entered the index!!!");
    }
}
