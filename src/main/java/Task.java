public class Task {

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static String[] parseLine(String line, String[] validArgs) throws WrongArgumentsException {

        String args[] = line.split(" "); // remove the first word (command)
        String newArgs[] = new String[validArgs.length + 1];
        String aux = "";

        int i;
        int indexOfNewArgs = 0;
        int indexOfValidArgs = 0;
        int lenOfArgGiven = 0;

        for (i = 1; i < args.length; i++) {
            String word = args[i];
            if (word.equals(validArgs[Math.min(indexOfValidArgs, validArgs.length - 1)])) {
                if (lenOfArgGiven == 0) {
                    String name = indexOfValidArgs > 0 ? validArgs[indexOfValidArgs - 1] : "description";
                    throw new WrongArgumentsException("No value for " + name);
                }

                newArgs[indexOfNewArgs++] = aux.trim();
                aux = "";
                indexOfValidArgs++;
                lenOfArgGiven = 0;
            } else if (word.startsWith("/")) {
                //We are forcing to receive the arguments in one order.
                //TODO: consider to allow the user to input the arguments in any order
                throw new WrongArgumentsException("Invalid argument " + word +
                        ". Expected " + validArgs[indexOfValidArgs]);
            } else {
                aux += word + " ";
                lenOfArgGiven++;
            }
        }

        if (lenOfArgGiven == 0) {
            throw new WrongArgumentsException("No value for " + validArgs[indexOfValidArgs - 1]);
        }

        if (indexOfValidArgs != validArgs.length) {
            String message = "Missing arguments. Expected ";
            for (String arg : validArgs) {
                message += arg + " ";
            }
            throw new WrongArgumentsException(message);
        }

        return newArgs;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone){
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}



