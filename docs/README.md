# KikuBot User Guide

Welcome to the KikuBot User Guide! KikuBot is a friendly task management chatbot to help you keep track of your tasks. Let's delve into how you can utilize all the important features of KikuBot effectively!

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `Kiku.jar` from the releases page.
3. Copy the file to the folder you want to use as the _home folder_ for KikuBot.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar KikuBot.jar` command to run KikuBot. The GUI should appear in a few seconds.
5. Type the command in the command box and press Enter to execute it.
Some example commands you can try:
   - `todo attend lecture`: Adds a todo task with the description `attend lecture`.
   - `mark 1`: Mark the first task in the current list as done.
   - `find lecture`: Search for all tasks that contains the keyword `lecture`.
   - `delete 1`: Delete the first task in the current list.
   - `bye`: Exit KikuBot.

6. Refer to the [Features](#Features) section below for an overview of all valid commands.

## Features

### Adding Tasks
There are three commands to add a new task to KikuBot's task list:
- `todo`: Adds a basic todo task with a description.
- `deadline`: Adds a deadline task with a due date.
- `event`: Adds an event task with a start date/time and end date/time.

### Managing Tasks
There are five commands to help manage existing tasks in KikuBot's task list:
- `list`: Listing all tasks in the current KikuBot task list.
- `mark`: Mark a task as done.
- `unmark`: Mark a task as not done.
- `delete`: Delete a task.
- `find`: Find all tasks containing a given keyword.

### Exiting KikuBot
There is only one command to terminate KikuBot:
- `bye`: Saves all tasks in the current task list and exits KikuBot.

Refer to the [Usage](#Usage) section below for details of each command.


## Usage

### Adding a todo task: `todo`

Adds a new todo task to the task list.

Format: `todo [task description]`

Example of usage: 

`todo attend CS2113 lecture`

Expected outcome:
```
Got it. I've added this task: 
[T][ ] attend CS2113 lecture
Now you have 1 tasks in the list!
```

The command adds a new todo task with  description `attend CS2113 lecture` into the task list.

### Adding a deadline task: `deadline`

Adds a new deadline task to the task list with a specific due date/time.

Format: `deadline [task_description] /by [due_date]`

Example of usage:

`deadline CS2113 tutorial /by Monday 2359`

Expected outcome:

```
Alright, I've added this task: 
[D][ ] CS2113 tutorial (by: Monday 2359)
Now you have 2 tasks in the list!
```

The command adds a new deadline task with description `CS2113 tutorial` and due date/time `Monday 2359` into the task list.

### Adding an event task: `event`

Adds a new event task to the task list with a specific start and end date/time.

Format: `event [task_description] /from [start_date_time] /to [end_date_time]`

Example of usage:

`event project group meeting /from Saturday 6pm /to 8pm`

Expected outcome:
```
Got it. I've added this task: 
[E][ ] project group meeting (from: Saturday 6pm to: 8pm)
Now you have 3 tasks in the list!
```

The command adds a new event task with description `project group meeting` with start date/time `Saturday 6pm` and end date/time `8pm` into the task list.

### Listing all tasks: `list`

Displays all tasks in the task list, including todos, deadlines, and events, with their respective task completion status.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list: 
1. [T][ ] attend CS2113 lecture
2. [D][ ] CS2113 tutorial (by: Monday 2359)
3. [E][ ] project group meeting (from: Saturday 6pm to: 8pm)
```

### Marking a task as done: `mark`

Marks a specific task in the task list as done based on its task number.

Format: `mark [task_number]`

Example of usage:

`mark 2`

Expected outcome:

```
Awesomeee! I've marked this task as done: 
[D][X] CS2113 tutorial (by: Monday 2359)
```

The command marks the second task in the task list, showing an [X] to indicate its completed status.

### Marking a task as not done: `unmark`

Marks a specific task in the task list as not done based on its task number.

Format: `unmark [task_number]`

Example of usage:

`unmark 2`

Expected outcome:

```
OK! I've marked this task as not done yet: 
[D][ ] CS2113 tutorial (by: Monday 2359)
```

The command unmarks the second task in the task list, removing the [X] to indicate its incomplete status.

### Deleting a task: `delete`

Removes a task from the task list based on its task number.

Format: `delete [task_number]`

Example of usage:

`delete 3`

Expected outcome:

```
Alright, I've removed this task: 
[E][ ] project group meeting (from: Saturday 6pm to: 8pm)
Now you have 2 tasks in the list!
```

The command deletes the third task in the task list. The task to be deleted will be shown.

### Finding for a task: `find`

Searches in the task list for tasks containing certain keyword(s) in their description.

Format: `find [keyword_or_phrase]`

Example of usage:

`find CS2113`

Expected outcome:

```
Alright! Here are the matching tasks in your list:
1. [T][ ] attend CS2113 lecture
2. [D][ ] CS2113 tutorial (by: Monday 2359)
```

### Exiting KikuBot: `bye`

Terminates the current session with KikuBot. All existing tasks will be saved before exiting.

Example of usage:

`bye`

Expected outcome:

```
Goodbye! Have a good day and hope to see you again soon :)
```