// subclass of Task
public class Event extends Task{
    protected String from;
    protected String to;

    String[] splitLine = description.split("/"); // Split input from / onwards

    public Event(String description, int index) {
        super(description, index);
        //printHeaders();
        System.out.println(toString());
    }
    public String getFrom() throws DukeException {
        String[] splitLine = description.split("/"); // Split input from / onwards
        String getfrom = null;
        for (String s : splitLine) {
            if (s.startsWith("from")) {
                getfrom = s.substring(5);
                return getfrom;
            }
        }
        throw new DukeException("Invalid Syntax! Please try again!");
        //return splitLine[1].substring(5); // Return starting time
    }

    public String getTo() throws DukeException {
        String[] splitLine = description.split("/from");
        String[] durationLine = splitLine[1].split("/to");
        if (splitLine.length != 2 || durationLine.length != 2) {
            throw new DukeException("Invalid Syntax! Please try again!");
        }
        return splitLine[1].substring(3);
    /*    String[] splitLine = description.split("/"); // Split input from / onwards
        String getto = null;
        for (String s : splitLine) {
            if (s.startsWith("to")) {
                getto = s.substring(3);
                return getto;
            }

        }
        throw new DukeException("Invalid Syntax! Please try again!");*/

        //return splitLine[1].substring(5); // Return starting time

        //return splitLine[2].substring(3); // Return ending time
    }


    // Override task's toString() to add [E] and the deadline interval
    @Override
    public String toString() {
        String date = null;
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
    }