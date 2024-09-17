# G.One

G.One is a todo chatbot that allows the user to
add, delete, mark, find and save their tasks which could be
todo tasks, deadlines or events. 

The instructions for G.one's usage are below:

## Usage

## Commands

### 1. find
- **Description:** Finds tasks containing a specific keyword.
- **Usage:** `find <keyword>`
- **Example:** `find meeting`
- **Expected Output:** Lists tasks containing the keyword "meeting".

### 2. todo
- **Description:** Adds a new todo task.
- **Usage:** `todo <description>`
- **Example:** `todo Buy groceries`
- **Expected Output:** Adds a new todo task with the provided description.

### 3. deadline
- **Description:** Adds a new deadline task.
- **Usage:** `deadline <description> /by <deadline>`
- **Example:** `deadline Submit report /by 2024-03-15`
- **Expected Output:** Adds a new deadline task with the provided description and deadline.

### 4. event
- **Description:** Adds a new event task.
- **Usage:** `event <description> /from <start time> /to <end time>`
- **Example:** `event Team meeting /from 10:00 /to 11:00`
- **Expected Output:** Adds a new event task with the provided description and time range.

### 5. mark
- **Description:** Marks a task as done.
- **Usage:** `mark <task number>`
- **Example:** `mark 1`
- **Expected Output:** Marks the task with the specified number as done.

### 6. unmark
- **Description:** Marks a task as not done.
- **Usage:** `unmark <task number>`
- **Example:** `unmark 1`
- **Expected Output:** Marks the task with the specified number as not done.

### 7. delete
- **Description:** Deletes a task.
- **Usage:** `delete <task number>`
- **Example:** `delete 1`
- **Expected Output:** Deletes the task with the specified number.

### 8. list
- **Description:** Displays the list of tasks.
- **Usage:** `list`
- **Example:** `list`
- **Expected Output:** Displays the list of tasks along with their status.


#### *The tasks are automatically saved and are automatically loaded back everytime the program is ran*