# User Guide of Charlie, a Task-managing Chatbot

## Quick start
1. Ensure you have Java 11 installed in your Computer.
2. Download the latest `ip.jar` from [here](https://github.com/Hws2209/ip/releases).
3. Open a command terminal, `cd` into the folder you put the jar file in, and use the 
   `java -jar ip.jar` command to run the application.
4. Type the command in the command box and press Enter to execute it.

## Features
> For the command format, words in UPPER_CASE are the parameters to be supplied by the user.

<br>

### Adding a todo: `todo`
Adds a task of 'todo' type to the list of tasks.

Format: `todo DESCRIPTION`

Example of usage:
`todo read book` assuming list is currently empty

Expected outcome:

```
New task added: [T][ ] read book
Current number of tasks: 1
```

<br>

### Adding a deadline: `deadline`
Adds a task of 'deadline' type to the list of tasks.

Format: `deadline DESCRIPTION /by BY_WHEN`

Example of usage:
`deadline return book /by 27 March`

Expected outcome:

```
New task added: [D][ ] return book (by: 27 March)
Current number of tasks: 2
```

<br>

### Adding an event: `event`
Adds a task of 'event' type to the list of tasks.

Format: `event DESCRIPTION /from FROM_WHEN /to TO_WHEN`

Example of usage:
`event carnival /from 4 March 5pm /to 9pm`

Expected outcome:

```
New task added: [E][ ] carnival (from: 4 March 5pm , to: 9pm)
Current number of tasks: 3
```

<br>

### Marking a task as completed: `mark`
Marks the specified task in the list as completed.

Format: `mark INDEX`
- Marks the task at the specified index
- The index refers to the index number shown in the displayed task list
- The index must be a positive integer (1, 2, 3, …), ranging from 1 
  to the number of tasks in the list

Example of usage:
`mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] read book
```

<br>

### Un-marking a task as not completed: `unmark`
Un-marks the specified task in the list as not completed.

Format: `unmark INDEX`
- Similar rule as marking

Example of usage:
`unmark 2`

Expected outcome:

```
OK, I've marked this task as not done yet:
[D][ ] return book (by: 27 March)
```

<br>

### Listing all tasks: `list`
Lists all the existing tasks in the list.

Format: `list`

Example of usage:
`list` assuming all the above example usages are entered

Expected outcome:

```
Here is your list of tasks:
1.[T][X] read book
2.[D][ ] return book (by: 27 March)
3.[E][ ] carnival (from: 4 March 5pm , to: 9pm)
```

<br>

### Searching tasks by keyword: `find`
Finds tasks that contain the keyword in their details.

Format: `find KEYWORD`

Example of usage:
`find March`

Expected outcome:

```
Here are the tasks containing 'March':
1.[D][ ] return book (by: 27 March)
2.[E][ ] carnival (from: 4 March 5pm , to: 9pm)
```

<br>

### Deleting a task: `delete`
Deletes the specified task in the list.

Format: `delete INDEX`
- Deletes the task at the specified index
- The index refers to the index number shown in the displayed task list
- The index must be a positive integer (1, 2, 3, …), ranging from 1
  to the number of tasks in the list

Example of usage:
`delete 1`

Expected outcome:

```
Task removed: [T][X] read book
Current number of tasks: 2
```

<br>

### Exiting the program: `bye`
Ends the program.

Format: `bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```

<br>

### Saving the data
Data are saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.