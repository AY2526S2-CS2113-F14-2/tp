# Bryan Yeo - Project Portfolio Page

## Overview

BitBites is a command-line food tracking chatbot that helps users log daily meals, monitor
nutritional intake, and track progress against personal health goals. It is written in Java
and targets users who prefer fast, keyboard-driven interfaces over graphical applications.

My contributions focused on the user profile system, nutritional goal management, the food
adding workflow, shared command infrastructure, and robustness improvements across multiple
features.

---

## Summary of Contributions

### Code Contributed

[View on tP Code Dashboard](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=bryanyeo3125&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2026-02-20T00%3A00%3A00&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&filteredFileName=&tabOpen=true&tabType=authorship&tabAuthor=bryanyeo3125&tabRepo=AY2526S2-CS2113-F14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

---

### Enhancements Implemented

**1. User Profile System (`Profile`, `ProfileCommand`, `ProfileStorage`)**

Designed and implemented the full profile feature end-to-end. `Profile` computes BMI and BMR
(Mifflin-St Jeor formula) from stored physical attributes. `ProfileStorage` handles per-user
file I/O using a `<username>_profile.txt` naming convention. `ProfileCommand` supports
`profile`, `profile set`, `profile list`, and `profile clear`. Saving a profile automatically
triggers `GoalsCommand.autoSetGoalsFromBmr()` to keep calorie goals in sync.

**2. Nutritional Goals System (`GoalsCommand`, `GoalsStorage`)**

Designed and implemented the goals feature with persistence and cross-command integration.
`GoalsCommand` tracks daily and weekly progress over a Monday-to-Sunday window and exposes
static methods (`showDailyProgress`, `autoSetGoalsFromBmr`, `loadGoalsIfNeeded`) used by
`AddCommand`, `DeleteCommand`, `SummaryByDateCommand`, and `HistoryBestCommand`.
`GoalsStorage` persists four goal values per user in `<username>_goals.txt`.

**3. Add Food Command (`AddCommand`)**

Implemented the core food logging command. Supports optional `d/DATE` (defaults to today),
validates all fields, and displays daily goal progress after a successful add. Later
refactored to support order-independent prefix parsing using `extractValue` / `nextPrefix`.

**4. Shared Command Infrastructure (`Command`, `AppContext`)**

Designed the `Command` abstract base class and `AppContext` context object adopted by all
commands. `Command` provides shared `extractValue` / `nextPrefix` utilities reused across
five command classes. `AppContext` follows the Context Object design pattern, keeping command
method signatures stable as the application grows.

**5. Bug Fixes and Robustness Improvements**

Fixed 13 bugs across multiple features discovered during testing:
- **`GoalsCommand`:** Crash on invalid food dates in `showGoalsMenu`; zero/negative goal
  values accepted; unknown prefixes silently ignored.
- **`AddCommand`:** Crash when `d/` omitted; invalid calendar dates accepted (e.g.
  `29-02-2025` on non-leap year); pipe character `|` accepted corrupting storage; integer
  overflow on large calorie values; no upper bound on protein values; parameters required
  in fixed order contrary to User Guide.
- **`ProfileCommand`:** No range validation on age/weight/height producing nonsensical
  BMI/BMR; gender silently defaulting to male; name changes silently ignored.
- **`Storage`/`AppContext`/`LoginCommand`/`Bitbites`:** Food log and presets shared across
  all users — implemented per-user food and preset files (`<username>_foods.txt`,
  `<username>_presets.txt`) with save/load on user switch.
- **`Parser`:** Generic error messages for incomplete commands like bare `summary` and `add`
  replaced with descriptive format reminders.
- **`Bitbites`:** Added visual separator between command outputs for readability.

---

### Contributions to the User Guide

- Introduction, Table of Contents, Quick Start, Notes on Command Format
- `add`, `goals`, and `profile` command sections
- FAQ, Known Issues, Command Summary

---

### Contributions to the Developer Guide

- Table of Contents, Acknowledgements
- Section 3.1: Architecture Overview
- Section 3.2: Design — Command Pattern and AppContext
- Section 3.4: Adding a Food Item
- Section 3.9: Managing User Profiles
- Section 3.10: Managing Nutritional Goals

**UML Diagrams:** `architecture.png`,`add_sequence.png`, `command_class.png`, `goals_set.png`,
`profile_set.png`

---

### Contributions to Team-Based Tasks

- Set up the team organisation and repository.
- Designed `AppContext` and refactored codebase midway to adopt it as the standard for
  passing application state, avoiding the need to update every command signature as the
  project grew.
- Established the `extractValue` / `nextPrefix` parsing pattern reused by teammates in
  `EditCommand` and `PresetCommand`.
- Set up the per-user file naming convention used consistently across all storage classes.
- Added routing entries in `Parser` and response strings in `BitbitesResponses` for `add`,
  `goals`, and `profile`.
- Helped to standardise puml diagrams

---

### Review/Mentoring Contributions

PRs reviewed:
[#20](https://github.com/AY2526S2-CS2113-F14-2/tp/pull/20),
[#22](https://github.com/AY2526S2-CS2113-F14-2/tp/pull/22),
[#29](https://github.com/AY2526S2-CS2113-F14-2/tp/pull/29),
[#36](https://github.com/AY2526S2-CS2113-F14-2/tp/pull/36),
[#48](https://github.com/AY2526S2-CS2113-F14-2/tp/pull/48),
[#53](https://github.com/AY2526S2-CS2113-F14-2/tp/pull/53),
[#58](https://github.com/AY2526S2-CS2113-F14-2/tp/pull/58),
[#70](https://github.com/AY2526S2-CS2113-F14-2/tp/pull/70),
[#74](https://github.com/AY2526S2-CS2113-F14-2/tp/pull/74),
[#76](https://github.com/AY2526S2-CS2113-F14-2/tp/pull/76),
[#80](https://github.com/AY2526S2-CS2113-F14-2/tp/pull/80),
[#82](https://github.com/AY2526S2-CS2113-F14-2/tp/pull/82),
[#138](https://github.com/AY2526S2-CS2113-F14-2/tp/pull/138),
[#142](https://github.com/AY2526S2-CS2113-F14-2/tp/pull/142),
[#144](https://github.com/AY2526S2-CS2113-F14-2/tp/pull/144),
[#147](https://github.com/AY2526S2-CS2113-F14-2/tp/pull/147)
---

