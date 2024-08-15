# Gene User Guide
Gene is a CLI task management bot that allows users to track tasks like Todos, Events and Deadlines.


## Table of Content

- [Quick Start](#quick-start)
- [Features](#features)
    - [`help` - View a list of all commands](#help---view-a-list-of-all-commands)
    - [`todo` - Add a Todo task](#todo---add-a-todo-task)
    - [`deadline` - Add a Deadline task](#deadline---add-a-deadline-task)
    - [`event` - Add an Event task](#event---add-an-event-task)
    - [`list` - List out all tasks](#list---list-out-all-tasks)
    - [`mark` - Mark a task as done](#mark---mark-a-task-as-done)
    - [`unmark` - Mark a task as undone](#unmark---mark-a-task-as-undone)
    - [`delete` - Remove a task](#delete---remove-a-task)
    - [`find` - Query for a task](#find---query-for-a-task)
    - [`bye` - Exit Gene](#bye---exit-gene)


## Quick Start
To start `Gene` using the jar file, copy the jar file to an empty folder of your choice. Afterward, using a terminal, navigate to the folder directory containing the jar file and run:
```
-$ java -jar ip.jar
```

## Features

### `help` - View a list of all commands
Show a list of commands that the user can use.


Example of usage:
```angular2html
help
```

Expected outcome:
```angular2html
Here is a list of commands you can do:
- list
- todo <task_description>
- deadline <task_description> /by <yyyy-MM-dd HHmm>
- event <task_description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>
- mark/unmark <task_number>
- delete <task_number>
- find <keyword>
- bye
```


### `todo` - Add a Todo task
Add a new Todo task to the task list.

Syntax:
```angular2html
todo <task_description>
```

Example of usage:
```angular2html
todo finish my homework
```

Expected outcome:
```angular2html
Got it. I've added this task:
  [T][ ] finish my homework
Now you have 1 tasks in the list.
```



### `deadline` - Add a Deadline task
Add a new Deadline task to the task list. 
Deadline Task will require a due-date, written in a specified format containing the date and time shown below.

Syntax:
```angular2html
deadline <task_description> /by <yyyy-MM-dd HHmm>
```

Example of usage:
```angular2html
deadline submit my essay /by 2024-05-12 2359
```

Expected outcome:
```angular2html
Got it. I've added this task:
  [D][ ] submit my essay (by: May 12 2024 23:59)
Now you have 2 tasks in the list.
```

### `event` - Add an Event task
Add a new Event task to the task list.
Event Task will require a start and end date, written in a specified format containing the date and time shown below.

Syntax:
```angular2html
event <task_description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>
```

Example of usage:
```angular2html
event internship interview with GovTech /from 2024-06-19 1000 /to 2024-06-19 1100 
```

Expected outcome:
```angular2html
Got it. I've added this task:
  [E][ ] internship interview with GovTech (from: Jun 19 2024 10:00 to: Jun 19 2024 11:00)
Now you have 3 tasks in the list.
```

### `list` - List out all tasks
Display a list of all the current tasks in the task list. 
The task list will show the status of each task with `[X]` representing a completed task, and `[ ]` representing an uncompleted task.
- `[T][ ]` represents an uncompleted Todo task.
- `[D][ ]` represents an uncompleted Deadline task.
- `[E][X]` represents a completed Event task.

Example of usage:
```angular2html
list
```

Expected outcome:
```angular2html
Here are the items in your list:
1. [T][ ] finish my homework
2. [D][ ] submit my essay (by: May 12 2024 23:59)
3. [E][X] internship interview with GovTech (from: Jun 19 2024 10:00 to: Jun 19 2024 11:00)
```


### `mark` - Mark a task as done
Mark a task in the task list as done.
The task number is the list number of that task shown in the list

Syntax:
```angular2html
mark <task_number>
```

Example of usage:
```angular2html
mark 1
```

Expected outcome:
```angular2html
Nice! I've marked this task as done:
  [X] finish my homework
```


### `unmark` - Mark a task as undone
Mark a task in the task list as not done.
The task number is the list number of that task shown in the list

Syntax:
```angular2html
unmark <task_number>
```

Example of usage:
```angular2html
unmark 1
```

Expected outcome:
```angular2html
OK, I've marked this task as not done yet:
  [ ] finish my homework
```

### `delete` - Remove a task
Delete a task from the list.
The task number is the list number of that task shown in the list

Syntax:
```angular2html
delete <task_number>
```

Example of usage:
```angular2html
delete 2
```

Expected outcome:
```angular2html
Got it. I've deleted this task:
  [D][ ] submit my essay (by: May 12 2024 23:59)
Now you have 2 tasks in the list.
```

### `find` - Query for a task
Look for tasks that contains a matching query keyword.

Syntax:
```angular2html
find <keyword>
```

Example of usage:
```angular2html
find interview
```

Expected outcome:
```angular2html
Here are the matching tasks in your list:
1. [E][X] internship interview with GovTech (from: Jun 19 2024 10:00 to: Jun 19 2024 11:00)
```

### `bye` - Exit Gene
Exit the Gene bot application. 

Example of usage:
```angular2html
bye
```

Expected outcome:
```angular2html
Bye. Hope to see you again soon!
```

