# User Guide
This is the user guide for the Binks task manager :)
## Features 

Users are able to add and delete todo, deadline and event type tasks as well as mark and unmark them based on whether or not the task is completed. 
Upon exiting the chatbot, the list that the user has input will be saved and re-uploaded when the user comes back. 

## Usage

### `list` - Lists out the current task list

After you type `list`, the chatbot will output the task list

Example of usage: 

`list`

Expected outcome:

```
____________________________________________________________
Here are the tasks in you list:
1. [T][ ] Read a book
2. [D][ ] Complete Homework (by: Tomorrow)
3. [E][ ] Group meeting (From: 1pm To: 4pm)
____________________________________________________________
```

### `mark` - Marks the corresponding task with an 'X'

After you type `mark INDEX`, mark the task with the corresponding index with an 'X'.

Example of usage:

`mark 1`

Expected outcome:

```
____________________________________________________________
Nice! I have marked this task as done:
[T][X] Read a book
____________________________________________________________
```

### `unmark` - Unmarks the corresponding task

After you type `unmark INDEX`, unmarks the corresponding task.

Example of usage:

`unmark 1`

Expected outcome:

```
____________________________________________________________
OK, I have marked this task as not done yet:
[T][ ] yes
____________________________________________________________
```

### `bye` - Exits the chatbot

After you type `bye`, exit the chatbot

Example of usage:

`bye`

Expected outcome:

```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

### `todo` - Adds a todo task into the list

After you type `todo`, adds a todo task into the list

Example of usage:

`todo read book`

Expected outcome:

```
____________________________________________________________
Got it. I've added this task:
 [T][ ] read book
Now you have 1 tasks in the list.
____________________________________________________________
```

### `deadline` - Adds a todo task into the list

After you type `deadline TASK /by TIME`, adds a deadline task into the list with the due time

Example of usage:

`deadline Complete Homework /by Tomorrow`

Expected outcome:

```
____________________________________________________________
Got it. I've added this task:
 [D][ ] Complete Homework (by: Tomorrow)
Now you have 2 tasks in the list.
____________________________________________________________

```

### `event` - Adds an event task into the list

After you type `event TASK /from TIME /to TIME`, adds a event task into the list with the to and from time.

Example of usage:

`event Group meeting /from 1pm /to 4pm`

Expected outcome:

```
____________________________________________________________
Got it. I've added this task:
 [E][ ] Group meeting (from: 1pm to: 4pm)
Now you have 3 tasks in the list.
____________________________________________________________

```

### `delete` - Deletes a task from the list

After you type `delete INDEX`, deletes the task with the corresponding index.

Example of usage:

`delete 1`

Expected outcome:

```
____________________________________________________________
I have deleted the task: 
[T][ ] Read Book
That task was useless anyways ¯\_(ツ)_/¯
You now have 2 tasks left
____________________________________________________________

```

### `find` - Finds task in the list

After you type `find KEYWORD`, finds the task in the list that contains the corresponding keyword.

Example of usage:

`find meeting`

Expected outcome:

```
____________________________________________________________
Here are the matching tasks in your list:
2. [E][ ] Group meeting (from: 1pm to: 4pm)
____________________________________________________________

```