# Noriaki User Guide

## Features 

### Add Task to List
Users can add different Tasks to a List of Tasks: 
* ToDo
* Deadline
* Event.

### Mark/Unmark Task
Users can mark Tasks as done or undone.

### Delete Task
Users can delete Tasks.

### Find Task
Users can search for Tasks in List of Tasks through a keyword search.

### Delete Task
Users can delete Tasks.

### Local Database
All Tasks entered are stored on user's hard drive.

## Usage

### `todo` - Add ToDo
Adds a ToDo Task to List.

Example of usage:

`todo <description>`

Expected outcome:

```
todo Eat Breakfast
______________________________
Got it. I've added this task:
  [T][ ] Eat Breakfast
Now you have 1 tasks in the list.
______________________________
```

### `deadline` - Add Deadline
Adds a Deadline Task to List.

Example of usage:

`deadline <description> /by <deadline>`

Expected outcome:

```
deadline Coursemology /by Friday
______________________________
Got it. I've added this task:
  [D][ ] Coursemology (by: Friday)
Now you have 2 tasks in the list.
______________________________
```
### `event` - Add Event
Adds a Deadline Task to List.

Example of usage:

`event <description> /from <start> /to <end>`

Expected outcome:

```
event Tutorial /from 1pm /to 3pm
______________________________
Got it. I've added this task:
  [E][ ] Tutorial (from: 1pm to: 3pm)
Now you have 3 tasks in the list.
______________________________
```
### `list` - List Tasks
Lists all Tasks.

Example of usage:

`list`

Expected outcome:

```
list
______________________________
1.[T][ ] Eat Breakfast
2.[D][ ] Coursemology (by: Friday)
3.[E][ ] Tutorial (from: 1pm to: 3pm)
______________________________
```
### `mark` - Mark Task
Marks Task as Done.

Example of usage:

`mark <index>`

Expected outcome:

```
mark 1
______________________________
Nice! I've marked this task as done
  [T][X] Eat Breakfast
______________________________
```
### `unmark` - Unmark Task
Marks Task as Unone.

Example of usage:

`unmark <index>`

Expected outcome:

```
unmark 1
______________________________
Nice! I've marked this task as undone
  [T][ ] Eat Breakfast
______________________________
```
### `delete` - Delete Task
Deletes a Task from List.

Example of usage:

`delete <index>`

Expected outcome:

```
delete 1
______________________________
 Noted. I've removed this task:
  [T][ ] Eat Breakfast
______________________________
```
### `find` - Find Task
Finds Tasks with matching key word/key phrase.

Example of usage:

`find <keywords>`

Expected outcome:

```
find Eat
______________________________
Here are the matching tasks in your list-gari:
1.[T][ ] Eat Breakfast
______________________________
```