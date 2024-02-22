package duke;

import static duke.print.printMessage;

public class DukeException extends Exception {
    public static class DatabaseLoadException extends Exception {
        public void printErrorMessage() {
            printMessage("Error Loading Database!!\nHEY HEY soko no hip HOP guy!!");
        }
    }

    public static class InvalidCommandException extends Exception {
        public void printErrorMessage(){
            printMessage(
                    "Invalid Command!!\nYAMAGATA kita baby FACE!!");
        }
    }

    public static class EndListException extends Exception {

    }

    public static class InvalidIntegerException extends Exception {
        public void printErrorMessage() {
            printMessage(
                    "Please input an integer!!\nInvalid instruction ZENBU FAKE!!");
        }
    }

    public static class IntegerOutOfBoundsException extends Exception {
        public void printErrorMessage() {
            printMessage(
                    "Task does not exist!!\nInvalid instruction ZENBU FAKE!!");
        }
    }
}
