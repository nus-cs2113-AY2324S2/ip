# User Guide

## Features 

### Feature-ABC

Description of the feature.

### Feature-XYZ

Description of the feature.

## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```


# User Guide

## Features

### Marking Tasks

Allows the user to mark tasks as done or undone.

### Adding Todo Tasks

Enables the user to add a todo task to the task list.

### Adding Deadline Tasks

Facilitates the addition of a deadline task to the task list.

### Adding Event Tasks

Allows the user to add an event task to the task list.

### Deleting Tasks

Allows the user to delete a task from the task list.

### Finding Tasks

Enables the user to search for tasks containing a specific keyword.

## Usage

### `mark <task_number>` - Mark a task as done

Marks the specified task as done.

Example of usage:

`mark 2`

Expected outcome:

The task with index 2 will be marked as done.

```
Task 2 marked as done.
```

### `unmark <task_number>` - Mark a task as undone

Marks the specified task as undone.

Example of usage:

`unmark 3`

Expected outcome:

The task with index 3 will be marked as undone.

```
Task 3 marked as undone.
```

### `delete <task_number>` - Delete a task

Deletes the specified task from the task list.

Example of usage:

`delete 1`

Expected outcome:

The task with index 1 will be deleted from the task list.

```
Task 1 deleted.
```

### `todo <task_description>` - Add a todo task

Adds a todo task with the specified description to the task list.

Example of usage:

`todo Buy groceries`

Expected outcome:

A todo task with the description "Buy groceries" will be added to the task list.

```
Todo task added: Buy groceries
```

### `deadline <description> /by <deadline>` - Add a deadline task

Adds a deadline task with the specified description and deadline to the task list.

Example of usage:

`deadline Submit report /by 2024-03-31`

Expected outcome:

A deadline task with the description "Submit report" and the deadline "2024-03-31" will be added to the task list.

```
Deadline task added: Submit report (by: 2024-03-31)
```

### `event <description> /from <start_date> /to <end_date>` - Add an event task

Adds an event task with the specified description, start date, and end date to the task list.

Example of usage:

`event Project meeting /from 2024-03-05 /to 2024-03-07`

Expected outcome:

An event task with the description "Project meeting", start date "2024-03-05", and end date "2024-03-07" will be added to the task list.

```
Event task added: Project meeting (from: 2024-03-05 to: 2024-03-07)
```

### `find <keyword>` - Find tasks

Searches for tasks containing the specified keyword in their descriptions.

Example of usage:

`find meeting`

Expected outcome:

Displays a list of tasks containing the keyword "meeting".

```
Behold, the deeds that align with your purpose.
    1. Event: Project meeting (from: 2024-03-05 to: 2024-03-07)
    2. Deadline: Submit report (by: 2024-03-31)
```