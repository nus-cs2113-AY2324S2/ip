# Suv User Guide
Welcome to **Suv!** This tool is designed to streamline your task management, helping you stay on top of your tasks, deadlines, and events effortlessly.

## Features

### Add Task
You can add three types of tasks: 
1. To-dos 
2. Deadlines 
3. Events
   
Each task type helps categorize your tasks for better organization.

### List Tasks
Displays all your tasks, including To-dos, Deadlines, and Events, along with their status (completed or pending) and additional information if there's any.

### Mark Task as done
Mark any task as completed based on its list number. This feature helps you track your progress effectively.

### Mark Task as uncompleted
Unmark any completed task based on its list number. This feature helps you manage your completed tasks easily.

### Delete Task
Delete tasks that are no longer relevant or were added by mistake.

### Find Tasks
Search for tasks by keywords to quickly find specific tasks in your list.

### Save Data
All your tasks are automatically saved and loaded when you start the program again.

## Getting Started

### Prerequisites
Make sure you have Java 11 or later installed on your computer.
### Installation

1. Download the latest release of Suv from the releases page.
2. Extract the downloaded zip file to a folder on your computer.
3. Navigate to the folder where you extracted the zip  and open a terminal.
4. Run the following command to start Suv application:

```bash
java -jar suv.jar
```

If the application started successfully, it will display the following welcome message.

```plaintext
----------------------------------------------------------------
 Hello! I'm Suv
 What can I do for you?
----------------------------------------------------------------
```

### Usage

### `todo` - Add a To-do Task
Adds a simple task with no additional information.

**Example of usage:**

`todo Buy bread`

**Expected outcome:**

A new Todo task "read book" is added to your task list.

```plaintext
----------------------------------------------------------------
 Got it. I've added this task:
  [T][ ] Buy bread
 Now you have 1 tasks in the list.
----------------------------------------------------------------
```
----------------------------------------------------------------
### `deadline` - Add a Deadline Task

Adds a task with a specific deadline to complete it by.

**Example of usage:**

`deadline Submit user guide /by 03-08-2024 1159`

**Expected outcome:**

A new Deadline task "Submit user guide" with the deadline "Mar 8 2024 11:59pm" is added to your task list.

```plaintext
----------------------------------------------------------------
 Got it. I've added this task:
  [D][ ] Submit user guide (by: Mar 08 2024 11:59 AM)
 Now you have 2 tasks in the list.
----------------------------------------------------------------
```
----------------------------------------------------------------
### `event` - Add an Event Task

Adds a task with a specific date and time.

**Example of usage:**

`event Football Practice /from Friday 4pm /to 6pm`

**Expected outcome:**

A new Event task "Football Practice" with the date "Friday 4pm to 6pm" is added to your task list.

```plaintext
----------------------------------------------------------------
 Got it. I've added this task:
  [E][ ] Football Practice (from: Friday 4pm to: 6pm)
 Now you have 3 tasks in the list.
----------------------------------------------------------------
```
----------------------------------------------------------------
### `list` - List all Tasks
Displays all your tasks, including To-dos, Deadlines, and Events, along with their status (completed or pending).

**Example of usage:**

```list```

**Expected outcome:**

All your tasks are displayed in a list format.
```plaintext
----------------------------------------------------------------
 Here are the tasks in your list:
 1.[T][ ] Buy bread
 2.[D][ ] Submit user guide (by: Mar 08 2024 11:59 AM)
 3.[E][ ] Football Practice (from: Friday 4pm to: 6pm)
----------------------------------------------------------------
```
----------------------------------------------------------------
### `mark` - Mark Task as Completed
Marks a task as completed based on its list number.

**Example of usage:**

```mark 1```

**Expected outcome:**

The first task in your list is marked as completed.

```plaintext
----------------------------------------------------------------
 Nice! I've marked this task as done:
 [T][X] Buy bread
----------------------------------------------------------------
```
----------------------------------------------------------------
### `unmark` - Mark completed task as not done
Unmarks a completed task based on its list number.

**Example of usage:**

```unmark 1```

**Expected outcome:**

The first completed task in your list is restored as pending.

```plaintext
----------------------------------------------------------------
 OK, I've marked this task as not done yet:
 [T][ ] Buy bread
----------------------------------------------------------------
```
----------------------------------------------------------------
### `delete` - Remove Task
Removes a task from your list based on its list number.

**Example of usage:**

```remove 2```

**Expected outcome:**

The first task in your list is removed.

```plaintext
----------------------------------------------------------------
 Noted. I've removed this task:
 [D][ ] Submit user guide (by: Aug 03 2024 11:59 AM)
 Now you have 2 tasks in the list.
----------------------------------------------------------------
```
----------------------------------------------------------------
### `find` - Find Tasks
Search for tasks by keywords to quickly find specific tasks in your list.

**Example of usage:**

```find bread```

**Expected outcome:**

All tasks containing the keyword "book" are displayed.

```plaintext
----------------------------------------------------------------
 Here are the matching tasks in your list:
 1.[T][ ] Buy bread
----------------------------------------------------------------
```
----------------------------------------------------------------
### `bye` - Exit the Program

Exits the program.

**Example of usage:**

`bye`

**Expected outcome:**

The program exits.

```plaintext
----------------------------------------------------------------
 Bye. Hope to see you again soon!
----------------------------------------------------------------
```