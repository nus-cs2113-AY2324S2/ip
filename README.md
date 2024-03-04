# OGFCore.OGF OGFTask.Task Handler
This chatbot helps users keep track of the various todos, deadlines and events they may have.
## Getting Started
1. Run the JAR file
2. Enter your commands into the command line interface
3. Enjoy! (?)

## Commands
### OGFTask.Todo
- Creates a todo that can be marked or unmarked
- Usage: `todo {name of todo}`

### OGFTask.Deadline
- Creates a deadline that can be marked or unmarked and has a due date
- Usage: `deadline {name of deadline} /by {due date}`

### OGFTask.Event
- Creates an event that can be marked or unmarked and has a start and end time
- Usage: `event {name of event} /from {start time} /to {end time}`

### List
- Lists out all the tasks in the task list
- Usage: `list`

### Delete
- Deletes a task from the list
- Usage: `delete {index of task to delete}`

### Mark/unmark
- Marks/unmarks a task on the list as done
- Usage: `mark {index of task to mark}` or `unmark {index of task to unmark}`

### Find
- Searches the task list of a search term
- Usage: `find {search term}`

### Bye
- Quits the program
- Usage: `bye`
