package MassimoBoi;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Handles the storage and loading of task list.
 */
public class Storage {

    private String filename;

    /**
     * Creates a new Storage object.
     *
     * @param filename the name of the file where the task list is stored.
     */
    public Storage(String filename){
        this.filename = filename;
    }

    /**
     * Returns the task list previously stored when the program is first loaded.
     *
     * @return an arrayList of type Task containing all tasks previously saved by user.
     * @throws FileNotFoundException when the file is not found in the specified path.
     */
    public List<Task> loadList() throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();
        File f = new File(this.filename);
        Scanner s = new Scanner(f);
        while(s.hasNext()){
            Task newTask;
            String line = s.nextLine();

            String[] lineArray = line.split("\\|");
            if(line.contains("[T]")){
                newTask = new ToDo(lineArray[0]);
            } else if(line.contains("[D]")){
                newTask = new Deadline(lineArray[0], lineArray[1]);
            } else if (line.contains("[E]")){
                newTask = new Event(lineArray[0], lineArray[1], lineArray[2]);
            } else {
                continue;
            }

            if(line.contains("[X]")){
                newTask.markAsDone();
            }
            tasks.add(newTask);

        }
        return tasks;
    }

    /**
     * Saves all new tasks created by user.
     * Saves the tasks in a text file so that they can be loaded next time the program runs.
     *
     * @param tasks the list of tasks to be saved.
     * @throws IOException when the file cannot be written to or does not exist and cannot be created.
     */
    public void addToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter("./src/main/java/list.txt");
        String list = "";
        for(int i = 0; i < tasks.size(); i++){
            if(Objects.equals(tasks.get(i).taskType(), "[D]")){
                String[] deadlineDescription = tasks.get(i).getDescription().split("\\(by:");
                String[] extractDeadline = deadlineDescription[1].split("\\)");
                list += Objects.toString(deadlineDescription[0]+ "|" +
                        extractDeadline[0] + "|" + tasks.get(i).taskType()) + "|" + Objects.toString(tasks.get(i).getStatus())  + "\n";
            } else if (Objects.equals(tasks.get(i).taskType(),"[E]")){
                String[] eventDescription = tasks.get(i).getDescription().split("\\(from:");
                String[] extractFrom = eventDescription[1].split("to:");
                String[] extractTo = extractFrom[1].split("\\)");
                list += Objects.toString(eventDescription[0]+ "|" + extractFrom[0] + "|" + extractTo[0] + "|"
                        + tasks.get(i).taskType()) + "|" + Objects.toString(tasks.get(i).getStatus())  + "\n";
            }
            else {
                list += Objects.toString(Objects.toString(tasks.get(i).getDescription())+ "|" + tasks.get(i).taskType()) + "|" + Objects.toString(tasks.get(i).getStatus())  + "\n";
            }
        }
        fw.write(list);
        fw.close();
    }

}
