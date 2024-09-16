# User Guide - Chatbot Ekud
This is the user guide for chatbot Ekud, a Command Line Interface (CLI) application.<br>

## Features 

### Adding a task
Adds a task to the task list. The task can be a Todo, Deadline or Event.<br>

## Usage
> `todo <description>`<br>
> Adds a Todo task with the given description.

> `deadline <description> /by <by>`<br>
> Adds a Deadline task with the given description and deadline to meet.

> `event <description> /from <from> /to <to>`<br>
> Adds an event task with its ongoing period.

### Listing all the tasks

Shows a list of all the tasks in the task list.

## Usage
> `list`

### Deleting a task

Deletes a task in the task list.

## Usage
> `delete <index>`

### Marking a task

Mark a task as completed.

## Usage
> `mark <index>`

### Unmarking a task

Unmark a task as uncompleted.

## Usage
> `unmark <index>`

### Deleting a task

Deletes a task based on the task number.

## Usage
> `delete <index>`

### Finding a task

Finds relevant tasks based on keyword.

## Usage
> `find <keyword>`

#### Example of usage:
`find books` - Finds tasks that contain keyword "books".

#### Expected outcome:
```
Here are the relevant tasks:
1.[T][] read book
2.[D][] return book
```
