# Avocado User Guide

## Features 

### words in UPPER-CASE are parameters to be supplied 

### Feature-ToDo

Add a todo item without further instruction
Format: todo ITEM
Example: todo book
Output: [T][ ] book

### Feature-Deadline

Add an item with a due date
Format: deadline ITEM / by X
Example: dealine return book / by tue
Output: [D][ ] return book (by: tue)

### Feature-Event

Add an item with a start and fin
Format: event ITEM /from X /to Y
Example: event party /from 1pm /to 3pm
Output: [E][ ] party (from: 1pm to:3pm)

### Feature-Mark

Tick an item as done
Format: mark [ITEM NUMBER]
Example: mark 1
Output: [T][X] book

### Feature-Unmark

Undo the item
Format: unmark[ITEM NUMBER]
Example: unmark 1
Output: [T][ ] book

### Feature-Delete

delete an item
Format: delete[ITEM NUMBER]
Example: delete 1

### Feature-List

List all items
Format: list
Output:
1. [T][ ]book
2. [E][ ]party(from:1pm to: 3pm)

### Feature-Find

Returns all items with description
Format: find [ITEM]
Example: find book
Output:
1. [T][ ]book


