public class EventTask extends Task{
    protected static String eventStart;
    protected static String eventEnd;

    public EventTask() throws SalmonMissingArgument{
        this(false, "DEFAULT /from DEFAULT /to DEFAULT");
    }

    public EventTask(boolean status, String description) throws SalmonMissingArgument {
        this.isDone = status;
        this.description = setEventTask(description);
        this.type = "E";
    }

    /**
     * setEventTask parse argument of inputted query,
     * assign start and end of event to eventStart and eventEnd
     * @param argument
     * @return String description of eventTask
     */
    public String setEventTask(String argument) throws SalmonMissingArgument {
        if (argument == null) {
            throw new SalmonMissingArgument();
        }
        // find index of '/'
        int firstSlash = argument.indexOf('/');
        int secondSlash = argument.indexOf('/', firstSlash + 1);
        eventStart = argument.substring(firstSlash + 6, secondSlash - 1);
        eventEnd = argument.substring(secondSlash + 4);
        return argument.substring(0, firstSlash - 1);
    }

    public String getEventStart() {
        return eventStart;
    }
    public String getEventEnd() {
        return eventEnd;
    }

    public void setEventStart(String s) {
        this.eventStart = s;
    }

    public void setEventEnd(String s) {
        this.eventEnd = s;
    }


    @Override
    public void printTask() {
        if (isDone) {
            System.out.println("[" + this.type + "][X] " + this.getDescription() + " (from: " + this.getEventStart() + " to: " + this.getEventEnd() + ")");
        } else {
            System.out.println("[" + this.type + "][ ] " + this.getDescription() + " (from: " + this.getEventStart() + " to: " + this.getEventEnd() + ")");
        }
    }

    @Override
    public String toString() {
        String answer;
        answer = this.type + " | " + this.isDone + " | " + this.description + " | " + this.eventStart + " | " + this.eventEnd;
        return answer;
    }

    @Override
    public void parse(String s) throws SalmonMissingArgument {
        // format T | true | description
        int firstSlash = s.indexOf('|');
        int secondSlash = s.indexOf('|', firstSlash + 1);
        int thirdSlash = s.indexOf('|', secondSlash + 1);
        int fourthSlash = s.indexOf('|', thirdSlash + 1);
        int spaceAfterIsDone = s.indexOf(' ', firstSlash + 2);

        String isDoneStatus = s.substring(firstSlash + 2, spaceAfterIsDone);
        String description = s.substring(secondSlash + 2, thirdSlash - 1);
        String eventStart = s.substring(thirdSlash + 2, fourthSlash - 1);
        String eventEnd = s.substring(fourthSlash + 2);

        this.setBoolean(Boolean.parseBoolean(isDoneStatus));
        this.setDescription(description);
        this.setEventStart(eventStart);
        this.setEventEnd(eventEnd);
    }
}
