# User Guide for John Chatbot

John Chatbot is a terminal chatbot capable of managing a user's tasks. By default, it stores the user's tasks in a text file
called **John.txt** in the **same directory as the jar file**.

## Running the chatbot

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

- Add a ToDo task ```todo```
- Add an Event task ```event```
- Add a Deadline task ```deadline```
- Mark/Unmark a task ```mark``` / ```unmark```
- List all tasks ```list```
- Find certain tasks ```find```
- Remove a task ```delete```

### Adding a task

The format for adding specific tasks are ask follows. The command keyword is not case sensitive, but the /*vars* are. An error will be thrown if a blank is found when a string is expected.

**Formats**:
- Event: ```event NAME /from STRING /to STRING```
- Deadline: ```deadline NAME /by STRING```
- Todo: ```todo NAME```

Examples: 
- ```deadline have a dream /from now /to I wake up```
- ```todo do my laundry```

### Mark/Unmark tasks: ```mark``` / ```unmark```

**Format**: ```mark/unmark NUMBER```

Firstly, the task Id must be retrieved. This can be done easily using the command ```find <string to search for>```. 

Next, use ```mark x``` or ```unmark x``` to set the state of the task to the desired state.

Example usage: 
- ```mark 3```
- ```unmark 5```

### List: ```list```

**Format**: ```list```

By typing list, the user can print out all existing tasks into the command line.

*Example output*:
```1. [T][ ] do my laundry```

### Find: ```find```

**Format**: ```find STRING```

The command looks through all existing tasks, and prints them out if their name contains the string query.

### Delete: ```delete```

**Format**: ```delete NUMBER```

As with *mark/unmark*, the Id of the task to be deleted must first be obtained. afterwards, typing the command in the given format will delete said task from the list.

Once deleted, said task **CANNOT** be retrieved.

# Known Issues
-Chatbot has little to no personality (cause I have no creativity. :upside_down_face: )



