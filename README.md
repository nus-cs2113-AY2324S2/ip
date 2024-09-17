# Jason : Personal Task Manager User Guide

```
   J     A     SSSS    OOO   N   N 
   J    A A    S      O   O  NN  N 
   J   A A A    SSS   O   O  N N N 
J  J  A     A      S  O   O  N  NN 
 JJJ A       A  SSSS   OOO   N   N 
Eyy wassup I'm Jason
What can I do for you?
```
Welcome to Jason, your dedicated task manager chatbot designed to streamline your to-do list and manage deadlines and events with ease. This guide will navigate you through Jason's capabilities and how you can interact with it to manage your tasks efficiently.

## Features

- **Task Management:** Seamlessly add, delete, mark tasks as done or not done, and list all tasks in an intutive manner.
- **Supports Multiple Task Types:** Jason can handle various types of tasks, such as To-dos, Deadlines, and Events, catering to all your scheduling needs.
- **Date and Time formatting:** For deadlines and events, Jason can understand and process different date and time formats, making it easier for you to set reminders.
- **Search by Keyword:** Quickly locate specific tasks by searching for keywords.
- **Data Storage:** All input tasks are stored locally, ensuring you can pick up where you left off.

## Commands

Below are the commands for Jason:

### Adding Tasks

- **Todo Task:** `todo [description]`  
  Add a simple task without date/time requirements.
  
- **Deadline Task:** `deadline [description] /by [YYYY-MM-DD]`  
  Add a task with a specific due date.
  
- **Event Task:** `event [description] /from [MM-DD HH:MM] /to [MM-DD HH:MM] `  
  Schedule an event with a start and endtime.

### Task Management

- **List Tasks:** `list`  
  Displays all your tasks.
  
- **Mark Task as Done:** `mark [task number]`  
  Marks a specific task as completed.
  
- **Unmark Task:** `unmark [task number]`  
  Reverts a task to not done.
  
- **Delete Task:** `delete [task number]`  
  Removes a task from your list.
  
- **Find Task:** `find [keyword]`  
  Lists tasks containing the specified keyword.

### Exiting

- **Exit:** `bye`  
  Saves all input tasks and terminates the program .

## Examples

Here's an example of how you can use Jason:

- Add a todo task: `todo Finish the assignment`
- Set a deadline:  `deadline Submit assignment /by 2024-12-20`
- Schedule an event: `event Project meeting /from 12-15 09:00 /to 12-17 09:00`
- Mark a task as done:  `mark 2`
- Mark task as not done: `unmark 1`
- Remove task from list: `delete 4`
- Search for a task: `find CS2113`

## Date and Time Formats

When adding deadlines or events, please follow these formats for date/time:

- Deadlines: `YYYY-MM-DD`
- Events: `MM-DD HH:MM`

## Some Useful Tips

- Be precise with task descriptions for easy retrieval.
- Ensure deadline and event tasks always follow the exact date/time format. 
- Use the `find` command to quickly locate tasks.
- Remember to terminate Jason with the `bye` command to save tasks before shutting down.

Thank you for choosing Jason as your task management assistant!
