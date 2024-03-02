# User Guide for John Chatbot

John Chatbot is a terminal chatbot capable of managing a user's tasks. By default, it stores the user's tasks in a text file
called **John.txt** in the **same directory as the jar file**.

### Running the chatbot

Prerequisites: Have java *(verson >= 11)* installed.

Create an empty folder, put the provided jar file inside, and run it from terminal
with 
```
java -jar ./John.jar
```

### Task types

- Event: Task with ```Name```, ```From date/time``` and ```To date/time```
- Deadline: Task with ```Name``` and ```Deadline```
- ToDo: Task with ```Name```

# Features

## - Add a ToDo task ```todo```

Adds in a task for the user, containing only the name.

## - Add an Event task ```event```

Adds in an event for the user, containing a name, start date/time and end date/time.

## - Add a Deadline task ```deadline```

Adds in a deadline for the user, containing a name and a deadline.

## - Mark/Unmark a task ```mark``` / ```unmark```

Marks or unmarks a task as complete/uncomplete.

## - List all tasks ```list```

Lists all stored tasks.

## - Find certain tasks ```find```

Find tasks containing a keyword, and prints them out in a list.

## - Remove a task ```delete```

Removes a task.


# Usage

## Adding a task

The format for adding specific tasks are ask follows. The command keyword is not case sensitive, but the /*vars* are. An error will be thrown if a blank is found when a string is expected.

**Formats**:
- Event: ```event NAME /from STRING /to STRING```
- Deadline: ```deadline NAME /by STRING```
- Todo: ```todo NAME```

*Example usage*: 

- ```event have a dream /from now /to I wake up```
- ```todo do my laundry```
- ```deadline finish my homework /by tomorrow```

*Expected outcome*:

If the arguments were entered correctly, the task should be added successfully.

*Example output*:

``` Added deadline finish my homework ```


## ```mark``` / ```unmark``` : Mark/Unmark tasks

**Format**: ```mark/unmark NUMBER```

Firstly, the task Id must be retrieved. This can be done easily using the command ```find <string to search for>```. 

Next, use ```mark x``` or ```unmark x``` to set the state of the task to the desired state.

*Example usage*: 

- ```mark 3```
- ```unmark 5```

*Expected outcome*:

If a valid number is entered, a response marking the task will be shown.

*Example output*:

``` 
Marking as done:
[T][X] do the laundry 
```

```
Marking as undone:
[T][ ] do the laundry 
```


## List: ```list```: Listing all tasks

**Format**: ```list```

By typing list, the user can print out all existing tasks into the command line.

*Example usage*: 

- ```list```

*Expected outcome*:

All tasks will be printed out in the console.

*Example output*:

```
1. [T][ ] do my laundry
2. [D][ ] do my homework (by: tomorrow)
3. [E][X] attend lecture (from: now to: later)
```


### Find: ```find``` : Finding keywords

**Format**: ```find STRING```

The command looks through all existing tasks, and prints them out if their name contains the string query.

*Example usage*: 

- ```find lecture```
- ```find homework```

*Expected outcome*:

All tasks containing the keywords in their name will be printed out.

*Example output*:

__Input:__``` find do```

```
2. [T][ ] do my laundry
7. [D][ ] do my homework (by: tomorrow)
9. [E][X] do my will and testament (from: now to: later)
```

### Delete: ```delete``` : Deleting a task

**Format**: ```delete NUMBER```

As with *mark/unmark*, the Id of the task to be deleted must first be obtained. afterwards, typing the command in the given format will delete said task from the list.

Once deleted, said task **CANNOT** be retrieved.

*Example usage*: 

- ```delete 3```

*Expected outcome*:

Task of the given id number will be deleted.

*Example output*:

```
Removing Task: 
[D] do my homework (by: later)
```

### ```bye``` : Exit the program:

**Format**: ```bye```

Exits the program and updates the stored tasks in a storage file.

# Known Issues
-Chatbot has little to no personality (cause I have no creativity. :upside_down_face: )




