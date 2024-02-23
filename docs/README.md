# Mario Task Manager

## Overview
The Mario Task Manager is a Java-based application designed to assist users in managing tasks through a CLI interface. It features a variety of commands for task manipulation, including adding, deleting, marking, unmarking, postponing tasks, along with support for deadlines and events.

## Classes and Packages

### Logic Package
- **Mario**: Handles task management logic, including loading and saving tasks.
- **Parser**: Parses user input into commands.
- **Storage**: Manages task storage and retrieval from a file.

### Command Package
- Includes classes for each command (`ByeCommand`, `DeadlineCommand`, `EventCommand`, `MarkCommand`, `UnmarkCommand`, `PostponeCommand`, etc.).

### Templates Package
- **TaskList**: Manages a collection of tasks.
- **BaseDate**: Utility for date and time handling.
- **Task**: Base class for tasks, with subclasses `Deadline`, `Event`, `ToDo`.

### Exceptions Package
- Custom exceptions for task management and command execution errors.

## Features
- Add, delete, mark, unmark, and postpone tasks.
- Support for deadlines and events with date and time.
- Find tasks by keyword or date.
- Save and load tasks from file.

## Usage
Start the application via `MarioUI`. Enter commands in the input field to manage tasks.

## Commands
- **todo**: Add a to-do task.
- **deadline**: Add a task with a deadline.
- **event**: Add an event with start and end dates.
- **list**: List all tasks.
- **delete**: Delete tasks.
- **mark**: Mark tasks as completed.
- **unmark**: Mark tasks as not completed.
- **postpone**: Postpone tasks to a new date/time.
- **bye**: Exit the application.

This documentation provides an overview of the Mario Task Manager's structure and functionality.
