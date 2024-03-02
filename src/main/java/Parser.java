import sam.task.Deadline;
import sam.task.Event;
import sam.task.Task;
import sam.task.Todo;

class Parser {
	public static void parseCommand(String line, TaskList taskList) throws SamException {
		String[] words = line.split(" ");
		switch (words[0]) {
		case "list":
			taskList.listTasks();
			break;
		case "mark":
			taskList.markTask(words[1]);
			break;
		case "unmark":
			taskList.unmarkTask(words[1]);
			break;
		case "todo":
			taskList.addTodoTask(line.replace("todo", ""));
			break;
		case "deadline":
			taskList.addDeadlineTask(line.replace("deadline", ""));
			break;
		case "event":
			taskList.addEventTask(line.replace("event", ""));
			break;
		case "delete":
			taskList.deleteTask(words[1]);
			break;
		default:
			System.out.println("No valid command detected.");
			System.out.println("Please try again.");
		}
	}

	public static Task parseTask(String line) throws SamException {
		String[] parts = line.split("\\|");
		if (parts.length < 3) {
			throw new SamException("Invalid task format in file.");
		}

		String type = parts[0].trim();
		boolean isDone = parts[1].trim().equals("1");
		String description = parts[2].trim();

		switch (type) {
		case "T":
			return new Todo(description);
		case "D":
			if (parts.length < 4) {
				throw new SamException("Invalid deadline task format in file.");
			}
			String by = parts[3].trim();
			return new Deadline(description, by);
		case "E":
			if (parts.length < 4) {
				throw new SamException("Invalid event task format in file.");
			}
			String from = parts[3].trim();
			String to = parts[4].trim();
			return new Event(description, from, to);
		default:
			throw new SamException("Unknown task type in file.");
		}
	}
}
