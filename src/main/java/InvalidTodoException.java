public class InvalidTodoException extends ElnException{

    @Override
    public void printExceptionMessage() {
        System.out.println("Heyy!! You can't possibly have nothing Todo right? Try writing");
        System.out.println("todo (task to do)");
        System.out.println("If you need /help, just ask Eln.");
    }

}
