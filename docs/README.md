# User Guide for LeoDas

## Features 

### Task Listing (list)

This feature allows users to view the list of tasks they have added.

### Task Marking (mark)

Users can mark a task as done using this feature.

### Task Unmarking (unmark)

This feature enables users to mark a completed task as not done.

### Adding Todo (todo)

Users can add a new Todo task with a specified description.

### Adding Event (event)

This feature allows users to delete a specific task from the list.

### Finding Tasks(find)

This feature allows users to search for tasks containing a specific keyword in their descriptions.

### Exiting the Application (bye)

Users can exit the application by typing "bye."

## Usage

### `list` - View tasks

Displays the list of tasks.

Example of usage: 

`list`

Expected outcome:

Displays task numbers, descriptions, and completion status.

```
Here are the tasks in your list:
1.[ ] Sample Todo Task
2.[X] Completed Deadline Task (by: Feb 28, 2024 18:00)
3.[ ] Incomplete Event Task (from: Mar 1, 2024 12:00, to: Mar 1, 2024 15:00)

```

### `mark` - Mark a task as done

Marks a specified task as done.

Example of usage: 

`mark 1`

Expected outcome:

The task's completion status is updated, and users receive confirmation.

```
Nice! I've marked this task as done:
  [X] Sample Todo Task
```

### `unmark` - Mark a task as not done

Marks a completed task as not done.

Example of usage: 

`unmark 2`

Expected outcome:

The task's completion status is reversed, and users receive confirmation.

```
OK, I've marked this task as not done yet:
  [ ] Completed Deadline Task (by: Feb 28, 2024 18:00)
```

### `todo` - Add a new Todo task

Adds a new Todo task with a specified description.

Example of usage: 

`todo Complete Java assignment`

Expected outcome:

Confirms the addition and displays the updated number of tasks.

```
Got it. I've added this task:
[T][ ] Complete Java assignment
Now you have 4 tasks in the list.
```
### `deadline` - Add a task with a deadline

Adds a task with a specified description and deadline.

Example of usage: 

`deadline Submit project report /by Mar 5, 2024 23:59`

Expected outcome:

Confirms the addition and displays the updated number of tasks.

```
Got it. I've added this task:
  [ ] Submit project report (by: Mar 5, 2024 23:59)
Now you have 5 tasks in the list.
```
### `event` - Add an event with a specified duration

Adds an event with a specified description and duration.

Example of usage: 

`event Team meeting /from Mar 8, 2024 14:00 /to Mar 8, 2024 16:00`

Expected outcome:

Confirms the addition and displays the updated number of tasks.

```
Got it. I've added this task:
  [ ] Team meeting (from: Mar 8, 2024 14:00, to: Mar 8, 2024 16:00)
Now you have 6 tasks in the list.
```
### `delete` - Delete a specific task

Deletes a specified task from the list.

Example of usage: 

`delete 3`

Expected outcome:

Provides confirmation and displays the updated number of tasks.

```
Noted. I've removed this task: 
  [ ] Incomplete Event Task (from: Mar 1, 2024 12:00, to: Mar 1, 2024 15:00)
Now you have 5 tasks in the list.
```
### `find` - Search for tasks

Finds tasks containing a specified keyword in their descriptions

Example of usage: 

`find assignment`

Expected outcome:

Upon using the find command with the keyword "assignment," the expected outcome is the display of matching tasks in the user's list, including their task numbers and descriptions.

```
____________________________________________________________
Here are the matching tasks in your list:
1.[ ] Complete Java assignment
____________________________________________________________
```

### `bye` - Exit the application

Exits the application.

Example of usage: 

`bye`

Expected outcome:

Ends the interaction with the task management system.

```
Bye. Hope to see you again soon!
```
