# Orion User Guide


Welcome to Orion, your friendly task management system. This guide will help you navigate through the features of the Orion application and make the best use of it to manage your tasks effectively.

## Features 

### Adding a Todo: `todo`
A todo task is a task.

### Adding a Deadline: `deadline`
A deadline task is a task that needs to be completed before a specific date/time.

### Adding an Event: `event`
An event task is a task that starts at a specific date/time and is useful for tracking events such as meetings or appointments.

### Listing All Tasks: `list`
List all tasks you have in your task list.

### Deleting a Task: `delete`
Delete a task from your task list when you no longer need it.

### Finding Tasks: `find`
Find a task by searching for a keyword.

## Usage

### `todo` - Adding a Todo

Adds a todo task to your task list.

Example of usage: 

`todo read book`

Expected outcome:

A new todo task with the description is added to the task list.

Description of the outcome:

```
Got it. I've added this task:
  [T][ ] read book
Now you have X tasks in the list.
```

### `deadline` - Adding a Deadline

Adds a deadline task to your task list with a specified deadline.

Example of usage:

`deadline return book /by Sunday`

Expected outcome:

A new deadline task with the description and due date is added to the task list.

Description of the outcome:

```
Got it. I've added this task:
  [D][ ] return book (by: Sunday)
Now you have X tasks in the list.
```

### `event` - Adding an Event

Adds an event task to your task list with a specified start date/time.

Example of usage:

`event book club meeting /at Mon 2pm`

Expected outcome:

A new event task with the description and start date/time is added to the task list.

Description of the outcome:

```
Got it. I've added this task:
  [E][ ] book club meeting (at: Mon 2pm)
Now you have X tasks in the list.
```

### `list` - Listing All Tasks

Shows a list of all tasks in your task list.

Example of usage:

`list`

Expected outcome:

All tasks in your task list are displayed.

Description of the outcome:

```
Here are the tasks in your list:
1.[T][ ] read a book
2.[D][ ] return book (by: Sunday)
3.[E][ ] book club meeting (at: Mon 2pm)
```

### `delete` - Deleting a Task

Deletes a task from your task list.

Example of usage:

`delete 2`

Expected outcome:

The task at the specified index is deleted from the task list.

Description of the outcome:

```
Noted. I've removed this task:
  [D][ ] return book (by: Sunday)
Now you have X tasks in the list.
```

### `find` - Finding Tasks

Finds tasks by a keyword.

Example of usage:

`find book`

Expected outcome:

All tasks containing the keyword in their description are displayed.

Description of the outcome:

```
Here are the matching tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Sunday)
```

## Frequently Asked Questions (FAQs)

#### How do I start using Orion?
Launch the application via the command line, and follow the prompts. It's straightforward and user-friendly.

#### Can I add multiple tasks at one time?
Orion currently supports adding one task at a time to ensure accuracy and detail.

#### How will I know if my task was added successfully?
After adding a task, Orion will display a confirmation message along with the updated number of tasks in your list.

#### What format should I use for dates and times in Deadline and Event tasks?
Use a clear and consistent format like "MM/DD/YYYY" for dates and "HH:MM AM/PM" for times.

#### How can I mark a task as completed?
Currently, you can delete the task to indicate completion. We're considering adding a feature to mark tasks as completed in the future.

#### Can I edit a task?
Editing tasks is not available at the moment. To change a task, delete it and add a new one with the updated information.

#### How does the `find` feature work?
The `find` command searches your task descriptions for the provided keyword. It is not case-sensitive.

#### What if I search for a task that doesn't exist?
If no tasks match your search, Orion will let you know that there are no matching tasks.

#### Is there a way to recover a deleted task?
Once a task is deleted, it cannot be recovered. Please be certain before using the `delete` command.

#### Are there plans for new features?
Yes, we aim to continuously improve Orion and welcome any suggestions or feedback.

If you need more help or have any other questions, don't hesitate to ask!


## Command Summary

Command | Format | Example
--------|--------|--------
Add Todo | `todo DESCRIPTION` | `todo Read a book`
Add Deadline | `deadline DESCRIPTION /by DATE/TIME` | `deadline Return library book /by Friday`
Add Event | `event DESCRIPTION /at DATE/TIME` | `event Jake's Birthday Party /at Sunday 2pm`
List Tasks | `list` | `list`
Delete Task | `delete INDEX` | `delete 2`
Find Tasks | `find KEYWORD` | `find book`
