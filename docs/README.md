# User Guide

## Features

### Simple, easy to use interface

Huan's a bot of few words, but the meaning always gets across

### Supports multiple types of tasks

currently have 3 types:

- todo-tasks, which can be marked as finished or unfinished.
- EventTasks, for representing your future events, have a start time and an end time, can also be marked as finished or unfinished
- DeadlineTasks, for tasks with specific deadlines, comes with an additional datetime field which can be used for organizing your deadlines

### data persistence through file storage

All changes to the task list will be saved on your computer, and will be safely loaded next time when launching the chatbot.

So there's no data loss between different instances of launching.

### find tasks via name

supports task-filtering using names

### filter deadline tasks before a specific time

find all deadlines before a specific time with special formatting

## Commands

- bye
  - safely quit the chatbot
- list
  - list all the tasks you currently have
- todo *taskName
  - add a todo type task to your list, taskname can have in-between spaces
  - e.g. `todo do homework`
- event *taskname /from *starttime /to *endtime
  - add a event type task to your list, taskname, starttime, endtime can have in-between spaces
  - e.g. `event concert with gf /from 3pm /to 6pm`
- deadline *task /by *ddltime
  - add a deadline type task to your list, taskname, ddltime can have in-between spaces
  - ddltime can be formatted in a special way to support date time tracking, format is "yyyy-MM-dd HH:mm:ss"
  -  e.g. "deadline 2113 gp /by 2024-03-08 23:59:59" or "deadline book report /by tonight"
- mark *index
  - mark the n-th task in the list as completed
  - use `list` to check for index if not certain
  - e.g. `mark 3`
- unmark *index
  - unmark the n-th task in the list
  - e.g. `unmark 2`
- delete *index
  - remove the n-th task from the list
  - e.g. `delete 6`
- find *name
  - list all tasks with tasknames containing *name
  - e.g. `find book`
- list_deadline *datetime
  - list all deadline tasks before a given time
  - all time must be correctly formatted("yyyy-MM-dd HH:mm:ss")
  - only deadline tasks with correctly formatted datetime values will be listed
