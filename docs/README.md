# User Guide - Brad

## Features 

Brad is a task manager bot that supports the addition of todo tasks, event tasks and deadline tasks.
It has versatile commands which makes life easier such as:
- Mark
- Unmark
- Delete
- Find

### Add and Manage Various Types of Tasks
The primary feature of Brad is to add and delete tasks off 3 different task types which supports the feature to mark
a task as Complete / Incomplete. The full list of tasks can also be queried all at once for ease of viewing.


#### Todo Task
A Todo task is a simple bare-bone task. Brad only stores the description of the todo.

#### Deadline Task
A Deadline task is an extension of the Todo task, with extra information storing the due timing. (by when a task should
be completed)

#### Event Task
An Event task is an extension of the Todo task, with extra information storing the event timing.

### Automatic Saving
Brad is able to keep track of all tasks entered by the user automatically and saves it to the local storage in MarkDown format
for easy reference and formatting. When user initializes Brad, it automatically loads all previously stored tasks
from the local file and repopulate the task list.


## Usage

### Program initialization
If this is the first time you are running Brad, you should see something like the following:

```
Hello I am Brad.

How can I help you today?

File created: C:\Users\djleong01\Desktop\Uni stuff\Y2S2\CS2113\ip\data\data.md
file exists?: true
```

If this is not the first time you are running Brad, you should see something like the following:

```
Hello I am Brad.

How can I help you today?

File already exists!
__________________________________________________________
1. [T][ ] task 1
2. [D][ ] task 2 (by: tmr)
3. [E][ ] task 3 (from: 3pm to: 6pm)

__________________________________________________________
```

### `bye` -  Exit the Brad App
Exits the program.

Example of usage: 

`bye`

Expected outcome:
Successful exiting of the program

```
bye
__________________________________________________________
:( Shutting down...
 Hope to see you again!
__________________________________________________________
```
### `todo` -  Add a Todo Task
Adds a Todo task to the list of task. It only takes in the task description without any other information.

Supports Marking and Unmarking. Task will be saved to the local file.

Any user input after the `todo` command will be taken as the task description.

Example of usage:

`todo CS2113 Quiz`

Expected outcome:

Successful addition of task to the list

```
todo CS2113 Quiz
__________________________________________________________
Got it. I've added a 
todo:
[T][ ] CS2113 Quiz
 Now you have 1 tasks in the list.
__________________________________________________________
```

### `deadline` -  Add a Deadline Task
Adds a Deadline task to the list of task. It takes in the task description along with time information
to indicate by when the deadline task should be completed by.

Supports Marking and Unmarking. Task will be saved to the local file.

User must indicate the time by including the `/by` command after the deadline description. Any argument after that will be 
taken as the due date/time.

Example of usage:

`deadline release iP Jar file /by Sunday`

Expected outcome:

Successful addition of task to the list with time information stored.

```
deadline release iP Jar file /by Sunday
__________________________________________________________
Got it. I've added a 
deadline:
[D][ ] release iP Jar file (by: Sunday)
 Now you have 2 tasks in the list.
__________________________________________________________

```
### `event` -  Add an Event Task
Adds an Event task to the list of task. It takes in the task description along with time information
to indicate when the event is happening.

Supports Marking and Unmarking. Task will be saved to the local file.

User must indicate the time infomration by including the `/from` command to indicate
when the event is starting from, and `/to` for when the event is ending. Any user input after those commands will be
taken as the start/end timings.

Example of usage:

`event Ed Sheeran concert /from 7pm /to 11pm`

Expected outcome:

Successful addition of task to the list with time information stored.

```
event Ed Sheeran concert /from 7pm /to 11pm
__________________________________________________________
Got it. I've added a 
event:
[E][ ] Ed Sheeran concert (from: 7pm to: 11pm)
 Now you have 3 tasks in the list.
__________________________________________________________

```
### `list` -  View all current tasks on the list
View the current list of tasks that are stored in the file. It is formatted to 
display information such as:
- Type of task (first box)
  - `[T]` - todo
  - `[E]` - event
  - `[D]` - deadline
- Completion status (2nd box)
  - `[X]`- done
  - `[ ]` - not done
- Task Description
- Time Information (for deadline / event tasks only)

Example of usage:

`list`

Expected outcome:

A list of all current tasks.
```
list
__________________________________________________________
1. [T][ ] CS2113 Quiz
2. [D][ ] release iP Jar file (by: Sunday)
3. [E][ ] Ed Sheeran concert (from: 7pm to: 11pm)

__________________________________________________________

```

### `mark` -  Mark a Task as Done
Marks a task as done or completed. Once marked, the `X` symbol on the `list` command will appear.

User must specify a task to mark based on the index number of the task list after the command. 
Changes will be saved to the local file.

Example of usage:

`mark 2`

Expected outcome:

The `X` symbol appearing in the second box `[]` of the specified task description.
```
mark 2
__________________________________________________________
Nice! I've marked this task as done:
[D][X] release iP Jar file (by: Sunday)
__________________________________________________________

```
### `unmark` -  Unmark a Task as Not Done
Marks a task as not done or incomplete. Once unmarked, the `X` symbol on the `list` command will disappear.

User must specify a task to unmark based on the index number of the task list after the command. 
Changes will be saved to the local file.

Example of usage:

`unmark 2`

Expected outcome:

The `X` symbol that was previously in the second box `[]` of the specified task
description will be removed.
```
unmark 2
__________________________________________________________
Nice! I've marked this task as not done:
[D][ ] release iP Jar file (by: Sunday)
__________________________________________________________

```
### `delete` -  Delete a Task
Deletes a task from the current list.

User must specify a task to delete based on the index number of the task list after the command. 
Changes will be saved to the local file.

Example of usage:

`delete 1`

Expected outcome:

The task is removed from the list and local file.
```
delete 1
__________________________________________________________
Got it. I've removed this task:
[T][ ] CS2113 Quiz
 Now you have 2 tasks in the list.
__________________________________________________________
```

### `find` -  Search for a Task
Search for a task from the current list. Task(s) that contain the keyword will be displayed accordingly.

User must specify a keyword to search for in the list after the command. 


Example of usage:

`find concert`

Expected outcome:
A list of task(s) containing the specified keyword will appear.

```
find concert
__________________________________________________________
Here are the matching tasks in your list:
1. [E][ ] Ed Sheeran concert (from: 7pm to: 11pm)

__________________________________________________________
```

If the keyword does not exist in the list, the following will appear.

Example command:

`find CS1010`

```
find CS1010
__________________________________________________________
Yikes! I can't find that task :(
__________________________________________________________
```
