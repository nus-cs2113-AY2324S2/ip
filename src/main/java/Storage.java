import sam.task.Task;
import java.io.*;
import java.util.ArrayList;

// Class to handle storage operations for tasks
class Storage {
	private String filePath; // File path to store tasks

	// Constructor to initialize Storage object with file path
	public Storage(String filePath) {
		this.filePath = filePath; // Set the file path
	}

	// Method to load tasks from file
	public ArrayList<Task> load() throws SamException {
		ArrayList<Task> tasks = new ArrayList<>(); // Create an ArrayList to store tasks
		File file = new File(filePath); // Create a File object with the file path

		// Create the file and directory if they don't exist
		if (!file.exists()) {
			try {
				File directory = new File("data"); // Create a directory for storing data files
				if (!directory.exists()) {
					directory.mkdir(); // Create the directory if it doesn't exist
				}
				file.createNewFile(); // Create the file
			} catch (IOException e) {
				throw new SamException("Error creating the file: " + e.getMessage()); // Throw an exception if file creation fails
			}
		}

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				Task task = Parser.parseTask(line); // Parse each line of the file into a Task object
				tasks.add(task); // Add the Task object to the ArrayList
			}
		} catch (IOException e) {
			throw new SamException("Error reading tasks from file: " + e.getMessage()); // Throw an exception if reading from file fails
		}
		return tasks; // Return the ArrayList of tasks
	}

	// Method to save tasks to file
	public void saveTasks(ArrayList<Task> tasks) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
			for (Task task : tasks) {
				bw.write(task.saveTask()); // Write each task to the file
				bw.newLine(); // Add a newline character after each task
			}
		} catch (IOException e) {
			System.out.println("Error saving tasks to file: " + e.getMessage()); // Print error message if saving to file fails
		}
	}
}
