import ListCommands.SamException;
import sam.task.Task;

import java.util.ArrayList;

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
}