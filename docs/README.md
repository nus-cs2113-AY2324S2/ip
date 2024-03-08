
# Phoebe: Your CLI Task Manager

Phoebe is a Java-based personal assistant designed to help you manage your tasks efficiently using the Command Line Interface (CLI). Whether you need to track your to-dos, deadlines, or events, Phoebe is here to make your life easier and more organized.

## Features

### Task Management

- **Add Tasks**: Easily add tasks of various types (ToDo, Deadline, Event) to your list.
- **Delete Tasks**: Remove tasks from your list once they are no longer needed.
- **Mark Tasks**: Mark tasks as completed to keep track of your progress.
- **Unmark Tasks**: Mark tasks as not done in case you need to revisit them.
- **List Tasks**: View all your tasks in one place, making it easy to see what's on your agenda.
- **Find Tasks**: Search for tasks by keywords, so you can quickly locate specific items on your list.

## Quick Start

> [!IMPORTANT]
> Ensure you have **Java 11** or above installed on your Computer.

## Installation

To get started with Phoebe, 

1. Download the latest `ip.jar` from [here](https://github.com/iscyng/ip/releases).
2. Copy the file into an empty folder to run Phoebe.
3. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar ip.jar` command to run the application.

If the setup is correct, Phoebe will be the first one to greet you!


```
█▀█ █░█ █▀█ █▀▀ █▄▄ █▀▀
█▀▀ █▀█ █▄█ ██▄ █▄█ ██▄
```

## Usage

### Adding a Task

#### To-Do

`todo TASK_DESCRIPTION`

Example:

`todo Read book`

Expected outcome:

```
OKIE I MEMORISED FOR U:
  [T][ ] Read book
You have 3 remaining things to dododo.
```

#### Deadline

`deadline TASK_DESCRIPTION /by DATE`

Example:

`deadline Submit report /by 2021-09-30 2359`

Expected outcome:

```
OKIE I MEMORISED FOR U:
  [D][ ] Submit report (by: Sep 30 2021, 23:59)
You have 4 remaining things to dododo.
```

#### Event

`event TASK_DESCRIPTION /from START_TIME /to END_TIME`

Example:

`event Team meeting /from 2021-09-29 1400 /to 2021-09-29 1600`

Expected outcome:

```
OKIE I MEMORISED FOR U:
  [E][ ] Team meeting (from: Sep 29 2021, 14:00 to Sep 29 2021, 16:00)
You have 5 remaining things to dododo.
```

### Deleting a Task

`delete TASK_INDEX`

Example:

`delete 2`

Expected outcome:

```
Just now you said do but now say don't, so I forgot this:
  [T][X] Wash dishes
Now you have 4 remaining things to dododo.
```

### Marking a Task

`mark TASK_INDEX`

Example:

`mark 1`

Expected outcome:

```
YAY GOOD JOB
  [T][X] Read book
```

### Unmarking a Task

`unmark TASK_INDEX`

Example:

`unmark 1`

Expected outcome:

```
OK, I'VE MARKED THIS TASK AS NOT DONE YET:
  [T][ ] Read book
```

### Listing All Tasks

`list`

Expected outcome:

```
Every time need me to remind you...
1.[T][X] Read book
2.[D][ ] Submit report (by: Sep 30 2021, 23:59)
3.[E][ ] Team meeting (from: Sep 29 2021, 14:00 to Sep 29 2021, 16:00)
```

### Finding a Task

`find KEYWORD`

Example:

`find book`

Expected outcome:

```
Here are the matching tasks in your list:
1.[T][X] Read book
```

### Exiting and leaving Phoebe

`bye`

Expected outcome:

```
byebye
ฅ^•ﻌ•^ฅ
```
When leaving Phoebe, do be respectful and say your goodbyes. A cat will say goodbye to you too!
Phoebe will save your tasks locally after each command that results in a change. Make sure to say `bye` to ensure your data is saved properly!
Only when you say bye will Phoebe manage your tasks properly and remember them for life! If you decide to walk away and leave her hanging she might choose to forget what you have told her...

### Data Management

Phoebe's memory are saved locally on your device after each command that results in any change in data.

## Contributing

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.
