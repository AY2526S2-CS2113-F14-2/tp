# BitBites User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 17 installed.
2. Download the latest version of `BitBites` from [here](https://github.com/AY2526S2-CS2113-F14-2/tp/releases).

## Features 

{Give detailed description of each feature}

### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

### Listing all food items: `list`
Displays a complete chronological history of all the food items you have ever logged.

Format: `list`

* The list will display each food item's name, calories, protein, and the date it was logged.
* Items are numbered sequentially.

Example of usage:

`list`

### Listing food items by date: `list d/DATE`
Filters your food list and displays only the items consumed on a specific date.

Format: `list d/DATE`

* The `DATE` must strictly follow the `DD-MM-YYYY` format.
* If no food items were logged on that date, the list will simply be empty.

Example of usage:

`list d/27-03-2026`

### Exiting the application: `exit`

Exits the application and saves all your food tracking data.

When you execute the `exit` command, the application will:
1. Save all logged food items to your profile's data file
2. Save all food presets to your profile's data file
3. Display a farewell message
4. Close the application

Format: `exit`

**Important:** Your data is automatically saved when you exit. You will not lose any tracked food items or presets.

Example of usage:

`exit`

The system will display: "Bye. Hope to see you again soon!"

---

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
