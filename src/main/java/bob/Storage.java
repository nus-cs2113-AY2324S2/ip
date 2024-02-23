package bob;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Storage {

    public final String FILENAME;
    public Storage(String filename) {
        this.FILENAME = filename;
    }

    public List<Task> generateListOnStartup() {
        List<String> stringList;
        try {
            stringList = Files.readAllLines(Paths.get(FILENAME));
        } catch (IOException e) {
            /* if there is any problem with the file or the file does
               not exist, return an empty list. this means if the file is
               corrupted it will not be used, and overwritten later */
            return new ArrayList<>();
        }

        List<Task> list = new ArrayList<>();
        for (String s : stringList) {
            switch (s.substring(0, 3)) {
            case Ui.TODO_ICON:
                list.add(new Task(s));
                break;
            case Ui.DEADLINE_ICON:
                list.add(new Deadline(s));
                break;
            case Ui.EVENT_ICON:
                list.add(new Event(s));
                break;
            }
        }

        return list;
    }

    public void saveList(List<Task> list) throws BobException {

        // convert list of tasks objects to list of strings
        List<String> stringList = list.stream()
                .map(object -> Objects.toString(object, null))
                .collect(Collectors.toList());

        Path file = Paths.get(FILENAME);

        try {
            Files.write(file, stringList, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new BobException("Some problem with your computer, could not save list.");
        }


    }
}