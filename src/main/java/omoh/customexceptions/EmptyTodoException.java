package omoh.customexceptions;

public class EmptyTodoException extends Exception {
    public EmptyTodoException() {
        System.out.println("Todo cannot be empty! Please key in input in this format [Todo] [task]"
                + "example: todo hang clothes");
    }
}
