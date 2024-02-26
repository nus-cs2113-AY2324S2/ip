# Natsu User Guide

Welcome to **Natsu**! This tool helps you keep track of your tasks, deadlines, and events, making your daily task
management more efficient and straightforward.

## Features

### Add Task

You can add three types of tasks: Todos, Deadlines, and Events. Each task type helps categorize your tasks for better
organization.

### List Tasks

Displays all your tasks, including Todos, Deadlines, and Events, with their status (completed or pending).

### Mark Task as Done

Mark any task as done based on its list number. This feature helps you keep track of your task completion.

### Unmark Task as Undone

Unmark any task as undone based on its list number. This feature helps you keep track of your task completion.

### Delete Task

Remove tasks that are no longer relevant or were added by mistake.

### Find Task

Search for tasks by keywords to quickly find specific tasks in your list.

### Save Data

All your tasks are saved automatically and loaded when you start the program again.

## Getting Started

### Prerequisites

- Java 11 or later installed on your computer.

### Installation

1. Download the latest release of Natsu from the [releases page](
2. Extract the downloaded zip file to a folder on your computer.
3. Open a terminal or command prompt and navigate to the folder where you extracted the zip file.
4. Run the following command to start Natsu:

```bash
java -jar natsu.jar
```

## Usage

### `todo` - Add a Todo Task

Adds a task without any date/time attached to it.

**Example of usage:**

`todo read book`

**Expected outcome:**

A new Todo task "read book" is added to your task list.

```plaintext
    ----------------------------------------------------------------
     Got it. I've added this task:
       [T][ ] read book
     Now you have 1 tasks in the list.
    ----------------------------------------------------------------
```

### `deadline` - Add a Deadline Task

Adds a task with a specific due date.

**Example of usage:**

`deadline return book /by 01-01-2023 1300`

**Expected outcome:**

A new Deadline task "return book" with the due date "Jan 1 2023 1:00pm" is added to your task list.

```plaintext
    ----------------------------------------------------------------
     Got it. I've added this task:
       [D][ ] return book (by: Jan 1 2023 )
     Now you have 2 tasks in the list.
    ----------------------------------------------------------------
```

### `event` - Add an Event Task

Adds a task with a specific date and time.

**Example of usage:**

`event project meeting /from Thursday 5pm /to Thursday 7pm`

**Expected outcome:**

A new Event task "project meeting" with the date "Thursday 5pm to 7pm" is added to your task list.

```plaintext
    ----------------------------------------------------------------
     Got it. I've added this task:
       [E][ ] project meeting (from: Thursday 5pm to: Thursday 7pm)
     Now you have 3 tasks in the list.
    ----------------------------------------------------------------
```

### `list` - List all Tasks

Displays all your tasks, including Todos, Deadlines, and Events, with their status (completed or pending).

**Example of usage:**

`list`

**Expected outcome:**

All your tasks are displayed in a list format.

```plaintext
    ----------------------------------------------------------------
     Here are the tasks in your list:
     1. [T][ ] read book
     2. [D][ ] return book (by: Jan 1 2023 )
     3. [E][ ] project meeting (from: Thursday 5pm to: Thursday 7pm)
    ----------------------------------------------------------------
```

### `mark` - Mark Task as Done

Marks a task as done based on its list number.

**Example of usage:**

`mark 1`

**Expected outcome:**

The first task in your list is marked as done.

```plaintext
    ----------------------------------------------------------------
     Nice! I've marked this task as done:
       [T][X] read book
    ----------------------------------------------------------------
```

### `unmark` - Unmark Task as Undone

Unmarks a task as undone based on its list number.

**Example of usage:**

`unmark 1`

**Expected outcome:**

The first task in your list is unmarked as undone.

```plaintext
    ----------------------------------------------------------------
     OK, I've marked this task as not done yet:
       [T][ ] read book
    ----------------------------------------------------------------
```

### `delete` - Delete Task

Removes a task from your list based on its list number.

**Example of usage:**

`delete 1`

**Expected outcome:**

The first task in your list is removed.

```plaintext
    ----------------------------------------------------------------
     Noted. I've removed this task:
       [T][ ] read book
     Now you have 2 tasks in the list.
    ----------------------------------------------------------------
```

### `find` - Find Task

Search for tasks by keywords to quickly find specific tasks in your list.

**Example of usage:**

`find book`

**Expected outcome:**

All tasks with the keyword "book" are displayed.

```plaintext
    ----------------------------------------------------------------
     Here are the matching tasks in your list:
     1. [D][ ] return book (by: Jan 1 2023 )
    ----------------------------------------------------------------
```

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
