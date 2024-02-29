package omoh.customexceptions;

public class EmptyFindException extends Exception {
    public EmptyFindException() {
        System.out.println("Please enter non empty keyword after find!" +
                " for example, find book");

    }
}
