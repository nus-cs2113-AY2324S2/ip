import sam.task.Deadline;
import sam.task.Event;
import sam.task.Task;
import sam.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// Manages the list of tasks and performs various operations on them
class TaskList {
	private ArrayList<Task> tasks; // Stores the list of tasks

	// Constructor to create TaskList object with existing tasks
	public TaskList(ArrayList<Task> tasks) {
		this.tasks = tasks; // Initialize tasks ArrayList with existing tasks
	}

	// Default constructor to create an empty TaskList object
	public TaskList() {
		this.tasks = new ArrayList<>(); // Initialize an empty tasks ArrayList
	}

	// Retrieves the list of tasks
	public ArrayList<Task> getTasks() {
		return tasks; // Returns the tasks ArrayList
	}

	// Lists all tasks
	public void listTasks() {
		// Listing tasks
		if (tasks.isEmpty()) {
			System.out.println("Your list is completely empty."); // Print message if task list is empty
			return;
		}
		System.out.println("Here are the tasks in your list:");
		for (int j = 0; j < tasks.size(); j++) {
			System.out.println((j + 1) + "." + tasks.get(j)); // Print each task with index
		}
		System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ? " task " : " tasks ") + "in the list."); // Print total number of tasks
	}

	// Marks a task as done
	public void markTask(String indexStr) throws SamException {
		int index = Integer.parseInt(indexStr) - 1; // Convert index string to integer
		indexCheck(index); // Check if index is valid
		tasks.get(index).setStatus(true); // Mark the task as done
		System.out.println("Nice! I've marked this task as done:\n" + tasks.get(index)); // Print confirmation message
	}

	// Marks a task as not done
	public void unmarkTask(String indexStr) throws SamException {
		int index = Integer.parseInt(indexStr) - 1; // Convert index string to integer
		indexCheck(index); // Check if index is valid
		tasks.get(index).setStatus(false); // Mark the task as not done
		System.out.println("OK, I've marked this task as not done yet:\n" + tasks.get(index)); // Print confirmation message
	}

	// Adds a todo task
	public void addTodoTask(String description) throws SamException {
		description = description.trim(); // Remove leading and trailing whitespaces
		if (description.equals("")) {
			throw new SamException("You forgot to add a description for this task."); // Throw exception if description is empty
		}
		tasks.add(new Todo(description)); // Add todo task to the task list
		System.out.println("Got it. I've added this task:\n" + tasks.get(tasks.size() - 1)); // Print confirmation message
	}

	// Adds a deadline task
	public void addDeadlineTask(String description) throws SamException {
		// Split the description into parts
		String[] deadlineParts = description.split("/by");
		if (deadlineParts.length < 2) {
			throw new SamException("You messed up the deadline format: deadline <description> /by <date>"); // Throw exception if deadline format is incorrect
		}
		deadlineParts[0] = deadlineParts[0].trim(); // Remove leading and trailing whitespaces
		deadlineParts[1] = deadlineParts[1].trim(); // Remove leading and trailing whitespaces
		if (deadlineParts[0].equals("")) {
			throw new SamException("You forgot to add a description for this task."); // Throw exception if description is empty
		}
		if (deadlineParts[1].equals("")) {
			throw new SamException("You forgot to add a deadline for this task."); // Throw exception if deadline is empty
		}
		deadlineParts[1] = parseStringToDate(deadlineParts[1]); // Parse date string to desired format
		tasks.add(new Deadline(deadlineParts[0], deadlineParts[1])); // Add deadline task to the task list
		System.out.println("Got it. I've added this task:\n" + tasks.get(tasks.size() - 1)); // Print confirmation message
	}

	// Adds an event task
	public void addEventTask(String description) throws SamException {
		// Split the description into parts
		String[] eventParts = description.split("/from|/to");
		if (eventParts.length < 3) {
			throw new SamException("You messed up the event format: event <description> /from <date> /to <date>"); // Throw exception if event format is incorrect
		}
		eventParts[0] = eventParts[0].trim(); // Remove leading and trailing whitespaces
		eventParts[1] = eventParts[1].trim(); // Remove leading and trailing whitespaces
		eventParts[2] = eventParts[2].trim(); // Remove leading and trailing whitespaces
		if (eventParts[0].equals("")) {
			throw new SamException("You forgot to add a description for this task."); // Throw exception if description is empty
		}
		if (eventParts[1].equals("")) {
			throw new SamException("You forgot to add a start date for this task."); // Throw exception if start date is empty
		}
		if (eventParts[2].equals("")) {
			throw new SamException("You forgot to add an end date for this task."); // Throw exception if end date is empty
		}

		eventParts[1] = parseStringToDate(eventParts[1]); // Parse start date string to desired format
		eventParts[2] = parseStringToDate(eventParts[2]); // Parse end date string to desired format

		tasks.add(new Event(eventParts[0], eventParts[1], eventParts[2])); // Add event task to the task list
		System.out.println("Got it. I've added this task:\n" + tasks.get(tasks.size() - 1)); // Print confirmation message
	}

	// Deletes a task
	public void deleteTask(String indexStr) throws SamException {
		int index = 0;
		try {
			index = Integer.parseInt(indexStr) - 1; // Convert index string to integer
		} catch (NumberFormatException e) {
			throw new SamException("You can only delete a within bounds list index of 'all' to delete the entire list"); // Throw exception if index is invalid
		}
		indexCheck(index); // Check if index is valid
		System.out.println("Noted. I've removed this task:\n" + tasks.get(index)); // Print confirmation message
		tasks.remove(index); // Remove task from the task list
		System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ? " task " : " tasks ") + "in the list."); // Print total number of tasks
	}

	// Finds tasks matching a search term
	public void findTask(String findTerm) throws SamException {
		findTerm = findTerm.trim(); // Remove leading and trailing whitespaces
		if (findTerm.isEmpty()) {
			throw new SamException("You forgot to enter a search term."); // Throw exception if search term is empty
		}
		ArrayList<Task> findList = new ArrayList<>(); // List to store found tasks
		ArrayList<Integer> indexList = new ArrayList<>(); // List to store indices of found tasks
		for (int i = 0; i < tasks.size(); i++) {
			String description = tasks.get(i).getDescription(); // Get description of task
			if (description.toLowerCase().contains(findTerm.toLowerCase())) { // Check if description contains search term
				findList.add(tasks.get(i)); // Add task to findList if description matches search term
				indexList.add(i + 1); // Add index of task to indexList
			}
		}
		if (findList.isEmpty()) {
			System.out.println("Sorry, I couldn't find any tasks matching your search."); // Print message if no tasks found
		} else {
			System.out.println("Here are the tasks matching your search:");
			for (int j = 0; j < findList.size(); j++) {
				System.out.println(indexList.get(j) + "." + findList.get(j)); // Print matching tasks with indices
			}
			System.out.println("Now you have " + findList.size() + (findList.size() == 1 ? " task " : " tasks ") + "in the search list."); // Print total number of matching tasks
		}
	}

	// Deletes all tasks
	public void deleteAllTasks() {
		System.out.println("You sure you want to completely clear your task list? Enter 'y'/'Y' to confirm");
		Scanner in = new Scanner(System.in);
		String confirmation = in.nextLine();
		confirmation = confirmation.toLowerCase();
		if (confirmation.equals("y")) {
			System.out.println("Confirmed, I deleted your entire list.");
			while (!tasks.isEmpty()) {
				tasks.remove(0);
			}
			return;
		}
		System.out.println("Understood, I won't clear your list.");
	}

	// Checks if index is valid
	private void indexCheck(int index) throws SamException {
		if (index < 0 || index >= tasks.size()) {
			if (index < 0) {
				throw new SamException("No can do, the list starts at 1."); // Throw exception if index is less than 0
			} else {
				throw new SamException("Hey lil bro, you have only " + tasks.size() + " things in the list."); // Throw exception if index is out of bounds
			}
		}
	}

	// Parses string to date format
	public String parseStringToDate(String dateString) throws SamException {
		String[] parts = dateString.split(" ");
		for(int i = 0; i < parts.length; i++) {
			try {
				LocalDate parsedDate = LocalDate.parse(parts[i]);
				parts[i] = parsedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
			} catch (DateTimeParseException e) {
				// If parsing fails, keep the original part as it is
			}
		}
		return String.join(" ", parts); // Return formatted date string
	}

}