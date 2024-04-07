# Quokka Chatbot User Guide

Welcome to Quokka, your personal task management chatbot! Quokka helps you manage your tasks efficiently through a simple and intuitive interface. This guide will walk you through the features and commands supported by Quokka.

## Features

1. **Task Management**: Quokka allows you to add, delete, mark as done, and list tasks.
2. **Task Types**: You can create different types of tasks such as Todo, Deadline, and Event.
3. **Date and Time Parsing**: Quokka understands dates and times in various formats.
4. **Keyword Search**: Find tasks quickly by searching for keywords.
5. **Data Persistence**: Quokka saves your tasks to a file for easy retrieval.

## Commands

### Adding Tasks

- **Todo**: `todo [description]` - Adds a todo task with the given description.
- **Deadline**: `deadline [description] /by [date/time]` - Adds a deadline task with the given description and due date/time.
- **Event**: `event [description] /from [start] /to [end]` - Adds an event task with the given description and start/end times.

### Managing Tasks

- **List**: `list` - Displays all tasks in the task list.
- **Mark as Done**: `mark [task number]` - Marks the task with the specified number as done.
- **Mark as Not Done**: `unmark [task number]` - Marks the task with the specified number as not done.
- **Delete**: `delete [task number]` - Deletes the task with the specified number from the task list.
- **Find**: `find [keyword]` - Searches for tasks containing the specified keyword in their descriptions.

### Exiting

- **Exit**: `bye` - Exits Quokka.

## Date and Time Formats

- **Deadline**: `dd/MM/yyyy HHmm` (e.g., 18/2/2024 1800)

## Example Usage

1. Add a todo task: `todo Read a book`
2. Add a deadline task: `deadline Submit report /by 31/12/2024 1900`
3. Add an event task: `event Team meeting /from 2pm /to 5pm`
4. List all tasks: `list`
5. Mark a task as done: `mark 1`
6. Find tasks containing a keyword: `find report`
7. Exit Quokka: `bye`

## Tips

- Make sure to provide clear and concise task descriptions.
- Use the keyword search feature to quickly find relevant tasks.
- Double-check date and time formats when adding deadline or event tasks.
