# Artemis User Guide
Artemis is a friendly task management tool that allows you to keep track of the tasks in your busy life!

## Features 
Artemis can track of several types of tasks, such as:
1. Todos
2. Deadlines
3. Events

You may also keep track of your progress through the utility functions:
1. List: to see all the tasks that you have
2. Mark: to mark a task as complete or incomplete 
3. Find: to search for a specific task through a keyword 
4. Delete: to remove tasks from your list
5. Save: to export your data into a file to synchronize your progress across runs!

## Usage
### Running Artemis 
- You are required to install Java 11 onto your computer
- Download the latest release from the [releases page](https://github.com/joshualeejunyi/ip/releases)
- Run the program in your preferred terminal using the command: `java -jar Artemis.jar`
- You should be greeted with a prompt on first start up asking for your name

### Todos
Todos are simple tasks that you wish to complete, without a deadline or a time frame to be met.

```
todo <task name [string]> 
```

Example Usage:
```
todo get in the batmobile
```

Expected Outcome:
```
[artemis]: you have added this task:
 [T] [ ] get in the batmobile
```

### Deadlines
Deadlines are tasks that have a deadline to be met.

```
deadline <task name [string]> /by <datetime [string]>
```

Example Usage:
```
deadline meet hugo /by tomorrow evening 11pm
```

Expected Outcome:
```
[artemis]: you have added this task:
 [D] [ ] meet hugo  (by: tomorrow evening 11pm)
```

### Events
Events are tasks that have a start and end datetime.

```
event <task name [string]> /from <datetime [string]> /to <datetime [string]>
```

Example Usage:
```
event wayne foundation charity ball /from saturday 6pm /to saturday 9pm
```

Expected Outcome:
```
[artemis]: you have added this task:
 [E] [ ] wayne foundation charity ball  (from: saturday 6pm  to: saturday 9pm)
```

### Listing Tasks
You may list existing tasks by using the `list` command.

Example Usage:
```
list
```

Expected Outcome:
```
[artemis]: Your list is as such:
1. [T] [ ] get in the batmobile
2. [D] [ ] meet hugo  (by: tomorrow evening 11pm)
3. [E] [ ] wayne foundation charity ball  (from: saturday 6pm  to: saturday 9pm)
```

### Marking Tasks
You may mark tasks as complete or incomplete by using either the `mark` or `unmark` command.

```
mark <item index on list [number]> 

unmark <item index on list [number]> 
```

Example Usage:
```
mark 2

unmark 3
```

Expected Outcome (with list commands added):
```
[batman]: list
[artemis]: Your list is as such:
1. [T] [ ] get in the batmobile
2. [D] [ ] meet hugo  (by: tomorrow evening 11pm)
3. [E] [X] wayne foundation charity ball  (from: saturday 6pm  to: saturday 9pm)
[batman]: mark 2
alright! i have set "meet hugo " as complete
[batman]: unmark 3
alright! i have set "wayne foundation charity ball " as incomplete
[batman]: list
[artemis]: Your list is as such:
1. [T] [ ] get in the batmobile
2. [D] [X] meet hugo  (by: tomorrow evening 11pm)
3. [E] [ ] wayne foundation charity ball  (from: saturday 6pm  to: saturday 9pm)

```

### Finding Tasks
You may find tasks using keywords through the `find` command.
```
find <keyword [string]>
```

Example usage:
```
find batmobile
```

Expected outcome:
```
[batman]: find batmobile
[artemis]: here are matching tasks with the keyword: batmobile
1. [T] [ ] get in the batmobile
```

### Deleting Tasks
You may delete existing tasks through the `delete` command
```
delete <item index on list [number]>
```

Example Usage:
```
delete 3
```

Expected Outcome:
```
[batman]: delete 3
the task "wayne foundation charity ball " has been successfully deleted
```

### Saving Progress
You may save your current progress through the `save` command. A text file will be saved with your username and existing tasks in the list in the current directory.

Example Usage:
```
save
```

Expected Outcome:
```
[batman]: save
[artemis]: successfully saved data!
```
