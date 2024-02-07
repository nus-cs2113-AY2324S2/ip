import java.util.Optional;
import java.util.function.Function;

public class TaskGenerator implements Function<String[], Optional<Task>> {
    @Override
    public Optional<Task> apply(String[] parsedInput) {
        if (parsedInput.length == 0) {
            return Optional.empty();
        } else if (parsedInput.length == 1) {
            return Optional.of(new ToDo(parsedInput[0]));
        } else if (parsedInput.length == 2) {
            return Optional.of(new Deadline(parsedInput[0], parsedInput[1]));
        }
        return Optional.of(new Event(parsedInput[0], parsedInput[1], parsedInput[2]));
    }
}
