public class InvalidDeadlineException extends ElnException {

    @Override
    public void printExceptionMessage() {
        System.out.println("PLEASE follow the format. Try writing");
        System.out.println("deadline (task to be completed) /by (when)");
        System.out.println("Make sure to include /by when and ask Eln for /help if you want.");
    }

}

