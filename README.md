# Sayo User Guide

Welcome to the Sayo User Guide. This document will help you understand how to use Sayo, the friendly chatbot designed to keep track of various tasks.

## Features 

### Listing All Tasks: `list`
Shows a list of all tasks.

### Adding a Todo Task: `todo <description>`
Adds a todo task to the list.

Example: 
`todo read book`

### Marking a Task as Done: `mark <index>`
Marks the task at the specified index as done.

Example: 
`mark 1`

### Unmarking a Task: `unmark <index>`
Unmarks the task at the specified index.

Example:
`unmark 1`

### Adding a Deadline: `deadline <description> /by <date>`
Adds a task with a deadline.

Example: 
`deadline return book /by Sunday`

### Adding an Event: `event <description> /from <start_date> to <end_date>`
Adds an event happening between two dates.

Example:
`event project meeting /from Monday to Tuesday`

### Deleting a Task: `delete <index>`
Deletes the task at the specified index.

Example:
`delete 1`

### Finding Tasks by Keyword: `find <keyword>`
Finds all tasks that contain the given keyword.

Example:
`find book`

### Exiting the Chatbot: `bye`
Closes the chatbot.

Example:
`bye`

## Command Summary

- `list` - Lists all tasks
- `todo <description>` - Adds a todo task
- `mark <index>` - Marks a task as done
- `unmark <index>` - Unmarks a task
- `deadline <description> /by <date>` - Adds a deadline
- `event <description> /from <start_date> to <end_date>` - Adds an event
- `delete <index>` - Deletes a task
- `find <keyword>` - Finds tasks by keyword
- `bye` - Exits Sayo

## Getting Started

1. Ensure Java 11 or above is installed on your computer.
2. Download the latest version of `Sayo.jar` from our [Releases page](https://github.com/soongensayo/ip/releases/tag/v1.0.0).
3. Run the program using the command `java -jar Sayo.jar`.
4. Start interacting with Sayo using the commands listed above.
