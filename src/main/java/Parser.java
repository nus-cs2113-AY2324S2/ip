import AddTask.AddDeadline;
import AddTask.AddEvent;
import AddTask.AddTodo;
import ListCommands.*;
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
			/*taskList.listTasks(); // Execute list command to display tasks*/
			ListTasks listTasks = new ListTasks(taskList.getTasks());
			listTasks.execute();
			break;
		case "mark":
			/*taskList.markTask(words[1]); // Execute mark command to mark a task as done*/
			MarkTask markTask = new MarkTask(taskList.getTasks());
			markTask.execute(words[1]);
			break;
		case "unmark":
			/*taskList.unmarkTask(words[1]); // Execute unmark command to mark a task as not done*/
			UnmarkTask unmarkTask = new UnmarkTask(taskList.getTasks());
			unmarkTask.execute(words[1]);
			break;
		case "todo":
			AddTodo addTodo = new AddTodo(taskList.getTasks());
			addTodo.execute(line.replace("todo", "")); // Execute todo command to add a todo task
			break;
		case "deadline":
			AddDeadline addDeadline = new AddDeadline(taskList.getTasks());
			addDeadline.execute(line.replace("deadline", "")); // Execute todo command to add a todo task
			break;
		case "event":
			AddEvent addEvent = new AddEvent(taskList.getTasks());
			addEvent.execute(line.replace("event", "")); // Execute todo command to add a todo task
			break;
		case "delete":
			try {
				DeleteTask deleteTask = new DeleteTask(taskList.getTasks());
				if (words[1].equals("all")) {
					deleteTask.deleteAllTasks(); // Execute delete command to delete all tasks
				} else {
					deleteTask.execute(words[1]); // Execute delete command to delete a specific task
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
