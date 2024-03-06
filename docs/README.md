# User Guide

## Features

### Marking Tasks

Allows the user to mark tasks as done or undone.

### Adding Todo Tasks

Enables the user to add a todo task to the task list.

### Adding Deadline Tasks

Facilitates the addition of a deadline task to the task list.

### Adding Event Tasks

Allows the user to add an event task to the task list.

### Deleting Tasks

Allows the user to delete a task from the task list.

### Finding Tasks

Enables the user to search for tasks containing a specific keyword.

### Help Command

Enables the user to view all the commands at ones disposal.

### List Tasks

Allows the user to view the list of all tasks saved.

## Usage

### `todo <task_description>` - Add a todo task

Adds a todo task with the specified description to the task list.

Example of usage:

`todo Buy groceries`

Expected outcome:

A todo task with the description "Buy groceries" will be added to the task list.

```
Task noted. A duty without a deadline? Dangerous.
What now? Forge ahead or risk oblivion?
```

### `deadline <description> /by <deadline>` - Add a deadline task

Adds a deadline task with the specified description and deadline to the task list.

Example of usage:

`deadline Submit report /by 2024-03-31`

Expected outcome:

A deadline task with the description "Submit report" and the deadline "2024-03-31" will be added to the task list.

```
Deadline acknowledged. Time ticks away, mortal.
What next? Embrace purpose or succumb to chaos?
```

### `event <description> /from <start_date> /to <end_date>` - Add an event task

Adds an event task with the specified description, start date, and end date to the task list.

Example of usage:

`event Project meeting /from 2024-03-05 /to 2024-03-07`

Expected outcome:

An event task with the description "Project meeting", start date "2024-03-05", and end date "2024-03-07" will be added to the task list.

```
Event recorded. Destiny's hourglass turns.
What now? Seize control or be swept by its sands?
```

### `mark <task_number>` - Mark a task as done

Marks the specified task as done.

Example of usage:

`mark 2`

Expected outcome:

The task with index 2 will be marked as done.

```
Task vanquished. Another notch on the blade of progress.
What next, mortal?
         [T][X] borrow book
```

### `unmark <task_number>` - Mark a task as undone

Marks the specified task as undone.

Example of usage:

`unmark 3`

Expected outcome:

The task with index 3 will be marked as undone.

```
Task restored from the depths of completion.
A twist of fate, mortal. What now?
Reclaim victory or face the abyss once more.
         [T][ ] borrow book
```

### `delete <task_number>` - Delete a task

Deletes the specified task from the task list.

Example of usage:

`delete 1`

Expected outcome:

The task with index 1 will be deleted from the task list.

```
Task erased. Its existence now a whisper in the winds of fate.
What's your next decree?
         [D][X] return book (by: Sunday)
8 tasks linger, shadows yet unvanquished. How will you face them?
```

### `find <keyword>` - Find tasks

Searches for tasks containing the specified keyword in their descriptions.

Example of usage:

`find project`

Expected outcome:

Displays a list of tasks containing the keyword "meeting".

```
Behold, the deeds that align with your purpose.
     1. [E][ ] project meeting (from: Mon 2pm to: 4pm)
```

### `help` - Display Commands

Displays all valid commands available to user.

Example of usage:

`help`

Expected outcome:

Displays a list of Commands in a neat formatted order.

```
╔════════════════════════════════════════╗
║           Available Commands           ║
╠════════════════════════════════════════╣
║ 1. list            - Display all tasks ║
║ 2. mark <num>      - Mark task as done ║
║ 3. unmark <num>    - Unmark task       ║
║ 4. deadline <desc> - Add a deadline    ║
║ 5. todo <desc>     - Add a todo        ║
║ 6. event <desc>    - Add an event      ║
║ 7. delete <num>    - Delete a task     ║
║ 8. find <keyword>  - Find tasks        ║
║ 9. bye             - Exit program      ║
╚════════════════════════════════════════╝
```

### `list` - Display Tasks

Displays all task the user has stored.

Example of usage:

`help`

Expected outcome:

Displays a list of Tasks which also tell us if they are completed or not.

```
----------------------------------------------------------------
    Your list of Tasks
     1. [T][ ] helllo
     2. [E][X] car wash (from: saturday night 6pm to: 9pm)
     3. [T][X] work
     4. [T][ ] project 1
     5. [T][X] project 2
     6. [D][ ] assignment (by: tonight)
----------------------------------------------------------------
```