class TaskParser {
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
