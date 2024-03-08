# Lovie User Guide <3

Welcome to the Lovie User Guide! Lovie ♡〜٩( ˃▿˂ )۶〜♡ is a command-line interface (CLI) application that helps you manage your tasks
efficiently. This guide provides detailed instructions on using Lovie's features, including adding, marking, finding, 
and deleting tasks. 

## Table of Contents
1. [Getting Started](#getting-started)
2. [Features](#features)
    1. [Adding Tasks](#adding-tasks)
    2. [Listing All Tasks](#listing-all-tasks)
    3. [Marking Tasks as Done](#marking-tasks-as-done)
    4. [Unmarking Tasks](#unmarking-tasks)
    5. [Deleting Tasks](#deleting-tasks)
    6. [Finding Tasks](#finding-tasks)
3. [Commands Summary](#commands-summary)
4. [Exiting the Application](#exiting-the-application)
5. [OOP Concepts in Lovie](#oop-concepts-in-lovie)
6. [Contributing](#contributing)
7. [License](#license)

## Getting Started

To start using Lovie, ensure Java 11 is installed on your machine. Lovie specifically uses Java 11. 
Download Lovie and run it using the Java command:

```
java -jar Lovie.jar
```

Upon launching, Lovie will very sweetly greet you and help you if you need a list of possible commands.

## Features

### Listing All Possible Commands

Lovie will happily list all possible commands you can ask her. Simply use the `command` input to get a list of 
everything you can do. 
```
command
```


### Adding Tasks

Lovie supports three types of tasks: Todos, Deadlines, and Events. Each task type is represented by a class
(`Todo`, `Deadline`, `Event`) that extends the abstract `Task` class, demonstrating inheritance and polymorphism.

- **Todos**: Tasks without a date/time.
  ```
  todo <EVENT DESCRIPTION>
  ```
- **Deadlines**: Tasks with a due date.
  ```
  deadline <EVENT DESCRIPTION> /by <DEADLINE>
  ```
- **Events**: Tasks with a start and end time.
  ```
  event <EVENT DESCRIPTION> /from <START TIME> /to <END TIME>
  ```

### Listing All Tasks

To see all tasks, use the `list` command. This feature leverages the `TaskList` class,
which encapsulates a list of `Task` objects.

```
list
```

### Marking Tasks as Done

Mark a task as done with the `mark` command. This action modifies the task's state, and (until changed or deleted) will 
remain in your list as done.

```
mark <TASK NUMBER>
```

### Unmarking Tasks

If a task was mistakenly marked as done, you can unmark it. 

```
unmark <TASK NUMBER>
```

### Deleting Tasks

Remove a task from your task list with the `delete` command. 

```
delete <TASK NUMBER>
```

### Finding Tasks

Search for tasks containing a specific keyword. This uses the `find` method in `TaskList`, 
which filters tasks based on those that contain the keyword.

```
find <KEYWORD>
```

## Exiting the Application

To leave Lovie, type `bye`.  

```
bye
```

Lovie will automatically save your tasks for the next session. So when reloading back, don't
hesitate to ask Lovie to `list` your previously saved tasks as a reminder!

## Commands Summary

| Command                                                       | Description                                    |
|---------------------------------------------------------------|------------------------------------------------|
| `todo <EVENT DESCRIPTION>`                                    | Adds a todo task to your tasks list.           |
| `deadline <EVENT DESCRIPTION> /by <DEADLINE>`                 | Adds a deadline task to your tasks list.       |
| `event <EVENT DESCRIPTION> /from <START TIME> /to <END TIME>` | Adds an event task to your tasks list.         |
| `list`                                                        | Lists all tasks in your tasks list.            |
| `mark <TASK NUMBER>`                                          | Marks a task as done.                          |
| `unmark <TASK NUMBER>`                                        | Unmarks a task.                                |
| `delete <<TASK NUMBER>`                                       | Deletes a task from your tasks list.           |
| `find <KEYWORD>`                                              | Finds tasks by keyword.                        |
| `bye`                                                         | Exits the application.                         |
| `command`                                                     | Lists all possible commands that Lovie can do. |
 

## OOP Concepts in Lovie

Lovie utilizes several OOP concepts, including:

- **Encapsulation**: By keeping `Task` and its subclasses' details private and exposing operations 
through public methods.
- **Inheritance**: `Todo`, `Deadline`, and `Event` classes extend the `Task` class, inheriting its properties and 
methods. Additionally, the `LovieException` class extends the `Exception` class.
- **Polymorphism**: Methods such as `getDescription()` and `getTimespan()` are implemented differently in `Task`
subclasses to accommodate their specific behaviors.
- **Abstraction**: The `Task` class provides a generic representation of a task, while its concrete subclasses
provide specific implementations. It has generic methods and flow to represent a more generalized idea of every kind of 
task.

## Contributing

Contributions to Lovie are welcome! Please feel free to open an issue or pull request on my GitHub repository for 
suggestions or bug reports. 