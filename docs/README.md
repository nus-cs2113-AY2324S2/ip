# Kratos Task Management Application User Guide

### Greeting Message
```
			──────────────────────────────
			───────────────────────▓▓▓▓───
			──────────────────────▓▓▓▓▓▓──
			─────────────────────▓▓▓▓▓▓▓▓─
			────────────────────▓▓▓▓▓▓▓▓──
			───────────────────▓▓▓▓▓▓▓▓───
			──────────────────▓▓▓▓▓▓▓▓────
			─────────────────▓▓▓▓▓▓▓▓─────
			─────────█──────▓▓▓▓▓▓▓▓──────
			────────██─────▓▓▓▓▓▓▓▓───────
			───────██──────▓▓▓▓▓▓▓▓▓──────
			─▀██▄─██───────▓▓▓▓▓▓▓▓██──▄█─
			─█████████▄─▄───▓▄▓▄████████──
			──█▀▀▀███████────███████▀▀▀██─
			─███▄▄██▄███▀─────███▄██▄▄███─
			─█─██████▀█────────▓████████▀─
			────██▀───▀─█────█──▓▓▓▓▓▓▓▓──
			────▄█▀──────█──█────▓▓▓▓▓▓▓▓─
			───▀█────────█──█─────▓▓▓▓▓▓▓─
			───▄█▀────▄▄█▀──▀█▄▄───▓▓▓▓▓▓─
			───█▄────█──█────█──█──▓▓▓▓▓▓─
			───█─────▀──────────▀──▓▓▓▓▓▓─
			────▀─────▀█──────█▀────▓▓▓───
			────────────▀▄▄▄▄▀──────▓▓────
			────────────────────────▓─────
			────────▄█████████████▄───────
			───────██▀▀▀▀▀▀▀▀▀▀▀▀▀██──────
			───────▀───────────────▀──────
			───────────█████████──────────
			────────────███████───────────
			──────────────███─────────────
			──────██▄█─▄─█████─▄─█▄██─────
			───────█████████████████──────
			────────███████████████───────
			─────────█████████████────────
			──────────███████████─────────
			──────────▀─███████─▀─────────
			────────────▀─███─▀───────────
			───────────────▀──────────────

----------------------------------------------------------------
Kratos commends you for your presence. Prepare for battle.
Enter your commands with purpose.
----------------------------------------------------------------
```

## Features

### Help Command
- Enables the user to view all the commands at ones disposal, providing a quick reference for available commands.

### Adding Tasks
- **Todo Tasks**: Enables the user to add a todo task to the task list, a simple task without a deadline.
- **Deadline Tasks**: Facilitates the addition of a deadline task to the task list, a task with a specific end date.
- **Event Tasks**: Allows the user to add an event task to the task list, a task that spans a specific period.

### Marking Tasks
- Allows the user to mark tasks as done or undone, enhancing task management with a simple toggle.

### Deleting Tasks
- Allows the user to delete a task from the task list, providing the ability to clean up completed or irrelevant tasks.

### Finding Tasks
- Enables the user to search for tasks containing a specific keyword, 
- making it easy to locate specific tasks in the list.

### List Tasks
- Allows the user to view the list of all tasks saved, giving an overview of the current task list.

### Exit Command
- Allows the user to exit the program, while saving all the changes made during operation


## Usage

### Displaying Commands
```bash
help
```
- **Example**: `help`
- **Expected Outcome**: Displays a list of Commands in a neat formatted order.
```
// Example Output
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

### Adding a Todo Task
```bash
todo <task_description>
```
- **Example**: `todo Buy groceries`
- **Expected Outcome**: A todo task with the description "Buy groceries" will be added to the task list.
```
Task noted. A duty without a deadline? Dangerous.
What now? Forge ahead or risk oblivion?
     7. [T][ ] Buy groceries
```

### Adding a Deadline Task
```bash
deadline <description> /by <deadline>
```
- **Example**: `deadline Submit report /by 2024-03-31`
- **Expected Outcome**: A deadline task with the description "Submit report" and the deadline "2024-03-31" 
will be added to the task list.
```
// Example Output
Deadline acknowledged. Time ticks away, mortal.
What next? Embrace purpose or succumb to chaos?
     9. [D][ ] Submit report (by: 2024-03-31)
```

### Adding an Event Task
```bash
event <description> /from <start_date> /to <end_date>
```
- **Example**: `event Project meeting /from 2024-03-05 4pm /to 9pm`
- **Expected Outcome**: An event task with the description "Project meeting", start date "2024-03-05", 
and end date "2024-03-07" will be added to the task list.
```
// Example Output
Event recorded. Destiny's hourglass turns.
What now? Seize control or be swept by its sands?
     8. [E][ ] Project meeting (from: 2024-03-05 4pm to: 9pm)
```

### Marking a Task as Done
```bash
mark <task_number>
```
- **Example**: `mark 2`
- **Expected Outcome**: The task with index 2 will be marked as done.
```
// Example Output
Task vanquished. Another notch on the blade of progress.
        What next, mortal?
        [T][X] borrow book
```

### Marking a Task as Undone
```bash
unmark <task_number>
```
- **Example**: `unmark 3`
- **Expected Outcome**: The task with index 3 will be marked as undone.
```
// Example Output
Task restored from the depths of completion.
A twist of fate, mortal. What now?
Reclaim victory or face the abyss once more.
        [T][ ] borrow book
```

### Deleting a Task
```bash
delete <task_number>
```
- **Example**: `delete 1`
- **Expected Outcome**: The task with index 1 will be deleted from the task list.
```
// Example Output
Task erased. Its existence now a whisper in the winds of fate.
What's your next decree?
        [E][X] project meeting (from: Mon 2pm >>> to: 4pm)
10 tasks linger, shadows yet unvanquished. How will you face them?
```

### Finding Tasks
```bash
find <keyword>
```
- **Example**: `find project`
- **Expected Outcome**: Displays a list of tasks containing the keyword "meeting".
```
// Example Output
Behold, the deeds that align with your purpose.
     2. [E][X] project meeting 1 (from: Mon 2pm >>> to: 4pm)
     5. [E][ ] project meeting 2 (from: Mon 2pm >>> to: 4pm)
```


### Displaying Tasks
```bash
list
```
- **Example**: `list`
- **Expected Outcome**: Displays a list of Tasks which also tell us if they are completed or not.
```
// Example Output
----------------------------------------------------------------
Your list of Tasks
     1. [T][ ] helllo
     2. [E][X] car wash (from: saturday night 6pm >>> to: 9pm)
     3. [T][X] work
     4. [T][ ] project 1
     5. [T][X] project 2
     6. [D][ ] assignment (by: tonight)
----------------------------------------------------------------
```

### Exiting Application
```bash
bye
```
- **Example**: `bye`
- **Expected Outcome**: Displays a goodbye message to indicate to the user, that the application will be closed.
```
// Example Output
----------------------------------------------------------------
            Until the next battle, mortal.
May your tasks be conquered with the ferocity of a god.
----------------------------------------------------------------
```