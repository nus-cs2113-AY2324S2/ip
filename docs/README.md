# User Guide for "Howard Smith"

Howard Smith is a personal chatbot assistant that will remember key tasks and their deadlines.
You can view your tasks at a glance and keep track of deadlines for key events.
## Features 

### List tasks

Lists all tasks

### Mark/Unmark tasks

Mark/Unmark selected task as done/undone.

### Add task

Add task to list.

### Delete task

Delete selected task.

### Find task

Find task in list using keywords.

## Notes
* No arguments are required unless otherwise stated.
* Words in capital case denote the type of arguments users should pass. For example INT indicates that an argument expects a number

## Usage

### `bye` - Exits the program

Example of usage: 

`bye`

Expected outcome:

```
	〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰
	 Bye. Hope to see you again soon! ツ
	〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰
```
### `list` - Lists all tasks in list.

Example of usage:

`list`

Expected outcome:

```
	〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓
	 Here are the tasks in your list:
 	1. [E][ ] taylor swift concert (from today till tmr 9pm)
 	2. [T][ ] do homework
 	3. [T][ ] do chores
 	4. [T][ ] do homeworrmk
 	5. [T][ ] do chores
	〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓
```
### `mark` - Marks selected task as done

Format:

mark TASK_AT_INDEX_NUMBER

Example of usage:

`mark 1`

Expected outcome:

```
	〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓
	 Here are the tasks in your list:
 	1. [E][X] taylor swift concert (from today till tmr 9pm)
 	2. [T][ ] do homework
 	3. [T][ ] do chores
 	4. [T][ ] do homeworrmk
 	5. [T][ ] do chores
	〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓
```
### `unmark` - Unmarks selected task as done

Format:

unmark TASK_AT_INDEX_NUMBER

Example of usage:

`unmark 1`

Expected outcome:

Description of the outcome.

```
	〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓
	 Here are the tasks in your list:
 	1. [E][ ] taylor swift concert (from today till tmr 9pm)
 	2. [T][ ] do homework
 	3. [T][ ] do chores
 	4. [T][ ] do homeworrmk
 	5. [T][ ] do chores
	〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓
```
### `todo` - Adds a todo task to list

Format:

todo DESCRIPTION

Example of usage:

`todo math assignment`

Expected outcome:

```
	____________________________________________________________
	 Got it. I've added this task:
		[T][ ] math assignment
	 Now you have 6 tasks in the list.
	____________________________________________________________
```
### `deadline` - Adds a deadline task to list

Format:

todo DESCRIPTION /DEADLINE

Example of usage:

`deadline CS2113 ip /by today 1159pm`

Expected outcome:

```
	____________________________________________________________
	 Got it. I've added this task:
		[D][ ] CS2113 ip (by today 1159pm)
	 Now you have 7 tasks in the list.
	____________________________________________________________
```
### `event` - Adds an event task to list

Format:

todo DESCRIPTION /START_TIME /END_TIME

Example of usage:

`event soccer match /from 9 March 2023 3pm /to 9 March 2023 5pm`

Expected outcome:

```
	____________________________________________________________
	 Got it. I've added this task:
		[E][ ] soccer match (from 9 March 2023 3pm to 9 March 2023 5pm)
	 Now you have 8 tasks in the list.
	____________________________________________________________
```
### `delete` - Deletes task from list.

Format:

delete TASK_AT_INDEX_NUMBER

Example of usage:

`delete 4`

Expected outcome:

```
	____________________________________________________________
	 Noted. I've removed this task:
		[T][ ] do homeworrmk
	 Now you have 7 tasks in the list.
	____________________________________________________________
```
### `find` - find tasks with descriptions matching the query

Format:

find TASK_WITH_SEARCH_TERM

Example of usage:

`find do`

Expected outcome:

```
	〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓
	 Here are the matching tasks in your list:
 	2. [T][ ] do homework
 	3. [T][ ] do chores
 	4. [T][ ] do chores
	〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓
```
