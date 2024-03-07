# User Guide for Laika

Welcome to Laika, your friendly task management assistant! 
Laika helps you keep track of your todos, deadlines, and events,
ensuring you stay organized and productive. This guide will 
you through the basic operations to get you started.

## Installation

Get started with Laika,

1. Download the latest ip.jar from (https://github.com/cirelesna/ip/releases).
2. Copy the file into an empty folder to run Laika.
3. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar ip.jar command to run the application.

If the setup is correct, Laika will greet you!

## Features 

### Adding Tasks

Add tasks to your list. Laika supports three types of tasks: Todos, Deadlines, and Events.

### Viewing All Tasks

To see a list of all your tasks.

### Marking Tasks as Done

Mark a task as completed.

### Unmarking Tasks

Mark a task as not completed.

### Deleting Tasks

Remove a task from your list.

### Finding Tasks

Search for tasks by keywords.

### Exiting the Application

Exits Laika and saves your data.

## Usage

### 'todo' - Add a todo task

todo TASK_DESCRIPTION

Example of usage: 

`todo do homework`

Expected outcome:

```
Laika: Gotcha! I've added the task for you
  [T][ ] do homework
You have 4 tasks in your list. :P
```

### `deadline` - Add a deadline task

deadline TASK_DESCRIPTION /by DATE

Example of usage: 

`deadline submit homework /by 2023-03-21`

Expected outcome:


```
Laika: Gotcha! I've added the task for you
  [D][ ] submit homework  (by: 2023-03-21)
You have 5 tasks in your list. :P
```

### `event` - Add an event task

event TASK_DESCRIPTION /from START /to END

Example of usage: 

`event lecture quiz /from Friday 2pm /to Friday 4pm`

Expected outcome:


```
Laika: Gotcha! I've added the task for you
  [E][ ] lecture quiz  (from: Friday 2pm to: Friday 4pm)
You have 6 tasks in your list. :P
```

### `list` - View all tasks

list

Example of usage:

`list`

Expected outcome:

```
Task List:
1) [T][ ] do homework
2) [D][ ] submit homework  (by: 2023-03-21)
3) [E][ ] lecture quiz  (from: Friday 2pm to: Friday 4pm)
```

### 'mark' - Mark a task as done

mark/unmark TASK_NUMBER

Example of usage:

`mark 2`

Expected outcome:

```
Laika: Good job! Task has been marked as done.
[D][X] submit homework  (by: 2023-03-21)
```

### 'unmark' - Mark a task as undone

unmark TASK_NUMBER

Example of usage:

`unmark 2`

Expected outcome:


```
Laika: Alright, task has been marked as undone.
[D][ ] submit homework  (by: 2023-03-21)
```

### `delete` - Delete a task

delete TASK_NUMBER

Example of usage:

`delete 3`

Expected outcome:

```
Laika: Gotcha! I've dealt with this task:
[E][ ] lecture quiz  (from: Friday 2pm to: Friday 4pm)
Laika: You have 2 tasks left!
```

### `find` - Find tasks

find KEYWORD

Example of usage:

`find homework`

Expected outcome:

```
Here are the matching tasks in your list:
1) [T][ ] do homework
2) [D][ ] submit homework  (by: 2023-03-21)
```

### `bye` - Exit Laika

bye

Example of usage:

`bye`

Expected outcome:

```
Laika: Bye! Have a nice day!
```

Do remember to exit Laika by saying **bye** so that your data is saved
properly!