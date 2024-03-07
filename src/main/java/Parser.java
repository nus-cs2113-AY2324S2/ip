import sam.task.Deadline;
import sam.task.Event;
import sam.task.Task;
import sam.task.Todo;

// Parser class to parse user commands and task information
class Parser {
	// Method to parse user commands
	public static void parseCommand(String line, TaskList taskList) throws SamException {
		String[] words = line.split(" "); // Split the input line into words
		switch (words[0]) { // Check the first word to determine the command
		case "list":
			taskList.listTasks(); // Execute list command to display tasks
			break;
		case "mark":
			taskList.markTask(words[1]); // Execute mark command to mark a task as done
			break;
		case "unmark":
			taskList.unmarkTask(words[1]); // Execute unmark command to mark a task as not done
			break;
		case "todo":
			taskList.addTodoTask(line.replace("todo", "")); // Execute todo command to add a todo task
			break;
		case "deadline":
			taskList.addDeadlineTask(line.replace("deadline", "")); // Execute deadline command to add a deadline task
			break;
		case "event":
			taskList.addEventTask(line.replace("event", "")); // Execute event command to add an event task
			break;
		case "delete":
			try {
				if (words[1].equals("all")) {
					taskList.deleteAllTasks(); // Execute delete all command to delete all tasks
				} else {
					taskList.deleteTask(words[1]); // Execute delete command to delete a specific task
				}
			} catch (IndexOutOfBoundsException e) {
				throw new SamException("You have to delete an index or delete all.");
			}
			break;
		case "find":
			taskList.findTask(line.replace("find", "")); // Execute find command to search for tasks
			break;
		default:
			System.out.println("No valid command detected."); // Print error message for invalid command
			System.out.println("Please try again.");
		}
	}

	// Method to parse task information from a string
	public static Task parseTask(String line) throws SamException {
		String[] parts = line.split("\\|"); // Split the input line using pipe symbol
		if (parts.length < 3) {
			throw new SamException("Invalid task format in file."); // Throw exception for invalid task format
		}

		String type = parts[0].trim(); // Get the type of the task
		boolean isDone = parts[1].trim().equals("1"); // Check if the task is done
		String description = parts[2].trim(); // Get the description of the task

		switch (type) { // Create a task object based on the task type
		case "T":
			return new Todo(description); // Create a Todo task
		case "D":
			if (parts.length < 4) {
				throw new SamException("Invalid deadline task format in file."); // Throw exception for invalid deadline task format
			}
			String by = parts[3].trim(); // Get the deadline of the task
			return new Deadline(description, by); // Create a Deadline task
		case "E":
			if (parts.length < 4) {
				throw new SamException("Invalid event task format in file."); // Throw exception for invalid event task format
			}
			String from = parts[3].trim(); // Get the start time of the event
			String to = parts[4].trim(); // Get the end time of the event
			return new Event(description, from, to); // Create an Event task
		default:
			throw new SamException("Unknown task type in file."); // Throw exception for unknown task type
		}
	}
}
