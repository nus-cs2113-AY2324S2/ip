# Jason Chatbot User Guide

Welcome to Jason, your personal task management chatbot! Jason helps you efficiently manage your daily tasks through a simple and intuitive command-line interface. This guide will introduce you to the features and commands supported by Jason.

## Features

1. **Task Management:** Jason allows you to add, delete, mark as done, and list tasks.
2. **Task Types:** Create different types of tasks such as Todo, Deadline, and Event.
3. **Date and Time Parsing:** Jason can interpret dates and times in various formats for deadlines and events.
4. **Keyword Search:** Quickly find tasks by searching for keywords.
5. **Data Persistence:** Jason saves your tasks to a file for easy retrieval and continuity.

## Commands

### Adding Tasks

- **Todo:** `todo [description]` - Adds a todo task with the given description.
- **Deadline:** `deadline [description] /by [date/time]` - Adds a deadline task with the given description and due date/time.
- **Event:** `event [description] /from [start] /to [end]` - Adds an event task with the given description and start/end times.

### Managing Tasks

- **List:** `list` - Displays all tasks in the task list.
- **Mark as Done:** `mark [task number]` - Marks the task with the specified number as done.
- **Mark as Not Done:** `unmark [task number]` - Marks the task with the specified number as not done.
- **Delete:** `delete [task number]` - Deletes the task with the specified number from the task list.
- **Find:** `find [keyword]` - Searches for tasks containing the specified keyword in their descriptions.

### Exiting

- **Exit:** `bye` - Exits Jason.

## Date and Time Formats

- **Deadline:** `dd/MM/yyyy HHmm` (e.g., 18/12/2024 1800)
- **Event:** Use `dd-MM HH:mm` for input and Jason will automatically parse and format the dates and times.

## Example Usage

1. Add a todo task: `todo Read a book`
2. Add a deadline task: `deadline Submit report /by 31/12/2024 1900`
3. Add an event task: `event Team meeting /from 19-10 14:00 /to 19-10 15:00`
4. List all tasks: `list`
5. Mark a task as done: `mark 1`
6. Find tasks containing a keyword: `find report`
7. Exit Jason: `bye`

## Tips

- Provide clear and concise task descriptions.
- Use the keyword search feature to quickly find relevant tasks.
- Remember to exit using the `bye` command to save your tasks before closing Jason.

Thank you for using Jason, your friendly chatbot for managing tasks. Happy tasking!
