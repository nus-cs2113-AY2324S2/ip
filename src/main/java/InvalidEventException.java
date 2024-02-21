public class InvalidEventException extends ElnException {

    @Override
    public void printExceptionMessage() {
        System.out.println("Need help describing the event? Try writing");
        System.out.println("event (name of event) /from (start date) /to (end date)");
        System.out.println("Need any more /help, just call Eln.");
    }

}

