# User Guide for Sura Chatbot

## Features 

### Add-Todo

Allows users to add a todo task to the list.

### Add Deadline

Enables users to add a task with a deadline to the list.

### Add Event

Facilitates users in adding an event task to the list.

### Find a Keyword

Facilitates users to find for the task easily.

### Mark a task

Allows users to mark their task as done.

### Unmark a task

Allows users to unmark their task when not done.

### Delete

Allows users to delete a task from the list.

### List

Enables users to view all the tasks in the list.

## Usage

### `todo` - Add a todo task

Adds a todo task to the list.

Example of usage: 

`todo Buy groceries`

Expected outcome:

```
____________________________________________________________
Got it. I've added this task:
  [T][ ] Buy groceries
Now you have 1 tasks in the list.
____________________________________________________________
```


### `deadline` - Add a task with a deadline

Adds a taskwith a deadline to the list.

Example of usage: 

`deadline Complete report /by 2024-03-10`

Expected outcome:
```
____________________________________________________________
Got it. I've added this task:
  [T][ ] Buy groceries
  [D][ ] Complete report (by: Mar 10 2024)
Now you have 2 task(s) in the list.
____________________________________________________________
```


### `event` - Add an event task

Adds an event task to the list.

Example of usage: 

`event Project meeting /from 2 March 14:00 /to 2 March 15:00`

Expected outcome:
```
____________________________________________________________
Got it. I've added this task:
  [T][ ] Buy groceries
  [D][ ] Complete report (by: Mar 10 2024)
  [E][] Project meeting (from: 2 Mar 2024 14:00 to: 2 Mar 2024 15:00)
Now you have 3 task(s) in the list.
____________________________________________________________
```


### `find` - Search for a task

Searches for tasks in the list based on keywords

Example of usage:

`find book`

Expected outcome:
```
____________________________________________________________
Here are the matching tasks in your list:
1.[T][X] read book
2.[D][X] return book (by: June 6th)
____________________________________________________________
```


### `mark` - Marks a task as done

Marks a task as done in the list

Example of usage:

`mark 2`

Expected outcome:
```
____________________________________________________________
I have marked this task as done.
 [X] return book
____________________________________________________________
```


### `unmark` - unmarks a task 

Unmarks a task as done in the list

Example of usage:

`unmark 2`

Expected outcome:
```
____________________________________________________________
I have unmarked this task.
 [ ] return book
____________________________________________________________
```


### `delete` - Delete a task

Deletes a task from the list.

Example of usage: 

`delete 1`

Expected Outcome:
```
____________________________________________________________
Noted. I've removed this task: 
  [T][✘] Buy groceries
Now you have 2 task(s) in the list.
____________________________________________________________
```


### `list` - List down task

Lists down the remaining tasks.

Example of usage: 

`list`

Expected Outcome:
```
____________________________________________________________
Here are the tasks in your list:
[D][ ] Complete report (by: Mar 10 2024)
[E][ ] Project meeting (from: 2 Mar 2024 14:00 to: 2 Mar 2024 15:00)
Now you have 3 task(s) in the list.
____________________________________________________________
```




