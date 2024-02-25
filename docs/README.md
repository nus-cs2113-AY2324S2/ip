# DavinciBot

## Overview 
The DavinciBot is a simple to-do list application made in Java that allows users to manage tasks, including adding, marking as done, deleting, listing tasks and finding tasks. The DaviniciBot will also display tasks from the past if the user had interacted with the chatbot before.

### Classes
- `DavinciBot (Main class)`
- `DaviniciException (Custom exceptions for task management)`
- `DavinciFileHandler (Handles file operations such as reading and writing)`
- `Deadline (Handles Deadline task specific outputs)`
- `Event (Handles Event task specific outputs)`
- `Todo (Handles Todo task specific outputs)`
- `Parser (Parses user input and performs actions based on pre-set commands)`
- `Task (Represents a general task with a description and completion status)`
- `TaskList (Represents a collection of tasks and provides methods to manipulate the task list.)`
- `Ui (Handles interactions with the user and gives displays messages based on the user's input)` 

## Features 
- Add, delete, mark and unmark tasks.
- Add tasks based on 3 types; ToDo, Deadline and Event.
- Find tasks based through searching for keywords associated with each task.
- Save and load past tasks from the text file.
- Creation of directory and text file if the file/path is missing.

### Feature-Mark

Mark a task as done by specifying the index within the list, and returns a 'X', which denotes as a completed task.

### Feature-Saving

All the tasks in the list would be updated to the textfile whenever a change is made to the list, i.e. deletion, addition or marking/unmarking of a task. This ensures that the items that a user has input into their list would be saved should the programme crash.

## Commands
- **todo (description)**: Add a to-do task.
- **deadline (description) /by (deadline)**: Add a task with a deadline.
- **event (description) /from (start time) /to (end time)**: Add an event with start and end dates.
- **list**: List all tasks.
- **delete (index in list)**: Delete tasks.
- **mark (index)**: Mark tasks as completed.
- **unmark (index)**: Mark tasks as not completed.
- **find (word)**: Returns all tasks in the list which contains that specified word.
- **bye**: Exit the application.

### Example input/output
What do you want me to do? `Event Attend banquet dinner in Marina Bay Sands /from 6pm /to 11pm`
____________________________________________________________
Got it. I've added this task:
[E][ ] Attend banquet dinner in Marina Bay Sands (from: 6pm to: 11pm)
Now you have 1 tasks in the list.
____________________________________________________________

What do you want me to do? `list`
____________________________________________________________
Here are the tasks in your list:
1. [E] [ ] Attend banquet dinner in Marina Bay Sands (from: 6pm to: 11pm)
____________________________________________________________

What do you want me to do? mark 1
____________________________________________________________
Nice job! I've marked this task as done :D
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1. [E] [X] Attend banquet dinner in Marina Bay Sands (from: 6pm to: 11pm)
____________________________________________________________

## Usage
1. Download the latest Jar releasse.
2. Move the Jar file into an empty folder.
3. In terminal, cd to the directory that the Jar file is in.
4. Run the command java -jar {filename}.jar (i.e., run the command in the same folder as the jar file).
5. To exit the application, type the keyword 'bye'. To restart the application, repeat steps 3 and 4.

This User Guide serves as an overview of how DavinciBot works, as well as a description of all the features that it gives to the user.
