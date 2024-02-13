// subclass of Task
public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, int index) {
        super(description, index);
        //printHeaders();
        toPrint();
        //System.out.println(this);
    }
 /*   public String getFrom() throws DukeException {
        String[] splitLine = description.split("/"); // Split input from / onwards
        String getfrom = null;
        for (String s : splitLine) {
            if (s.startsWith("from")) {
                getfrom = s.substring(5);
                return getfrom;
            }
        }
        throw new DukeException("Invalid Syntax! Please try again!");
    }

    public String getTo() throws DukeException {
        String[] splitLine = description.split("/from");
        String[] durationLine = splitLine[1].split("/to");
        if (splitLine.length != 2 || durationLine.length != 2) {
            throw new DukeException("Invalid Syntax! Please try again!");
        }
        return splitLine[1].substring(3);
    }*/


    @Override
    public String toString() {
        String date;
        try {
            String[] splitLine = description.split("/from");
            String[] durationLine = splitLine[1].split("/to");
            if (splitLine.length != 2 || durationLine.length != 2) {
                throw new DukeException("Invalid Syntax! Please try again!");
            }
            date = " (from:" + durationLine[0]  + "to:" + durationLine[1] + ")";

        return "[E]" + super.toString() + date;
    } catch (DukeException e) {
            throw new RuntimeException(e);
        }
    }

    public void toPrint() {
        if (toString() != null) {
            printHeaders();
            System.out.println(this);
        }
    }
}

