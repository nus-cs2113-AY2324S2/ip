import java.util.ArrayList;

public class Parser {

    public static void ProcessSavedLine(String line, ArrayList todolist) {
        String Done = "|"+"([x ])"+"|";

        Boolean isDone ;
        if(line.contains("| |")){
            isDone = false;
        }else if(line.contains("|x|")){

            isDone = true;
        }else{
            System.out.println("unexpected format");
            return;
        }
        String description;
        String starttime;
        String endtime;

        if(line.contains("|T|")){
            String parts = line.substring(7);
            description = parts;
            Todo task = new Todo(isDone,description.trim());
            todolist.add(task);
        }else if(line.contains("|D|")){

            //String[] parts = line.split(Done,2);
            String parts = line.substring(7);
            String[] contents = parts.split("" +
                    "by:",2);
            description = contents[0].replace("(","");
            endtime = contents[1].replace(")","");
            Deadline task= new Deadline(isDone,description.trim(),endtime.trim());
            todolist.add(task);

        }else if(line.contains("|E|")){
            String parts = line.substring(7);
            String[] contents = parts.split( "from:",2);
            description = contents[0].replace("(","");
            String[] time= contents[1].split("by:",2 );
            starttime = time[0].replace("(","").replace(")","");
            endtime = time[1].replace("(","").replace(")","");
            Event task = new Event(isDone,description.trim(),endtime.trim(),starttime.trim());
            todolist.add(task);
        }else{
            System.out.println("unexpected format,skipping");
            return;
        }

    }


    static String AddTodo(String description) {
        Todo todo = new Todo(false, description);
        ListManager.todolist.add(todo);
        return "Todo added\n";
    }

    static String AddDeadline(String description) throws InvalidTimeForm {
        String[] part = (description.trim()).split("/by");

        if (part.length > 2) {
            throw new InvalidTimeForm();// two or more "/by"
        }

        Deadline deadline = new Deadline(false, part[0].trim(), part[1].trim());
        ListManager.todolist.add(deadline);
        return "deadline added\n";

    }

    static String AddEvent(String description) throws InvalidTimeForm {

        String[] part = (description.trim()).split("/from");
        if (part.length > 2) {
            throw new InvalidTimeForm();
        }
        String EventDescription = part[0].trim();
        String[] content = (part[1].trim()).split("/to");

        if (content.length > 2) {
            throw new InvalidTimeForm();
        }
        String end = content[1].trim();
        String start = content[0].trim();

        Event event = new Event(false, EventDescription, end, start);
        ListManager.todolist.add(event);
        return "event added\n";

    }
}

