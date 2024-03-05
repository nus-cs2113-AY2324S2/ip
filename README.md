# Massimo Boi User Guide
Welcome to Massimo Boi! This guide will help you effectively manage your tasks using various commands. Please follow the instructions below:

## Adding a Task
There are 3 main types of tasks you can add. To add a task, use the following command format based on the type of task:
### Todo Task:
Adds a task with only description.

format: **`todo` DESCRIPTION**

- Replace DESCRIPTION with a word or sentence describing your task.

### Deadline Task:
Adds a task with a specific deadline date.

format: **`deadline` DESCRIPTION /by DUE_DATE**

- Replace DESCRIPTION with a word or sentence describing your task.
- For deadline tasks, include **`/by`** at the end of the description followed by the due date.

### Event Task:
Adds a task with a specific from date and to date.

format: **`event` DESCRIPTION /from START_DATE /to END_DATE**

- Replace DESCRIPTION with a word or sentence describing your task.
- For event tasks, include **`/from`** followed by the start date and **`/to`** followed by the end date after the description.


Examples for adding tasks:
- **`todo` Read a book**
- **`deadline` Complete project `/by` 2024-03-15**
- **`event` Team meeting `/from` 2024-03-10 `/to` 2024-03-12**

## Viewing Task List: **`list`**
Shows the list of tasks:

format: **`list`**

## Marking Tasks: **`mark`**
Marks the specified task as done.

format: **`mark` INDEX**

- Replace INDEX with the corresponding task index in the list.
- Ensure that the index number exists in the list.

## Unmarking Tasks: **`unmark`**
Unmarks the specified task (not done).

format: **`unmark` INDEX**

- Replace INDEX with the corresponding task index in the list.
- Ensure that the index number exists in the list.

## Deleting a Task: **`delete`**
Deletes a specified task.

format: **`delete` INDEX**

- Replace INDEX with the corresponding task index in the list.
- Ensure that the index number exists in the list.

## Finding a Task: **`find`**
Finds all tasks containing the keyword(s) searched for.

format: **`find` KEYWORDS**

- Replace KEYWORDS with the words you would like to search for.

## Saving:
All task details entered are automatically saved when the program terminates. Nothing needs to be done by user.

- To see your saved data, use the `list` command when you first start the app.

## Ending chat: **`bye`**
Ends the chat with Massimo Boi and terminates the program.

format: **`bye`**

- All task data in task list at time of termination will be saved and available for use next time the program is run.