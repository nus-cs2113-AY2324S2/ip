# Pythia

       █████████████   
      ██           ██ 
      ██   █████   ██ 
      ██   █████   ██ 
      ██           ██ 
       █████████████ 
    ██                ██
    ████████████████████
This is Pythia an assistant that makes use of the CLI keep track of all your day to day tasks

# User Guide

## Features

### `todo`

Add a todo task (task with no due date)

Format: `todo [TASK]`

Example of usage:

`todo placeholder1`

Expected outcome:
```
added: [T][ ]  placeholder1
----------------------------------------------------------
```

### `deadline`

Add a deadline task (task with a due date)

Format: `deadline [TASK] /[DUEDATE]`

Example of usage:

`deadline placeholder2 /June 25th 6pm`

Expected outcome:
```
added: [D][ ] placeholder2 (June 25th 6pm)
----------------------------------------------------------
```

### `event`

Add an event task (task with a start and end time)

Format: `event [TASK] /[STARTTIME] /[ENDTIME]`

Example of usage:

`event placeholder3 /3pm /6pm`

Expected outcome:
```
added: [E][ ] placeholder3 (from: 3pm to: 6pm)
----------------------------------------------------------
```

### `list` 

Lists all tasks currently in the list

Format: `list`

Example of usage: 

`list`

Expected outcome:
```
1.[T][ ] placeholder1
2.[D][ ] placeholder2 (June 25th 6pm)
3.[E][ ] placeholder3 (from: 3pm to: 6pm)
----------------------------------------------------------
```

### `mark`

Marks the specified index in the list

Format: `mark [INDEX]`

Example of usage:

`mark 1`

Expected outcome:
```
Marked  placeholder1
----------------------------------------------------------
list
1.[T][X]  placeholder1
2.[D][ ] placeholder2 (June 25th 6pm)
3.[E][ ] placeholder3 (from: 3pm to: 6pm)
----------------------------------------------------------
```

### `delete`

Delete a specific index in the task list

Format: `delete [INDEX]`

Example of usage:

`delete 2`

Expected outcome:
```
Deleted [D][ ] placeholder2 (June 25th 6pm)
----------------------------------------------------------
list
1.[T][X]  placeholder1
2.[E][ ] placeholder3 (from: 3pm to: 6pm)
----------------------------------------------------------
```

### `unmark`

Unmarks the specified index in the list

Format: `unmark [INDEX]`

Example of usage:

`unmark 1`

Expected outcome:
```
Unmarked  placeholder1
----------------------------------------------------------
list
1.[T][ ]  placeholder1
2.[E][ ] placeholder3 (from: 3pm to: 6pm)
----------------------------------------------------------
```

### `find`

Finds and prints all tasks that contain the specified keyword (only applies to task description)

Format: `find [KEYWORD]`

Example of usage:

`find 3`

Expected outcome:
```
[E][ ] placeholder3 (from: 3pm to: 6pm)
----------------------------------------------------------
```

