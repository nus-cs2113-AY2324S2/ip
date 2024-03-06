# SalmonSan! Task Management CLI App

*An individual project done under NUS course CS2113: Software Engineering and Object-Oriented Programming*

> SalmonSan is a simple command line based interface task management app that allows user to set todos, events, and deadline

## Features
### Adding a todo task: `todo`
Adding a todo task to your list

Format: `todo TASK`

**Examples:**

Input: `todo buy eggs for birthday cake`

Output:
```
Hai ~ your task have been added successfully
    [T][ ] buy eggs for birthday cake 
You have 1 tasks remaining
--------------------------------
How can I assist you today?
```

### Adding an event task: `event`
Adding an event task to your list

Format: `event TASK /from START_TIME /to END_TIME`

**Examples:**

Input: `event do CS2113 tP /from 1 January 2024 /to 5 May 2024`

Output:
```
Hai ~ your task have been added successfully
    [E][ ] do CS2113 tP (from: 1 January 2024 to: 5 May 2024)
You have 2 tasks remaining
--------------------------------
How can I assist you today?
```

### Adding a deadline task: `deadline`
Adding a deadline task to your list

Format: `deadline TASK /by END_TIME`

**Examples:**

Input: 
- `deadline submit critical reflection /by this weekend`
- `deadline submit critical reflection /by 2024-05-22`

> [!TIP]
> END_TIME input in LocalDate format of yyyy-MM-dd is supported


Output:
```
Hai ~ your task have been added successfully
    [D][ ] submit critical reflection (by: 22/05/2024)
You have 3 tasks remaining
--------------------------------
How can I assist you today?
```

### Listing down all task: `list`
list down all the tasks in your list

Format: `list`

Output:
```
Osu! Your tasks are as follows:
  1.[T][ ] buy eggs for birthday cake 
  2.[E][ ] do CS2113 tP (from: 1 January 2024 to: 5 May 2024)
  3.[D][ ] submit critical reflection (by: 22/05/2024)
--------------------------------
How can I assist you today?
```

### Marking a task as done: `mark`
Mark a task as done

Format: `mark TASK_INDEX`

**Examples:**

Input: `mark 1`

Output: 
```
Nice! I've marked this task as done:
    [T][X] buy eggs for birthday cake 
--------------------------------
How can I assist you today?
```

### Marking a task as not done: `unmark`
Mark a task as not done

Format: `unmark TASK_INDEX`

**Examples:**

Input: `unmark 1`

Output:
```
As you wished, your task is now undone:
    [T][ ] buy eggs for birthday cake 
--------------------------------
How can I assist you today?
```

### Deleting a task: `delete`
Delete a task from your list 

Format: `delete TASK_INDEX`

**Examples:**

Input: `delete 3`

Output:
```
Deleted! The following task has been removed!
    [D][ ] submit critical reflection (by: 2024-05-22)
You have 2 tasks remaining
--------------------------------
How can I assist you today?
```

### Finding keywords in task: `find`
Find tasks which contain specified keyword

Format: `find KEYWORD`

**Example:**

Input: `find book`

Output:
```

```

### Quitting the program: `bye`
Quit the program

Format: `bye`

Output:
```
Sankyuuu! BYE (^ _^ )
```
