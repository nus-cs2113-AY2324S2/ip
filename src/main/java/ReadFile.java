import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ReadFile {
    public static void readFileToArrayList() throws IOException, RoleyPoleyException {
        try {
            Path myPath = Paths.get("./src/main/java/RoleyPoleyData.txt");
            List<String> taskList = Files.readAllLines(myPath, StandardCharsets.UTF_8);
            taskList.forEach(ex -> {
                try {
                    convertTask(ex);
                } catch (RoleyPoleyException e) {
                    System.out.println("Error!");
                }
            });
        }
        catch (IOException e) {
            System.out.println("Error File not found");
        }
    }


    public static void convertTask(String line) throws RoleyPoleyException {
        String[] identifyTaskType = line.split(" ");
        String description = line.substring("X | Y | ".length());
        boolean isDone = switch (identifyTaskType[2]) {
            case "1" -> true;
            case "0" -> false;
            default -> throw new RoleyPoleyException("FileContentError");
        };

        switch (identifyTaskType[0]) {
        case "T":
                RoleyPoley.taskList.add(new Todo(description, isDone));
            break;
        case "D":
            if (!description.contains("(by:")) {
                throw new RoleyPoleyException("FileContentError");
            } else {
                RoleyPoley.taskList.add(new Deadline(description, isDone));
            }
            break;
        case "E":
            if (!description.contains("(from:") || !description.contains("to:")) {
                throw new RoleyPoleyException("FileContentError");
            } else {
                RoleyPoley.taskList.add(new Event(description, isDone));
            }
            break;
        default:
            throw new RoleyPoleyException("FileContentError");
        }
    }
}