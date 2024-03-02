import sam.task.Task;

import java.io.*;
import java.util.ArrayList;

class Storage {
	private String filePath;

	public Storage(String filePath) {
		this.filePath = filePath;
	}

	public ArrayList<Task> load() throws SamException {
		ArrayList<Task> tasks = new ArrayList<>();
		File file = new File(filePath);

		if (!file.exists()) {
			try {
				File directory = new File("data");
				if (!directory.exists()) {
					directory.mkdir();
				}
				file.createNewFile();
			} catch (IOException e) {
				throw new SamException("Error creating the file: " + e.getMessage());
			}
		}

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				Task task = Parser.parseTask(line);
				tasks.add(task);
			}
		} catch (IOException e) {
			throw new SamException("Error reading tasks from file: " + e.getMessage());
		}
		return tasks;
	}

	public void saveTasks(ArrayList<Task> tasks) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
			for (Task task : tasks) {
				bw.write(task.saveTask());
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("Error saving tasks to file: " + e.getMessage());
		}
	}
}
