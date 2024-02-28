# Misty Chatbot: User Guide

## Features 

### 1. Add tasks with only task name

You can add tasks with only a task name to the task list.

### 2. Add tasks with deadlines

You can add tasks with a due dates to the task list.

### 3. Add tasks with start and end dates

You can add tasks which has both a start and end date to the task list.

### 4. List all tasks

You can show all tasks in the task list.

### 5. Mark tasks as completed

You can mark tasks to set them as completed in the task list.

### 6. Unmark tasks as not completed

You can Unmark tasks to set them as not completed in the task list.

### 7. Delete tasks

You can remove tasks from the task lists.

### 8. Find tasks

You can search tasks that contains a keyword in their name.

### 9. Check which tasks will occur on a specific date

You can search tasks that will occur on a specific date.
Will show deadlines with due date on that specified date.
Will show events that are happening on that specified date.

### 10. Save task list automatically

You don't need to manually sae as changes to the task list will automatically be saved to a text file on your computer.

### 11. Auto interprets date formats

Entering dates in the form yyyy-mm-dd will automatically be translated to reader friendly dates:
eg 2024-01-13  -> Sunday, Jan 13 2024

### 12. Exit chatbot

You can exit the chatbot when you are done using it.

## Usage

### `todo` - Add tasks with only task name

Format: `todo <task name>`

Example of usage:

`todo Workout`
`todo Clean House`

Expected outcome:

You should see something like on your screen:

```
todo Workout
	--------------------------------------------------
	Got it. I've added this task:
		[T][ ] Workout
	You now have 1 tasks in the list.
	--------------------------------------------------
```
```
todo Clean House
	--------------------------------------------------
	Got it. I've added this task:
		[T][ ] Clean House
	You now have 2 tasks in the list.
	--------------------------------------------------
```

### `deadline` - Add tasks with deadlines

Format: `deadline <task name> /by <due date>`

Example of usage:

`deadline homework /by tomorrow`
`deadline quiz /by 2024-06-06`

Expected outcome:

You should see something like this on your screen:

```
deadline homework /by tomorrow
	--------------------------------------------------
	Got it. I've added this task:
		[D][ ] homework (by: tomorrow)
	You now have 1 tasks in the list.
	--------------------------------------------------
```
```
deadline quiz /by 2024-06-06
	--------------------------------------------------
	Got it. I've added this task:
		[D][ ] quiz (by: THURSDAY, Jun 6 2024)
	You now have 2 tasks in the list.
	--------------------------------------------------
```

### `event` - Add tasks with start and end dates

Format: `eveny <task name> /from <start date> /to <end date>`

Example of usage:

`event concert /from Monday /to Wednesday`
`event competition /from 2024-07-08 /to 2024-07-09`

Expected outcome:

You should see something like on your screen:

```
event concert /from Monday /to Wednesday
	--------------------------------------------------
	Got it. I've added this task:
		[E][ ] concert (from: Monday to: Wednesday)
	You now have 1 tasks in the list.
	--------------------------------------------------
```
```
event competition /from 2024-07-08 /to 2024-07-09
	--------------------------------------------------
	Got it. I've added this task:
		[E][ ] competition (from: MONDAY, Jul 8 2024 to: TUESDAY, Jul 9 2024)
	You now have 2 tasks in the list.
	--------------------------------------------------
```

### `list` - List all tasks

Format: `list`

Example of usage:

`list`

Expected outcome:

You should see something like on your screen:

```
list
	--------------------------------------------------
	Here are the tasks in your list:
	1.[E][ ] concert (from: Monday to: Wednesday)
	2.[E][ ] competition (from: MONDAY, Jul 8 2024 to: TUESDAY, Jul 9 2024)
	3.[T][ ] read book
	4.[D][ ] homework (by: tomorrow)
	5.[E][ ] concert (from: SUNDAY, Jun 9 2024 to: SATURDAY, Jun 15 2024)
	--------------------------------------------------
```

### `mark` - Mark tasks as completed

Format: `mark <task index>`

Example of usage:

`mark 1`
`mark 3`

Expected outcome:

Let's say you have a task list that looks like this:

```
list
	--------------------------------------------------
	Here are the tasks in your list:
	1.[E][ ] concert (from: Monday to: Wednesday)
	2.[E][ ] competition (from: MONDAY, Jul 8 2024 to: TUESDAY, Jul 9 2024)
	3.[T][ ] read book
	4.[D][ ] homework (by: tomorrow)
	5.[E][ ] concert (from: SUNDAY, Jun 9 2024 to: SATURDAY, Jun 15 2024)
	--------------------------------------------------

```

You should see something like on your screen after entering the example commands:

```
mark 1
	--------------------------------------------------
	Nice! I've marked this task as done:
	[E][X] concert (from: Monday to: Wednesday)
	--------------------------------------------------
```
```
mark 3
	--------------------------------------------------
	Nice! I've marked this task as done:
	[T][X] read book
	--------------------------------------------------
```

When we list the tasks again, it should look like this:

```
list
	--------------------------------------------------
	Here are the tasks in your list:
	1.[E][X] concert (from: Monday to: Wednesday)
	2.[E][ ] competition (from: MONDAY, Jul 8 2024 to: TUESDAY, Jul 9 2024)
	3.[T][X] read book
	4.[D][ ] homework (by: tomorrow)
	5.[E][ ] concert (from: SUNDAY, Jun 9 2024 to: SATURDAY, Jun 15 2024)
	--------------------------------------------------
```

### `unmark` - Unmark tasks as not completed

Format: `unmark <task index>`

Example of usage:

`unmark 1`
`unmark 3`

Expected outcome:

Let's say you have a task list that looks like this:

```
list
	--------------------------------------------------
	Here are the tasks in your list:
	1.[E][X] concert (from: Monday to: Wednesday)
	2.[E][ ] competition (from: MONDAY, Jul 8 2024 to: TUESDAY, Jul 9 2024)
	3.[T][X] read book
	4.[D][ ] homework (by: tomorrow)
	5.[E][ ] concert (from: SUNDAY, Jun 9 2024 to: SATURDAY, Jun 15 2024)
	--------------------------------------------------

```

You should see something like on your screen after entering the example commands:

```
unmark 1
	--------------------------------------------------
	OK, I've marked this task as not done yet:
	[E][ ] concert (from: Monday to: Wednesday)
	--------------------------------------------------
```
```
unmark 3
	--------------------------------------------------
	OK, I've marked this task as not done yet:
	[T][ ] read book
	--------------------------------------------------
```

When we list the tasks again, it should look like this:

```
list
	--------------------------------------------------
	Here are the tasks in your list:
	1.[E][ ] concert (from: Monday to: Wednesday)
	2.[E][ ] competition (from: MONDAY, Jul 8 2024 to: TUESDAY, Jul 9 2024)
	3.[T][ ] read book
	4.[D][ ] homework (by: tomorrow)
	5.[E][ ] concert (from: SUNDAY, Jun 9 2024 to: SATURDAY, Jun 15 2024)
	--------------------------------------------------
```

### `delete` - Delete tasks

Format: `delete <task index>`

Example of usage:

`delete 3`
`delete 1`

Expected outcome:

Let's say you have a task list that looks like this:

```
list
	--------------------------------------------------
	Here are the tasks in your list:
	1.[E][ ] concert (from: Monday to: Wednesday)
	2.[E][ ] competition (from: MONDAY, Jul 8 2024 to: TUESDAY, Jul 9 2024)
	3.[T][ ] read book
	4.[D][ ] homework (by: tomorrow)
	5.[E][ ] concert (from: SUNDAY, Jun 9 2024 to: SATURDAY, Jun 15 2024)
	--------------------------------------------------
```

You should see something like on your screen after entering the example commands:

```
delete 3
	--------------------------------------------------
	Alright, I've deleted this task:
	[T][ ] read book
	You now have 4 tasks in the list.
	--------------------------------------------------
```
```
delete 1
	--------------------------------------------------
	Alright, I've deleted this task:
	[E][ ] concert (from: Monday to: Wednesday)
	You now have 3 tasks in the list.
	--------------------------------------------------
```

When we list the tasks again, it should look like this:

```
list
	--------------------------------------------------
	Here are the tasks in your list:
	1.[E][ ] competition (from: MONDAY, Jul 8 2024 to: TUESDAY, Jul 9 2024)
	2.[D][ ] homework (by: tomorrow)
	3.[E][ ] concert (from: SUNDAY, Jun 9 2024 to: SATURDAY, Jun 15 2024)
	--------------------------------------------------
```

### `find` - Find tasks

Format: `find <keyword>`

Example of usage:

`find home`
`find co`

Expected outcome:

Let's say you have a task list that looks like this:

```
list
	--------------------------------------------------
	Here are the tasks in your list:
	1.[E][ ] competition (from: MONDAY, Jul 8 2024 to: TUESDAY, Jul 9 2024)
	2.[D][ ] homework (by: tomorrow)
	3.[E][ ] concert (from: SUNDAY, Jun 9 2024 to: SATURDAY, Jun 15 2024)
	--------------------------------------------------
```

You should see something like on your screen after entering the example commands:

```
find home
	--------------------------------------------------
	Here are the matching tasks in the list:
	1.[D][ ] homework (by: tomorrow)
	--------------------------------------------------
```
```
find co
	--------------------------------------------------
	Here are the matching tasks in the list:
	1.[E][ ] competition (from: MONDAY, Jul 8 2024 to: TUESDAY, Jul 9 2024)
	2.[E][ ] concert (from: SUNDAY, Jun 9 2024 to: SATURDAY, Jun 15 2024)
	--------------------------------------------------
```

### `check` - Check which tasks will occur on a specific date

Note the date entered must of the format yyyy-mm-dd.

Format: `check <date in yyyy-mm-dd>`

Example of usage:

`check 2024-09-09`
`check 2024-07-07`

Expected outcome:

Let's say you have a task list that looks like this:

```
list
	--------------------------------------------------
	Here are the tasks in your list:
	1.[D][ ] quiz (by: THURSDAY, Oct 10 2024)
	2.[D][ ] assignment (by: MONDAY, Sep 9 2024)
	3.[E][ ] party (from: SUNDAY, Sep 8 2024 to: TUESDAY, Sep 10 2024)
	4.[E][ ] camping trip (from: SUNDAY, Jul 7 2024 to: TUESDAY, Jul 9 2024)
	--------------------------------------------------
```

You should see something like on your screen after entering the example commands:

```
check 2024-09-09
	--------------------------------------------------
	Here are the tasks occurring on Sep 9 2024:
	1.[D][ ] assignment (by: MONDAY, Sep 9 2024)
	2.[E][ ] party (from: SUNDAY, Sep 8 2024 to: TUESDAY, Sep 10 2024)
	--------------------------------------------------
```
```
check 2024-07-07
	--------------------------------------------------
	Here are the tasks occurring on Jul 7 2024:
	1.[E][ ] camping trip (from: SUNDAY, Jul 7 2024 to: TUESDAY, Jul 9 2024)
	--------------------------------------------------
```

### `bye` - Exit chatbot

Format: `bye`

Example of usage:

`bye`

Expected outcome:

You should see something like on your screen after entering the example commands:

```
bye
	--------------------------------------------------
	Bye! Hope to see you again soon!
	--------------------------------------------------
```