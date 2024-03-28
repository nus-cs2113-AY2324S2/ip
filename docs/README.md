# User Guide

Welcome to AVRY, your personal task management assistant! AVRY helps you keep track of your todos, deadlines, and events so you can stay organized and focused.


## Features 

### Add Tasks

You can add three types of tasks: Todos, Deadlines, and Events. Each task type is designed to suit different needs.

### Todos

Tasks without any specific deadline.

### Deadlines

Tasks that need to be completed by a certain date and time.

### Events

Activities that occur at a specific time or within a specific time frame.

### List Tasks

View all your tasks in a neatly formatted list with their completion status, descriptions, and relevant date/time information.

### Marking Tasks as Done

Mark tasks as completed to track your progress.

### Unmarking Tasks

In case you marked a task as done by mistake, you can unmark it.

### Deleting Tasks

Remove tasks that are no longer relevant or were added by mistake.

### Finding Tasks

Quickly find tasks by searching for a keyword in their descriptions.

## Usage

### `todo` - Add a todo task
- **Command**: `todo <taskDescription>`
- **Description**: Adds a todo task to your list.
- **Example**: `todo Read a book`
- **Expected Outcome**:
    ```
    ____________________________________________________________
    Got it. AVRY added this task:
       [T][ ] Read a book
    Now you have 1 task in the list.
    ____________________________________________________________
    
### `deadline` - Add a deadline task
- **Command**: `deadline <taskDescription> /by <DD-MM-YYYY HHmm>`
- **Description**: Adds a task with a specified deadline to your list.
- **Example**: `deadline Return library books /by 06-10-2023 1700`
- **Expected Outcome**:
    ```
    ____________________________________________________________
    Got it. AVRY added this task:
       [D][ ] Return library books (by: Oct 5 2023, 5:00 PM)
    Now you have 2 tasks in the list.
    ____________________________________________________________
    ```

### `event` - Add an event task
- **Command**: `event <taskDescription> /from <DD-MM-YYYY HHmm> /to <DD-MM-YYYY HHmm>`
- **Description**: Adds an event task with start and end times to your list.
- **Example**: `event Team meeting /from 06-10-2023 0900 /to 06-10-2023 1100`
- **Expected Outcome**:
    ```
    ____________________________________________________________
    Got it. AVRY added this task:
       [E][ ] Team meeting (From: Oct 6 2023, 9:00 AM To: Oct 6 2023, 11:00 AM)
    Now you have 3 tasks in the list.
    ____________________________________________________________
    ```
### `list` - Listing all tasks
- **Command**: `list`
- **Description**: Displays all tasks in your list.
- **Example**: `list`
- **Expected Outcome**:
    ```
    ____________________________________________________________
    Here are the tasks in your list:
    1.[T][ ] Read a book
    2.[D][ ] Return library books (by: Oct 5 2023, 5:00 PM)
    3.[E][ ] Team meeting (From: Oct 6 2023, 9:00 AM To: Oct 6 2023, 11:00 AM)
    ____________________________________________________________
    ```

### `mark` -  Marking a task as Done
- **Command**: `mark <taskNumber>`
- **Description**: Marks the specified task as done.
- **Example**: `mark 1`
- **Expected Outcome**:
    ```
    ____________________________________________________________
    Nice! I've marked this task as done:
       [T][X] Read a book
    ____________________________________________________________
    ```

### `delete` -  Deleting a Task
- **Command**: `delete <taskNumber>`
- **Description**: Deletes the specified task from your list.
- **Example**: `delete 3`
- **Expected Outcome**:
    ```
    ____________________________________________________________
    orh ok. Make sure hor, I deleted this:
       [E][ ] Team meeting (From: Oct 6 2023, 9:00 AM To: Oct 6 2023, 11:00 AM)
    Now you have 2 tasks in the list.
    ____________________________________________________________
    ```
  
### `find` - Find a task/tasks that contain a specific keyword
- **Command**: `find <keyword>`
- **Description**: Searches for tasks that contain the specified keyword in their


### Refer to the instructions provided by AVRY upon startup for more details on command syntax and additional features.
