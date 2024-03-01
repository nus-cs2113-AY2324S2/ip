# User Guide for Jeff
This is Jeff, a chatbot that keeps track of your daily tasks.
This guide is a simple user guide to get you started with Jeff.

## Format of Commands
The dates and times mentioned can be in any format as long as they are in a string that does not contain `/` 

## Features 

### Add Various Tasks to the list: `todo` / `deadline` / `event`
- Type `todo` followed by the description of your task to add a single todo task to the list
- Type `deadline` followed by the description and "do by" date in the format shown in the example below to add a deadline task to the list.
- Type `event` followed by the description, start date and end date in the format in the example below to add an event task to the list.

#### Example Commands
- `todo example task`
- `deadline some deadline /some date`
- `event some event /start date and time /end date and time`

#### Example Outputs

```
Got it. I've added this task
[T][ ] example task
Now you have 5 tasks in the list.
TASK UPLOADED
```

```
Got it. I've added this task
[D][ ] some deadline (by: some date)
Now you have 6 tasks in the list.
TASK UPLOADED
```

```
Got it. I've added this task
[E][ ] some event (from: start date and time to: end date and time)
Now you have 7 tasks in the list.
TASK UPLOADED
```

### List Tasks
- Type `list` to get Jeff to list all the tasks you have created.

#### Example output
```
Here are the tasks in your list:
1. [D][ ] some deadline (by: some date)
2. [E][ ] some event (from: start date and time to: end date and time)
```

### Mark Tasks
- Type `mark` followed by a number corresponding to a task in the list to get Jeff to mark that task as completed.

#### Example Command 
`mark 1`

#### Example Output
```
Nice Ive marked this task as completed 
TASK UPLOADED
```

### Unmark Tasks
-Type `unmark` followed by a number corresponding to a task in the list to get Jeff to unmark that task.

#### Example Command
- `unmark 1` (unmarks 2nd task)

#### Example Output
```
OK, I have unchecked this task
TASK UPLOADED
```

### Delete tasks
- Type remove followed by a number corresponding to a task in the list to get Jeff to get rid of that task.

#### Example Command
- `remove 1`

#### Example Output
```
Noted. I've removed this task:
[D][ ] some deadline (by: some date)
Now you have 1 tasks in the list.
TASK UPLOADED
```

### Find Tasks
- Type `find` followed by a keyword to get Jeff to give you tasks that have that keyword in its description.

#### Example Command
- `find something`

#### Example Output
```
I found something!
Here are the tasks in your list:
1. [T][ ] something
```

### Saving the list
Jeff automatically saves the list for you after every command that changes the list. This is shown in the `TASK UPLOADED` portion of the output

### Exit
- Type `bye`

#### Example Output
```
TASK UPLOADED
Bye. Hope to see you again soon!
```




# ENJOY!
