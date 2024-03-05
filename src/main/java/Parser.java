public class Parser {

    public static boolean parseCommand(String input, TaskList tasks, Ui ui) {
        try {
            if (input.equalsIgnoreCase("bye")) {
                Ui.printExit();
                return false; // Indicate to stop the program
            } else if (input.toLowerCase().startsWith("delete ")) {
                TaskList.deleteTask(input);
            } else if (input.equalsIgnoreCase("list")) {
                TaskList.displayTasks();
            } else if (input.toLowerCase().startsWith("mark ")) {
                TaskList.markTask(input);
            } else if (input.toLowerCase().startsWith("unmark ")) {
                TaskList.unmarkTask(input);
            } else {
                TaskList.addTask(input);
            }
        } catch (Exception e) { // Replace with a more specific exception if needed
            Ui.printCorrupted();
        }
        return true; // Continue running the program
    }
    public static Task parseTaskFromString(String data) {
        String[] parts = data.split("\\|");

        if (parts.length < 3) return null;

        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();
        Task task = null;

        switch (type) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                if (parts.length < 4) return null;
                String by = parts[3].trim();
                task = new Deadline(description, by);
                break;
            case "E":
                if (parts.length < 5) return null;
                String times = parts[3].trim(); // Assuming "from to to" format
                String[] timeParts = times.split(" to ", 2);
                if (timeParts.length < 2) return null; // Proper validation
                task = new Event(description, timeParts[0], timeParts[1]);
                break;
        }

        if (task != null && isDone) {
            task.markAsDone();
        }
        return task;
    }


}
