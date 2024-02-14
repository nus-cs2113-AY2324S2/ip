package exceptions;

import utils.constants;

public class EmptyIndexException extends Exception {
    public EmptyIndexException() {
        System.out.println(constants.BREAKLINE);
        System.out.println("You have not entered the index!!!");
        System.out.println(constants.BREAKLINE);
    }
}
