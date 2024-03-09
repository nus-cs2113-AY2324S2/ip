# User Guide

## Features 


### Adding a Todo: `Todo`

Add a todo in the task list in your chatbot.

Format: `todo CONTENT`

Example:

- `todo return book` Add a todo with content return book in the chatBot task list.

### Adding a Event: `event`

Add a event in the task list in your chatbot.

Format: `event CONTENT \from d/M/yyyy[ HHmm] \to d/M/yyyy[ HHmm]`

Example:

- `CS2113 meeting \from 8/3/2024 1800 \to 8/3/2024 1830` Add a event with content CS2113 meeting from March 8th 2024 18:00 to March 8th 2024 18:30 in chatbot task list.
- `orientation \from 8/3/2024 \to 10/3/2024 1600`  Add a event with content orientation from March 8th 2024 to March 10th 2024 16:00 in chatbot task list.

### Adding a Deadline: `deadline`

Add a deadline in the task list in your chatbot.

Format: `dealine CONTENT \by d/M/yyyy[ HHmm]`

Example:

- `dealine buy book \by 8/3/2024 1800 ` Add a deadline with content buy book by March 8th 2024 18:00 in chatbot task list.
- `dealine buy book \by 8/3/202 `  Add a deadline with content buy book by March 8th 2024 in chatbot task list.
### Finding keywork: `find`

find task contains a keyword in the task list.

Format: `find KEYWORD`

- The search is case-sensitive. e.g `book` will not match `Book`
- Only the content of the task is searched.
- Only full words will be matched e.g. `boo` will not match `book`

Example:

- `find book` finds task contents contains "book" keywords. Returns deadline buy book and todo return book

### Listing all tasks: `list`

Shows a list of all tasks in the task List.

Format: `list`

-Task type icons: 

E: event T: todo D: deadline

Expected outcome:

It will show the tasks in the list including task type, complete status, content (and time).

```
____________________________________________________________
Here are the tasks in your list:
1. [E][ ] buy book (from: 12月 12 2024, 6:00 下午 to: 4月 02 2025)
2. [D][X] return book (by: 8月 03 2024)
____________________________________________________________

```

### Marking a task as done: `mark`

Mark the task at index i in the task list as done

Format: `mark INDEX`

- Only can mark unmarked task as done.
- INDEX must be a positive integer .

Example:

- `list` followed by `mark 1` marks the first task in the task list as done

Expected outcome:

If successfully mark the task as done it will show like this:

```
____________________________________________________________
Nice! I've marked this task as done:
[E][X] buy book (from: 12月 12 2024, 6:00 下午 to: 4月 02 2025)
____________________________________________________________
```
If unsuccessfully mark the task it will show like this:
```agsl
____________________________________________________________
Sorry, this task have been marked as completed. You cannot mark this task again.
____________________________________________________________
```

### Unmaking a task as done: `unmark`

Unmark the task as done at index i in the task list.

Format: `unmark INDEX`

- Only can unmark marked task as done.
- INDEX must be a positive integer 1,2,3.
- The index refers to the index number shown in the displayed task list.

Example:

- `list` followed by `unmark 1` marks the first task in the task list as done

Expected outcome:

If successfully unmark the task as done it will show like this:

```
____________________________________________________________
OK, I've marked this task as not done yet:
[D][ ] return book (by: 8月 03 2024)
____________________________________________________________
```
If unsuccessfully unmark the task it will show like this:
```agsl
____________________________________________________________
Sorry, this task is not completed yet. You cannot unmark as done.
____________________________________________________________
```

### Deleting a task: `delete`

Deletes the specified task from the task list.

Format: `delete INDEX`

- INDEX must be a positive integer 1,2,3.
- Deletes the task at the specified INDEX.
- The index refers to the index number shown in the displayed task list.

Example:

- `list` followed by `delete 1` delete the first task in the task list as done

Expected outcome:

If successfully delete the task at INDEX it will show like this:

```
____________________________________________________________
Noted. I've removed this task:
  [E][ ] buy book (from: 12月 12 2024, 6:00 下午 to: 4月 02 2025)
Now you have 1 tasks in the list.
____________________________________________________________
```
If unsuccessfully unmark the task it will show like this:
```agsl
____________________________________________________________
Sorry, this task is not completed yet. You cannot unmark as done.
____________________________________________________________
```

### Exiting the program: `bye`

Exits the program.

Format: `bye`

### Saving the data

TaskList data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TaskList data are saved automatically as a txt file `"./data/Venti.txt"`. Advanced users are welcome to update data directly by editing that data file.


