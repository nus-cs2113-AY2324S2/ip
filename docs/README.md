# Tony (Chatbot) User Guide
Tony the chatbot is designed to help you track the tasks you need to do in the near future.
It can track three different types of tasks, namely: Todos, Deadlines, and Events.

* [Quick Start](#quick-start)
* [Features](#features)
  - [Add Todo task: `todo`](#add-todo-task-todo)
  - [Add Deadline Task: `deadline`](#add-deadline-task-deadline)
  - [Add Event Task: `event`](#add-event-task-event)
  - [List Tasks: `list`](#list-tasks-list)
  - [Mark Task as Done: `mark`](#mark-task-as-done-mark)
  - [Mark Task as Undone: `unmark`](#mark-task-as-not-done-unmark)
  - [Deleting Tasks: `delete`](#delete-task-delete)
  - [Find a Task: `find`](#find-task-find)
  - [Exit: `bye`](#exit-program-bye)
  - [Save Data](#save-data)
* [Frequently Asked Questions (FAQ)](#faq)

## Quick Start
1. Ensure you have Java 11 installed on your Computer.
2. Download the latest `tony.jar` from here [release](linkkk).
3. Save the file to the folder that you want to load the program from.
4. Open the command terminal, 'cd' into the folder that contains tony.jar.
5. Enter the command `java -jar tony.jar` to run the chatbot.
6. Refer to the [Features](#features) below to help with using the application.
7. Happy tasking!

## Features

### Add Todo Task: `todo`

Adds a todo [T] task. Simply enter `todo` followed by the task description.

Format: `todo <Description>`

Examples:
* todo reading
* todo hanging clothes

Usage:
```
-----------------------------------------------
todo reading
	Got it. I've added this task:
		[T][ ] reading
	Now you have 1 tasks in the list.
-----------------------------------------------
```

### Add Deadline Task: `deadline`

Adds a deadline [D] task. Simply enter `deadline` followed by the task description
and `/by` to specify when to complete the task by.

Format: `deadline <Description> /by <DATE>`

Examples:
* deadline watch lecture /by tonight
* deadline do maths quiz /by this Sunday

Usage:
```
-----------------------------------------------
deadline watch lecture /by tonight
    Got it. I've added this task:
		[D][ ] watch lecture (by: tonight)
	Now you have 2 tasks in the list.
-----------------------------------------------
```

### Add Event Task: `event`

Adds an event [E] task. Simply enter `event` followed by the task description,
`/from` to specify start of the event and `/to` to specify end of the event.

Format: `event <Description> /from <DATE> /to <DATE>`

Examples:
* event exercise today /from 10am /to 12pm
* event practice dance /from 2pm /to 5pm

Usage:
```
-----------------------------------------------
event exercise today /from 10am /to 12pm
	Got it. I've added this task:
		[E][ ] exercise today (from: 10am to: 12pm)
	Now you have 3 tasks in the list.
-----------------------------------------------
```

### List Tasks: ``list``

Shows a numbered list of all the tasks in the task list.

Format: `list`

Usage:
```
-----------------------------------------------
list
    Here are the tasks in your list:
    1.[T][ ] reading
    2.[D][ ] watch lecture (by: tonight)
    3.[E][ ] exercise today (from: 10am to: 12pm)
-----------------------------------------------
```

### Mark Task as Done: `mark`

Marks a completed task as done.
(Ensure that the number supplied after `mark` is a number from the task list.)

Format: `mark <Task_Number>`

Example:
* `mark 1`

Usage:
```
-----------------------------------------------
mark 1
    Nice! I've marked this task as done:
     [T][X] reading
-----------------------------------------------
```

### Mark Task as not Done: `unmark`

Marks a task as NOT done.
(Ensure that the number supplied after `unmark` is a number from the task list.)

Format: `unmark <Task_Number>`

Example:
* `unmark 1`

Usage:
```
-----------------------------------------------
unmark 1
    OK, I've marked this task as not done yet:
     [T][ ] reading
-----------------------------------------------
```

### Delete task: `delete`

Deletes a task item from the task list.
(Ensure that the number supplied after `delete` is a number from the task list.)

Format: `delete <Task_Number>`

Example:
* `delete 1`

Usage (Below shows the **Before** and **After** deletion):
```
-----------------------------------------------
list
    Here are the tasks in your list:
    1.[T][ ] reading
    2.[D][ ] watch lecture (by: tonight)
    3.[E][ ] exercise today (from: 10am to: 12pm)
-----------------------------------------------
delete 1
	Got it. I've removed this task:
		[T][ ] reading
	Now you have 2 tasks in the list.
-----------------------------------------------
list
    Here are the tasks in your list:
    1.[D][ ] watch lecture (by: tonight)
    2.[E][ ] exercise today (from: 10am to: 12pm)
-----------------------------------------------
```

### Find task: `find`

Finds a task that matches the keyword you typed.

Format: `find <keyword>`

Example:
* find watch
* find today

Usage:
```
find watch
-----------------------------------------------
	Here are the matching tasks in your list:
	1. [D][ ] watch lecture (by: tonight)
-----------------------------------------------
```

### Exit program: `bye`
A goodbye message is printed on the screen, and the program exits after.

Format: `bye`

Usage:
```
bye
-----------------------------------------------
Bye. Hope to see you again soon!
-----------------------------------------------
```

### Save Data
After each change to the current task list, the program automatically writes the updated tasks onto a file.
The default location of the saved textfile is a relative file path from 'tony.jar' 
and is located in a subfolder `./data/tonytask.txt`.

## FAQ
**Q:** Will my tasks in the list be wiped when I end the program?
**A:** No, the program creates a file and saved all the data into that file, and the next time the program is run,
the data is loaded back from the file.
