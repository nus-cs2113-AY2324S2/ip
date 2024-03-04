# User Guide

## Features 

### Adding tasks to the list

Users can choose to add either a Todo, a Deadline, or an Event to the list of tasks

### Marking/unmarking tasks

Users can mark tasks as done or undone.

### Deleting tasks

Users can delete tasks.

### Finding tasks

Users can search for a task in the list.

### Hardware storage

All tasks in the list are saved to hard drive.

## Usage

### `todo` - Adds todo

Adds a Todo task to the list

Example of usage:

`todo (description of todo)`

Expected outcome:

```
todo sleep
Alright, adding this task to the list: 
[T][ ] sleep
You have 1 tasks in the list.
____________________________________________________________
```

### `deadline` - Adds deadline

Adds a Deadline task to the list with a due date

Example of usage:

`deadline (description of deadline) /by (deadline time)`

Expected outcome:

```
deadline study /by tonight
Alright, adding this task to the list: 
[D][ ] study (by: tonight)
You have 2 tasks in the list.
____________________________________________________________
```

### `event` - Adds event

Adds an Event task to the list with a start and end time

Example of usage:

`event (description of event) /from (start time) /to (end time)`

Expected outcome:

```
event class /from 10am /to 11am
Alright, adding this task to the list: 
[E][ ] class (from: 10am to: 11am)
You have 3 tasks in the list.
____________________________________________________________
```

### `list` - List tasks

Lists all tasks in the list

Example of usage: 

`list`

Expected outcome:

All tasks in the list

```
list
Here are your tasks: 
1. [T][ ] sleep
2. [D][X] study (by: tonight)
3. [E][ ] class (from: 10am to: 11am)
____________________________________________________________
```

### `delete` - Deletes a task

Deletes a task from the list

Example of usage:

`delete (task index)`

Expected outcome:

Remaining tasks in the list

```
delete 1
Deleting task: [T][ ] sleep
Here are your tasks: 
1. [D][X] study (by: tonight)
2. [E][ ] class (from: 10am to: 11am)
____________________________________________________________
```

### `mark` - Marks a task

Marks a task on the list as done

Example of usage:

`mark (task index)`

Expected outcome:

Task marked as done

```
mark 2
Good Job! I'm setting this task as done: 
[E][X] class (from: 10am to: 11am)
____________________________________________________________
```

### `unmark` - Unmarks a task

Marks a task on the list as undone

Example of usage:

`unmark (task index)`

Expected outcome:

Task marked as undone

```
unmark 2
Oop! I'm setting this task as undone: 
[E][ ] class (from: 10am to: 11am)
____________________________________________________________
```

### `find` - Searches for a task


Searches for a task in the list with a search term
Example of usage:

`find (search term)`

Expected outcome:

Search results

```
find study
Here are your search results: 
1. [D][X] study (by: tonight)
____________________________________________________________
```

