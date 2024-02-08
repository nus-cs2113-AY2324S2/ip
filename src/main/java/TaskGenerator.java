import java.util.Optional;
import java.util.function.Function;

public class TaskGenerator {
    public static Task generate(String[] parsedInput) {
        if (parsedInput.length == 1) {
            return new ToDo(parsedInput[0]);
        } else if (parsedInput.length == 2) {
            return new Deadline(parsedInput[0], parsedInput[1]);
        }
        return new Event(parsedInput[0], parsedInput[1], parsedInput[2]);
    }
}
