# Mario Task Manager

## Overview
The Mario Task Manager is a Java-based application designed to assist users in managing tasks through a GUI interface. It features a variety of commands for task manipulation, including adding, deleting, marking, and finding tasks, along with support for deadlines and events.

## Classes and Packages

### UI Package
- **MarioUI**: The main class for the user interface, handling the initialization of the GUI components and user interactions.
- **ResponseStreamer**: A utility class to animate the display of responses in the GUI.

### Logic Package
- **Mario**: The core logic handler for task management, including loading and saving tasks.
- **Parser**: Parses user input into commands to be executed by the system.
- **Storage**: Manages the storage and retrieval of tasks from a file.

### Command Package
- Contains classes for each specific command (`ByeCommand`, `DeadlineCommand`, `EventCommand`, etc.) that can be executed by the application.

### Templates Package
- **TaskList**: A collection of tasks.
- **BaseDate**: Utility class for handling date and time.
- **Task**: Abstract base class for tasks, with derived classes for specific types (`Deadline`, `Event`, `ToDo`).

### Exceptions Package
- Custom exceptions to handle specific error cases related to task management and command execution.

## Features
- Add, delete, mark, and unmark tasks.
- Support for deadlines and events with date and time.
- Find tasks by keyword or date.
- Save and load tasks from file.
- Animated text display in the GUI for interactions.

## Usage
Run `MarioUI` to start the application. Use the input field to enter commands and interact with Mario to manage your tasks.

## Commands
- **todo**: Add a to-do task.
- **deadline**: Add a task with a deadline.
- **event**: Add an event with start and end dates.
- **list**: List all tasks.
- **bye**: Exit the application.

This documentation provides a comprehensive overview of the Mario Task Manager application structure and functionality.
