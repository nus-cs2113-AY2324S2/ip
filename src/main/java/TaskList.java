import java.util.ArrayList;

class TaskList {
	private ArrayList<Task> tasks;

	public TaskList(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	public TaskList() {
		this.tasks = new ArrayList<>();
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}

	public void listTasks() {
		// Listing tasks
		System.out.println("Here are the tasks in your list:");
		for (int j = 0; j < tasks.size(); j++) {
			System.out.println((j + 1) + "." + tasks.get(j));
		}
		System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ? " task " : " tasks ") + "in the list.");
	}

	public void markTask(String indexStr) throws SamException {
		int index = Integer.parseInt(indexStr) - 1;
		indexCheck(index);
		tasks.get(index).setStatus(true);
		System.out.println("Nice! I've marked this task as done:\n" + tasks.get(index));
	}

	public void unmarkTask(String indexStr) throws SamException {
		int index = Integer.parseInt(indexStr) - 1;
		indexCheck(index);
		tasks.get(index).setStatus(false);
		System.out.println("OK, I've marked this task as not done yet:\n" + tasks.get(index));
	}

	public void addTodoTask(String description) throws SamException {
		description = description.trim();
		if (description.equals("")) {
			throw new SamException("You forgot to add a description for this task.");
		}
		tasks.add(new Todo(description));
		System.out.println("Got it. I've added this task:\n" + tasks.get(tasks.size() - 1));
	}

	public void addDeadlineTask(String description) throws SamException {
		String[] deadlineParts = description.split("/by");
		if (deadlineParts.length < 2) {
			throw new SamException("You messed up the deadline format: deadline <description> /by <date>");
		}
		deadlineParts[0] = deadlineParts[0].trim();
		deadlineParts[1] = deadlineParts[1].trim();
		if (deadlineParts[0].equals("")) {
			throw new SamException("You forgot to add a description for this task.");
		}
		if (deadlineParts[1].equals("")) {
			throw new SamException("You forgot to add a deadline for this task.");
		}
		tasks.add(new Deadline(deadlineParts[0], deadlineParts[1]));
		System.out.println("Got it. I've added this task:\n" + tasks.get(tasks.size() - 1));
	}

	public void addEventTask(String description) throws SamException {
		String[] eventParts = description.split("/from|/to");
		if (eventParts.length < 3) {
			throw new SamException("You messed up the event format: event <description> /from <date> /to <date>");
		}
		eventParts[0] = eventParts[0].trim();
		eventParts[1] = eventParts[1].trim();
		eventParts[2] = eventParts[2].trim();
		if (eventParts[0].equals("")) {
			throw new SamException("You forgot to add a description for this task.");
		}
		if (eventParts[1].equals("")) {
			throw new SamException("You forgot to add a start date for this task.");
		}
		if (eventParts[2].equals("")) {
			throw new SamException("You forgot to add an end date for this task.");
		}
		tasks.add(new Event(eventParts[0], eventParts[1], eventParts[2]));
		System.out.println("Got it. I've added this task:\n" + tasks.get(tasks.size() - 1));
	}

	public void deleteTask(String indexStr) throws SamException {
		int index = Integer.parseInt(indexStr) - 1;
		indexCheck(index);
		System.out.println("Noted. I've removed this task:\n" + tasks.get(index));
		tasks.remove(index);
		System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ? " task " : " tasks ") + "in the list.");
	}

	private void indexCheck(int index) throws SamException {
		if (index < 0 || index >= tasks.size()) {
			if (index < 0) {
				throw new SamException("No can do, the list starts at 1.");
			} else {
				throw new SamException("Hey lil bro, you have only " + tasks.size() + " things in the list.");
			}
		}
	}
}
