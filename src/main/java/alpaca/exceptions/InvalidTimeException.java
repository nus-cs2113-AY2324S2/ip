package alpaca.exceptions;

public class InvalidTimeException extends AlpacaException {

    public String toString() {
        return "The time is missing or incorrect! It should be: \n" +
            "         YYYY-MM-DD HH:MM     such as: 2025-01-01 12:00\n";
    }
}
